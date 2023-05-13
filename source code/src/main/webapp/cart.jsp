<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<jsp:include page="header.jsp"></jsp:include>

<div class="grid wide">
	<div class="row">
		<table class="cart-table">
			<tr>
				<th>Products in cart: <c:out value="${size}"></c:out></th>
				<th>Price</th>
				<th>Quantity</th>
				<th>Amount</th>
				<th>Action</th>
			</tr>
			<c:forEach var="product" items="${items}">
			<tr>
				<td class="cart-table_product_name"><c:out value="${product.name}"></c:out></td>
				<td rowspan="2" class="cart-table_product_price"><c:out value="$ ${product.price}"></c:out></td>
				<td rowspan="2" class="cart-table_product_quantity"> <c:out value="${product.number}"></c:out></td>
				<td rowspan="2" class="cart-table_product_amount"> <c:out value="$ ${product.price * product.number}"></c:out></td>
				<td rowspan="2" class="cart-table_product_action">
					<form action="/PRJ321x_A3ver1/AddToCartController?action=remove&id=${product.id}" method="post">
						<input type="submit" value="Remove" class="cart-table_product_action_submit">
					</form>
				</td>
			</tr>

			<tr>
				<td class="cart-table_product_id"> <c:out value="ID:  ${product.id}"></c:out></td>
			</tr>
			
			</c:forEach>
			<tr class="cart-table_product_total">
				<td colspan="6" align="end">
					Total: <c:out value="${total}"></c:out>
				</td>
			</tr>
		</table>
	</div>
	<div class="row customer-info">
		<div class=" c-4">
			<table class="customer-info_list">
				<tr>
					<td>Customer name</td>
				</tr>
				<tr>
					<td>Customer address</td>
				</tr>
				<tr>
					<td>Discount code (if any)</td>
				</tr>
			</table>
		</div>
		<div class=" c-8">
			<form action="http://localhost:8080/PRJ321x_A3ver1/PayController?action=payController" method="post">
			<input type="hidden" name="total" value="${total}">
				<div class="customer-info_form-wrap">
					<table class="customer-info_detail">
					
						<tr>
							<td><input type="text" name="customer_name" placeholder="Customer name?"></td>
						</tr>
						<tr>
							<td><input type="text" name="customer_address" placeholder="Customer address?"></td>
						</tr>
						<tr>
							<td><input type="text" name="discount_code" placeholder="Discount code?"></td>
						</tr>
					</table>
					<input type="submit" value="Submit" class="detail_product-item__button submit" />
				</div>
			</form>
		</div>
	</div>
	
</div>
	<jsp:include page="footer.jsp"></jsp:include>
