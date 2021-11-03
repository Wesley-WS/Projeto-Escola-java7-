package br.com.ebix.escola.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

import com.opensymphony.xwork2.ActionSupport;

import br.com.ebix.escola.facade.AlunoFacade;
import br.com.ebix.escola.facade.AlunoFacadeImpl;
import br.com.ebix.escola.facade.MateriaFacade;
import br.com.ebix.escola.facade.MateriaFacadeImpl;
import br.com.ebix.escola.facade.ProfessorFacade;
import br.com.ebix.escola.facade.ProfessorFacadeImpl;
import br.com.ebix.escola.model.Aluno;
import br.com.ebix.escola.model.Materia;
import br.com.ebix.escola.model.Professor;

public class ExcelAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private MateriaFacade materiasFacade = new MateriaFacadeImpl();
	private AlunoFacade alunosFacade = new AlunoFacadeImpl();
	private ProfessorFacade professorFacade = new ProfessorFacadeImpl();
	
	private InputStream excelStream;

	public String gerarExcelProfessores() {
		List<Professor> professores = professorFacade.getAll();
		
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
		
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			workBook.write(baos);
			setExcelStream(new ByteArrayInputStream(baos.toByteArray()));
			
			workBook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String gerarExcelAlunos() {
		List<Aluno> alunos = alunosFacade.getAll();
		
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
		
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			workBook.write(baos);
			setExcelStream(new ByteArrayInputStream(baos.toByteArray()));
			
			workBook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String gerarExcelMaterias() {
		List<Materia> materias = materiasFacade.getAll();
		
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
		
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			workBook.write(baos);
			setExcelStream(new ByteArrayInputStream(baos.toByteArray()));
			
			workBook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public InputStream getExcelStream() {
		return excelStream;
	}

	public void setExcelStream(InputStream excelStream) {
		this.excelStream = excelStream;
	}
}
