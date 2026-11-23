package cl.duocuc.induztek.usuarios.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import cl.duocuc.induztek.usuarios.model.Usuario;
import cl.duocuc.induztek.usuarios.repository.UsuarioRepository;

@Component
public class DataInitializer implements CommandLineRunner {

	private final UsuarioRepository usuarioRepository;

	public DataInitializer(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	public void run(String... args) {
		if (usuarioRepository.count() > 0) {
			return;
		}

		// Hash SHA-256 de "Admin123", "Tecnico123" y "Cliente123"
		Usuario admin = new Usuario();
		admin.setNombre("Administrador Induztek");
		admin.setCorreo("admin@induztek.cl");
		admin.setPasswordHash("240be518fabd2724ddb6f04eeb1da5967448d7e831c08c8fa822809f74c720a9");
		admin.setRol("ADMINISTRADOR");
		admin.setActivo(true);

		Usuario tecnico = new Usuario();
		tecnico.setNombre("Carlos Técnico");
		tecnico.setCorreo("carlos.tecnico@induztek.cl");
		tecnico.setPasswordHash("240be518fabd2724ddb6f04eeb1da5967448d7e831c08c8fa822809f74c720a9");
		tecnico.setRol("TECNICO");
		tecnico.setActivo(true);

		Usuario cliente = new Usuario();
		cliente.setNombre("Contacto Enel");
		cliente.setCorreo("contacto@enel.cl");
		cliente.setPasswordHash("240be518fabd2724ddb6f04eeb1da5967448d7e831c08c8fa822809f74c720a9");
		cliente.setRol("CLIENTE");
		cliente.setActivo(true);
		cliente.setEmpresa("Enel Generación");

		usuarioRepository.save(admin);
		usuarioRepository.save(tecnico);
		usuarioRepository.save(cliente);
	}
}
