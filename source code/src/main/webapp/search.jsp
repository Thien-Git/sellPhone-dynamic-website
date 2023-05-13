<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<jsp:include page="header.jsp"></jsp:include>
<c:set var="product" value="${product}" scope="page"></c:set>
<div class="app">
		<div class="grid wide">
			<div class="row">
				<c:forEach var="product" items="${listProduct}">
					<div class="col c-4">
						<a class="product-item"
							href='<c:url value = "/InformationProductController?action=informationProduct&product_id=${product.id}"></c:url>'>
							<div class="product-item__img"
								style="background-image: url(${product.src});"></div>
							<div class="product-info">
								<h2 class="product-item__type">
									<c:out value="${fn:toUpperCase(product.type)}"></c:out>
								</h2>
								<h3 class="product-item__name">
									<c:out value="${product.name}"></c:out>
								</h3>
								<span class="product-item__price"> <c:out
										value="${product.price}"></c:out>
								</span>
							</div>
						</a>
					</div>
				</c:forEach>
			</div>
			<ul class="pagination">
				<li class="pagination-item"><a href=""
					class="pagination-item__link"><i
						class="pagination-item__icon fas fa-angle-left"></i></a></li>
				<li class="pagination-item pagination-item--active "><a href=""
					class="pagination-item__link">1</a></li>
				<li class="pagination-item"><a href=""
					class="pagination-item__link">2</a></li>
				<li class="pagination-item"><a href=""
					class="pagination-item__link">3</a></li>
				<li class="pagination-item"><a href=""
					class="pagination-item__link">4</a></li>
				<li class="pagination-item"><a href=""
					class="pagination-item__link">5</a></li>
				<li class="pagination-item"><a href=""
					class="pagination-item__link"> <i
						class="pagination-item__icon fas fa-angle-right"></i></a></li>
			</ul>

		</div>
	</div>

<jsp:include page="footer.jsp"></jsp:include>