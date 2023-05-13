<%@ page pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Java Web</title>
<link rel="stylesheet" href="./style1.css">
<script type="text/javascript">
	function validateForm() {
		let x = document.forms["form-login"]["user"].value;
		let y = document.forms["form-login"]["password"].value;
		if (x == "") {
			alert("User must be filled out");
			return false;
		}
		if (y == "") {
			alert("Password must be filled out");
			return false;
		}
	}
</script>

<style type="text/css">
.login-form_checkbox {
    display: flex;
    height: 30px;
    align-items: center;
    position: relative;
    left: -15%;
}

input.login-form__input.checkbox {
    height: 15px;
    width: 15px;
    margin-right: 10px;
    transform: translateY(-40%);
}

</style>
</head>
<body>
	
	<div class="grid wide">
		<div class="row">
			<div class="c-6 wrap">
				<div class="sign-in">
					<form name="form-login" action="/PRJ321x_A3ver1/LoginServlet?action=dologin" method="post" class="form" onsubmit="return validateForm()">
						<input type="hidden" name="formsubmit" value="formsubmit">
						<h2 class="title-login">Sign in</h2>
						<input type="text" name="user" placeholder="User">
						<input type="password" name="password" placeholder="Password">
						<div class="login-form_checkbox">
							<input type="checkbox" class="login-form__input checkbox"><span>Remember me</span>
						</div>
						<span class=""><a href="">Forgot your password?</a></span>
						<div class="btn-login">
							<input type="submit" value="LOGIN">
						</div>
					</form>
					
					<%String error = (String)session.getAttribute("error"); %>
					<%String formsubmit = (String)session.getAttribute("formsubmit"); %>
					
					<%if(formsubmit != null){ %>
						<div class="error"><%=error %></div>
					<%} else { %>
						<div class="error"></div>
					<%} %>
					
				</div>
				<div class="welcome-back">
					<h2 class="title-login">Welcome Back!</h2>
					<span>To keeps conected with us please login with your
						personal info</span>
				</div>
			</div>
		</div>
	</div>
	
</body>
</html>