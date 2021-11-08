package br.com.ebix.escola.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import br.com.ebix.escola.enums.AcoesValidacao;
import br.com.ebix.escola.facade.AlunoFacade;
import br.com.ebix.escola.facade.AlunoFacadeImpl;
import br.com.ebix.escola.facade.AlunoMateriaFacade;
import br.com.ebix.escola.facade.AlunoMateriaFacadeImpl;
import br.com.ebix.escola.model.Aluno;
import br.com.ebix.escola.model.AlunoMateria;
import br.com.ebix.escola.model.Materia;


public class AlunosAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	
	// @Autowired
	// private AlunoFacade alunoFacade;
	private AlunoFacade alunoFacade = new AlunoFacadeImpl();
	private AlunoMateriaFacade alunoMateriaFacade = new AlunoMateriaFacadeImpl();
	
	private Aluno aluno = new Aluno();
	
	private List<Materia> materias;
	private List<AlunoMateria> alunoMaterias;
	private List<Aluno> alunos;

	private String[] materiasSelecionadas;
	private List<Materia> materiasAssociadas;
	
	private InputStream excelStream;
	private ByteArrayInputStream pdfStream;
	
	public String listar() {
		alunos = alunoFacade.getAll();
		return SUCCESS;
	}
	
	public String detalhar() {
		aluno = alunoFacade.get(aluno);
		materias = alunoFacade.getMateriasByCodAluno(aluno);
		
		return SUCCESS;
		
		/*aluno = alunoFacade.get(aluno);
		if(aluno != null) {
			materias = alunoMateriaFacade.getAllMateriasByCodAlunoHaving(aluno);
			return SUCCESS;
		} else {
			return ERROR;
		}*/
	}
	
	public String listarMaterias() {
		aluno = alunoFacade.get(aluno);
		
		alunoMaterias = alunoMateriaFacade.getAllMaterias(aluno);
		// alunoMaterias = alunoMateriaFacade.getAllMaterias();
		
		// System.out.println(alunoMaterias.toString());
		
		if(alunoMaterias != null) {
			return SUCCESS;
		}else {
			return ERROR;
		}
	}
	
	public String listarMateriasAssociadas() {
		// aluno = alunoFacade.get(aluno);

		/*materias = alunoMateriaFacade.getAllMateriasByCodAlunoHaving(aluno);
		if(materias != null) {
			return SUCCESS;
		}else {
			return ERROR;
		}*/
		return SUCCESS;
	}
	
	public String cadastrar() {
		List<AcoesValidacao> acoes = alunoFacade.add(aluno);
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
		List<AcoesValidacao> acoes = alunoFacade.update(aluno);
		if(acoes.size() > 0) {
			for (AcoesValidacao acao : acoes) {
				addFieldError(acao.getCampo(), acao.getMensagem());
			}
			return INPUT;
		} else {
			return SUCCESS;
		}
	}
	
	public String associar() {
		try {
			alunoMateriaFacade.desassociar(aluno);
			
			if(materiasSelecionadas != null) {
				for(String cod_materia : materiasSelecionadas) {
					Materia materia = new Materia();
					
					materia.setCod_materia(Long.parseLong(cod_materia));
					
					alunoMateriaFacade.associar(aluno, materia);
				}
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
	}
	
	public String deletar() {
		AcoesValidacao acao = alunoFacade.delete(aluno);
		if(acao == null) {
			return SUCCESS;
		} else {
			addFieldError(acao.getCampo(), acao.getMensagem());
			return INPUT;
		}
	}
	
	public String gerarExcel() {
		excelStream = alunoFacade.gerarRelatorioExcel();
		
		if(excelStream == null) {
			return ERROR;
		}
		return SUCCESS;
	}
	
	public String gerarPdf() {
		ServletContext context = ServletActionContext.getServletContext();
		
		pdfStream = alunoFacade.gerarRelatorioPdf(context.getRealPath("/WEB-INF/jasper/alunosRelatorio.jrxml"));
		if(pdfStream == null) {
			return ERROR;
		}
		return SUCCESS;
	}
	
	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
	
	public List<Aluno> getAlunos() {
		return alunos;
	}
	
	public List<Materia> getMaterias() {
		return materias;
	}

	public void setMaterias(List<Materia> materias) {
		this.materias = materias;
	}
	
	public List<AlunoMateria> getAlunoMaterias() {
		return alunoMaterias;
	}

	public void setAlunoMaterias(List<AlunoMateria> alunoMaterias) {
		this.alunoMaterias = alunoMaterias;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}

	public String[] getMateriasSelecionadas() {
		return materiasSelecionadas;
	}

	public void setMateriasSelecionadas(String[] materiasSelecionadas) {
		this.materiasSelecionadas = materiasSelecionadas;
	}
	
	public InputStream getExcelStream() {
		return excelStream;
	}

	public void setExcelStream(InputStream excelStream) {
		this.excelStream = excelStream;
	}
	
	public ByteArrayInputStream getPdfStream() {
		return pdfStream;
	}

	public void setPdfStream(ByteArrayInputStream pdfStream) {
		this.pdfStream = pdfStream;
	}

	public List<Materia> getMateriasAssociadas() {
		return materiasAssociadas;
	}

	public void setMateriasAssociadas(List<Materia> materiasAssociadas) {
		this.materiasAssociadas = materiasAssociadas;
	}

}
