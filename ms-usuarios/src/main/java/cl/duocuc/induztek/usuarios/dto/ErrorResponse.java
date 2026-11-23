package cl.duocuc.induztek.usuarios.dto;

import java.time.LocalDateTime;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {

	private LocalDateTime timestamp;
	private int status;
	private String error;
	private String mensaje;
	private String ruta;
	private Map<String, String> detalles;

	public ErrorResponse() {
	}

	public ErrorResponse(LocalDateTime timestamp, int status, String error, String mensaje, String ruta,
			Map<String, String> detalles) {
		this.timestamp = timestamp;
		this.status = status;
		this.error = error;
		this.mensaje = mensaje;
		this.ruta = ruta;
		this.detalles = detalles;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getRuta() {
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	public Map<String, String> getDetalles() {
		return detalles;
	}

	public void setDetalles(Map<String, String> detalles) {
		this.detalles = detalles;
	}
}
