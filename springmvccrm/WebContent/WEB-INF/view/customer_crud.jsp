<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>CRM App-CustomerList</title>

<!-- Bootstrap Core CSS -->
<link
	href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css"
	rel="stylesheet">

<!-- Custom CSS -->
<link
	href="${pageContext.request.contextPath}/resources/css/one-page-wonder.css"
	rel="stylesheet">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
<!-- jQuery -->
<script src="${pageContext.request.contextPath}/resources/js/jquery.js">
	
</script>

<!-- Bootstrap Core JavaScript -->
<script
	src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>

</head>

<body>

	<!-- Navigation -->
	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#bs-example-navbar-collapse-1">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand"
					href="${pageContext.request.contextPath}/customer/customerlist">Customer-List</a>
			</div>
			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li><a href="#about">Add</a></li>
				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container -->
	</nav>
	<div id="head-div" class="container">
		<h1>
			Customer Relationship Management <small>${methodName}</small>
		</h1>
	</div>
	<div class="container">
		<!-- 	<div class="container"> -->
		<c:if test="${not empty css}">
			<div class="alert alert-${css} alert-block alert-dismissible"
				role="alert">
				<span type="button" class="close" data-dismiss="alert"
					aria-label="Close"> <span aria-hidden="true">x</span>
				</span> <strong>${msg}</strong>
			</div>

		</c:if>
		<form:form id="submit-form" class="form-horizontal" method="post"
			modelAttribute="saveCustomer"
			action="${pageContext.request.contextPath}/customer/${action}">
			<div id="firstname_div" class="form-group">
				<label class="col-sm-2 control-label">FirstName</label>
				<div class="col-sm-5">
					<form:input class="form-control input-sm" id="firstname"
						placeholder="firstname" path="firstName" />
					<form:errors class="control-label error" style="color:red"
						path="firstName" />
				</div>
			</div>
			<div id="LastName_div" class="form-group">
				<label class="col-sm-2 control-label">LastName</label>
				<div class="col-sm-5">
					<form:input class="form-control" id="lastname"
						placeholder="lastname" path="lastName" />
					<form:errors class="control-label error" style="color:red"
						path="lastName" />
				</div>
			</div>
			<div id="email_div" class="form-group ">
				<label class="col-sm-2 control-label">Email</label>
				<div class="col-sm-5">
					<form:input class="form-control" id="email" placeholder="email"
						path="email" />
					<form:errors class="control-label" style="color:red" path="email" />
				</div>
			</div>
			<div>
				<form:hidden path="id" />
			</div>
			<div class="col-sm-5 form-group">
				<button type="submit" id="savebutton"
					class="btn btn-primary center-block ">${button}</button>
			</div>
		</form:form>
		<c:if test="${msg eq 'customer added succesfully'}">
			<script type="text/javascript">
				$('#firstname').val('');
				$('#lastname').val('');
				$('#email').val('');
			</script>
		</c:if>
		<c:if test="${action eq 'removeCustomer'}">
			<div class="modal fade" id="exampleModalCenter" tabindex="-1"
				role="dialog" aria-labelledby="exampleModalCenterTitle"
				aria-hidden="true">
				<div class="modal-dialog modal-dialog-centered align-center"
					role="document">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="exampleModalLongTitle">Delete
								Confirmation</h5>
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<div class="modal-body">Are You Sure You Want to Delete?</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary"
								data-dismiss="modal">cancel</button>
							<button type="button" id="deleteCustomer" class="btn btn-primary">delete</button>
						</div>
					</div>
				</div>
			</div>
			<div class="modal" id="result-modal" tabindex="-1" role="dialog">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title">Success</h5>
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<div class="modal-body">
							<p>Deleted successfully</p>
						</div>
						<div class="modal-footer">
							<a id = "success-btn"type="button" class="btn btn-primary"
								href="${pageContext.request.contextPath}/customer/customerlist">OK</a>
						</div>
					</div>
				</div>
			</div>
			<script type="text/javascript">
				$(document).ready(
						function() {
							
							$('#submit-form').submit(function(e) {
								if(!$('#exampleModalCenter').is(':visible')){
									e.preventDefault();
								}
							});
							$('#savebutton').attr('data-toggle', 'modal');
							$('#savebutton').attr('data-target',
									'#exampleModalCenter');
							$('#firstname').attr('disabled', true);
							$('#lastname').attr('disabled', true);
							$('#email').attr('disabled', true);
							$('#deleteCustomer').click(function(){
								$('#submit-form').submit();
							});
							$('#deleteCustomer').focus();
						});
			</script>
			<c:if test="${msg eq 'deletionSuccess'}">
				<script type="text/javascript">
					$(document).ready(function() {
						$('#result-modal').modal('show');
						$('#success-btn').focus();
						$('#result-modal').modal({
							backdrop:'static',
							keyboard:false
						})
					});
				</script>
			</c:if>
		</c:if>
	</div>

	<!-- Full Width Image Header -->


	<!-- /.container -->

</body>

</html>
