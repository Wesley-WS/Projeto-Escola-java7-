package br.com.ebix.escola.facade;

import java.util.ArrayList;
import java.util.List;

import org.apache.jasper.tagplugins.jstl.core.ForEach;

import br.com.ebix.escola.dao.AlunoMateriaDao;
import br.com.ebix.escola.dao.AlunoMateriaDaoImpl;
import br.com.ebix.escola.model.Aluno;
import br.com.ebix.escola.model.AlunoMateria;
import br.com.ebix.escola.model.Materia;
import br.com.ebix.escola.utils.ValidaStringUtil;

public class AlunoMateriaFacadeImpl implements AlunoMateriaFacade{
	
	private AlunoMateriaDao alunoMateriaDao = new AlunoMateriaDaoImpl();
	
	private MateriaFacade materiaFacade = new MateriaFacadeImpl();
	private AlunoFacade alunoFacade = new AlunoFacadeImpl();
	
	public List<Materia> getAllMateriasByCodAluno(Aluno aluno){
		List<Materia> materiasAll = new ArrayList<Materia>();
		materiasAll = materiaFacade.getAll();
		
		if(!codigoEInvalidoAluno(aluno)) {
			List<Long> cod_materias = alunoMateriaDao.getAllCodMatByCod(aluno);
			
			if(cod_materias.size() > 0) {
				for(Long cod_materia : cod_materias) {
					for(Materia materia : materiasAll) {
						if(materia.getCod_materia()==cod_materia) {
							materiasAll.remove(materia);
							break;
						}
					}
				}
			}
		}
		return materiasAll;
	}
	
	public List<Materia> getAllMateriasByCodAlunoHaving(Aluno aluno){
		List<Long> cod_materias = alunoMateriaDao.getAllCodMatByCod(aluno);
		List<Materia> materiasAll = new ArrayList<Materia>();
		materiasAll = materiaFacade.getAll();
		List<Materia> materiasAssociadas = new ArrayList<Materia>();
		if(cod_materias.size() > 0) {
			for(Long cod_materia : cod_materias) {
				for(Materia materia : materiasAll) {
					if(materia.getCod_materia()==cod_materia) {
						materiasAssociadas.add(materia);
						break;
					}
				}
			}
			return materiasAssociadas;
		} else {
			return materiasAssociadas;
		}
	}
	
	
	@Override
	public void associar(Aluno aluno, Materia materia) {
		if(!codigoEInvalidoAlunoMateria(aluno, materia)) {
			
			Materia materiaObtida = materiaFacade.get(materia);
			
			if(materiaObtida != null) {
				alunoMateriaDao.associar(aluno, materia);
			}
		}
	}
	
	@Override
	public void desassociar(Aluno aluno) {
		if(!codigoEInvalidoAluno(aluno)) {
			alunoMateriaDao.desassociar(aluno);
		}
	}

	
	
	public List<AlunoMateria> getAllMaterias(Aluno aluno){
		List<AlunoMateria> alunoMateriasEncontradas = new ArrayList<AlunoMateria>();
		
		List<Materia> materias = materiaFacade.getAll();
		List<AlunoMateria> alunoMaterias = alunoMateriaDao.getAll(); //.getAllCodMatByCod(aluno);
		
		
		for (Materia materia : materias) {
			AlunoMateria relacao = new AlunoMateria();
			relacao.setCod_aluno(aluno.getCod_aluno());
			relacao.setCod_materia(materia.getCod_materia());
			
			for (AlunoMateria alunoMateria : alunoMaterias) {
				if(alunoMateria.getCod_materia() == relacao.getCod_materia()) {
					System.out.println("Contem");
					break;
				} else {
					
					System.out.println("Não contem");
				}
			}
			
//			for (AlunoMateria alunoMateria : alunoMaterias) {
//				AlunoMateria relacao = new AlunoMateria();
//				relacao.setCod_aluno(aluno.getCod_aluno());
//				relacao.setCod_materia(materia.getCod_materia());
//				
//				
//				
////				if(materia.getCod_materia() == alunoMateria.getCod_materia() && 
////						aluno.getCod_aluno() == alunoMateria.getCod_materia()) {
////					alunoMateria.setNome(materia.getNome());
////					alunoMateria.setSigla(materia.getSigla());
////					
////					alunoMateriasEncontradas.add(alunoMateria);
////					break;
////				}
//			}
		}
		
		return alunoMateriasEncontradas;
		
//		for (AlunoMateria alunoMateria : alunoMaterias) {
//			for (Materia materia : materias) {
//				if(aluno.getCod_aluno() == alunoMateria.getCod_aluno()) {
//					alunoMateria.setNome(materia.getNome());
//					alunoMateria.setSigla(materia.getSigla());
//					
//					alunoMateriasEncontradas.add(alunoMateria);
//					break;
//				} else if(!alunoMaterias.contains(materia)) {
//					
//				}
//			}
//		}
		
//		for (Long cod_materia : cod_materias) {	
//			AlunoMateria alunoMateria = new AlunoMateria();
//			alunoMateria.setCod_materia(cod_materia);
//			alunoMateria.setCod_aluno(aluno.getCod_aluno());
//			
//			for (Materia materia : materias) {
//				if(materia.getCod_materia() == cod_materia) {
//					alunoMateria.setNome(materia.getNome());
//					alunoMateria.setSigla(materia.getSigla());
//					
//					break;
//				}
//			}
//			
//			alunoMaterias.add(alunoMateria);
//			
//		}
		
		/*List<Materia> materias = alunoMateriaDao.getAllCodMatByCod(aluno);
		List<Aluno> alunos = alunoFacade.getAll();
		
		for (Aluno alunoL : alunos) {
			for (Materia materiaL : materias) {
				if(alunoL.getCod_aluno() == aluno.getCod_aluno()) {
					
					AlunoMateria alunoMateria = new AlunoMateria();
					alunoMateria.setCod_aluno(aluno.getCod_aluno());
					alunoMateria.setCod_materia(materiaL);
					
				}
			}
		}*/
		
//		return alunoMateriaDao.getAllMaterias();
	}
	
	public boolean codigoEInvalidoAlunoMateria(Aluno aluno, Materia materia) {
		return (ValidaStringUtil.eNuloVazioOuHaApenasEspaco(aluno.getCod_aluno()) || ValidaStringUtil.eNuloVazioOuHaApenasEspaco(materia.getCod_materia()));
	}
	
	public boolean codigoEInvalidoAluno(Aluno aluno) {
		return (ValidaStringUtil.eNuloVazioOuHaApenasEspaco(aluno.getCod_aluno()));
	}
}
