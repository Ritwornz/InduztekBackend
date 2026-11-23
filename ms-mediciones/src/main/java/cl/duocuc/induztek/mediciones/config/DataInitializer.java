package cl.duocuc.induztek.mediciones.config;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import cl.duocuc.induztek.mediciones.model.Medicion;
import cl.duocuc.induztek.mediciones.repository.MedicionRepository;

@Component
public class DataInitializer implements CommandLineRunner {

	private static final Long PROTOCOLO_PT_2026_0001 = 1L;
	private static final Long PROTOCOLO_PT_2026_0002 = 2L;
	private static final Long PROTOCOLO_PT_2026_0004 = 4L;
	private static final Long PROTOCOLO_PT_2026_0005 = 5L;

	private final MedicionRepository medicionRepository;

	public DataInitializer(MedicionRepository medicionRepository) {
		this.medicionRepository = medicionRepository;
	}

	@Override
	public void run(String... args) {
		if (medicionRepository.count() > 0) {
			return;
		}

		medicionRepository.saveAll(List.of(
				crear(PROTOCOLO_PT_2026_0001, "AISLAMIENTO_IP", "Fase A-Tierra", 1850.0, "MΩ", true),
				crear(PROTOCOLO_PT_2026_0001, "AISLAMIENTO_IP", "Fase B-Tierra", 1720.0, "MΩ", true),
				crear(PROTOCOLO_PT_2026_0002, "RESISTENCIA_TTR", "H1-H2", 0.85, "Ω", true),
				crear(PROTOCOLO_PT_2026_0002, "AISLAMIENTO_IP", "Fase A-Tierra", 2100.0, "MΩ", true),
				crear(PROTOCOLO_PT_2026_0004, "RESISTENCIA_TTR", "H1-H3", 0.92, "Ω", true),
				crear(PROTOCOLO_PT_2026_0004, "AISLAMIENTO_IP", "Fase B-Tierra", 1950.0, "MΩ", true),
				crear(PROTOCOLO_PT_2026_0005, "AISLAMIENTO_IP", "Fase C-Tierra", 960.0, "MΩ", true),
				crear(PROTOCOLO_PT_2026_0005, "RESISTENCIA_TTR", "H1-H2", 1.02, "Ω", false)));
	}

	private Medicion crear(Long protocoloId, String tipoPrueba, String parametro, Double valor, String unidad,
			Boolean cumpleNorma) {
		Medicion medicion = new Medicion();
		medicion.setProtocoloId(protocoloId);
		medicion.setTipoPrueba(tipoPrueba);
		medicion.setParametro(parametro);
		medicion.setValorMedido(valor);
		medicion.setUnidadMedida(unidad);
		medicion.setCumpleNorma(cumpleNorma);
		medicion.setFechaMedicion(LocalDateTime.now());
		return medicion;
	}
}
