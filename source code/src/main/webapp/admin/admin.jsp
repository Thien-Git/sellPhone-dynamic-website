<%@ page pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Java Web</title>

<style type="text/css">
* {
	margin: 0;
	padding: 0;
}

.header-with-nav {
    height: 40px;
    background-color: #293847;
    font-size: 16px;
    padding: 12px;
    color: white;
    display: flex;
    align-items: center;
    justify-content: space-between;
}

.grid {
    display: block;
    padding: 0;
}

.grid.wrap {
    display: flex;
    align-items: center;
    height: 100%;
    justify-content: space-between;
}

.wrap {
    display: flex;
    align-items: center;
    height: 100%;
    justify-content: space-between;
}

ul.header-nav-list {
    list-style: none;
    display: flex;
}

li.header-nav-item {
    margin-right: 36px;
    cursor: pointer;
}

a.header-nav-item__login {
    text-decoration: none;
    color: white;
}

</style>
</head>
<body>
	<div class="header-with-nav">
	<%String adminName = (String) session.getAttribute("user");%>
	<%if(adminName.equals("thien@gmail.com.vn")){ %>
		<h2>Welcome admin: <%= adminName%></h2>	
	<% } else {%>
		<h2>Wellcome guess</h2>	
	<%} %>
		<div class="grid wrap wide">
			<ul class="header-nav-list">
				<li class="header-nav-item"><a href="/PRJ321x_A3ver1/LogoutServlet" class="header-nav-item__login">Logout</a></li>
			</ul>
		</div>
	</div>
</body>
</html>