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
import br.com.logusretail.database.entitiesInterfaceDao.IPacienteDao;
import br.com.logusretail.model.entities.Paciente;

@Repository
public class PacienteDao implements IPacienteDao {
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;

	@Override
	public List<Paciente> readAll(Map<String, Object> criteria) {
		connection = null;
		preparedStatement = null;
		resultSet = null;

		List<Paciente> listaPaciente = new ArrayList<Paciente>();

		try {
			connection = ConnectionsFactory.getConnection();

			String sql = "SELECT * FROM paciente;";

			preparedStatement = connection.prepareStatement(sql);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Paciente paciente = new Paciente();
				paciente.setId(resultSet.getLong("id_paciente"));
				paciente.setNome(resultSet.getString("nome_paciente"));
				listaPaciente.add(paciente);
			}

		} catch (Exception e) {
			System.out.println("Erro: " + e);
		} finally {
			ConnectionsFactory.close(resultSet, preparedStatement, connection);
		}
		// TODO Auto-generated method stub
		return listaPaciente;
	}

	@Override
	public Map<String, String> create(Paciente entity) {
		Map<String, String> status = new LinkedHashMap<>();
		connection = null;
		preparedStatement = null;

		try {
			connection = ConnectionsFactory.getConnection();

			connection.setAutoCommit(false);

			String sql = "INSERT INTO paciente (nome_paciente) VALUES (?);";
			int count = 0;
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(++count, entity.getNome());
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
	public Paciente readById(long id) {
		connection = null;
		preparedStatement = null;
		resultSet = null;

		Paciente paciente = null;

		try {
			connection = ConnectionsFactory.getConnection();

			String sql = "SELECT * FROM paciente where id_paciente = ?;";

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, id);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				paciente = new Paciente();
				paciente.setId(resultSet.getLong("id_paciente"));
				paciente.setNome(resultSet.getString("nome_paciente"));
			}

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			ConnectionsFactory.close(resultSet, preparedStatement, connection);
		}

		return paciente;
	}

	@Override
	public boolean update(Paciente entity) {
		connection = null;
		preparedStatement = null;

		try {

			connection = ConnectionsFactory.getConnection();

			String sql = "UPDATE paciente SET nome_paciente = ? WHERE id_paciente = ?;";

			connection.setAutoCommit(false);

			int count = 0;
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(++count, entity.getNome());
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

			String sql = "DELETE paciente where id_paciente = ?;";

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
