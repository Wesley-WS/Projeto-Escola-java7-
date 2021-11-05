package br.com.ebix.escola.facade;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

import br.com.ebix.escola.dao.AlunoDao;
import br.com.ebix.escola.dao.AlunoDaoImpl;
import br.com.ebix.escola.enums.AcoesValidacao;
import br.com.ebix.escola.model.Aluno;
import br.com.ebix.escola.model.Materia;
import br.com.ebix.escola.utils.ValidaCpf;
import br.com.ebix.escola.utils.ValidaDataUtil;
import br.com.ebix.escola.utils.ValidaEmail;
import br.com.ebix.escola.utils.ValidaStringUtil;
import br.com.ebix.escola.utils.ValidaTelefoneUtil;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class AlunoFacadeImpl implements AlunoFacade {

	// @Autowired
	// private AlunoDao alunoDao;
	private MateriaFacade materiaFacade = new MateriaFacadeImpl();
	private AlunoDao alunoDao = new AlunoDaoImpl();

	@Override
	public Aluno get(Aluno aluno) {
		if(codigoEstaInvalido(aluno)) {
			return null;
		} else {
			Aluno alunoObtido = alunoDao.get(aluno);
			
			if(alunoObtido != null) {
				return alunoObtido;
			} else {
				return null;
			}
		}
	}

	@Override
	public List<Aluno> getAll() {
		return alunoDao.getAll();
	}
	
	public List<Materia> getAllMateriasByCodAluno(Aluno aluno){
		List<Long> cod_materias = alunoDao.getAllCodMatByCod(aluno);
		List<Materia> materiasAll = new ArrayList<Materia>();
		materiasAll = materiaFacade.getAll();
		
		if(cod_materias.size() > 0) {
			for(Long cod_materia : cod_materias) {
				for(Materia materia : materiasAll) {
					if(materia.getCod_materia()==cod_materia) {
						materiasAll.remove(materia);
						break;
					}
				}
			}
			return materiasAll;
		} else {
			return materiasAll;
		}
		
	}
	
	public List<Materia> getAllMateriasByCodAlunoHaving(Aluno aluno){
		List<Long> cod_materias = alunoDao.getAllCodMatByCod(aluno);
		List<Materia> materiasAll = new ArrayList<Materia>();
		materiasAll = materiaFacade.getAll();
		List<Materia> materiasAssociadas = new ArrayList<Materia>();
		if(cod_materias.size() > 0) {
			for(Long cod_materia : cod_materias) {
				for(Materia materia : materiasAll) {
					if(materia.getCod_materia()==cod_materia) {
						materiasAssociadas.add(materia);
						break;
					}
				}
			}
			return materiasAssociadas;
		} else {
			return materiasAssociadas;
		}
		
	}

	@Override
	public List<AcoesValidacao> add(Aluno aluno) {
		List<AcoesValidacao> acoes = dadosEstaoInvalidos(aluno);
		if(cpfJaExiste(aluno)) {
			acoes.add(AcoesValidacao.CPFEXISTENTE);
		}
		
		if(acoes.size() == 0) {
			alunoDao.add(aluno);
		}
		return acoes;

	}

	@Override
	public List<AcoesValidacao> update(Aluno aluno) {
		List<AcoesValidacao> acoes = dadosEstaoInvalidos(aluno);
		if(codigoEstaInvalido(aluno)) {
			acoes.add(AcoesValidacao.CODIGOINVALIDO);
		}
		if(updateCpfJaExiste(aluno)) {
			acoes.add(AcoesValidacao.CPFEXISTENTE);
		}
		
		if(acoes.size() == 0) {
			alunoDao.update(aluno);
		}
		return acoes;
	}

	@Override
	public AcoesValidacao delete(Aluno aluno) {
		AcoesValidacao acao = null;
		if (codigoEstaInvalido(aluno)) {
			acao = AcoesValidacao.CODIGOINVALIDO;
		} else {
			alunoDao.delete(aluno);
		}
		return acao;

	}
	
	@Override
	public InputStream gerarRelatorioExcel() {
		List<Aluno> alunos = alunoDao.getAll();
		
		HSSFWorkbook workBook = new HSSFWorkbook();
		HSSFSheet sheet = workBook.createSheet();
		HSSFRow row = null;
		
		Row header = sheet.createRow(0);
		header.createCell(0).setCellValue("Nome");
		header.createCell(1).setCellValue("CPF");
		header.createCell(2).setCellValue("Email");
		
		for (int i = 0; i < alunos.size(); i++) {
			Aluno aluno = alunos.get(i);
			
			row = sheet.createRow(i+1);
			Row data = sheet.createRow(i+1);
			data.createCell(0).setCellValue(aluno.getNome());
			data.createCell(1).setCellValue(aluno.getCpf());
			data.createCell(2).setCellValue(aluno.getEmail());
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

	@Override
	public ByteArrayInputStream gerarRelatorioPdf(String path) {
		List<Aluno> alunos = alunoDao.getAll();
		
		ByteArrayInputStream stream = null;
        try {
        	Map<String, Object> map = new HashMap<String, Object>();

        	JasperReport teste = JasperCompileManager.compileReport(path);
        	JasperPrint print = JasperFillManager.fillReport(teste, map, new JRBeanCollectionDataSource(alunos, false));
        	
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			JasperExportManager.exportReportToPdfStream(print, baos);
			stream = new ByteArrayInputStream(baos.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
        }

		return stream;
	}
	
	public boolean naoEstaEmIdadeEscolar(Aluno aluno) {
		return (aluno.obterIdade() <= 4);
	}
	
	public boolean codigoEstaInvalido(Aluno aluno) {
		return (ValidaStringUtil.eNuloVazioOuHaApenasEspaco(aluno.getCod_aluno()));
	}

	public List<AcoesValidacao> dadosEstaoInvalidos(Aluno aluno) {
		List<AcoesValidacao> campos = new ArrayList<AcoesValidacao>();
		
		if(ValidaStringUtil.eNuloVazioOuHaApenasEspaco(aluno.getNome())) {
			campos.add(AcoesValidacao.NOMEEMBRANCO);
		} else {
			if(aluno.getNome().length() > 100) {
				campos.add(AcoesValidacao.NOMEMINIMOCHAREXCEDIDO);
			}
		}
		if(ValidaStringUtil.eNuloVazioOuHaApenasEspaco(aluno.getCpf())) {
			campos.add(AcoesValidacao.CPFEMBRANCO);
		} else {
			if(ValidaCpf.cpfEInvalido(aluno.getCpf())) {
				campos.add(AcoesValidacao.CPFINVALIDO);
			}
		}
		if(ValidaStringUtil.eNuloVazioOuHaApenasEspaco(aluno.getEmail())) {
			campos.add(AcoesValidacao.EMAILEMBRANCO);
		} else {
			if (ValidaEmail.eUmEmailInvalido(aluno.getEmail())) {
				campos.add(AcoesValidacao.EMAILINVALIDO);
			}
		}
		if(!ValidaStringUtil.eNuloVazioOuHaApenasEspaco(aluno.getTelefoneCelular())) {
			if(ValidaTelefoneUtil.eUmNumeroDeCelularInvalido(aluno.getTelefoneCelular())) {
				campos.add(AcoesValidacao.TELEFONECELULARINVALIDO);
			}
		}
		if(!ValidaStringUtil.eNuloVazioOuHaApenasEspaco(aluno.getTelefoneResidencial())) {
			if(ValidaTelefoneUtil.eUmNumeroResidencialInvalido(aluno.getTelefoneResidencial())) {
				campos.add(AcoesValidacao.TELEFONERESIDENCIALINVALIDO);
			}
		}
		if(ValidaStringUtil.eNuloVazioOuHaApenasEspaco(aluno.getDataNascimento())) {
			campos.add(AcoesValidacao.DATANASCIMENTOEMBRANCO);
		} else {
			if(ValidaDataUtil.eUmaDataInvalida(aluno.getDataNascimento())) {
				campos.add(AcoesValidacao.DATANASCIMENTOINVALIDA);
			} else {
				if(naoEstaEmIdadeEscolar(aluno)) {
					campos.add(AcoesValidacao.DATANASCIMENTOINVALIDAALUNO);
				}
			}
		}
		
		return campos;
	}
	
	public boolean cpfJaExiste(Aluno aluno) {
		return alunoDao.cpfCadastrado(aluno);
	}
	
	public boolean updateCpfJaExiste(Aluno aluno) {
		String cpfDigitado = aluno.getCpf();
		Aluno alunoObtido = alunoDao.get(aluno);
		if(alunoObtido == null) {
			return false;
		}
		if(cpfDigitado.equals(alunoObtido.getCpf())) {
			return false;
		}else {
			return alunoDao.cpfCadastrado(aluno);
		}
		
	}
	
}
