package br.com.ebix.escola.facade;

import java.util.List;

import br.com.ebix.escola.dao.ProfessorMateriaDao;
import br.com.ebix.escola.dao.ProfessorMateriaDaoImpl;
import br.com.ebix.escola.model.Materia;
import br.com.ebix.escola.model.Professor;
import br.com.ebix.escola.utils.ValidaStringUtil;

public class ProfessorMateriaFacadeImpl implements ProfessorMateriaFacade {

	private ProfessorMateriaDao professorMateriaDao = new ProfessorMateriaDaoImpl();
	private MateriaFacade materiaFacade = new MateriaFacadeImpl();
	
	@Override
	public void associate(Professor professor, Materia materia) {
		if (!codigoEInvalido(professor, materia)) {
			Materia materiaObtida = materiaFacade.get(materia);
			
			if(materiaObtida != null) {
				materiaObtida.setCod_materia(materia.getCod_materia());
				materiaObtida.setCod_professor(professor.getCod_professor());
				
				materiaFacade.update(materiaObtida);
			}
		}
	}
	
	@Override
	public void disassociate(Professor professor) {
		if(!codigoEInvalido(professor)) {
			Materia materiaObtida = professorMateriaDao.getMateriaByCodProfessor(professor);
			
			if(materiaObtida != null) {
				Materia materia = materiaObtida;
				materia.setCod_professor(null);
				
				materiaFacade.update(materia);
			}
		}
	}
	
	@Override
	public List<Materia> getAllAvaiable() {
		return materiaFacade.getAllAvaiable();
	}
	
	@Override
	public List<Materia> getAllMateriasFromProfessor(Professor professor) {
		return professorMateriaDao.getAllMateriasFromProfessor(professor);
	}
	
	private boolean codigoEInvalido(Professor professor, Materia materia) {
		return (ValidaStringUtil.eNuloVazioOuHaApenasEspaco(professor.getCod_professor())
				|| ValidaStringUtil.eNuloVazioOuHaApenasEspaco(materia.getCod_materia()));
	}
	
	private boolean codigoEInvalido(Professor professor) {
		return ValidaStringUtil.eNuloVazioOuHaApenasEspaco(professor.getCod_professor());
	}

	@Override
	public void desassociateMateria(Materia materia) {
		if(materia.getCod_materia()!=null) {
			materia = materiaFacade.get(materia);
			materia.setCod_professor(null);
			materiaFacade.update(materia);
			
		}
		
	}

}
