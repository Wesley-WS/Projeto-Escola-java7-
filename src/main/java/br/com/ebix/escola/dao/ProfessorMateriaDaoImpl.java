package br.com.ebix.escola.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.ebix.escola.model.Materia;
import br.com.ebix.escola.model.Professor;

public class ProfessorMateriaDaoImpl extends ConnectionFactory implements ProfessorMateriaDao {
	@Override
	public List<Materia> getAllMateriasFromProfessor(Professor professor) {
		List<Materia> materias = new ArrayList<Materia>();
		try {
			String sql = "SELECT * FROM escola.materias WHERE cod_professor=?";
			
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setLong(1, professor.getCod_professor());
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Materia materia = new Materia();
				materia.setCod_materia(rs.getLong("cod_materia"));
				materia.setCod_professor(rs.getLong("cod_professor"));
				materia.setNome(rs.getString("nome"));
				materia.setSigla(rs.getString("sigla"));
				materias.add(materia);
			}
			rs.close();
			ps.close();
			
			return materias;
		} catch (SQLException e) {
			e.printStackTrace();
			return materias;
		}
	}

	@Override
	public Materia getMateriaByCodProfessor(Professor professor) {
		Materia materiaObtida = null;
		try {
			String sql = "SELECT * FROM escola.materias WHERE cod_professor=?";
			
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setLong(1, professor.getCod_professor());
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				materiaObtida = new Materia();
				materiaObtida.setCod_materia(rs.getLong("cod_materia"));
				materiaObtida.setCod_professor(rs.getLong("cod_professor"));
				materiaObtida.setNome(rs.getString("nome"));
				materiaObtida.setSigla(rs.getString("sigla"));
			}
			ps.close();
			rs.close();
			
			return materiaObtida;
		} catch (SQLException e) {
			e.printStackTrace();
			return materiaObtida;
		}
	}
}
