<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@	taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>

<c:set var="context" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" href="${context}/assets/css/styles.css">
	<link rel="stylesheet"
		href="https://use.fontawesome.com/releases/v5.15.4/css/all.css"
		integrity="sha384-DyZ88mC6Up2uqS4h/KRgHuoeGwBcD4Ng9SiP4dIRy0EXTlnuz47vAwmeGwVChigm"
		crossorigin="anonymous">
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script src="../assets/js/modal.js"></script>
	<title>Escola - Lista de professores</title>
</head>
<body>
	<div class="app">
		<s:include value="../components/sidebar.jspf"></s:include>
		<div class="body-wrapper">
			<div class="body-header">
				<h2>Lista de professores</h2>
				<div class="d-flex gap-8">
					<div class="d-flex fit-flex-container">
						<a href="iniciarCadastro" class="btn btn-primary"><i class="fas fa-plus"></i> Inserir Professor</a>
					</div>
					<a href="gerarPdf" class="btn btn-primary"><i class="fas fa-file-pdf"></i> Gerar relatório</a>
					<a href="visualizarRelatorio" class="btn btn-primary"><i class="fas fa-file-pdf"></i> Visualizar relatório</a>
					<a href="gerarExcel" class="btn btn-primary"><i
						class="fas fa-file-excel"></i> Gerar Excel</a>
				</div>
			</div>
			<div class="body p-8">
				<div class="scroller scroller-base">
					<table class="table">
						<tr>
							<th>Nome</th>
							<th>CPF</th>
							<th>Email</th>
							<th colspan="2">Ações</th>
						</tr>
						<!-- O problema aqui é que a tabela de professores é muito grande, isso acaba atrapalhando a tabela e a página fica toda torta. -->
						<s:iterator value="professores">
							<tr>
								<td><s:property value="nome" /></td>
								<td><s:property value="cpf" /></td>
								<td><s:property value="email" /></td>
								<td class="td-actions" colspan="2">
									<a href="iniciarAlteracao?professor.cod_professor=${cod_professor}" title="Alterar" alt="Alterar"><i class="far fa-edit"></i></a>
									<a href="detalhar?professor.cod_professor=${cod_professor}" title="Detalhar" alt="Detalhar"><i class="far fa-eye"></i></a>
									<a href="iniciarAssociacao?professor.cod_professor=${cod_professor}" title="Associar" alt="Associar"><i class="fas fa-plug"></i></a>
									<a href="#" onclick="toggleModal('deletar?professor.cod_professor=${cod_professor}')" title="Deletar" alt="Deletar"><i class="far fa-trash-alt"></i></a>
								</td>
							</tr>
						</s:iterator>
					</table>
				</div>
			</div>
		</div>
	</div>
	<div id="modal" class="modal-content" style="display: none;">
		<s:include value="../components/modal.jspf"></s:include>
	</div>
</body>
</html>