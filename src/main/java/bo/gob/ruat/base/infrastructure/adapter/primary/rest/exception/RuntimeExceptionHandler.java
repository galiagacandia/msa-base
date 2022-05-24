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
 * Clase que define el manejador para la Excepción RunTime,
 * que se da por un problema en tiempo de ejecución.
 * <p>
 * @author Grover Aliaga Candia
 * @since 24/05/2022
 */
@Provider
public class RuntimeExceptionHandler implements ExceptionMapper<RuntimeException> {

	@Inject
	Tracer tracer;

	private static final Logger LOG = Logger.getLogger(RuntimeExceptionHandler.class);

	@Override
	public Response toResponse(RuntimeException exception) {
		final SpanContext spanContext = tracer.scopeManager().activeSpan().context();

		ExcepcionMensaje vMensajes = new ExcepcionMensaje();

		vMensajes.setTraceID(spanContext.toTraceId());
		vMensajes.getMensajes().add("Ha ocurrido un error inesperado. Contactese con soporte de datos: " + spanContext.toTraceId());

		LOG.error(exception.getMessage(), exception);

		return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
			.entity(vMensajes).build();
	}
}
