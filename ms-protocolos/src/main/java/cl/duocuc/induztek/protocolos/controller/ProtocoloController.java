package cl.duocuc.induztek.protocolos.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import cl.duocuc.induztek.protocolos.dto.ProtocoloDTO;
import cl.duocuc.induztek.protocolos.dto.ProtocoloRequest;
import cl.duocuc.induztek.protocolos.service.ProtocoloService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/protocolos")
public class ProtocoloController {

	private final ProtocoloService protocoloService;

	public ProtocoloController(ProtocoloService protocoloService) {
		this.protocoloService = protocoloService;
	}

	@GetMapping
	public ResponseEntity<List<ProtocoloDTO>> listar() {
		return ResponseEntity.ok(protocoloService.listar());
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProtocoloDTO> buscarPorId(@PathVariable Long id) {
		return ResponseEntity.ok(protocoloService.buscarPorId(id));
	}

	@GetMapping("/codigo/{codigo}")
	public ResponseEntity<ProtocoloDTO> buscarPorCodigo(@PathVariable String codigo) {
		return ResponseEntity.ok(protocoloService.buscarPorCodigo(codigo));
	}

	@GetMapping("/cliente/{clienteId}")
	public ResponseEntity<List<ProtocoloDTO>> listarPorCliente(@PathVariable Long clienteId) {
		return ResponseEntity.ok(protocoloService.listarPorCliente(clienteId));
	}

	@PostMapping
	public ResponseEntity<ProtocoloDTO> crear(@Valid @RequestBody ProtocoloRequest request) {
		return ResponseEntity.status(HttpStatus.CREATED).body(protocoloService.crear(request));
	}

	@PutMapping("/{id}")
	public ResponseEntity<ProtocoloDTO> actualizar(@PathVariable Long id, @Valid @RequestBody ProtocoloRequest request) {
		return ResponseEntity.ok(protocoloService.actualizar(id, request));
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void eliminar(@PathVariable Long id) {
		protocoloService.eliminar(id);
	}
}
