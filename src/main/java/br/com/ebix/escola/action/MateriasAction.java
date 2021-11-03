package br.com.ebix.escola.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

import com.opensymphony.xwork2.ActionSupport;

import br.com.ebix.escola.enums.AcoesValidacao;
import br.com.ebix.escola.facade.MateriaFacade;
import br.com.ebix.escola.facade.MateriaFacadeImpl;
import br.com.ebix.escola.model.Materia;

public class MateriasAction extends ActionSupport {
	private static final long serialVersionUID = 1L;

	private MateriaFacade materiaFacade = new MateriaFacadeImpl();
	private Materia materia = new Materia();

	private List<Materia> materias;

	private InputStream excelStream;
	
	public String listar() {
		materias = materiaFacade.getAll();
		return SUCCESS;
	}

	public String detalhar() {
		materia = materiaFacade.get(materia);
		if (materia != null) {
			return SUCCESS;
		} else {
			return ERROR;
		}
	}
	
	public String cadastrar() {
		List<AcoesValidacao> acoes = materiaFacade.add(materia);
		if(acoes.size() > 0) {
			for (AcoesValidacao acao : acoes) {
				addFieldError(acao.getCampo(), acao.getMensagem());
			}
			return INPUT;
		} else {
			return SUCCESS;
		}
	}

	public String alterar() {
		List<AcoesValidacao> acoes = materiaFacade.update(materia);
		if(acoes.size() > 0) {
			for (AcoesValidacao acao : acoes) {
				addFieldError(acao.getCampo(), acao.getMensagem());
			}
			return INPUT;
		} else {
			return SUCCESS;
		}
	}

	public String deletar() {
		AcoesValidacao acao = materiaFacade.delete(materia);
		if(acao == null) {
			return SUCCESS;
		} else {
			addFieldError(acao.getCampo(), acao.getMensagem());
			return INPUT;
		}
	}
	
	public String gerarExcel() {
		excelStream = materiaFacade.gerarRelatorioMaterias();
		if(excelStream == null) {
			return ERROR;
		}
		return SUCCESS;
	}
	
	public Materia getMateria() {
		return materia;
	}

	public void setMateria(Materia materia) {
		this.materia = materia;
	}

	public List<Materia> getMaterias() {
		return materias;
	}

	public void setMaterias(List<Materia> materias) {
		this.materias = materias;
	}

	public InputStream getExcelStream() {
		return excelStream;
	}

	public void setExcelStream(InputStream excelStream) {
		this.excelStream = excelStream;
	}
	
}
