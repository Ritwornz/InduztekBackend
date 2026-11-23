package cl.duocuc.induztek.protocolos.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cl.duocuc.induztek.protocolos.dto.ProtocoloDTO;
import cl.duocuc.induztek.protocolos.dto.ProtocoloRequest;
import cl.duocuc.induztek.protocolos.exception.RecursoNoEncontradoException;
import cl.duocuc.induztek.protocolos.exception.ReglaNegocioException;
import cl.duocuc.induztek.protocolos.model.Protocolo;
import cl.duocuc.induztek.protocolos.repository.ProtocoloRepository;

@Service
public class ProtocoloService {

	private final ProtocoloRepository protocoloRepository;

	public ProtocoloService(ProtocoloRepository protocoloRepository) {
		this.protocoloRepository = protocoloRepository;
	}

	@Transactional(readOnly = true)
	public List<ProtocoloDTO> listar() {
		return protocoloRepository.findAll().stream().map(this::toDto).toList();
	}

	@Transactional(readOnly = true)
	public ProtocoloDTO buscarPorId(Long id) {
		return toDto(obtenerOFallar(id));
	}

	@Transactional(readOnly = true)
	public ProtocoloDTO buscarPorCodigo(String codigo) {
		return protocoloRepository.findByCodigoProtocolo(codigo)
				.map(this::toDto)
				.orElseThrow(() -> new RecursoNoEncontradoException(
						"No se encontró el protocolo con código " + codigo));
	}

	@Transactional(readOnly = true)
	public List<ProtocoloDTO> listarPorCliente(Long clienteId) {
		return protocoloRepository.findByClienteId(clienteId).stream().map(this::toDto).toList();
	}

	@Transactional
	public ProtocoloDTO crear(ProtocoloRequest request) {
		if (protocoloRepository.existsByCodigoProtocolo(request.getCodigoProtocolo())) {
			throw new ReglaNegocioException("Ya existe un protocolo con el código " + request.getCodigoProtocolo());
		}
		Protocolo protocolo = new Protocolo();
		aplicar(protocolo, request);
		protocolo.setFechaCreacion(LocalDateTime.now());
		return toDto(protocoloRepository.save(protocolo));
	}

	@Transactional
	public ProtocoloDTO actualizar(Long id, ProtocoloRequest request) {
		Protocolo protocolo = obtenerOFallar(id);
		if (protocoloRepository.existsByCodigoProtocoloAndIdNot(request.getCodigoProtocolo(), id)) {
			throw new ReglaNegocioException("Ya existe un protocolo con el código " + request.getCodigoProtocolo());
		}
		aplicar(protocolo, request);
		return toDto(protocoloRepository.save(protocolo));
	}

	@Transactional
	public void eliminar(Long id) {
		Protocolo protocolo = obtenerOFallar(id);
		protocoloRepository.delete(protocolo);
	}

	private Protocolo obtenerOFallar(Long id) {
		return protocoloRepository.findById(id)
				.orElseThrow(() -> new RecursoNoEncontradoException("No se encontró el protocolo con id " + id));
	}

	private void aplicar(Protocolo protocolo, ProtocoloRequest request) {
		protocolo.setCodigoProtocolo(request.getCodigoProtocolo());
		protocolo.setClienteId(request.getClienteId());
		protocolo.setTecnicoId(request.getTecnicoId());
		protocolo.setEstado(request.getEstado());
		protocolo.setResultado(request.getResultado());
		protocolo.setObservaciones(request.getObservaciones());
	}

	private ProtocoloDTO toDto(Protocolo protocolo) {
		return new ProtocoloDTO(protocolo.getId(), protocolo.getCodigoProtocolo(), protocolo.getClienteId(),
				protocolo.getTecnicoId(), protocolo.getEstado(), protocolo.getResultado(),
				protocolo.getFechaCreacion(), protocolo.getObservaciones());
	}
}
