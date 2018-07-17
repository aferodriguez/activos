package com.prueba.activosbackend.base.rest;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.activosbackend.exception.ResourceNotFoundException;
import com.prueba.activosbackend.modelo.Activo;
import com.prueba.activosbackend.persistence.ActivosDAO;

@RestController(value = "/Activos")
public class ActivosController {

	Logger logger = LoggerFactory.getLogger(ActivosController.class);

	@Autowired
	private ActivosDAO activosDAO;

	/**
	 * 
	 * permite obtener la lista de todos los activos.
	 * 
	 * @return ResponseEntity que contiene la lista de activos existentes y en caso
	 *         de ser exitoso retorna 200, en caso de no encontrar objetos retorna
	 *         404 y un mensaje diciendo que no se encontraron activos;
	 **/

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Activo>> get() {
		try {
			return new ResponseEntity<List<Activo>>(activosDAO.sellectAll(), HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Permite realizar la busqueda de activos segun el parametro ingresado por el
	 * usuario.
	 * 
	 * @param tipo
	 *            tipo de activo del que se quiere obtener la lista de activos
	 * @param fecha_compra
	 *            fecha de compra del activo
	 * @param serial
	 *            serial del activo que se desea buscar
	 * 
	 * @return ResponseEntity que contiene el la lista de activos y el codigo 200
	 *         cuando existen activos. en caso de que no se encuentren documentos en
	 *         el sistema se retorna un mensaje y el codigo 404
	 **/
	@RequestMapping(value = "/Activos", method = RequestMethod.GET)
	public ResponseEntity get(@RequestParam(value = "tipo", required = false) String tipo,
			@RequestParam(value = "fecha_compra", required = false) String fecha_compra,
			@RequestParam(value = "serial", required = false) String serial) {
			System.out.println(" Received in controller --->  ");
			System.out.println(serial +" ---- "+tipo+" ----- "+fecha_compra);
		try {
			HashMap<String, String> parameters = new HashMap<String, String>();
			if (tipo != null) {
				parameters.put("fk_tipo", tipo);
			}
			if (fecha_compra != null) {
				parameters.put("fecha_compra", fecha_compra);
			}
			if (serial != null) {
				parameters.put("serial", serial);
			}

			logger.info("se encontraron activos.");
			return new ResponseEntity<List<Activo>>(activosDAO.selectByDistinctAttributes(parameters), HttpStatus.OK);
		} catch (CannotGetJdbcConnectionException e) {
			logger.error("Ocurrio un error al intentar conectarse a la base de datos.", e);
			return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT)
					.body("Ocurrio un error al intentar conectarse a la base de datos.");
		} catch (ResourceNotFoundException e) {
			logger.error("No se han encontrado Activos en el Sistema ", e);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se han encontrado Activos en el Sistema.");
		}

	}

	/****
	 * @param activo
	 *            el activo que se desea crear en el
	 * @return ResponseEntity con el codigo 201 en caso de que se haya creado el
	 *         activo exitosamente, en caso de error de conexión a base de datos se
	 *         retorna el codigo 504, en caso de que exista un activo con el numero
	 *         de inventario se retorna el codigo 406, en caso de otros errores se
	 *         retorna 500 internal Server error
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<String> post(@RequestBody Activo activo) {
		try {
			activosDAO.insert(activo);
			logger.info("Se creo exitosamente el objeto: " + activo.toString());
			return ResponseEntity.status(HttpStatus.CREATED).body("Activo creado exitosamente.");
		} catch (CannotGetJdbcConnectionException e) {
			logger.error("Ocurrio un error al intentar conectarse a la base de datos", e);
			return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body("Error al consultar la base de datos.");
		} catch (DuplicateKeyException e) {
			logger.error("El número de inventario: " + activo.getNumerointernoinventario() + " ya fue utilizado.", e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("El número de inventario: " + activo.getNumerointernoinventario() + " ya fue utilizado.");
		} catch (DataIntegrityViolationException e) {
			logger.error("Hace falta diligenciar un campo del activo.", e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Hace falta diligenciar un campo del activo.");

		} catch (Exception e) {
			logger.error("ocurrio un error en el servidor.", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

}
