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
					<li><a href="#about">${methodName}</a></li>
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
		<%-- 		<c:if test="${not empty action}"> --%>
		<%-- 			<c:if test="${action eq 'updateCustomer'}"> --%>
		<!-- 				<script type="text/javascript"> -->
		<!-- 			var url -->
		<!-- 				</script> -->
		<%-- 			</c:if> --%>

		<%-- 		</c:if> --%>
		<%-- <c:out value="${css}" > --%>
		<%-- </c:out> --%>
		<c:if test="${not empty methodName}">
			<c:if test="${methodName eq 'Add Customer' }">
				<script type="text/javascript">
					$(document)
							.ready(
									function() {

										// 										$('#result-modal').modal({
										// 											backdrop : 'static',
										// 											keyboard : false
										// 									});
										// 										$('#result-modal').modal('show');

										// 										$('#success-btn').focus();
										$('#email')
												.change(
														function() {
															var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
															if ($('#email')
																	.val() !== ''
																	&& re
																			.test($(
																					'#email')
																					.val())) {
																var result = {};
																var data = {};
																var deferred = $
																		.Deferred()
																data["email"] = $(
																		'#email')
																		.val();
																$
																		.ajax({
																			url : "http://localhost:8082/springmvccrm/customer/checkEmail",
																			type : 'POST',
																			dataType : 'json',
																			data : JSON
																					.stringify(data),
																			contentType : 'application/json',
																			mimeType : 'application/json',
																			beforeSend : function(
																					xhr) {
																				xhr
																						.setRequestHeader(
																								"Accept",
																								"application/json");
																				xhr
																						.setRequestHeader(
																								"Content-Type",
																								"application/json");
																			},
																			success : function(
																					data) {
																				if (data) {
																					if (!$(
																							'#emailError')
																							.is(
																									':visible')) {
																						$(
																								'#emaildup')
																								.text(
																										'email id already exists');
																						$(
																								'#emaildup')
																								.show();
																						$(
																								'#savebutton')
																								.attr(
																										'disabled',
																										true);
																					}

																				} else {
																					$(
																							'#emaildup')
																							.hide();
																					$(
																							'#savebutton')
																							.attr(
																									'disabled',
																									false);
																				}

																				// 																			if (window.opener
																				// 																					&& !window.opener.closed) {
																				// 																				window.opener.location
																				// 																						.reload();
																				// 																				window
																				// 																						.close();
																				// 																			} else {
																				// 																				window
																				// 																						.close();
																				// 																			}
																				// 				                        alert("sucess" + " " + JSON.stringify(data));
																				// 				                        alert(JSON.parse(data));
																				//commit(true);
																				return deferred
																						.resolve();
																			},

																			error : function(
																					data,
																					status,
																					er) {
																				// 																				alert("error: "
																				// 																						+ data
																				// 																						+ " status: "
																				// 																						+ status
																				// 																						+ " er:"
																				// 																						+ er);
																				return deferred
																						.reject();
																			}

																		});
																return deferred
																		.promise(true);
															}
														});

										// 										$('#submit-form')
										// 												.submit(
										// 														function(e) {
										// 															var deferred = $.Deferrerd();
										// 															$.Deferred().resolve().then(function(){
										// 																return $('#email').focusout()
										// 															}).then(function() {
										// 																				if ($(
										// 																						'#emaildup')
										// 																						.is(
										// 																								':visible')) {
										// 																					e.preventDefault();
										// 																					return deferred.resolve(false);
										// 																				} else {
										// 																					return deferred.resolve(true);
										// 																				}
										// 																				return deferred.promise(true);
										// 																			});

										// 														});
										$(document).ajaxStart(function() {
											$("#wait").css("display", "block");
										});
										$(document).ajaxComplete(function() {
											$("#wait").css("display", "none");
										});
									});
				</script>
			</c:if>
		</c:if>

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
					<form:errors id="firstNameError" class="control-label error"
						style="color:red" path="firstName" />
				</div>
			</div>
			<div id="LastName_div" class="form-group">
				<label class="col-sm-2 control-label">LastName</label>
				<div class="col-sm-5">
					<form:input class="form-control" id="lastname"
						placeholder="lastname" path="lastName" />
					<form:errors id="lastNameError" class="control-label error"
						style="color:red" path="lastName" />
				</div>
			</div>
			<div id="email_div" class="form-group ">
				<label class="col-sm-2 control-label">Email</label>
				<div class="col-sm-5">
					<form:input class="form-control" id="email" placeholder="email"
						path="email" />
					<div id="wait"
						style="display: none; width: 20px; height: 20px; border: 1px solid black; position: absolute; top: 20%; left: 98%; padding: 2px;">
						<img src="<c:url value="/resources/img/giphy.gif" />" width="20"
							height="20" />
					</div>
					<form:errors id="emailError" class="control-label"
						style="color:red" path="email" />

					<label id="emaildup" class="control-label error"
						style="color: red; display: none" />
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
				if (window.opener && !window.opener.closed) {
					window.opener.location.reload();
				}
			</script>
		</c:if>
		<c:if test="${msg eq 'customer updated succesfully'}">
			<script type="text/javascript">
				if (window.opener && !window.opener.closed) {
					window.opener.location.reload();
				}
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
						</div>
						<div class="modal-body">
							<p>Deleted successfully</p>
						</div>
						<div class="modal-footer">
							<a id="success-btn" type="button" class="btn btn-primary">OK</a>
						</div>
					</div>
				</div>
			</div>
			<script type="text/javascript">
				$(document).ready(
						function() {

							$('#submit-form').submit(function(e) {
								if (!$('#exampleModalCenter').is(':visible')) {
									e.preventDefault();
								}
							});
							$('#savebutton').attr('data-toggle', 'modal');
							$('#savebutton').attr('data-target',
									'#exampleModalCenter');
							$('#firstname').attr('disabled', true);
							$('#lastname').attr('disabled', true);
							$('#email').attr('disabled', true);

							$('#exampleModalCenter').on('shown.bs.modal',
									function() {
										$('#deleteCustomer').focus();
									});
							$('#deleteCustomer').click(function() {
								$('#submit-form').submit();
							});

						});
			</script>
			<c:if test="${msg eq 'deletionSuccess'}">
				<script type="text/javascript">
					var result = {};
					var data = {};
					data["msg"] = "welcome";
					$(document)
							.ready(
									function() {
										$('#result-modal').modal({
											backdrop : 'static',
											keyboard : false
										});
										$('#result-modal').modal('show');
										$('#success-btn').focus();
										$('#success-btn')
												.click(
														function() {
															$
																	.ajax({
																		url : "http://localhost:8082/springmvccrm/customer/getAjaxResult",
																		type : 'POST',
																		dataType : 'json',
																		data : JSON
																				.stringify(data),
																		contentType : 'application/json',
																		mimeType : 'application/json',
																		beforeSend : function(
																				xhr) {
																			xhr
																					.setRequestHeader(
																							"Accept",
																							"application/json");
																			xhr
																					.setRequestHeader(
																							"Content-Type",
																							"application/json");
																		},
																		success : function(
																				data) {
																			if (window.opener
																					&& !window.opener.closed) {
																				window.opener.location
																						.reload();
																				window
																						.close();
																			} else {
																				window
																						.close();
																			}
																			// 				                        alert("sucess" + " " + JSON.stringify(data));
																			// 				                        alert(JSON.parse(data));
																			//commit(true);
																		},

																		error : function(
																				data,
																				status,
																				er) {
																			alert("error: "
																					+ data
																					+ " status: "
																					+ status
																					+ " er:"
																					+ er);
																		}
																	});
														});
									});
				</script>
			</c:if>
		</c:if>
	</div>

	<!-- Full Width Image Header -->


	<!-- /.container -->

</body>

</html>
