package bo.gob.ruat.base.infrastructure.adapter.secondary.model.jpa.interceptor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Anotación para marcar los atributos de Clave Primaria de una Entidad JPA
 * que seran registrados mediante la trazabilidad.
 * <p>
 * @author Grover Aliaga Candia
 * @since 24/05/2022
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface LogAtributoPK {
}
