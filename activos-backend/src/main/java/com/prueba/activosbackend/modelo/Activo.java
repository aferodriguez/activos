package com.prueba.activosbackend.modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.RowMapper;

import com.fasterxml.jackson.annotation.JsonProperty;


@Configuration
public class Activo implements RowMapper<Activo> {

	private int idactivo;
	private String nombre;
	private String descripcion;
	private String fk_tipo;
	private String serial;
	private String numerointernoinventario;
	private float peso;
	private float alto; 
	private float ancho;
	private float largo;
	private double valor_compra;
	private Timestamp fecha_compra;
	private Timestamp fecha_baja;
	private int estado;
	private String color;

	public Activo() {
		super();
	}

	public Activo(@JsonProperty("idactivo") int idactivo, @JsonProperty("nombre") String nombre,
			@JsonProperty("descripcion") String descripcion, @JsonProperty("fk_tipo") String fk_tipo,
			@JsonProperty("serial") String serial,
			@JsonProperty("numerointernoinventario") String numerointernoinventario,
			@JsonProperty("peso") float peso, @JsonProperty("alto") float alto,
			@JsonProperty("ancho") float ancho, @JsonProperty("largo") float largo,
			@JsonProperty("valor_compra") double valor_compra, @JsonProperty("fecha_compra") Timestamp fecha_compra,
			@JsonProperty("fecha_baja") Timestamp fecha_baja, @JsonProperty("estado") int estado,
			@JsonProperty("color") String color) {
		super();
		this.idactivo = idactivo;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.fk_tipo = fk_tipo;
		this.serial = serial;
		this.numerointernoinventario = numerointernoinventario;
		this.peso = peso;
		this.alto = alto;
		this.ancho = ancho;
		this.largo = largo;
		this.valor_compra = valor_compra;
		this.fecha_compra = fecha_compra;
		this.fecha_baja = fecha_baja;
		this.estado = estado;
		this.color = color;

	}

	public int getIdActivo() {
		return idactivo;
	}

	public void setId(int idactivo) {
		this.idactivo = idactivo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getTipo() {
		return fk_tipo;
	}

	public void setTipo(String tipo) {
		this.fk_tipo = tipo;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public String getNumerointernoinventario() {
		return numerointernoinventario;
	}

	public void setNumerointernoinventario(String numerointernoinventario) {
		this.numerointernoinventario = numerointernoinventario;
	}

	public float getPeso() {
		return peso;
	}

	public void setPeso(float peso) {
		this.peso = peso;
	}

	public float getAlto() {
		return alto;
	}

	public void setAlto(float alto) {
		this.alto = alto;
	}

	public float getAncho() {
		return ancho;
	}

	public void setAncho(float ancho) {
		this.ancho = ancho;
	}

	public float getLargo() {
		return largo;
	}

	public void setLargo(float largo) {
		this.largo = largo;
	}

	public double getValor_compra() {
		return valor_compra;
	}

	public void setValor_compra(double valor_compra) {
		this.valor_compra = valor_compra;
	}

	public Timestamp getFecha_compra() {
		return fecha_compra;
	}

	public void setFecha_compra(Timestamp fecha_compra) {
		this.fecha_compra = fecha_compra;
	}

	public Timestamp getFecha_baja() {
		return fecha_baja;
	}

	public void setFecha_baja(Timestamp fecha_baja) {
		this.fecha_baja = fecha_baja;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public Activo mapRow(ResultSet rs, int arg1) throws SQLException {
		return new Activo(rs.getInt("idactivo"), rs.getString("nombre"), rs.getString("descripcion"), rs.getString("fk_tipo"),
				rs.getString("serial"), rs.getString("numerointernoinventario"), rs.getFloat("peso"),
				rs.getFloat("alto"), rs.getFloat("ancho"), rs.getFloat("largo"), rs.getDouble("valor_compra"),
				rs.getTimestamp("fecha_compra"), rs.getTimestamp("fecha_baja"), rs.getInt("estado"),
				rs.getString("Color"));
	}

	@Override
	public String toString() {
		return "Activo [idactivo=" + idactivo + ", nombre=" + nombre + ", descripcion=" + descripcion + ", fk_tipo="
				+ fk_tipo + ", serial=" + serial + ", numerointernoinventario=" + numerointernoinventario + ", peso="
				+ peso + ", alto=" + alto + ", ancho=" + ancho + ", largo=" + largo + ", valor_compra=" + valor_compra
				+ ", fecha_compra=" + fecha_compra + ", fecha_baja=" + fecha_baja + ", estado=" + estado + ", color="
				+ color + "]";
	}
	

}
