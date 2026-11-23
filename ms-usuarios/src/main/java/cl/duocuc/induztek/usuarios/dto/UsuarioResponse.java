package cl.duocuc.induztek.usuarios.dto;

public class UsuarioResponse {

	private Long id;
	private String nombre;
	private String correo;
	private String rol;
	private Boolean activo;
	private String empresa;

	public UsuarioResponse() {
	}

	public UsuarioResponse(Long id, String nombre, String correo, String rol, Boolean activo, String empresa) {
		this.id = id;
		this.nombre = nombre;
		this.correo = correo;
		this.rol = rol;
		this.activo = activo;
		this.empresa = empresa;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
}
