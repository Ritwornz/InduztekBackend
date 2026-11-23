package cl.duocuc.induztek.usuarios.service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cl.duocuc.induztek.usuarios.dto.UsuarioRequest;
import cl.duocuc.induztek.usuarios.dto.UsuarioResponse;
import cl.duocuc.induztek.usuarios.dto.UsuarioUpdateRequest;
import cl.duocuc.induztek.usuarios.exception.RecursoNoEncontradoException;
import cl.duocuc.induztek.usuarios.exception.ReglaNegocioException;
import cl.duocuc.induztek.usuarios.model.Usuario;
import cl.duocuc.induztek.usuarios.repository.UsuarioRepository;

@Service
public class UsuarioService {

	private static final String ROL_CLIENTE = "CLIENTE";

	private final UsuarioRepository usuarioRepository;

	public UsuarioService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	@Transactional(readOnly = true)
	public List<UsuarioResponse> listar() {
		return usuarioRepository.findAll().stream().map(this::toResponse).toList();
	}

	@Transactional(readOnly = true)
	public UsuarioResponse buscarPorId(Long id) {
		return toResponse(obtenerOFallar(id));
	}

	@Transactional(readOnly = true)
	public UsuarioResponse buscarPorCorreo(String correo) {
		return usuarioRepository.findByCorreo(correo)
				.map(this::toResponse)
				.orElseThrow(() -> new RecursoNoEncontradoException(
						"No se encontró un usuario con el correo " + correo));
	}

	@Transactional
	public UsuarioResponse crear(UsuarioRequest request) {
		if (usuarioRepository.existsByCorreo(request.getCorreo())) {
			throw new ReglaNegocioException("Ya existe un usuario con el correo " + request.getCorreo());
		}
		validarEmpresaCliente(request.getRol(), request.getEmpresa());

		Usuario usuario = new Usuario();
		usuario.setNombre(request.getNombre());
		usuario.setCorreo(request.getCorreo().toLowerCase());
		usuario.setPasswordHash(generarSha256(request.getPassword()));
		usuario.setRol(request.getRol());
		usuario.setActivo(request.getActivo() == null ? Boolean.TRUE : request.getActivo());
		usuario.setEmpresa(request.getEmpresa());
		return toResponse(usuarioRepository.save(usuario));
	}

	@Transactional
	public UsuarioResponse actualizar(Long id, UsuarioUpdateRequest request) {
		Usuario usuario = obtenerOFallar(id);
		if (usuarioRepository.existsByCorreoAndIdNot(request.getCorreo(), id)) {
			throw new ReglaNegocioException("Ya existe un usuario con el correo " + request.getCorreo());
		}
		validarEmpresaCliente(request.getRol(), request.getEmpresa());

		usuario.setNombre(request.getNombre());
		usuario.setCorreo(request.getCorreo().toLowerCase());
		usuario.setRol(request.getRol());
		usuario.setActivo(request.getActivo() == null ? usuario.getActivo() : request.getActivo());
		usuario.setEmpresa(request.getEmpresa());
		if (request.getPassword() != null && !request.getPassword().isBlank()) {
			usuario.setPasswordHash(generarSha256(request.getPassword()));
		}
		return toResponse(usuarioRepository.save(usuario));
	}

	@Transactional
	public void eliminar(Long id) {
		Usuario usuario = obtenerOFallar(id);
		usuarioRepository.delete(usuario);
	}

	private Usuario obtenerOFallar(Long id) {
		return usuarioRepository.findById(id)
				.orElseThrow(() -> new RecursoNoEncontradoException("No se encontró el usuario con id " + id));
	}

	private void validarEmpresaCliente(String rol, String empresa) {
		if (ROL_CLIENTE.equals(rol) && (empresa == null || empresa.isBlank())) {
			throw new ReglaNegocioException("La empresa es obligatoria cuando el rol es CLIENTE");
		}
	}

	private UsuarioResponse toResponse(Usuario usuario) {
		return new UsuarioResponse(usuario.getId(), usuario.getNombre(), usuario.getCorreo(), usuario.getRol(),
				usuario.getActivo(), usuario.getEmpresa());
	}

	private String generarSha256(String password) {
		if (password == null || password.isBlank()) {
			return "";
		}
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			byte[] hash = md.digest(password.getBytes(StandardCharsets.UTF_8));
			StringBuilder hex = new StringBuilder();
			for (byte b : hash) {
				hex.append(String.format("%02x", b));
			}
			return hex.toString();
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("Error al generar hash SHA-256", e);
		}
	}
}
