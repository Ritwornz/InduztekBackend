package cl.duocuc.induztek.protocolos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class ProtocoloRequest {

	@NotBlank(message = "El código de protocolo es obligatorio")
	@Size(max = 40, message = "El código de protocolo no puede superar 40 caracteres")
	private String codigoProtocolo;

	@NotNull(message = "El clienteId es obligatorio")
	private Long clienteId;

	@NotNull(message = "El tecnicoId es obligatorio")
	private Long tecnicoId;

	@NotBlank(message = "El estado es obligatorio")
	@Pattern(regexp = "BORRADOR|REGISTRADO_TERRENO|FORMALIZADO",
			message = "El estado debe ser BORRADOR, REGISTRADO_TERRENO o FORMALIZADO")
	private String estado;

	@NotBlank(message = "El resultado es obligatorio")
	@Pattern(regexp = "PENDIENTE|APROBADO|RECHAZADO",
			message = "El resultado debe ser PENDIENTE, APROBADO o RECHAZADO")
	private String resultado;

	@Size(max = 500, message = "Las observaciones no pueden superar 500 caracteres")
	private String observaciones;

	public String getCodigoProtocolo() {
		return codigoProtocolo;
	}

	public void setCodigoProtocolo(String codigoProtocolo) {
		this.codigoProtocolo = codigoProtocolo;
	}

	public Long getClienteId() {
		return clienteId;
	}

	public void setClienteId(Long clienteId) {
		this.clienteId = clienteId;
	}

	public Long getTecnicoId() {
		return tecnicoId;
	}

	public void setTecnicoId(Long tecnicoId) {
		this.tecnicoId = tecnicoId;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
}
