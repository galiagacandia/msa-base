package bo.gob.ruat.base.infrastructure.adapter.primary.rest.exception;

import bo.gob.ruat.base.domain.exception.ExcepcionMensaje;
import com.fasterxml.jackson.core.JsonParseException;
import io.opentracing.SpanContext;
import io.opentracing.Tracer;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Clase que define el manejador para la Excepción JsonParse,
 * que se da al deserializar peticiones JSON.
 * <p>
 * @author Grover Aliaga Candia
 * @since 24/05/2022
 */
@Provider
public class JsonParseExceptionHandler implements ExceptionMapper<JsonParseException> {

	@Inject
	Tracer tracer;

	private static final Logger LOG = Logger.getLogger(JsonParseExceptionHandler.class);

	@Override
	public Response toResponse(JsonParseException e) {
		final SpanContext spanContext = tracer.scopeManager().activeSpan().context();

		ExcepcionMensaje vMensajes = new ExcepcionMensaje();

		vMensajes.setTraceID(spanContext.toTraceId());
		vMensajes.getMensajes().add("Ha ocurrido un error al deserializar la petición. Contactese con soporte de datos: " + spanContext.toTraceId());

		LOG.error(e.getMessage(), e);

		return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
			.entity(vMensajes).build();
	}
}
