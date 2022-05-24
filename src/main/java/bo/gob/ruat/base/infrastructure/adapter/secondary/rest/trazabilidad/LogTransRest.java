package bo.gob.ruat.base.infrastructure.adapter.secondary.rest.trazabilidad;

import bo.gob.ruat.base.domain.entity.trazabilidad.LogTransEntity;
import io.vertx.core.json.JsonObject;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.IOException;

/**
 * Cliente Rest Elasticsearch para el LogTrans.
 * <p>
 * @author Grover Aliaga Candia
 * @since 24/05/2022
 */
@ApplicationScoped
public class LogTransRest {

	@Inject
	RestClient elasticRestClient;

	/**
	 * Indiza el LogTrans en elasticsearch en la colección logtrans.
	 * <p>
	 * @since 24/05/2022 | Grover Aliaga Candia
	 * <p>
	 * @param pLogTransEntity
	 * @throws IOException
	 */
	public void indizarLogTrans(LogTransEntity pLogTransEntity) throws IOException {
		Request vRequest = new Request(
			"POST",
			"/logstrans/_doc/");
		vRequest.setJsonEntity(JsonObject.mapFrom(pLogTransEntity).toString());
		elasticRestClient.performRequest(vRequest);
	}
}
