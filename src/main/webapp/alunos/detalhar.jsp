<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@	taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<c:set var="context" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="${context}/assets/css/styles.css">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.15.4/css/all.css"
	integrity="sha384-DyZ88mC6Up2uqS4h/KRgHuoeGwBcD4Ng9SiP4dIRy0EXTlnuz47vAwmeGwVChigm"
	crossorigin="anonymous">
<title>Escola - Detalhar dados aluno</title>
</head>
<body>
	<div class="app">
		<s:include value="../components/sidebar.jspf"></s:include>
		<div class="body-wrapper">
			<div class="body-header">
				<!-- <div class="logo">
					<i class="fas fa-user-graduate"></i>
				</div> -->
				<h2>Detalhes do aluno</h2>
			</div>
			<div class="body p-8">
				<div class="scroller scroller-base">
					<div class="d-flex flex-dir-col">
						<s:date name="aluno.dataNascimento.time" format="dd/MM/yyyy"
							var="dataNascimento" />
						<p><strong>Nome:</strong> ${aluno.nome}</p>
						<p><strong>Email:</strong> ${aluno.email}</p>
						<p><strong>Data de nascimento:</strong> ${dataNascimento}</p>
						<p><strong>Telefone celular:</strong> ${aluno.telefoneCelular}</p>
						<p><strong>Telefone residencial:</strong> ${aluno.telefoneResidencial}</p>
					</div>
					<div class="sidebar-extras-header">
						<h3>Matérias</h3>
					</div>

					<div class="d-flex card-group p-visual">
						<s:iterator value="materias">
							<div class="card">
								<div class="card-header">
									<p><strong>Matéria:</strong> ${nome}</p>
								</div>
								<div class="card-body"><p><strong>Sigla:</strong> ${sigla}</p></div>
							</div>
						</s:iterator>
					</div>
				</div>
			</div>
			<!-- <div class="body">
				<div class="body-content">
					<div class="fit-flex-container">
						<div class="d-flex flex-dir-col p-8">
							<s:date name="aluno.dataNascimento.time" format="dd/MM/yyyy"
								var="dataNascimento" />
							<p>Nome: ${aluno.nome}</p>
							<p>Email: ${aluno.email}</p>
							<p>Data de nascimento: ${dataNascimento}</p>
							<p>Telefone celular: ${aluno.telefoneCelular}</p>
							<p>Telefone residencial: ${aluno.telefoneResidencial}</p>
						</div>
					</div>

					<div class="body-wrapper">
						<div>
							<h3>Matérias</h3>
						</div>
						
						<div class="scroller scroller-base">
							<div class="d-flex card-group p-visual">
								<s:iterator value="materias">
									<div class="card">
										<div class="card-header">
											<h3>${nome}</h3>
										</div>
										<div class="card-body">Sigla: ${sigla}</div>
									</div>
								</s:iterator>
							</div>
						</div>
					</div>
				</div>
			</div> -->
		</div>
	</div>
</body>
</html>