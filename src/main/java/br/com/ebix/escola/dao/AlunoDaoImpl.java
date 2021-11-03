package br.com.ebix.escola.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.ebix.escola.model.Aluno;
import br.com.ebix.escola.model.Materia;
import br.com.ebix.escola.model.Professor;
import br.com.ebix.escola.utils.ConverteDataUtil;

public class AlunoDaoImpl extends ConnectionFactory implements AlunoDao {
	@Override
	public Aluno get(Aluno aluno) {
		Aluno alunoObtido = null;
		try {
			String sql = "SELECT * FROM escola.alunos WHERE cod_aluno=?";

			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setLong(1, aluno.getCod_aluno());
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				alunoObtido = new Aluno();
				alunoObtido.setCod_aluno(rs.getLong("cod_aluno"));
				alunoObtido.setNome(rs.getString("nome"));
				alunoObtido.setCpf(rs.getString("cpf"));
				
				Calendar calendario = ConverteDataUtil.converterDateParaCalendar(rs.getDate("dataNascimento"));
				alunoObtido.setDataNascimento(calendario);

				alunoObtido.setEmail(rs.getString("email"));
				alunoObtido.setTelefoneCelular(rs.getString("telefone_celular"));
				alunoObtido.setTelefoneResidencial(rs.getString("telefone_residencial"));
			}
			
			rs.close();
			ps.close();
			return alunoObtido; // Optional.ofNullable(alunoObtido);
		} catch (SQLException e) {
			e.printStackTrace();
			return alunoObtido;
		}
		
	}

	@Override
	public List<Aluno> getAll() {
		List<Aluno> alunos = new ArrayList<Aluno>();
		try(Connection conn = getConnection()) {
			String sql = "SELECT * FROM escola.alunos ";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				Aluno aluno = new Aluno();
				aluno.setCod_aluno(rs.getLong("cod_aluno"));
				aluno.setNome(rs.getString("nome"));
				aluno.setCpf(rs.getString("cpf"));

				Calendar calendario = ConverteDataUtil.converterDateParaCalendar(rs.getDate("dataNascimento"));
				aluno.setDataNascimento(calendario);

				aluno.setEmail(rs.getString("email"));
				aluno.setTelefoneCelular(rs.getString("telefone_celular"));
				aluno.setTelefoneResidencial(rs.getString("telefone_residencial"));
				alunos.add(aluno);
			}
			
			rs.close();
			ps.close();
			conn.close();
			return alunos;
		} catch (SQLException e) {
			e.printStackTrace();
			return alunos;
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
	
	

	@Override
	public void add(Aluno aluno) {
		try (Connection conn = getConnection()) {
			String sql = "INSERT INTO escola.alunos (nome, cpf, dataNascimento, email, telefone_celular, telefone_residencial) VALUES(?, ?, ?, ?, ?, ?)";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, aluno.getNome());
			ps.setString(2, aluno.getCpf());
			ps.setDate(3, ConverteDataUtil.converterCalendarParaDatesql(aluno.getDataNascimento()));
			ps.setString(4, aluno.getEmail());
			ps.setString(5, aluno.getTelefoneCelular());
			ps.setString(6, aluno.getTelefoneResidencial());
			
			ps.execute();
			ps.close();
			conn.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Aluno aluno) {
		try (Connection conn = getConnection()) {
			String sql = "UPDATE escola.alunos SET nome=?, cpf=?, dataNascimento=?, email=?, telefone_celular=?, telefone_residencial=? WHERE cod_aluno=?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, aluno.getNome());
			ps.setString(2, aluno.getCpf());
			ps.setDate(3, ConverteDataUtil.converterCalendarParaDatesql(aluno.getDataNascimento()));
			ps.setString(4, aluno.getEmail());
			ps.setString(5, aluno.getTelefoneCelular());
			ps.setString(6, aluno.getTelefoneResidencial());
			ps.setLong(7, aluno.getCod_aluno());
			
			ps.execute();
			ps.close();
			conn.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void delete(Aluno aluno) {
		try (Connection conn = getConnection()) {
			String sql = "DELETE FROM escola.alunos WHERE cod_aluno=?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setLong(1, aluno.getCod_aluno());
			
			ps.execute();
			ps.close();
			conn.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean cpfCadastrado(Aluno aluno) {
		try {
			String sql = "SELECT * FROM escola.alunos WHERE cpf=?";
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1, aluno.getCpf());
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
