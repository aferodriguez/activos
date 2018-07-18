package com.prueba.activosbackend.persistence;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import com.prueba.activosbackend.exception.FechaDeBajaException;
import com.prueba.activosbackend.exception.ResourceNotFoundException;
import com.prueba.activosbackend.modelo.Activo;

/**
 *
 * implementacion de jdbcDaoSupport para aplicar a las operaciones relacionadas
 * con la gestión de activos
 *
 * 
 * @author Andres Rodriguez
 */
@Component
public class ActivosDAO extends JdbcDaoSupport {

	@Autowired
	DataSource dataSource;
	SimpleJdbcCall jdbcCall;

	@PostConstruct
	private void initialize() {
		setDataSource(dataSource);
		JdbcTemplate template = new JdbcTemplate(dataSource);
	}

	/**
	 * Selecciona todos los activos existentes en el sistema en caso de que no
	 * exista el activo se arroja una excepción personalizada que permite
	 * identificar que no se encontro el recurso solicitado
	 * 
	 * @return listaActivos contiene la lista de activos existente en el sitema
	 * @exception ResourceNotFoundException
	 *                cuando no encuentra ningun activo se arroja dicha excepcion
	 **/
	public List<Activo> sellectAll() throws ResourceNotFoundException {
		try {
			List<Activo> listaActivos = getJdbcTemplate().query(
					"SELECT idactivo,nombre,descripcion,fk_tipo"
							+ ",serial, numerointernoinventario,peso,alto,ancho,largo"
							+ ",valor_compra, fecha_compra, fecha_baja, estado+0 estado, color FROM activo ",
					new Activo());
			if (listaActivos.size() == 0) {
				throw new ResourceNotFoundException();
			}
			return listaActivos;
		} catch (ResourceNotFoundException e) {
			throw new ResourceNotFoundException();
		} catch (CannotGetJdbcConnectionException e) {
			throw new CannotGetJdbcConnectionException(e.getStackTrace().toString());
		}
	}

