package bo.gob.ruat.base.domain.exception;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que contiene los mensajes de respuesta en caso de Excepción Lógica.
 * <p>
 * @author Grover Aliaga Candia
 * @since 24/05/2022
 */
public class ExcepcionMensaje {
	private String traceID;
	private List<String> mensajes = new ArrayList<>();

	public ExcepcionMensaje() {
		super();
	}

	public ExcepcionMensaje(String traceID, List<String> mensajes) {
		this.traceID = traceID;
		this.mensajes = mensajes;
	}

	public String getTraceID() {
		return traceID;
	}

	public void setTraceID(String traceID) {
		this.traceID = traceID;
	}

	public List<String> getMensajes() {
		return mensajes;
	}

	public void setMensajes(List<String> mensajes) {
		this.mensajes = mensajes;
	}
}
