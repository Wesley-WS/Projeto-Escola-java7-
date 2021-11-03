console.log("Carregado");

function toggleModal(href){
	console.log(href);
	$("#modal").toggle();
	$("#delete").attr('href', href); 
}