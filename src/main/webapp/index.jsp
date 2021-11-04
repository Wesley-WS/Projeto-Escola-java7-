<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@	taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="context" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" href="${context}/assets/css/styles.css">
	<script src="https://kit.fontawesome.com/6697f5eca6.js"
		crossorigin="anonymous"></script>
	<title>Home</title>
</head>
<body>
	<div class="app">
		<s:include value="./components/sidebar.jspf"></s:include>
		<div class="body-wrapper">
			<div class="body-header">
				<h2>Bem vindo ao sistema escolar</h2>
			</div>
			<div class="body p-8">
				<div class="scroller scroller-base">
					<div class="text-center">
						<img class="align-self-center" style="width: 50%;"
							src="https://upload.wikimedia.org/wikipedia/commons/thumb/0/08/Apache_Struts_2_logo.svg/512px-Apache_Struts_2_logo.svg.png" />
					</div>
					<p>Isso aqui é um paragrafo, coloquei para não ficar vazio</p>
					<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.
						Maecenas a turpis nec mauris placerat pellentesque. Sed nec
						aliquam purus, non venenatis justo. Nam pulvinar porttitor
						feugiat. Vestibulum interdum, eros sit amet aliquet lobortis, leo
						metus pellentesque erat, nec auctor neque felis eu nibh. Donec
						tempor pulvinar arcu, quis ultrices neque porta ut. Vestibulum
						fringilla quam quam. In luctus cursus felis, id viverra nulla
						dictum sit amet. Vivamus eget ultricies ante, vel hendrerit nisi.
						Vestibulum feugiat rhoncus quam, eu tristique urna iaculis id.
						Etiam vestibulum nibh sed arcu aliquam aliquet. Praesent tincidunt
						metus ut dolor malesuada, ac eleifend diam cursus. Mauris nec
						ultricies eros. Vivamus efficitur ante sodales nunc faucibus, quis
						pretium libero interdum. Donec venenatis tincidunt scelerisque.
						Mauris ipsum elit, pretium nec velit vel, dapibus aliquam nunc.
						Morbi non lectus condimentum, interdum neque elementum, sagittis
						enim.</p>
					<p>
						Lorem ipsum dolor sit amet, consectetur adipiscing elit.
						Maecenas a turpis nec mauris placerat pellentesque. Sed nec
						aliquam purus, non venenatis justo. Nam pulvinar porttitor
						feugiat. Vestibulum interdum, eros sit amet aliquet lobortis, leo
						metus pellentesque erat, nec auctor neque felis eu nibh. Donec
						tempor pulvinar arcu, quis ultrices neque porta ut. Vestibulum
						fringilla quam quam. In luctus cursus felis, id viverra nulla
						dictum sit amet. Vivamus eget ultricies ante, vel hendrerit nisi.
						Vestibulum feugiat rhoncus quam, eu tristique urna iaculis id.
						Etiam vestibulum nibh sed arcu aliquam aliquet. Praesent tincidunt
						metus ut dolor malesuada, ac eleifend diam cursus. Mauris nec
						ultricies eros. Vivamus efficitur ante sodales nunc faucibus, quis
						pretium libero interdum. Donec venenatis tincidunt scelerisque.
						Mauris ipsum elit, pretium nec velit vel, dapibus aliquam nunc.
						Morbi non lectus condimentum, interdum neque elementum, sagittis
						enim.
					</p>
				</div>
			</div>
		</div>
	</div>
</body>
</html>