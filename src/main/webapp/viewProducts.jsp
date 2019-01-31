<%@page import="java.util.*"%>
<%@page import="com.lti.model.Product"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<table align="center" border="1" bgcolor="wheat">
		<thead>
			<tr>
				<th>ID</th>
				<th>Name</th>
				<th>Price</th>
				<th>Quantity</th>
			</tr>
		</thead>
		<tbody>
			<%
				List<Product> products = (List<Product>) request.getAttribute("currentProducts");
				for (Product product : products) {
			%>
			<tr>
				<td><%=product.getId()%></td>
				<td><%=product.getName()%></td>
				<td><%=product.getPrice()%></td>
				<td><%=product.getQuantity()%></td>
			</tr>
			<%
				}
			%>
			<tr>
				<th colspan="4">
					<button onclick="go('prev')">&lt;&lt;</button>
					<button onclick="go('next')">&gt;&gt;</button>
				</th>
			</tr>
		</tbody>
	</table>

	<script type="text/javascript">
		var asyncRequest;
		function go(page) {
			try {
				var data = "page=" + page;
				asyncRequest = new XMLHttpRequest();

				asyncRequest.open('POST', 'ProductControllerServlet', true); //   /Test is url to Servlet!
				asyncRequest.send(data);
			} catch (exception) {
				console.log("Request failed"+ exception);
			}

			//window.location.href = "ProductControllerServlet?go="+page;
		}
	</script>

</body>
</html>