	/**
	 * inseta en base de datos el activo ingresado como parametro
	 * 
	 * @param activo
	 *            el activo que se desea crear.
	 * 
	 * @return ResonseEntity
	 */
	public ResponseEntity<String> insert(@RequestBody Activo activo)
			throws Exception, FechaDeBajaException, ParseException {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String dateFormatted = sdf.format(activo.getFecha_compra());
			java.util.Date datefecha_compra = (java.util.Date) sdf.parse(dateFormatted);
			
			String dateFormatted1 = sdf.format(activo.getFecha_baja());
			java.util.Date datefecha_baja;

			datefecha_baja = (java.util.Date) sdf.parse(dateFormatted1);

			if (datefecha_baja.before(datefecha_compra)) {
				throw new FechaDeBajaException();
			}
			getJdbcTemplate().update("INSERT into activo (nombre,descripcion,fk_tipo,serial,numerointernoinventario,"
					+ "peso,alto,ancho,largo,valor_compra,fecha_compra,fecha_baja,estado,color) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
					activo.getNombre(), activo.getDescripcion(), activo.getTipo(), activo.getSerial(),
					activo.getNumerointernoinventario(), activo.getPeso(), activo.getAlto(), activo.getAncho(),
					activo.getLargo(), activo.getValor_compra(), activo.getFecha_compra(), activo.getFecha_baja(),
					activo.getEstado(), activo.getColor());
			return ResponseEntity.status(HttpStatus.CREATED).build();
		} catch (DuplicateKeyException e) {
			throw new DuplicateKeyException(e.getStackTrace().toString());
		} catch (CannotGetJdbcConnectionException e) {
			throw new CannotGetJdbcConnectionException(e.getStackTrace().toString());
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException(e.getStackTrace().toString());
		} catch (FechaDeBajaException e) {
			throw new FechaDeBajaException();
		} catch (ParseException e) {
			e.printStackTrace();
			throw new Exception();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}

	}

	public List<Activo> selectByDistinctAttributes(HashMap<String, String> attributes)
			throws ResourceNotFoundException {
		try {

			MapSqlParameterSource parameters1 = new MapSqlParameterSource();
			Object[] parameters = parameters = new Object[0];
			String whereClause = " ";

			if (attributes.get("fk_tipo") != null && attributes.get("fecha_compra") != null
					&& attributes.get("serial") != null) {
				parameters = new Object[3];
				whereClause = " WHERE fk_tipo = ? AND serial = ? AND date_format(fecha_compra,'%Y%-%m%-%d') = ? ";
				parameters[0] = Integer.parseInt(attributes.get("fk_tipo"));
				parameters[1] = attributes.get("serial");
				parameters[2] = attributes.get("fecha_compra");

			}
			// Cuando vienen fk_tipo y fecha_compra
			if (attributes.get("fk_tipo") != null && attributes.get("fecha_compra") != null
					&& attributes.get("serial") == null) {
				parameters = new Object[2];
				whereClause += " WHERE fk_tipo = ? AND date_format(fecha_compra,'%Y%-%m%-%d') = ? ";
				parameters[0] = Integer.parseInt(attributes.get("fk_tipo"));
				parameters[1] = attributes.get("fecha_compra");
			}
			// Cuando vienen fk_tipo y serial
			if (attributes.get("fk_tipo") != null && attributes.get("serial") != null
					&& attributes.get("fecha_compra") == null) {
				parameters = new Object[2];
				whereClause += " WHERE fk_tipo = ? AND serial = ? ";
				parameters[0] = Integer.parseInt(attributes.get("fk_tipo"));
				parameters[1] = attributes.get("serial");
			}

			// Cuando vienen serial y fecha_compra
			if (attributes.get("serial") != null && attributes.get("fecha_compra") != null
					&& attributes.get("fk_tipo") == null) {
				parameters = new Object[2];
				whereClause += " WHERE date_format(fecha_compra,'%Y%-%m%-%d') = ? AND serial = ?";
				parameters[0] = attributes.get("fecha_compra");
				parameters[1] = attributes.get("serial");

			}

			// Cuando solo viene fecha_compra
			if (attributes.get("fecha_compra") != null && attributes.get("fk_tipo") == null
					&& attributes.get("fk_tipo") == null) {
				parameters = new Object[2];
				whereClause = " WHERE date_format(fecha_compra,'%Y%-%m%-%d') = ?";
				parameters[0] = attributes.get("fecha_compra");

			} // Cuando solo viene fk_tipo
			if (attributes.get("fk_tipo") != null && attributes.get("fecha_compra") == null
					&& attributes.get("serial") == null) {
				parameters = new Object[1];
				whereClause = " WHERE fk_tipo = ? ";
				parameters[0] = Integer.parseInt(attributes.get("fk_tipo"));
			}
			// Cuando solo viene serial
			if (attributes.get("serial") != null && attributes.get("fecha_compra") == null
					&& attributes.get("fk_tipo") == null) {
				parameters = new Object[1];
				whereClause = " WHERE serial = ? ";
				parameters[0] = attributes.get("serial");
			}
			String query = "SELECT idactivo,nombre,descripcion,fk_tipo"
					+ ",serial, numerointernoinventario,peso,alto,ancho,largo"
					+ ",valor_compra, fecha_compra, fecha_baja, estado+0 estado, color FROM activo ";

			query += whereClause;
			List<Activo> listaActivos = null;

			if (parameters.length > 0) {
				listaActivos = getJdbcTemplate().query(query, parameters, new Activo());
			} else {
				listaActivos = getJdbcTemplate().query(query, new Activo());
			}

			if (listaActivos.size() == 0) {
				throw new ResourceNotFoundException();
			}

			return listaActivos;

		} catch (ResourceNotFoundException e) {
			throw new ResourceNotFoundException();
		} catch (CannotGetJdbcConnectionException e) {
			throw new CannotGetJdbcConnectionException(e.getStackTrace().toString());
		}
	}

	/**
	 * Permite actualizar el serial interno
	 *
	 **/
	public void updateSerial(int idActivo, String serial) {
		try {
			getJdbcTemplate().update("UPDATE TABLE activo SET serial = ? WHERE idactivo = ? ", idActivo, serial);
		} catch (CannotGetJdbcConnectionException e) {
			throw new CannotGetJdbcConnectionException(e.getStackTrace().toString());
		}
	}

	public void updatefecha(int idActivo, Timestamp fecha_baja) {
		try {
			List<Activo> activosList = getJdbcTemplate().query("SELECT fecha_compra FROM activo WHERE idactivo = ? ",
					new Activo(), idActivo);
			Timestamp fecha_compra = null;

			for (Activo activo : activosList) {
				fecha_compra = activo.getFecha_compra();
			}

			getJdbcTemplate().update("UPDATE TABLE activo SET fecha_baja  = ? WHERE idactivo");
		} catch (CannotGetJdbcConnectionException e) {
			throw new CannotGetJdbcConnectionException(e.getStackTrace().toString());
		}
	}

	public void updateActivo(Activo activo) throws FechaDeBajaException {
		try {
			List<Activo> activosList = getJdbcTemplate().query("SELECT * FROM activo WHERE idactivo = ? ", new Activo(),
					activo.getIdActivo());
			Activo activoBeforeUpdate = activosList.get(0);

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String dateFormatted = sdf.format(activo.getFecha_compra());
			Date datefecha_compra = (Date) sdf.parse(dateFormatted);

			String dateFormatted1 = sdf.format(activo.getFecha_baja());
			Date datefecha_baja;

			datefecha_baja = (Date) sdf.parse(dateFormatted1);

			if (datefecha_baja.before(datefecha_compra)) {
				throw new FechaDeBajaException();
			}

			Object parameters[] = new Object[15];
			parameters[0] = activo.getNombre();
			parameters[1] = activo.getDescripcion();
			parameters[2] = activo.getTipo();
			parameters[3] = activo.getSerial();
			parameters[4] = activo.getNumerointernoinventario();
			parameters[5] = activo.getPeso();
			parameters[6] = activo.getAlto();
			parameters[7] = activo.getAncho();
			parameters[8] = activo.getLargo();
			parameters[9] = activo.getValor_compra();
			parameters[10] = activo.getFecha_compra();
			parameters[11] = activo.getFecha_baja();
			parameters[12] = activo.getEstado();
			parameters[13] = activo.getColor();
			parameters[14] = activo.getIdActivo();

			getJdbcTemplate().update("UPDATE activo SET nombre = ?, descripcion = ?"
					+ ", fk_tipo = ?, serial = ?, numerointernoinventario = ?, peso = ?, alto = ? "
					+ ", largo = ? , valor_compra  = ? , fecha_comrpa = ?, fecha_baja = ?, estado = ?, color = ? WHERE idactivo = ?",
					parameters);

		} catch (ParseException e) {
			e.printStackTrace();
		} catch (CannotGetJdbcConnectionException e) {
			throw new CannotGetJdbcConnectionException(e.getStackTrace().toString());
		}

	}

}
