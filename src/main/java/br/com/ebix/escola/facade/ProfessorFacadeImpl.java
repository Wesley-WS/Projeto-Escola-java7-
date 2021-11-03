package br.com.ebix.escola.facade;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

import br.com.ebix.escola.dao.ProfessorDao;
import br.com.ebix.escola.dao.ProfessorDaoImpl;
import br.com.ebix.escola.enums.AcoesValidacao;
import br.com.ebix.escola.model.Professor;
import br.com.ebix.escola.utils.ValidaCpf;
import br.com.ebix.escola.utils.ValidaDataUtil;
import br.com.ebix.escola.utils.ValidaEmail;
import br.com.ebix.escola.utils.ValidaStringUtil;
import br.com.ebix.escola.utils.ValidaTelefoneUtil;


public class ProfessorFacadeImpl implements ProfessorFacade {
	
	private ProfessorMateriaFacade professorMateriaFacade = new ProfessorMateriaFacadeImpl();
	private ProfessorDao professorDao = new ProfessorDaoImpl();

	@Override
	public Professor get(Professor professor) {
		if(codigoEstaInvalido(professor)) {
			return null;
		} else {
			Professor professorObtido = professorDao.get(professor);
			
			if(professorObtido != null) {
				return professorObtido;
			} else {
				return null;
			}
		}
	}
	
	@Override
	public List<Professor> getAll() {
		return professorDao.getAll();
	}

	@Override
	public List<AcoesValidacao> add(Professor professor) {
		List<AcoesValidacao> acoes = dadosEstaoInvalidos(professor);
		if(cpfJaExiste(professor)) {
			acoes.add(AcoesValidacao.CPFEXISTENTE);
		}
		
		if(acoes.size() == 0) {
			professorDao.add(professor);
		}
		return acoes;
	}

	@Override
	public List<AcoesValidacao> update(Professor professor) {
		List<AcoesValidacao> acoes = dadosEstaoInvalidos(professor);
		if(codigoEstaInvalido(professor)) {
			acoes.add(AcoesValidacao.CODIGOINVALIDO);
		}
		if(updateCpfJaExiste(professor)) {
			acoes.add(AcoesValidacao.CPFEXISTENTE);
		}
		
		if(acoes.size() == 0) {
			professorDao.update(professor);
		}
		return acoes;
	}

	@Override
	public AcoesValidacao delete(Professor professor) {
		AcoesValidacao acao = null;
		if(codigoEstaInvalido(professor)) {
			acao = AcoesValidacao.CODIGOINVALIDO;
		} else {
			professorMateriaFacade.disassociate(professor);
			professorDao.delete(professor);
		}
		return acao;
	}

	@Override
	public InputStream gerarRelatorioProfessores() {
		List<Professor> professores = professorDao.getAll();
		
		HSSFWorkbook workBook = new HSSFWorkbook();
		HSSFSheet sheet = workBook.createSheet();
		HSSFRow row = null;
		
		Row header = sheet.createRow(0);
		header.createCell(0).setCellValue("Nome");
		header.createCell(1).setCellValue("CPF");
		header.createCell(2).setCellValue("Email");
		
		for (int i = 0; i < professores.size(); i++) {
			Professor professor = professores.get(i);
			
			row = sheet.createRow(i+1);
			Row data = sheet.createRow(i+1);
			data.createCell(0).setCellValue(professor.getNome());
			data.createCell(1).setCellValue(professor.getCpf());
			data.createCell(2).setCellValue(professor.getEmail());
		}
		
		InputStream stream = null;
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			workBook.write(baos);
			
			stream = new ByteArrayInputStream(baos.toByteArray());
			
			workBook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return stream;
	}
	
	public boolean naoEstaEmIdadeParaLecionar(Professor professor) {
		return (professor.obterIdade() < 22);
	}
	
	public boolean codigoEstaInvalido(Professor professor) {
		return ValidaStringUtil.eNuloVazioOuHaApenasEspaco(professor.getCod_professor());
	}
	
	public List<AcoesValidacao> dadosEstaoInvalidos(Professor professor) {
		List<AcoesValidacao> campos = new ArrayList<AcoesValidacao>();
		
		if(ValidaStringUtil.eNuloVazioOuHaApenasEspaco(professor.getNome())) {
			campos.add(AcoesValidacao.NOMEEMBRANCO);
		} else {
			if(professor.getNome().length() > 100) {
				campos.add(AcoesValidacao.NOMEMINIMOCHAREXCEDIDO);
			}
		}
		if(ValidaStringUtil.eNuloVazioOuHaApenasEspaco(professor)) {
			campos.add(AcoesValidacao.CPFEMBRANCO);
		} else {
			if(ValidaCpf.cpfEInvalido(professor.getCpf())) {
				campos.add(AcoesValidacao.CPFINVALIDO);
			}
		}
		if(ValidaStringUtil.eNuloVazioOuHaApenasEspaco(professor.getEmail())) {
			campos.add(AcoesValidacao.EMAILEMBRANCO);
		} else {
			if (ValidaEmail.eUmEmailInvalido(professor.getEmail())) {
				campos.add(AcoesValidacao.EMAILINVALIDO);
			}
		}
		if(!ValidaStringUtil.eNuloVazioOuHaApenasEspaco(professor.getTelefoneCelular())) {
			if(ValidaTelefoneUtil.eUmNumeroDeCelularInvalido(professor.getTelefoneCelular())) {
				campos.add(AcoesValidacao.TELEFONECELULARINVALIDO);
			}
		}
		if(!ValidaStringUtil.eNuloVazioOuHaApenasEspaco(professor.getTelefoneResidencial())) {
			if(ValidaTelefoneUtil.eUmNumeroResidencialInvalido(professor.getTelefoneResidencial())) {
				campos.add(AcoesValidacao.TELEFONERESIDENCIALINVALIDO);
			}
		}
		if(ValidaStringUtil.eNuloVazioOuHaApenasEspaco(professor.getDataNascimento())) {
			campos.add(AcoesValidacao.DATANASCIMENTOEMBRANCO);
		} else {
			if(ValidaDataUtil.eUmaDataInvalida(professor.getDataNascimento())) {
				campos.add(AcoesValidacao.DATANASCIMENTOINVALIDA);
			} else {
				if(naoEstaEmIdadeParaLecionar(professor)) {
					campos.add(AcoesValidacao.DATANASCIMENTOINVALIDAALUNO);
				}
			}
		}
		
		return campos;
	}

	public boolean cpfJaExiste(Professor professor) {
		return professorDao.cpfCadastrado(professor);
	}
	
	public boolean updateCpfJaExiste(Professor professor) {
		String cpfDigitado = professor.getCpf();
		Professor professorObtido = professorDao.get(professor);
		
		if(professorObtido == null) {
			return false;
		}
		if(cpfDigitado.equals(professorObtido.getCpf())) {
			return false;
		}else {
			return professorDao.cpfCadastrado(professor);
		}
		
	}

}
