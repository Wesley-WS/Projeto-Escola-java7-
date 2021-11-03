$(document).ready(() => carregarMascaras());

function obterCamposPessoa() {
	return {
		nome: $("#nome"),
		cpf: $("#cpf"),
		email: $("#email"),
		telCelular: $("#telCelular"),
		telResidencial: $("#telResidencial"),
		dataNascimento: $("#dataNascimento")
	}
}

function obterCamposMateria() {
	return {
		nome: $("#nome"),
		sigla: $("#sigla")
	}
}

function carregarMascaras() {
	let { cpf, telCelular, telResidencial, dataNascimento } = obterCamposPessoa();
		
	cpf.mask('000.000.000-00', {reverse: false});
	telCelular.mask('(00)00000-0000', {reverse: false});
	telResidencial.mask('(00)0000-0000', {reverse: false});
	dataNascimento.mask('00/00/0000', {reverse: true});
}