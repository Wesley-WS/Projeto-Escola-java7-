<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<%@	taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="context" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" href="${context}/assets/css/styles.css">
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script src="https://kit.fontawesome.com/6697f5eca6.js" crossorigin="anonymous"></script>
	<script src="../assets/js/modal.js"></script>
	<title>Materias</title>
</head>
<body>
	<div class="app">
		<s:include value="../components/sidebar.jspf"></s:include>
		<div class="body-wrapper">
			<div class="body-header">
				<h2>Lista de matérias</h2>
				
				<div class="d-flex gap-8">
					<div class="d-flex fit-flex-container">
						<a href="iniciarCadastro" class="btn btn-primary"><i class="fas fa-plus"></i> Inserir materia</a>
					</div>
					<a href="relatorios" class="btn btn-primary">
						<i class="fas fa-file-pdf"></i> Gerar relatório
					</a>
					<a href="gerarExcel" class="btn btn-primary"><i
						class="fas fa-file-excel"></i> Gerar Excel</a>
				</div>
			</div>
			<div class="body p-8">
				<div class="scroller scroller-base">
					<table class="table">
						<tr>
							<th>Nome</th>
							<th>Sigla</th>
							<th colspan="2">Ações</th>
						</tr>
						<s:iterator value="materias">
							<tr>
								<td><s:property value="nome" /></td>
								<td><s:property value="sigla" /></td>
								<td class="td-actions" colspan="2">
									<a href="iniciarAlteracao?materia.cod_materia=${cod_materia}" title="Alterar" alt="Alterar"><i class="far fa-edit"></i></a>
									<a href="detalhar?materia.cod_materia=${cod_materia}" title="Detalhar" alt="Detalhar"><i class="far fa-eye"></i></a>
									<a href="#" onclick="toggleModal('deletar?materia.cod_materia=${cod_materia}')" title="Deletar" alt="Deletar"><i class="far fa-trash-alt"></i></a>
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