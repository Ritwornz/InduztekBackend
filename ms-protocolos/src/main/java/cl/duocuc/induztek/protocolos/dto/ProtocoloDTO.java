package cl.duocuc.induztek.protocolos.dto;

import java.time.LocalDateTime;

public class ProtocoloDTO {

	private Long id;
	private String codigoProtocolo;
	private Long clienteId;
	private Long tecnicoId;
	private String estado;
	private String resultado;
	private LocalDateTime fechaCreacion;
	private String observaciones;

	public ProtocoloDTO() {
	}

	public ProtocoloDTO(Long id, String codigoProtocolo, Long clienteId, Long tecnicoId, String estado, String resultado,
			LocalDateTime fechaCreacion, String observaciones) {
		this.id = id;
		this.codigoProtocolo = codigoProtocolo;
		this.clienteId = clienteId;
		this.tecnicoId = tecnicoId;
		this.estado = estado;
		this.resultado = resultado;
		this.fechaCreacion = fechaCreacion;
		this.observaciones = observaciones;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public LocalDateTime getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(LocalDateTime fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
}
