package cl.duocuc.induztek.mediciones.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "mediciones")
public class Medicion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private Long protocoloId;

	@Column(nullable = false, length = 40)
	private String tipoPrueba;

	@Column(nullable = false, length = 100)
	private String parametro;

	@Column(nullable = false)
	private Double valorMedido;

	@Column(nullable = false, length = 20)
	private String unidadMedida;

	@Column(nullable = false)
	private Boolean cumpleNorma;

	@Column(nullable = false)
	private LocalDateTime fechaMedicion;

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
