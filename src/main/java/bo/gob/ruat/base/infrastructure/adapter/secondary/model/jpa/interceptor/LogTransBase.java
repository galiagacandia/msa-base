package bo.gob.ruat.base.infrastructure.adapter.secondary.model.jpa.interceptor;

/**
 * Clase abstracta de la que deben extender las Entidades JPA que se
 * requiere registrar trazabilidad.
 * <p>
 * @author Grover Aliaga Candia
 * @since 24/05/2022
 */
public abstract class LogTransBase {
	private Long numSecCuentaUsuario;
	private Long numSecTramite;

	/** Getters y Setters */

	public Long getNumSecCuentaUsuario() {
		return numSecCuentaUsuario;
	}

	public void setNumSecCuentaUsuario(Long numSecCuentaUsuario) {
		this.numSecCuentaUsuario = numSecCuentaUsuario;
	}

	public Long getNumSecTramite() {
		return numSecTramite;
	}

	public void setNumSecTramite(Long numSecTramite) {
		this.numSecTramite = numSecTramite;
	}
}
