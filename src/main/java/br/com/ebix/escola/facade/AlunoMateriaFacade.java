package br.com.ebix.escola.facade;

import java.util.List;

import br.com.ebix.escola.model.Aluno;
import br.com.ebix.escola.model.AlunoMateria;
import br.com.ebix.escola.model.Materia;

public interface AlunoMateriaFacade {

	List<Materia> getAllMateriasByCodAluno(Aluno aluno);
	List<Materia> getAllMateriasByCodAlunoHaving(Aluno aluno);
	
	void associar(Aluno aluno, Materia materia);
	void desassociar(Aluno aluno);
	
	List<AlunoMateria> getAllMaterias(Aluno aluno);
}
