<div class="footer">
	<div class="footer-color"></div>
	<div class="copy-right">
		<span>Copy right @2023 PRJ321x_02_VN</span> <span>Contact Us:thiennhFX11332</span>
	</div>
</div>

<%String error_search = (String) request.getAttribute("error_search");%>

<%if (error_search != null) {%>
	<script type="text/javascript">alert("Product not found");</script>
<%}%>
</body>
</html>