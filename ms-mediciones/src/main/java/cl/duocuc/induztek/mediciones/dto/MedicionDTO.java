package cl.duocuc.induztek.mediciones.dto;

import java.time.LocalDateTime;

public class MedicionDTO {

	private Long id;
	private Long protocoloId;
	private String tipoPrueba;
	private String parametro;
	private Double valorMedido;
	private String unidadMedida;
	private Boolean cumpleNorma;
	private LocalDateTime fechaMedicion;

	public MedicionDTO() {
	}

	public MedicionDTO(Long id, Long protocoloId, String tipoPrueba, String parametro, Double valorMedido,
			String unidadMedida, Boolean cumpleNorma, LocalDateTime fechaMedicion) {
		this.id = id;
		this.protocoloId = protocoloId;
		this.tipoPrueba = tipoPrueba;
		this.parametro = parametro;
		this.valorMedido = valorMedido;
		this.unidadMedida = unidadMedida;
		this.cumpleNorma = cumpleNorma;
		this.fechaMedicion = fechaMedicion;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getProtocoloId() {
		return protocoloId;
	}

	public void setProtocoloId(Long protocoloId) {
		this.protocoloId = protocoloId;
	}

	public String getTipoPrueba() {
		return tipoPrueba;
	}

	public void setTipoPrueba(String tipoPrueba) {
		this.tipoPrueba = tipoPrueba;
	}

	public String getParametro() {
		return parametro;
	}

	public void setParametro(String parametro) {
		this.parametro = parametro;
	}

	public Double getValorMedido() {
		return valorMedido;
	}

	public void setValorMedido(Double valorMedido) {
		this.valorMedido = valorMedido;
	}

	public String getUnidadMedida() {
		return unidadMedida;
	}

	public void setUnidadMedida(String unidadMedida) {
		this.unidadMedida = unidadMedida;
	}

	public Boolean getCumpleNorma() {
		return cumpleNorma;
	}

	public void setCumpleNorma(Boolean cumpleNorma) {
		this.cumpleNorma = cumpleNorma;
	}

	public LocalDateTime getFechaMedicion() {
		return fechaMedicion;
	}

	public void setFechaMedicion(LocalDateTime fechaMedicion) {
		this.fechaMedicion = fechaMedicion;
	}
}
