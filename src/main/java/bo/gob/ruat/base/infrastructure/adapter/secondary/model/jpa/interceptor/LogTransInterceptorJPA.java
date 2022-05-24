package bo.gob.ruat.base.infrastructure.adapter.secondary.model.jpa.interceptor;

import bo.gob.ruat.base.application.usecase.trazabilidad.LogTransUseCaseImpl;
import io.quarkus.hibernate.orm.PersistenceUnitExtension;
import io.vertx.core.json.JsonObject;
import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

import javax.inject.Inject;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Interceptor para JPA Hibernate, que hace un seguimiento de las inserciones
 * y actualizaciones de las Entidades JPA, y las registra en Elasticsearch.
 * <p>
 * @author Grover Aliaga Candia
 * @since 24/05/2022
 */
@PersistenceUnitExtension
public class LogTransInterceptorJPA extends EmptyInterceptor {

	@Inject
	LogTransUseCaseImpl logTransUseCase;

	private List<Object> inserts = new ArrayList<>();
	private List<Object> updates = new ArrayList<>();

	@Override
	public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
		if (entity instanceof LogTransBase) {
			this.inserts.add(entity);
		}
		return super.onSave(entity, id, state, propertyNames, types);
	}

	@Override
	public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState, String[] propertyNames, Type[] types) {
		if (entity instanceof LogTransBase) {
			this.updates.add(entity);
		}
		return super.onFlushDirty(entity, id, currentState, previousState, propertyNames, types);
	}

	@Override
	public void postFlush(Iterator entities) {
		try {
			for (Object obj : inserts) {
				if (obj instanceof LogTransBase) {
					LogTransBase entity = (LogTransBase) obj;
					registrarTransaccion("Insertado", entity);
				}
			}

			for (Object obj : updates) {
				if (obj instanceof LogTransBase) {
					LogTransBase entity = (LogTransBase) obj;
					registrarTransaccion("Actualizado", entity);
				}
			}
		} finally {
			inserts.clear();
			updates.clear();
		}

		super.postFlush(entities);
	}

	private void registrarTransaccion(String pAccion, LogTransBase pEntidad) {
		JsonObject jsonPKObject = new JsonObject();
		JsonObject jsonObject = new JsonObject();

		if(pEntidad.getClass().getAnnotation(Entity.class) != null) {
			String vNombreTabla = null;
			if(pEntidad.getClass().getAnnotation(Table.class) != null) {
				vNombreTabla = pEntidad.getClass().getAnnotation(Table.class).name();
			}
			try {
				for (Field field : pEntidad.getClass().getDeclaredFields()) {
					field.setAccessible(true);
					if (field.isAnnotationPresent(LogAtributoPK.class)) {
						jsonPKObject.put(field.getName(), field.get(pEntidad));
					} else if (field.isAnnotationPresent(LogAtributo.class)) {
						jsonObject.put(field.getName(), field.get(pEntidad));
					} else {
						continue;
					}
				}

				logTransUseCase.registrarTransaccion(pAccion, pEntidad, vNombreTabla, jsonPKObject.toString(), jsonObject.toString());
			} catch (IOException | IllegalAccessException e) {
				throw new RuntimeException(e);
			}
		}
	}
}
