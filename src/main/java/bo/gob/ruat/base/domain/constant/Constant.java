package bo.gob.ruat.base.domain.constant;

import org.eclipse.microprofile.config.ConfigProvider;

/**
 * Clase que contiene las constantes del microservicio.
 * <p>
 * @author Grover Aliaga Candia
 * @since 24/05/2022
 */
public class Constant {

	public static final String NOMBRE_MICROSERVICIO = ConfigProvider
		.getConfig().getValue("quarkus.application.name", String.class);
}
