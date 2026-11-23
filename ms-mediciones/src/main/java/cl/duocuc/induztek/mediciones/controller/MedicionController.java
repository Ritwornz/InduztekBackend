package cl.duocuc.induztek.mediciones.controller;

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

import cl.duocuc.induztek.mediciones.dto.MedicionDTO;
import cl.duocuc.induztek.mediciones.dto.MedicionRequest;
import cl.duocuc.induztek.mediciones.service.MedicionService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/mediciones")
public class MedicionController {

	private final MedicionService medicionService;

	public MedicionController(MedicionService medicionService) {
		this.medicionService = medicionService;
	}

	@GetMapping
	public ResponseEntity<List<MedicionDTO>> listar() {
		return ResponseEntity.ok(medicionService.listar());
	}

	@GetMapping("/{id}")
	public ResponseEntity<MedicionDTO> buscarPorId(@PathVariable Long id) {
		return ResponseEntity.ok(medicionService.buscarPorId(id));
	}

	@GetMapping("/protocolo/{protocoloId}")
	public ResponseEntity<List<MedicionDTO>> listarPorProtocolo(@PathVariable Long protocoloId) {
		return ResponseEntity.ok(medicionService.listarPorProtocolo(protocoloId));
	}

	@PostMapping
	public ResponseEntity<MedicionDTO> crear(@Valid @RequestBody MedicionRequest request) {
		return ResponseEntity.status(HttpStatus.CREATED).body(medicionService.crear(request));
	}

	@PutMapping("/{id}")
	public ResponseEntity<MedicionDTO> actualizar(@PathVariable Long id, @Valid @RequestBody MedicionRequest request) {
		return ResponseEntity.ok(medicionService.actualizar(id, request));
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void eliminar(@PathVariable Long id) {
		medicionService.eliminar(id);
	}
}
