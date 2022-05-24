package bo.gob.ruat.base.domain.exception;

import java.io.Serializable;

/**
 * Clase que define la Excepción Lógica.
 * <p>
 * @author Grover Aliaga Candia
 * @since 24/05/2022
 */
public class ExcepcionLogica extends Exception implements Serializable {

	private static final long serialVersionUID = 1L;

	public ExcepcionLogica() {
	}

	public ExcepcionLogica(String message) {
		super(message);
	}

	public ExcepcionLogica(String message, Exception e) {
		super(message, e);
	}
}
