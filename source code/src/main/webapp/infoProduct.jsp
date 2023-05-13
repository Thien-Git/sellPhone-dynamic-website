<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<jsp:include page="header.jsp"></jsp:include>

<c:set scope="page" var="product" value="${product}"></c:set>
<div class=detail_product-item>
	<div class="grid wide">
		<div class="row">
			<div class="col detail_product-item__name">
				<c:out value="${product.name }"></c:out>
			</div>
		</div>
		<div class="row">
			<div class="col c-5">
				<a class="detail_product-item__img">
					<div class="product-item__img" style=" background-image: url(${product.src});"></div>
				</a>
			</div>
			<div class="col c-7">
				<div class="detail_product-item__info">
					<div class="detail_product-item__price"> <fmt:formatNumber value="${(product.price * 1000000)}" maxFractionDigits="0" />
						VND
					</div>
					<div class="detail_product-item__des">
						<c:out value="${product.description}"></c:out>
					</div>

					<form action="/PRJ321x_A3ver1/AddToCartController?action=addtocart&product_id=${product.id}" method="post">
						<input type="submit" value="Add to cart" class="detail_product-item__button" />
					</form>
				</div>
			</div>
		</div>
	</div>
</div>

<jsp:include page="footer.jsp"></jsp:include>









