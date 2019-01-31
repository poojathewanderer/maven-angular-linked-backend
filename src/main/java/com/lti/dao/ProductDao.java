package com.lti.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lti.model.Product;

public class ProductDao {
	
	public List<Product> fetchProduct(int from, int to) throws DataAccessException {
		PreparedStatement pstmt = null;
		Connection conn = null;

		// Step 1. Loading the JDBC Driver
		try {

			ResultSet rs = null;
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// Step 2. Now we can try connecting to the database
			String url = "jdbc:oracle:thin:@infva06863:1521:xe";
			String user = "hr";
			String pass = "hr";

			// Get connection from database
			conn = DriverManager.getConnection(url, user, pass);

			// Query the  database
			String sql = "select * from (select p.*,rownum r  from product p) "
					+ " where r between "
					+ " ? and ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, from);
			pstmt.setInt(2, to);
			 rs = pstmt.executeQuery();
			List<Product> products = new ArrayList<Product>();
			
			//fetch product rows
			while(rs.next()) {
				Product product = new Product();
				product.setId(rs.getInt("id"));
				product.setName(rs.getString("name"));
				product.setPrice(rs.getDouble("price"));
				product.setQuantity(rs.getInt("quantity"));
				products.add(product);
			}
			return products;
		} catch (ClassNotFoundException | SQLException e) {
			throw new DataAccessException("Exception in database");
		} finally {
			try {pstmt.close();} catch (Exception e) {}
			try {conn.close();} catch (Exception e) {}
		}
	}

}
