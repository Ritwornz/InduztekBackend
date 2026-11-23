package cl.duocuc.induztek.mediciones.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cl.duocuc.induztek.mediciones.dto.MedicionDTO;
import cl.duocuc.induztek.mediciones.dto.MedicionRequest;
import cl.duocuc.induztek.mediciones.exception.RecursoNoEncontradoException;
import cl.duocuc.induztek.mediciones.model.Medicion;
import cl.duocuc.induztek.mediciones.repository.MedicionRepository;

@Service
public class MedicionService {

	private final MedicionRepository medicionRepository;

	public MedicionService(MedicionRepository medicionRepository) {
		this.medicionRepository = medicionRepository;
	}

	@Transactional(readOnly = true)
	public List<MedicionDTO> listar() {
		return medicionRepository.findAll().stream().map(this::toDto).toList();
	}

	@Transactional(readOnly = true)
	public MedicionDTO buscarPorId(Long id) {
		return toDto(obtenerOFallar(id));
	}

	@Transactional(readOnly = true)
	public List<MedicionDTO> listarPorProtocolo(Long protocoloId) {
		return medicionRepository.findByProtocoloId(protocoloId).stream().map(this::toDto).toList();
	}

	@Transactional
	public MedicionDTO crear(MedicionRequest request) {
		Medicion medicion = new Medicion();
		aplicar(medicion, request);
		medicion.setFechaMedicion(LocalDateTime.now());
		return toDto(medicionRepository.save(medicion));
	}

	@Transactional
	public MedicionDTO actualizar(Long id, MedicionRequest request) {
		Medicion medicion = obtenerOFallar(id);
		aplicar(medicion, request);
		return toDto(medicionRepository.save(medicion));
	}

	@Transactional
	public void eliminar(Long id) {
		Medicion medicion = obtenerOFallar(id);
		medicionRepository.delete(medicion);
	}

	private Medicion obtenerOFallar(Long id) {
		return medicionRepository.findById(id)
				.orElseThrow(() -> new RecursoNoEncontradoException("No se encontró la medición con id " + id));
	}

	private void aplicar(Medicion medicion, MedicionRequest request) {
		medicion.setProtocoloId(request.getProtocoloId());
		medicion.setTipoPrueba(request.getTipoPrueba());
		medicion.setParametro(request.getParametro());
		medicion.setValorMedido(request.getValorMedido());
		medicion.setUnidadMedida(request.getUnidadMedida());
		medicion.setCumpleNorma(request.getCumpleNorma());
	}

	private MedicionDTO toDto(Medicion medicion) {
		return new MedicionDTO(medicion.getId(), medicion.getProtocoloId(), medicion.getTipoPrueba(),
				medicion.getParametro(), medicion.getValorMedido(), medicion.getUnidadMedida(),
				medicion.getCumpleNorma(), medicion.getFechaMedicion());
	}
}
