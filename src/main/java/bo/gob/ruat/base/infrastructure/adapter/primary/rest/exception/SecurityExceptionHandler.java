package bo.gob.ruat.base.infrastructure.adapter.primary.rest.exception;

import bo.gob.ruat.base.domain.exception.ExcepcionMensaje;
import io.opentracing.SpanContext;
import io.opentracing.Tracer;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Clase que define el manejador para la Excepción de Seguridad.
 * <p>
 * @author Grover Aliaga Candia
 * @since 24/05/2022
 */
@Provider
public class SecurityExceptionHandler implements ExceptionMapper<SecurityException> {

	@Inject
	Tracer tracer;

	private static final Logger LOG = Logger.getLogger(SecurityExceptionHandler.class);

	@Override
	public Response toResponse(SecurityException exception) {
		final SpanContext spanContext = tracer.scopeManager().activeSpan().context();

		ExcepcionMensaje vMensajes = new ExcepcionMensaje();

		vMensajes.setTraceID(spanContext.toTraceId());
		vMensajes.getMensajes().add("Acceso no autorizado. Contactese con soporte de datos: " + spanContext.toTraceId());

		LOG.error(exception.getMessage(), exception);

		return Response.status(Response.Status.UNAUTHORIZED)
			.entity(vMensajes).build();
	}
}
