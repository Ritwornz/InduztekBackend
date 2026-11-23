package cl.duocuc.induztek.mediciones.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class MedicionRequest {

	@NotNull(message = "El protocoloId es obligatorio")
	private Long protocoloId;

	@NotBlank(message = "El tipo de prueba es obligatorio")
	@Pattern(regexp = "AISLAMIENTO_IP|RESISTENCIA_TTR",
			message = "El tipo de prueba debe ser AISLAMIENTO_IP o RESISTENCIA_TTR")
	private String tipoPrueba;

	@NotBlank(message = "El parámetro es obligatorio")
	@Size(max = 100, message = "El parámetro no puede superar 100 caracteres")
	private String parametro;

	@NotNull(message = "El valor medido es obligatorio")
	private Double valorMedido;

	@NotBlank(message = "La unidad de medida es obligatoria")
	@Size(max = 20, message = "La unidad de medida no puede superar 20 caracteres")
	private String unidadMedida;

	@NotNull(message = "El indicador cumpleNorma es obligatorio")
	private Boolean cumpleNorma;

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
}
