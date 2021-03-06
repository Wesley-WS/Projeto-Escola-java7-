<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@	taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	
	<title>Escola - Associar aluno a uma mat?ria</title>
</head>
<body>
	<div class="app">
		<s:include value="../components/sidebar.jspf"></s:include>
		<div class="body-wrapper">
			<div class="body-header mb-8">
				<h2>Associar aluno a mat?ria</h2>
				<p>Aluno: ${ aluno.nome }</p>
			</div>
			<div class="body">
				<div class="scroller scroller-base">
					<s:form action="associar" method="post">
						<s:hidden name="aluno.cod_aluno" value="%{aluno.cod_aluno}" />
						
						<s:iterator value="alunoMaterias">
							<s:hidden value="cod_materia"/>
							
							<s:if test="%{cod_aluno != aluno.cod_aluno}">
								<s:checkbox name="materiasSelecionadas" id="%{cod_materia}" fieldValue="%{cod_materia}" label="%{nome}" value="false"></s:checkbox>
							</s:if>
							<s:else>
								<s:checkbox name="materiasSelecionadas" id="%{cod_materia}" fieldValue="%{cod_materia}" label="%{nome}" value="true"></s:checkbox>
							</s:else>
						</s:iterator>
						
						<s:submit value="Submit" name="submit"/>
					</s:form>
					
				</div>
			</div>
		</div>
	</div>
</body>
</html>