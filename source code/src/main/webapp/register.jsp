<%@ page pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Java Web</title>
<style type="text/css">
.create-wrap {
	margin-top: 10%;
	text-align: center;
}

.form-create {
	display: flex;
	flex-direction: column;
	width: 78%;
	align-items: flex-end;
	padding-top: 16px;
	margin: auto;
}

input[type="submit"] {
	margin-top: 15px;
	text-align: center;
	margin-left: 100%;
	margin-bottom: 15px;
}

.create {
	margin: auto;
	width: 25%;
	background-color: #c9ceff;
	border: 2px solid #d5d1d1;
}

.form-create__input {
	display: flex;
	margin-top: 6px;
}
</style>


</head>
<body>
	<div class="create-wrap">
		<h2>New Account</h2>
		<div class="create">
			<form class="form-create" action="<%=response.encodeUrl(request.getContextPath() + "/RegisterServlet?action=createaccount")%>" method="post">
				<input type="hidden" name="action" value="createaccount">
				<div class="form-create__input">
					<span>Email address:</span> <input type="text" name="user_mail" value="<%request.getAttribute("user_mail");%>">
				</div>
				
				<div class="form-create__input">
					Password: <input type="password" name="password" value="<%request.getAttribute("password");%>">
				</div>
				
				<div class="form-create__input">
					Repeat password: <input type="password" name="repeatpassword" value="<%request.getAttribute("password");%>">
				</div>
				
				<div class="form-create__input">
					Account_role: <input type="number" name="account_role" value="<%request.getAttribute("account_role");%>">
				</div>
				
				<div class="form-create__input">
					User_name: <input type="text" name="user_name" value="<%request.getAttribute("user_name");%>">
				</div>
				
				<div class="form-create__input">
					User_address: <input type="text" name="user_address" value="<%request.getAttribute("user_address");%>">
				</div>
				
				<div class="form-create__input">
					User_phone: <input type="text" name="user_phone" value="<%request.getAttribute("user_phone");%>">
				</div>
				
				<input type="submit" value="Create">

				<% String formsubmit = (String) request.getAttribute("formsubmit");%>

				<%String message = (String) request.getAttribute("message");%>

				<%if (formsubmit != null) {%>
				<p><%=message%></p>
				<%} else {%>
				<p></p>
				<%}%>
			</form>
		</div>
	</div>
</body>
</html>

























