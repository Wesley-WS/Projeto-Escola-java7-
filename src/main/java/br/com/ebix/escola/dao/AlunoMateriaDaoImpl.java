package br.com.ebix.escola.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.ebix.escola.model.Aluno;
import br.com.ebix.escola.model.AlunoMateria;
import br.com.ebix.escola.model.Materia;

public class AlunoMateriaDaoImpl  extends ConnectionFactory implements AlunoMateriaDao{

	public void associar(Aluno aluno, Materia materia) { //TODO: Revisar isso aqui
		try {
			String sql = "INSERT INTO escola.relalunomat (cod_aluno, cod_materia) VALUES(?, ?)";
			
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setLong(1, aluno.getCod_aluno());
			ps.setLong(2, materia.getCod_materia());
			ps.execute();
			ps.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void desassociar(Aluno aluno, Materia materia) { //TODO: Revisar isso aqui
		try {
			String sql = "DELETE from escola.relalunomat WHERE cod_aluno=? and cod_materia=?;;";
			
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setLong(1, aluno.getCod_aluno());
			ps.setLong(2, materia.getCod_materia());
			ps.execute();
			ps.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public List<Long> getAllCodMatByCod(Aluno aluno){
		List<Long> cod_materias = new ArrayList<Long>();
		try {
			String sql = "SELECT * FROM escola.relalunomat WHERE cod_aluno=?";
			
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setLong(1, aluno.getCod_aluno());
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				cod_materias.add(rs.getLong("cod_materia"));
			}
			return cod_materias;
		} catch (SQLException e) {
			e.printStackTrace();
			
			return cod_materias;
		}
	}
//	public List<AlunoMateria> selecionaMateriasAluno(long codigo) {
//		List<AlunoMateria> relacoes = new ArrayList<AlunoMateria>();
//		try {
//			String sql = "SELECT * FROM escola.Aluno_materia_professor WHERE cod_aluno=?";
//			PreparedStatement ps = getConnection().prepareStatement(sql);
//			ps.setLong(1, codigo);
//			ResultSet rs = ps.executeQuery();
//			
//			while (rs.next()) {
//				AlunoMateria relacaoObtida = new AlunoMateria();
//				relacaoObtida.setCod_aluno(rs.getLong("cod_aluno"));
//				relacaoObtida.setCod_materia(rs.getLong("cod_materia"));
//				relacoes.add(relacaoObtida);
//			}
//			
//			rs.close();
//			ps.close();
//			return relacoes;
//		} catch (SQLException e) {
//			e.printStackTrace();
//			return relacoes;
//		}
//		
//		
//	}

}
