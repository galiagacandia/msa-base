package bo.gob.ruat.base.infrastructure.adapter.primary.rest.exception;

import bo.gob.ruat.base.domain.exception.ExcepcionLogica;
import bo.gob.ruat.base.domain.exception.ExcepcionMensaje;
import io.opentracing.SpanContext;
import io.opentracing.Tracer;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Clase que define el manejador para la Excepción Lógica.
 * <p>
 * @author Grover Aliaga Candia
 * @since 24/05/2022
 */
@Provider
public class ExcepcionLogicaHandler implements ExceptionMapper<ExcepcionLogica> {

	@Inject
	Tracer tracer;

	@Override
	public Response toResponse(ExcepcionLogica excepcionLogica) {
		final SpanContext spanContext = tracer.scopeManager().activeSpan().context();

		ExcepcionMensaje vMensajes = new ExcepcionMensaje();
		vMensajes.setTraceID(spanContext.toTraceId());
		vMensajes.getMensajes().add(excepcionLogica.getMessage());

		return Response.status(Response.Status.BAD_REQUEST)
			.entity(vMensajes).build();
	}
}
