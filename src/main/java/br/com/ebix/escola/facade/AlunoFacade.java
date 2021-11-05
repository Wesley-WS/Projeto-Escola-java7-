package br.com.ebix.escola.facade;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletContext;

import br.com.ebix.escola.enums.AcoesValidacao;
import br.com.ebix.escola.model.Aluno;
import br.com.ebix.escola.model.Materia;

public interface AlunoFacade {
	List<Aluno> getAll();
	Aluno get(Aluno aluno);
	List<Materia> getAllMateriasByCodAluno(Aluno aluno);
	List<AcoesValidacao> add(Aluno aluno);
	List<AcoesValidacao> update(Aluno aluno);
	AcoesValidacao delete(Aluno aluno);
	List<Materia> getAllMateriasByCodAlunoHaving(Aluno aluno);
	InputStream gerarRelatorioExcel();
	ByteArrayInputStream gerarRelatorioPdf(String path);
}
