package bo.gob.ruat.base.application.usecase.trazabilidad;

import bo.gob.ruat.base.domain.entity.trazabilidad.LogTransEntity;
import bo.gob.ruat.base.infrastructure.adapter.secondary.model.jpa.interceptor.LogTransBase;
import bo.gob.ruat.base.infrastructure.adapter.secondary.rest.trazabilidad.LogTransRest;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Caso de uso para la trazabilidad de las Entidades.
 * en el LogTrans.
 * <p>
 * @author Grover Aliaga Candia
 * @since 13/05/2022
 */
@Singleton
public class LogTransUseCaseImpl {

	@Inject
	LogTransRest elasticLogTransRest;

	public void registrarTransaccion(String pAccion, LogTransBase pEntidad, String pNombreTabla, String pDetallePK, String pDetalle) throws IOException {
		LogTransEntity vLogTransEntity = new LogTransEntity();

		vLogTransEntity.setAccion(pAccion);
		vLogTransEntity.setIdentificadorEntidad(pDetallePK);
		vLogTransEntity.setNumSecCuentaUsuario(pEntidad.getNumSecCuentaUsuario());
		vLogTransEntity.setNumSecTramite(pEntidad.getNumSecTramite());
		vLogTransEntity.setNombreEntidad(pEntidad.getClass().toString());
		vLogTransEntity.setNombreTabla(pNombreTabla);
		vLogTransEntity.setDetalle(pDetalle);
		vLogTransEntity.setFechaHoraCreacion(LocalDateTime.now());

		elasticLogTransRest.indizarLogTrans(vLogTransEntity);
	}
}
