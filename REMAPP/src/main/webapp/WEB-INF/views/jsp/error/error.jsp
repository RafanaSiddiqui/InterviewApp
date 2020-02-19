<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Error Page</title>
</head>
<jsp:include page="../fragments/header.jsp" />
<body>


<div class="container">
		<div class="panel panel-default">
		<div class="panel-heading" style="background-color: #efe6f2">
				<span class="lead">Sorry the page you are looking for not exists because of</span>
				
			</div>
					<ul class="lead" style="color: red">
				 	<li>User Profile not available or</li>
				 	<li>Session expired or</li>
				 	<li>Access Denied</li>
				 </ul>

	</div>
		</div>
</body>
<jsp:include page="../fragments/footer.jsp" />
</html>