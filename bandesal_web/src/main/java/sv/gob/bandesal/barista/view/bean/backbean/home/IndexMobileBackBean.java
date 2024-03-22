package sv.gob.bandesal.barista.view.bean.backbean.home;

import lombok.Data;
import sv.gob.bandesal.barista.businesslogic.service.core.NavegacionService;
import sv.gob.bandesal.barista.businesslogic.service.infraestructura.EntidadService;
import sv.gob.bandesal.barista.businesslogic.service.seguridad.ModuloService;
import sv.gob.bandesal.barista.model.dao.DAOException;
import sv.gob.bandesal.barista.model.entity.infraestructura.Entidad;
import sv.gob.bandesal.barista.model.entity.seguridad.Modulo;
import sv.gob.bandesal.barista.model.entity.seguridad.SesionModulo;
import sv.gob.bandesal.barista.model.utils.RegExpresion;
import sv.gob.bandesal.barista.view.bean.session.SessionBean;
import sv.gob.bandesal.barista.view.errorHandler.ErrBitNotice;
import sv.gob.bandesal.barista.view.utils.web.NavigationResults;
import sv.gob.bandesal.barista.view.utils.web.WebUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.Map;


/**
 * Representa BackBean para pantalla Index
 *
 * @author Juan Evangelista Fletes Garcia
 * @since 30/04/2013
 * Modificado por jvillanueva el 08/08/2023
 */

@Data
@Named
@Scope("view")
public class IndexMobileBackBean implements Serializable {
    /**
     * ******************************************************************************
     * VARIABLES
     * ******************************************************************************
     **/

    private static final long serialVersionUID = 1L;
    List<Map<String, Object>> modulos;
    private Logger logger = Logger.getLogger(IndexMobileBackBean.class);
    private String txtNombreModulo = "";
    private String selectOrder = "nombre";
    private String regExpBuscar = RegExpresion.regExpNombre;
    private Integer moduloSeleccionado;
    private String paginaActual;
    private List<Map<String, Object>> menus;
    private List<Entidad> listaEntidads;

    /**
     * **********************************************************************************
     * CONSTRUCTOR
     * **********************************************************************************
     */
    private SessionBean sessionBean;
    private NavegacionService oNavegacionService;
    private MenuPrincipalMobileBackBean oMenuPrincipalMobileBackBean;
    private ErrBitNotice oErrBitNotice;
    private EntidadService oEntidadService;
    private ModuloService oModuloService;

    @Inject
    @Autowired
    public IndexMobileBackBean(@Qualifier("sessionBean") SessionBean sessionBean,
                               MenuPrincipalMobileBackBean oMenuPrincipalMobileBackBean,
                               EntidadService oEntidadService,
                               ModuloService oModuloService,
                               ErrBitNotice oErrBitNotice,
                               NavegacionService oNavegacionService) {
        this.sessionBean = sessionBean;
        this.oMenuPrincipalMobileBackBean = oMenuPrincipalMobileBackBean;
        this.oEntidadService = oEntidadService;
        this.oModuloService = oModuloService;
        this.oErrBitNotice = oErrBitNotice;
        this.oNavegacionService = oNavegacionService;
    }

    @PostConstruct
    public void init() {
        txtNombreModulo = "";
        selectOrder = "nombre";
        regExpBuscar = RegExpresion.regExpNombre;
        boolean moduloUnico = false;

        //Obteniendo lista de entidades por usuario
        this.listaEntidads = this.oEntidadService.obtenerEntidadesPorUsuario(sessionBean.getUsuarioActual().getId());

        if (sessionBean.getEntidadActual() != null) {
            cargarModulos(sessionBean.getEntidadActual());

            if (sessionBean.getModuloActual() != null) {
                moduloSeleccionado = sessionBean.getModuloActual().getId();
            }
        } else {
            //Si solo tiene acceso a una entidad
            if (this.listaEntidads.size() == 1) {
                sessionBean.setEntidadActual(listaEntidads.get(0));
                cargarModulos(sessionBean.getEntidadActual());
            }
        }

        if (sessionBean.getPaginaActual() != null) {
            this.paginaActual = sessionBean.getPaginaActual();
            sessionBean.setPaginaActual(null);
        }
    }

    /**
     * ***********************************************************************************
     * METODOS
     * ***********************************************************************************
     */
    public void buscarModulos() {
        cargarModulos(sessionBean.getEntidadActual());
    }

    public void selecionarEntidadEvent(ValueChangeEvent event) {
        cargarModulos((Entidad) event.getNewValue());
    }

    public void selecionarOrdenEvent(ValueChangeEvent event) {
        selectOrder = (String) event.getNewValue();
        cargarModulos(sessionBean.getEntidadActual());
    }

    private void cargarModulos(Entidad oEntidad) {
        if (oEntidad == null) {
            modulos = null;
        } else {
            try {
                modulos = oNavegacionService.obtenerModulosPorUsuarioMobile(sessionBean.getUsuarioActual().getId(), oEntidad.getId(), txtNombreModulo, selectOrder);
            } catch (Exception e) {
                logger.error(e);
                oErrBitNotice.setErrorData(this.getClass().getSimpleName() + "#cargarModulos(Integer entidadId)",
                        "/views/home/modulos.xhtml", sessionBean.getUsuarioActual(), e);
                oErrBitNotice.sendError();
            }
        }
    }

    public String seleccionarModulo() throws DAOException {
        if (moduloSeleccionado != null) {
            Modulo oModulo = oModuloService.obtenerModuloPorId(moduloSeleccionado);
            sessionBean.setModuloActual(oModulo);
            menus = oNavegacionService.obtenerMenuPorUsuarioModuloMobile(sessionBean.getUsuarioActual().getId(), sessionBean.getEntidadActual().getId(), moduloSeleccionado);
            //Guardando sesion modulo
            SesionModulo oSesionModulo = new SesionModulo();
            oSesionModulo.setUsuarioId(sessionBean.getUsuarioActual().getId());
            oSesionModulo.setFechaUltimaSesion(WebUtils.nowTimeStamp());
            oSesionModulo.setModulosByModuloId(oModulo);
            oSesionModulo.setEntidad(sessionBean.getEntidadActual());
            oNavegacionService.guardarSesionModulo(oSesionModulo);
            return NavigationResults.MENU_MOBILE;
        } else {
            return NavigationResults.MODULOS_MOBILE;
        }
    }
}