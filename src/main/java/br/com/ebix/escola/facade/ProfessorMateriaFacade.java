package br.com.ebix.escola.facade;

import java.util.List;

import br.com.ebix.escola.model.Materia;
import br.com.ebix.escola.model.Professor;

public interface ProfessorMateriaFacade {
	public List<Materia> getAllAvaiable();
	public List<Materia> getAllMateriasFromProfessor(Professor professor);
	public List<Materia> getAll();
	public void associate(Professor professor, Materia materia);
	public void disassociate(Professor professor);
	void disassociateProfessorFromMateriaByCodProfessor(Professor professor);
}
