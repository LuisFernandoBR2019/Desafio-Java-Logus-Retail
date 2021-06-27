package br.com.logusretail.database.entitiesDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import br.com.logusretail.database.connections.ConnectionsFactory;
import br.com.logusretail.database.entitiesInterfaceDao.IMedicoDao;
import br.com.logusretail.model.entities.Medico;

@Repository
public class MedicoDao implements IMedicoDao {
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;

	@Override
	public List<Medico> readAll(Map<String, Object> criteria) {
		connection = null;
		preparedStatement = null;
		resultSet = null;

		List<Medico> listaMedico = new ArrayList<Medico>();

		try {
			connection = ConnectionsFactory.getConnection();

			String sql = "SELECT * FROM medico;";

			preparedStatement = connection.prepareStatement(sql);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Medico medico = new Medico();
				medico.setId(resultSet.getLong("id_medico"));
				medico.setCrm(resultSet.getString("crm_medico"));
				medico.setNome(resultSet.getString("nome_medico"));
				medico.setEspecialidade(resultSet.getString("especialidade_medico"));
				medico.setIdade(resultSet.getInt("idade_medico"));

				listaMedico.add(medico);
			}

		} catch (Exception e) {
			System.out.println("Erro: " + e);
		} finally {
			ConnectionsFactory.close(resultSet, preparedStatement, connection);
		}
		// TODO Auto-generated method stub
		return listaMedico;
	}

	@Override
	public Map<String, String> create(Medico entity) {
		Map<String, String> status = new LinkedHashMap<>();
		connection = null;
		preparedStatement = null;

		try {
			connection = ConnectionsFactory.getConnection();

			connection.setAutoCommit(false);

			String sql = "INSERT INTO medico (nome_medico, especialidade_medico, crm_medico, idade_medico) VALUES (?, ?, ?, ?);";
			int count = 0;
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(++count, entity.getNome());
			preparedStatement.setString(++count, entity.getEspecialidade());
			preparedStatement.setString(++count, entity.getCrm());
			preparedStatement.setInt(++count, entity.getIdade());

			preparedStatement.execute();
			connection.commit();
			status.put("criado", "sucesso");
			return status;

		} catch (Exception e) {
			System.out.println("Erro: " + e);
			try {
				connection.rollback();

			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			status.put("criado", "erro");
			return status;
		} finally {
			ConnectionsFactory.close(preparedStatement, connection);
		}
	}

	@Override
	public Medico readById(long id) {
		connection = null;
		preparedStatement = null;
		resultSet = null;

		Medico medico = null;

		try {
			connection = ConnectionsFactory.getConnection();

			String sql = "SELECT * FROM medico where id_medico = ?;";

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, id);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				medico = new Medico();
				medico.setId(resultSet.getLong("id_medico"));
				medico.setCrm(resultSet.getString("crm_medico"));
				medico.setNome(resultSet.getString("nome_medico"));
				medico.setEspecialidade(resultSet.getString("especialidade_medico"));
				medico.setIdade(resultSet.getInt("idade_medico"));

			}

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			ConnectionsFactory.close(resultSet, preparedStatement, connection);
		}

		return medico;
	}

	@Override
	public boolean update(Medico entity) {
		connection = null;
		preparedStatement = null;

		try {

			connection = ConnectionsFactory.getConnection();

			String sql = "UPDATE medico SET nome_medico = ?, especialidade_medico = ?, crm_medico = ?, idade_medico = ? WHERE id_medico = ?";

			connection.setAutoCommit(false);

			int count = 0;
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(++count, entity.getNome());
			preparedStatement.setString(++count, entity.getEspecialidade());
			preparedStatement.setString(++count, entity.getCrm());
			preparedStatement.setInt(++count, entity.getIdade());
			preparedStatement.setLong(++count, entity.getId());

			preparedStatement.execute();
			connection.commit();

			return true;

		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return false;

		} finally {
			ConnectionsFactory.close(preparedStatement, connection);
		}
	}

	@Override
	public boolean deleteById(Long id) {
		connection = null;
		preparedStatement = null;

		try {
			connection = ConnectionsFactory.getConnection();

			String sql = "DELETE medico where id_medico = ?;";

			connection.setAutoCommit(false);

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, id);

			preparedStatement.execute();
			connection.commit();
			return true;
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return false;
		} finally {
			ConnectionsFactory.close(preparedStatement, connection);
		}
	}

}
