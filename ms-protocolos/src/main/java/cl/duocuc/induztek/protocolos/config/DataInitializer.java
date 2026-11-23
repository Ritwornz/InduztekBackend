package cl.duocuc.induztek.protocolos.config;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import cl.duocuc.induztek.protocolos.model.Protocolo;
import cl.duocuc.induztek.protocolos.repository.ProtocoloRepository;

@Component
public class DataInitializer implements CommandLineRunner {

	private static final Long CLIENTE_ENEL_ID = 3L;
	private static final Long TECNICO_CARLOS_ID = 2L;

	private final ProtocoloRepository protocoloRepository;

	public DataInitializer(ProtocoloRepository protocoloRepository) {
		this.protocoloRepository = protocoloRepository;
	}

	@Override
	public void run(String... args) {
		if (protocoloRepository.count() > 0) {
			return;
		}

		protocoloRepository.saveAll(List.of(
				crear("PT-2026-0001", "REGISTRADO_TERRENO", "PENDIENTE",
						"Protocolo de aislamiento en subestación Enel."),
				crear("PT-2026-0002", "FORMALIZADO", "APROBADO",
						"Protocolo formalizado y aprobado por el cliente."),
				crear("PT-2026-0003", "BORRADOR", "PENDIENTE",
						"Borrador pendiente de registro en terreno."),
				crear("PT-2026-0004", "REGISTRADO_TERRENO", "PENDIENTE",
						"Mediciones de resistencia TTR tomadas en terreno."),
				crear("PT-2026-0005", "FORMALIZADO", "PENDIENTE",
						"Protocolo formalizado a la espera de revisión final.")));
	}

	private Protocolo crear(String codigo, String estado, String resultado, String observaciones) {
		Protocolo protocolo = new Protocolo();
		protocolo.setCodigoProtocolo(codigo);
		protocolo.setClienteId(CLIENTE_ENEL_ID);
		protocolo.setTecnicoId(TECNICO_CARLOS_ID);
		protocolo.setEstado(estado);
		protocolo.setResultado(resultado);
		protocolo.setFechaCreacion(LocalDateTime.now());
		protocolo.setObservaciones(observaciones);
		return protocolo;
	}
}
