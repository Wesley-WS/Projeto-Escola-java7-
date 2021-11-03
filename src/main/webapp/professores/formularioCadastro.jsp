<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@	taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>

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
<script src="https://code.jquery.com/jquery-2.2.4.js" integrity="sha256-iT6Q9iMJYuQiMWNd9lDyBUStIq/8PuOW33aOqmvFpqI=" crossorigin="anonymous"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.0/jquery.mask.js"></script>
<script type="text/javascript" src="${context}/assets/js/app.js"></script>
<title>Formulario - Cadastrar professor</title>
</head>
<body>
	<div class="app">
		<s:include value="../components/sidebar.jspf"></s:include>
		<div class="body-wrapper">
			<div class="body">
				<div class="body-header mb-8">
					<h2>Cadastrar professor</h2>
				</div>
				<div class="body align-self-center">
					<s:form id="formularioPessoa" action="cadastrar" method="post">
						<div class="mb-8">
							<label>Nome</label><br />
							<input id="nome" type="text" name="professor.nome" value="${professor.nome}"/>
							<s:fielderror fieldName="nome" />
						</div>
						<div class="mb-8">
							<label>Cpf</label><br />
							<input id="cpf" type="text" name="professor.cpf" value="${professor.cpf}"/>
							<s:fielderror fieldName="cpf" />
						</div>
						<div class="mb-8">
							<label>Data de nascimento</label><br />
							<input id="dataNascimento" type="text" name="professor.dataNascimento" value="<s:date name="professor.dataNascimento" format="dd/MM/yyyy" />"/>
							<s:fielderror fieldName="dataNascimento" />
						</div>
						<div class="mb-8">
							<label>Email</label><br />
							<input id="email" type="email" name="professor.email" value="${professor.email}"/>
							<s:fielderror fieldName="email" />
						</div>
						<div class="mb-8">
							<label>Telefone Celular</label><br />
							<input id="telCelular" type="tel" name="professor.telefoneCelular" value="${professor.telefoneCelular}"/>
							<s:fielderror fieldName="telefoneCelular" />
						</div>
						<div class="mb-8">
							<label>Telefone Residêncial</label><br />
							<input id="telResidencial" type="tel" name="professor.telefoneResidencial" value="${professor.telefoneResidencial}"/>
							<s:fielderror fieldName="telefoneResidencial" />
						</div>
						
						<button type="submit" class="btn btn-primary">Submit</button>
					</s:form>
				</div>
			</div>
		</div>
	</div>

</body>
</html>