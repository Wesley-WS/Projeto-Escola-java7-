package br.com.ebix.escola.dao;

import java.util.List;

import br.com.ebix.escola.model.Aluno;
import br.com.ebix.escola.model.AlunoMateria;
import br.com.ebix.escola.model.Materia;

public interface AlunoMateriaDao {
//	List<AlunoMateria> selecionaMateriasAluno(long codigo);
	void associar(Aluno aluno, Materia materia);

	List<Long> getAllCodMatByCod(Aluno aluno);

	void desassociar(Aluno aluno, Materia materia);
}
