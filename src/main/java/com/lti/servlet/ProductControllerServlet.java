package com.lti.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lti.dao.DataAccessException;
import com.lti.dao.ProductDao;
import com.lti.model.Product;

public class ProductControllerServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int pageSize = 3;
		
		int currentPosition = 1;
		if(request.getParameter("currentPosition") !=null)
			currentPosition = Integer.parseInt(request.getParameter("currentPosition"));

		ProductDao dao = new ProductDao();
		try {
			System.out.println(currentPosition);
			List<Product> products = dao.fetchProduct(currentPosition, currentPosition + pageSize);
			request.setAttribute("currentProducts", products);

			ObjectMapper mapper = new ObjectMapper();
			String productsJSON = mapper.writeValueAsString(products);

			response.setContentType("application/json");
			response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
			response.setHeader("Access-Control-Allow-Methods", "GET");

			PrintWriter out = response.getWriter();
			out.write(productsJSON);

		} catch (DataAccessException e) {
			e.printStackTrace();
		}

	}

}
