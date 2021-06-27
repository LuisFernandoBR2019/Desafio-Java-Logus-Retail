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
import br.com.logusretail.database.entitiesInterfaceDao.IConsultorioDao;
import br.com.logusretail.model.entities.Consultorio;

@Repository
public class ConsultorioDao implements IConsultorioDao {
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;

	@Override
	public List<Consultorio> readAll(Map<String, Object> criteria) {
		connection = null;
		preparedStatement = null;
		resultSet = null;

		List<Consultorio> listaConsultorio = new ArrayList<Consultorio>();

		try {
			connection = ConnectionsFactory.getConnection();

			String sql = "SELECT * FROM consultorio;";

			preparedStatement = connection.prepareStatement(sql);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Consultorio consultorio = new Consultorio();
				consultorio.setId(resultSet.getLong("id_consultorio"));
				consultorio.setNumeroConsultorio(resultSet.getInt("numero_consultorio"));
				listaConsultorio.add(consultorio);
			}

		} catch (Exception e) {
			System.out.println("Erro: " + e);
		} finally {
			ConnectionsFactory.close(resultSet, preparedStatement, connection);
		}
		// TODO Auto-generated method stub
		return listaConsultorio;
	}

	@Override
	public Map<String, String> create(Consultorio entity) {
		Map<String, String> status = new LinkedHashMap<>();
		connection = null;
		preparedStatement = null;

		try {
			connection = ConnectionsFactory.getConnection();

			connection.setAutoCommit(false);

			String sql = "INSERT INTO consultorio (numero_consultorio) VALUES (?);";
			int count = 0;
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setInt(++count, entity.getNumeroConsultorio());
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
	public Consultorio readById(long id) {
		connection = null;
		preparedStatement = null;
		resultSet = null;

		Consultorio consultorio = null;

		try {
			connection = ConnectionsFactory.getConnection();

			String sql = "SELECT * FROM consultorio where id_consultorio = ?;";

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, id);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				consultorio = new Consultorio();
				consultorio.setId(resultSet.getLong("id_consultorio"));
				consultorio.setNumeroConsultorio(resultSet.getInt("numero_consultorio"));
			}

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			ConnectionsFactory.close(resultSet, preparedStatement, connection);
		}

		return consultorio;
	}

	@Override
	public boolean update(Consultorio entity) {
		connection = null;
		preparedStatement = null;

		try {

			connection = ConnectionsFactory.getConnection();

			String sql = "UPDATE consultorio SET numero_consultorio = ? WHERE id_consultorio = ?;";

			connection.setAutoCommit(false);

			int count = 0;
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setInt(++count, entity.getNumeroConsultorio());
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

			String sql = "DELETE consultorio where id_consultorio = ?;";

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
