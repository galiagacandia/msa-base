package bo.gob.ruat.base.infrastructure.adapter.primary.rest.exception;

import bo.gob.ruat.base.domain.exception.ExcepcionMensaje;
import io.opentracing.SpanContext;
import io.opentracing.Tracer;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Clase que define el manejador para la Excepción ConstraintViolationException
 * de Hibernate Validator.
 * <p>
 * @author Grover Aliaga Candia
 * @since 24/05/2022
 */
@Provider
public class ConstraintViolationExceptionHandler implements ExceptionMapper<ConstraintViolationException> {

	@Inject
	Tracer tracer;

	private static final Logger LOG = Logger.getLogger(ConstraintViolationExceptionHandler.class);

	@Override
	public Response toResponse(ConstraintViolationException e) {
		final SpanContext spanContext = tracer.scopeManager().activeSpan().context();

		ExcepcionMensaje vMensajes = new ExcepcionMensaje();

		vMensajes.setTraceID(spanContext.toTraceId());
		for (ConstraintViolation vViolation : e.getConstraintViolations()) {
			vMensajes.getMensajes().add(vViolation.getMessage());
		}

		LOG.error(e.getMessage(), e);

		return Response.status(Response.Status.BAD_REQUEST)
			.entity(vMensajes).build();
	}

}
