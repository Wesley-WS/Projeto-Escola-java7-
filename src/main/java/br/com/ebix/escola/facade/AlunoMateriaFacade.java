package br.com.ebix.escola.facade;

import java.util.List;

import br.com.ebix.escola.model.Aluno;
import br.com.ebix.escola.model.Materia;

public interface AlunoMateriaFacade {

	void associar(Aluno aluno, Materia materia);
	List<Materia> getAllMateriasByCodAluno(Aluno aluno);
	List<Materia> getAllMateriasByCodAlunoHaving(Aluno aluno);
	List<Materia> getAllMaterias();
	void desassociar(Aluno aluno, Materia materia);
}
