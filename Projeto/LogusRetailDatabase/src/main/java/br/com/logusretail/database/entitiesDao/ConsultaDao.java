package br.com.logusretail.database.entitiesDao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import br.com.logusretail.database.connections.ConnectionsFactory;
import br.com.logusretail.database.entitiesInterfaceDao.IConsultaDao;
import br.com.logusretail.model.entities.Consulta;
import br.com.logusretail.model.entities.Consultorio;
import br.com.logusretail.model.entities.Medico;
import br.com.logusretail.model.entities.Paciente;

@Repository
public class ConsultaDao implements IConsultaDao {
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;
	public static final String CRITERIA_DATA = "1";
	public static final String CRITERIA_MEDICO_ID = "2";

	@Override
	public List<Consulta> readAll(Map<String, Object> criteria) {
		connection = null;
		preparedStatement = null;
		resultSet = null;

		List<Consulta> listaConsulta = new ArrayList<Consulta>();

		try {
			connection = ConnectionsFactory.getConnection();

			String sql = "SELECT * FROM consulta AS C\r\n"
					+ "JOIN consultorio AS CO ON CO.id_consultorio = C.id_consultorio_fk\r\n"
					+ "JOIN paciente AS P ON P.id_paciente = C.id_paciente_fk \r\n"
					+ "JOIN consulta_medico AS CM on CM.id_consulta_fk = C.id_consulta\r\n"
					+ "JOIN medico AS M ON M.id_medico = CM.id_medico_fk where 1 = 1";

			Long criteriaIdMedico = (Long) criteria.get(CRITERIA_MEDICO_ID);
			if (criteriaIdMedico != null && criteriaIdMedico > 0) {
				sql += " AND M.id_medico = " + criteriaIdMedico + " ";
			}

			Date dataCriteria = (Date) criteria.get(CRITERIA_DATA);

			if (dataCriteria != null) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String dataFormatada = sdf.format(dataCriteria);
				String dataFormatadaInicial = dataFormatada + "00:00:00";
				String dataFormatadaFinal = dataFormatada + "23:59:00";

				sql += " AND C.datahora_consulta BETWEEN " + dataFormatadaInicial + " AND " + dataFormatadaFinal;

			}
			sql += " order by datahora_consulta desc;";

			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Consulta consulta = new Consulta();
				consulta.setId(resultSet.getLong("id_consulta"));
				consulta.setDataHora(resultSet.getTimestamp("datahora_consulta"));

				Paciente paciente = new Paciente();
				paciente.setId(resultSet.getLong("id_paciente_fk"));
				paciente.setNome(resultSet.getString("nome_paciente"));

				consulta.setPaciente(paciente);

				Consultorio consultorio = new Consultorio();
				consultorio.setId(resultSet.getLong("id_consultorio_fk"));
				consultorio.setNumeroConsultorio(resultSet.getInt("numero_consultorio"));

				consulta.setConsultorio(consultorio);

				sql = "SELECT M.id_medico, M.crm_medico, M.nome_medico, M.idade_medico, M.especialidade_medico FROM consulta AS C\r\n"
						+ "JOIN consulta_medico AS CM on CM.id_consulta_fk = C.id_consulta\r\n"
						+ "JOIN medico AS M ON M.id_medico = CM.id_medico_fk  WHERE C.id_consulta = ?;";

				PreparedStatement preparedStatement2 = null;
				ResultSet resultSet2 = null;

				preparedStatement2 = connection.prepareStatement(sql);
				preparedStatement2.setLong(1, consulta.getId());
				resultSet2 = preparedStatement2.executeQuery();
				List<Medico> listaMedico = new ArrayList<Medico>();

				while (resultSet2.next()) {
					Medico medico = new Medico();
					medico.setId(resultSet2.getLong("id_medico"));
					medico.setCrm(resultSet2.getString("crm_medico"));
					medico.setNome(resultSet2.getString("nome_medico"));
					medico.setEspecialidade(resultSet2.getString("especialidade_medico"));
					medico.setIdade(resultSet2.getInt("idade_medico"));
					listaMedico.add(medico);
				}

				consulta.setListaMedico(listaMedico);

				listaConsulta.add(consulta);
			}

		} catch (Exception e) {
			System.out.println("Erro: " + e);
		} finally {
			ConnectionsFactory.close(resultSet, preparedStatement, connection);
		}

		return listaConsulta;
	}

	@Override
	public Map<String, String> create(Consulta entity) {
		Map<String, String> status = new LinkedHashMap<>();
		connection = null;
		preparedStatement = null;
		resultSet = null;

		try {
			connection = ConnectionsFactory.getConnection();

			connection.setAutoCommit(false);

			String sql = "INSERT INTO consulta (id_paciente_fk, id_consultorio_fk, datahora_consulta) VALUES (?, ?, ?) RETURNING id_consulta;";
			int count = 0;
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setLong(++count, entity.getPaciente().getId());
			preparedStatement.setLong(++count, entity.getConsultorio().getId());
			preparedStatement.setTimestamp(++count, entity.getDataHora());
			resultSet = preparedStatement.executeQuery();
			Long idCriado = null;
			if (resultSet.next()) {
				idCriado = resultSet.getLong(1);
			}

			for (Medico medico : entity.getListaMedico()) {
				if (medico.getId() != null) {
					count = 0;
					preparedStatement = null;
					sql = "INSERT INTO consulta_medico (id_consulta_fk, id_medico_fk) VALUES (?, ?)";
					preparedStatement = connection.prepareStatement(sql);
					preparedStatement.setLong(++count, idCriado);
					preparedStatement.setLong(++count, medico.getId());
					preparedStatement.execute();
				}

			}

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
	public Consulta readById(long id) {
		connection = null;
		preparedStatement = null;
		resultSet = null;

		Consulta consulta = null;

		try {
			connection = ConnectionsFactory.getConnection();

			String sql = "SELECT * FROM consulta AS C\r\n"
					+ "JOIN consultorio AS CO ON CO.id_consultorio = C.id_consultorio_fk\r\n"
					+ "JOIN paciente AS P ON P.id_paciente = C.id_paciente_fk WHERE C.id_consulta = ?;";

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, id);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				consulta = new Consulta();
				consulta.setDataHora(resultSet.getTimestamp("datahora_consulta"));

				Paciente paciente = new Paciente();
				paciente.setId(resultSet.getLong("id_paciente_fk"));
				paciente.setNome(resultSet.getString("nome_paciente"));

				consulta.setPaciente(paciente);

				Consultorio consultorio = new Consultorio();
				consultorio.setId(resultSet.getLong("id_consultorio_fk"));
				consultorio.setNumeroConsultorio(resultSet.getInt("numero_consultorio"));

				consulta.setConsultorio(consultorio);

			}

			sql = "SELECT M.id_medico, M.crm_medico, M.nome_medico, M.idade_medico, M.especialidade_medico FROM consulta AS C\r\n"
					+ "JOIN consulta_medico AS CM on CM.id_consulta_fk = C.id_consulta\r\n"
					+ "JOIN medico AS M ON M.id_medico = CM.id_medico_fk  WHERE C.id_consulta = ?;";
			preparedStatement = null;
			resultSet = null;

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, id);
			resultSet = preparedStatement.executeQuery();
			List<Medico> listaMedico = new ArrayList<Medico>();

			while (resultSet.next()) {
				Medico medico = new Medico();
				medico.setId(resultSet.getLong("id_medico"));
				medico.setCrm(resultSet.getString("crm_medico"));
				medico.setNome(resultSet.getString("nome_medico"));
				medico.setEspecialidade(resultSet.getString("especialidade_medico"));
				medico.setIdade(resultSet.getInt("idade_medico"));
				listaMedico.add(medico);
			}

			consulta.setListaMedico(listaMedico);

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			ConnectionsFactory.close(resultSet, preparedStatement, connection);
		}

		return consulta;
	}

	@Override
	public boolean update(Consulta entity) {
		connection = null;
		preparedStatement = null;

		try {

			connection = ConnectionsFactory.getConnection();

			String sql = "UPDATE consulta SET id_paciente_fk = ?, id_consultorio_fk = ?, datahora_consulta = NOW() WHERE id_consulta = ?;";

			connection.setAutoCommit(false);

			int count = 0;
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setLong(++count, entity.getPaciente().getId());
			preparedStatement.setLong(++count, entity.getConsultorio().getId());
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

			String sql = "DELETE consulta where id_consulta = ?;";

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
