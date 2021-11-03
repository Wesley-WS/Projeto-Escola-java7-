package br.com.ebix.escola.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.ebix.escola.model.Professor;
import br.com.ebix.escola.utils.ConverteDataUtil;

public class ProfessorDaoImpl extends ConnectionFactory implements ProfessorDao {
	
	public Professor get(Professor professor) {
		Professor professorObtido = null;
		try {
			String sql = "SELECT * FROM escola.professores WHERE cod_professor=?";

			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setLong(1, professor.getCod_professor());
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				professorObtido = new Professor();
				professorObtido.setCod_professor(rs.getLong("cod_professor"));
				professorObtido.setNome(rs.getString("nome"));
				professorObtido.setCpf(rs.getString("cpf"));
				
				Calendar calendario = ConverteDataUtil.converterDateParaCalendar(rs.getDate("dataNascimento"));
				professorObtido.setDataNascimento(calendario);
			
				professorObtido.setEmail(rs.getString("email"));
				professorObtido.setTelefoneCelular(rs.getString("telefone_celular"));
				professorObtido.setTelefoneResidencial(rs.getString("telefone_residencial"));
			}
			rs.close();
			ps.close();
			
			return professorObtido;
		} catch (SQLException e) {
			e.printStackTrace();
			return professorObtido;
		}
	}
	
	@Override
	public List<Professor> getAll() {
		List<Professor> professores = new ArrayList<Professor>();
		try {
			String sql = "SELECT * FROM escola.professores ";
			
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				Professor professor = new Professor();
				professor.setCod_professor(rs.getLong("cod_professor"));
				professor.setNome(rs.getString("nome"));
				professor.setCpf(rs.getString("cpf"));

				Calendar calendario = ConverteDataUtil.converterDateParaCalendar(rs.getDate("dataNascimento"));
				professor.setDataNascimento(calendario);

				professor.setEmail(rs.getString("email"));
				professor.setTelefoneCelular(rs.getString("telefone_celular"));
				professor.setTelefoneResidencial(rs.getString("telefone_residencial"));
				professores.add(professor);
			}
			rs.close();
			ps.close();
			
			return professores;
		} catch (SQLException e) {
			e.printStackTrace();
			return professores;
		}
	}


	@Override
	public void add(Professor professor) {
		try {
			String sql = "INSERT INTO escola.professores (nome, cpf, email, dataNascimento, telefone_celular, telefone_residencial) VALUES(?, ?, ?, ?, ?, ?)";
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1, professor.getNome());
			ps.setString(2, professor.getCpf());
			ps.setString(3, professor.getEmail());
			ps.setDate(4, ConverteDataUtil.converterCalendarParaDatesql(professor.getDataNascimento()));
			ps.setString(5, professor.getTelefoneCelular());
			ps.setString(6, professor.getTelefoneResidencial());
			ps.execute();
			ps.close();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}


	@Override
	public void update(Professor professor) {
		try {
			String sql = "UPDATE escola.professores SET nome = ?, cpf= ?, email= ?, dataNascimento= ?, telefone_celular= ?, telefone_residencial= ? WHERE cod_professor = ?";
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1, professor.getNome());
			ps.setString(2, professor.getCpf());
			ps.setString(3, professor.getEmail());
			ps.setDate(4, ConverteDataUtil.converterCalendarParaDatesql(professor.getDataNascimento()));
			ps.setString(5, professor.getTelefoneCelular());
			ps.setString(6, professor.getTelefoneResidencial());
			ps.setLong(7, professor.getCod_professor());
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	@Override
	public void delete(Professor professor) {
		try {
			String sql = "DELETE FROM escola.professores WHERE cod_professor=?";
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setLong(1, professor.getCod_professor());
			ps.execute();
			ps.close();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public boolean cpfCadastrado(Professor professor) {
		try {
			String sql = "SELECT * FROM escola.professores WHERE cpf=?";
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1, professor.getCpf());
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				return true;
			}else {
				return false;
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			return true;
		}
	}

	
}
