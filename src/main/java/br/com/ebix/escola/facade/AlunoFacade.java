package br.com.ebix.escola.facade;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletContext;

import br.com.ebix.escola.enums.AcoesValidacao;
import br.com.ebix.escola.model.Aluno;
import br.com.ebix.escola.model.Materia;

public interface AlunoFacade {
	List<Materia> getAllMateriasByCodAluno(Aluno aluno);
	List<Materia> getAllMateriasByCodAlunoHaving(Aluno aluno);
	
	List<Aluno> getAll();
	List<Materia> getMateriasByCodAluno(Aluno aluno);
	Aluno get(Aluno aluno);
	List<AcoesValidacao> add(Aluno aluno);
	List<AcoesValidacao> update(Aluno aluno);
	AcoesValidacao delete(Aluno aluno);
	InputStream gerarRelatorioExcel();
	ByteArrayInputStream gerarRelatorioPdf(String path);
}
