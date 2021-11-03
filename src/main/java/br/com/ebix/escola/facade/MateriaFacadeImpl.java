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

import br.com.ebix.escola.dao.MateriaDao;
import br.com.ebix.escola.dao.MateriaDaoImpl;
import br.com.ebix.escola.enums.AcoesValidacao;
import br.com.ebix.escola.model.Materia;
import br.com.ebix.escola.utils.ValidaStringUtil;

public class MateriaFacadeImpl implements MateriaFacade {
	private MateriaDao materiaDao = new MateriaDaoImpl();

	@Override
	public Materia get(Materia materia) {
		if (codigoEstaInvalido(materia)) {
			return null;
		} else {
			Materia materiaObtida = materiaDao.get(materia);

			if (materiaObtida != null) {
				return materiaObtida;
			} else {
				return null;
			}
		}
	}

	@Override
	public List<Materia> getAll() {
		return materiaDao.getAll();
	}

	
	
	@Override
	public List<Materia> getAllAvaiable() {
		return materiaDao.getAllAvaiable();
	}
	
	@Override
	public List<AcoesValidacao> add(Materia materia) {
		List<AcoesValidacao> acoes = dadosEstaoInvalidos(materia);
		if(acoes.size() == 0) {
			materiaDao.add(materia);
		}
		
		return acoes;
	}
	
	@Override
	public List<AcoesValidacao> update(Materia materia) {
		List<AcoesValidacao> acoes = dadosEstaoInvalidos(materia);
		if(codigoEstaInvalido(materia)) {
			acoes.add(AcoesValidacao.CODIGOINVALIDO);
		}
		
		if(acoes.size() == 0) {
			materiaDao.update(materia);
		}
		
		return acoes;
	}

	@Override
	public AcoesValidacao delete(Materia materia) {
		AcoesValidacao acao = null;
		if(codigoEstaInvalido(materia)) {
			acao = AcoesValidacao.CODIGOINVALIDO;
		} else {
			materiaDao.delete(materia);
		}
		return acao;

	}

	@Override
	public InputStream gerarRelatorioMaterias() {
		List<Materia> materias = materiaDao.getAll();
		
		HSSFWorkbook workBook = new HSSFWorkbook();
		HSSFSheet sheet = workBook.createSheet();
		HSSFRow row = null;
		
		Row header = sheet.createRow(0);
		header.createCell(0).setCellValue("Sigla");
		header.createCell(1).setCellValue("Nome");
		
		for (int i = 0; i < materias.size(); i++) {
			Materia materia = materias.get(i);
			
			row = sheet.createRow(i+1);
			Row data = sheet.createRow(i+1);
			data.createCell(0).setCellValue(materia.getSigla());
			data.createCell(1).setCellValue(materia.getNome());
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

	public boolean codigoEstaInvalido(Materia materia) {
		return (ValidaStringUtil.eNuloVazioOuHaApenasEspaco(materia.getCod_materia()));
	}

	public List<AcoesValidacao> dadosEstaoInvalidos(Materia materia) {
		List<AcoesValidacao> acoes = new ArrayList<AcoesValidacao>();
		
		if(ValidaStringUtil.eNuloVazioOuHaApenasEspaco(materia.getNome())) {
			acoes.add(AcoesValidacao.NOMEEMBRANCO);
		}
		if(ValidaStringUtil.eNuloVazioOuHaApenasEspaco(materia.getSigla())) {
			acoes.add(AcoesValidacao.SIGLAEMBRANCO);
		} else {
			if(materia.getSigla().length() > 5) {
				acoes.add(AcoesValidacao.SIGLAMINIMOCHAREXCEDIDO);
			}
		}
		return acoes;
	}
	
}
