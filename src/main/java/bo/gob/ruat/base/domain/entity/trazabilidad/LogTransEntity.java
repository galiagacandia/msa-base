package bo.gob.ruat.base.domain.entity.trazabilidad;

import bo.gob.ruat.base.domain.constant.Constant;

import java.time.LocalDateTime;

/**
 * Entidad de dominio para el registro de LogTrans.
 * <p>
 * @author Grover Aliaga Candia
 * @since 24/05/2022
 */
public class LogTransEntity {
	private String microservicio = Constant.NOMBRE_MICROSERVICIO;
	private String accion;
	private Long numSecCuentaUsuario;
	private Long numSecTramite;
	private String identificadorEntidad;
	private String nombreEntidad;
	private String nombreTabla;
	private String detalle;
	private LocalDateTime fechaHoraCreacion;

	public String getMicroservicio() {
		return microservicio;
	}

	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}

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

	public String getIdentificadorEntidad() {
		return identificadorEntidad;
	}

	public void setIdentificadorEntidad(String identificadorEntidad) {
		this.identificadorEntidad = identificadorEntidad;
	}

	public String getNombreEntidad() {
		return nombreEntidad;
	}

	public void setNombreEntidad(String nombreEntidad) {
		this.nombreEntidad = nombreEntidad;
	}

	public String getNombreTabla() {
		return nombreTabla;
	}

	public void setNombreTabla(String nombreTabla) {
		this.nombreTabla = nombreTabla;
	}

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public LocalDateTime getFechaHoraCreacion() {
		return fechaHoraCreacion;
	}

	public void setFechaHoraCreacion(LocalDateTime fechaHoraCreacion) {
		this.fechaHoraCreacion = fechaHoraCreacion;
	}
}
