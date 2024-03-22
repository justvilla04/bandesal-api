package sv.gob.bandesal.bandesal.view.bean.backbean.blogs;

import lombok.Data;
import sv.gob.bandesal.barista.view.bean.backbean.BaseBackBean;
import sv.gob.bandesal.barista.view.security.SystemSecurityException;
import sv.gob.bandesal.bandesal.businesslogic.service.blogs.BlogService;
import sv.gob.bandesal.bandesal.businesslogic.service.blogs.BlogsReadersService;
import sv.gob.bandesal.bandesal.businesslogic.service.blogs.ListaLectoresService;
import sv.gob.bandesal.bandesal.model.entity.blogs.Blogs;
import sv.gob.bandesal.bandesal.model.entity.blogs.BlogsReader;
import sv.gob.bandesal.bandesal.model.entity.blogs.Readers;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import java.awt.print.PrinterException;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Data
@Named
@Scope("view")
public class ListaLectoresBackBean extends BaseBackBean implements Serializable {

    /**
     * ******************************************************************************
     * VARIABLES
     * ******************************************************************************
     **/
    private String txtBuscar = "";
    private String txtNombre;
    private String txtTitle;
    private String txtDescription;
    private Integer cmbLector;
    private Integer cmbBlog;
    private Map<String, Object> readersVSBlogsSeleccionado;
    private Map<String, Object> lectorSeleccionado;
    private Map<String, Object> registroSeleccionado;
    private List<Map<String, Object>> listaReaders;
    private Integer tablaSeleccionada = 1;
    private Boolean button;
    private List<Map<String, Object>> listaBlogs;
    private List<Map<String, Object>> listaLectores;

    /**
     * ***********************************************************************************
     * DEPENDENCIAS
     * ***********************************************************************************
     */
    private final ListaLectoresService oListaLectoresService;

    private final BlogService oBlogService;

    private final BlogsReadersService oBlogsReadersService;

    @Inject
    @Autowired
    public ListaLectoresBackBean(ListaLectoresService oListaLectoresService, BlogService oBlogService, BlogsReadersService oBlogsReadersService) {
        this.oListaLectoresService = oListaLectoresService;
        this.oBlogService = oBlogService;
        this.oBlogsReadersService = oBlogsReadersService;
    }

    /**
     * ***********************************************************************************
     * CONSTRUCTOR
     * ***********************************************************************************
     */
    @PostConstruct
    public void init() throws SystemSecurityException {
        cargarBlogs();
        cargarLectores();
        cargarReaders();
    }

    /**
     * **********************************************************************************
     * ACCIONES
     * ***********************************************************************************
     */

    //////////////////////////////////AGREGAR BACKBEAN/////////////////////////////
    public void cargarBlogs() {
        try {
            listaBlogs = oBlogService.listblogs(txtBuscar);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cargarLectores() {
        try {
            listaLectores = oListaLectoresService.listlectores(txtBuscar);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cargarReaders() {
        try {
            listaReaders = oListaLectoresService.listReaders(txtBuscar);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void cambiarTabla(int opcion) {
        tablaSeleccionada = opcion;
    }

    public int getTablaSeleccionada() {
        return tablaSeleccionada;
    }

    public void modalesGuadar() {
        if (tablaSeleccionada == 1) {
            PrimeFaces.current().executeScript("PF('NuevoRegistro').show()");
        } else {
            if (tablaSeleccionada == 2) {
                PrimeFaces.current().executeScript("PF('GuardarBlog').show()");
            } else {
                if (tablaSeleccionada == 3) {
                    PrimeFaces.current().executeScript("PF('lectorVsBlog').show()");
                }
            }
        }
    }

    public void limpiar() {
        txtDescription = null;
        txtTitle = null;
        txtNombre = null;

    }

    public void cerrarModalGuardaLector() {
        limpiar();
        PrimeFaces.current().ajax().update("NuevoRegistro");
        PrimeFaces.current().executeScript("PF('NuevoRegistro').hide()");
    }

    public void cerrarModalEditarLector() {
        limpiar();
        PrimeFaces.current().ajax().update("EditarRegistro");
        PrimeFaces.current().executeScript("PF('EditarRegistro').hide()");
    }

    public void cerrarModalGuardarBlog() {
        limpiar();
        PrimeFaces.current().ajax().update("GuardarBlog");
        PrimeFaces.current().executeScript("PF('GuardarBlog').hide()");

    }

    public void cerrarModalLectorVsBlog(){
        limpiar();
        PrimeFaces.current().ajax().update("lectorVsBlog");
        PrimeFaces.current().executeScript("PF('lectorVsBlog').hide()");
    }

    public void cerrarModalEditarBlog() {
        limpiar();
        PrimeFaces.current().ajax().update("editarBlog");
        PrimeFaces.current().executeScript("PF('editarBlog').hide()");

    }

    public void cerrarModalEditarlectorVsBlog(){
        limpiar();
        PrimeFaces.current().ajax().update("EditarlectorVsBlog");
        PrimeFaces.current().executeScript("PF('EditarlectorVsBlog').hide()");
    }

    public void guardarRegistro() {
        try {
            if (tablaSeleccionada == 1) {
                Readers oReaders = new Readers();
                oReaders.setName(txtNombre);
                oListaLectoresService.guardar(oReaders);
                mostrarMensajeSuccess("Se guardó con éxito");
                limpiar();
                cargarLectores();
                PrimeFaces.current().ajax().update(":formLectores");
                PrimeFaces.current().executeScript("PF('NuevoRegistro').hide()");

            } else {
                if (tablaSeleccionada == 2) {
                    Blogs oBlog = new Blogs();
                    oBlog.setTitle(txtTitle);
                    oBlog.setDescription(txtDescription);
                    oBlogService.guardar(oBlog);
                    mostrarMensajeSuccess("Se guardó con éxito");
                    limpiar();
                    cargarBlogs();
                    PrimeFaces.current().ajax().update(":formLectores");
                    PrimeFaces.current().executeScript("PF('GuardarBlog').hide()");
                } else {
                    if (tablaSeleccionada == 3) {
                        BlogsReader oBlogsReader = new BlogsReader();
                        oBlogsReader.setReaders(oListaLectoresService.obtenerIdReaders(cmbLector));
                        oBlogsReader.setBlog(oBlogService.obtenerIdBlog(cmbBlog));
                        oBlogsReadersService.guardar(oBlogsReader);
                        mostrarMensajeSuccess("Se guardó con éxito");
                        limpiar();
                        cargarReaders();
                        PrimeFaces.current().ajax().update(":formLectores");
                        PrimeFaces.current().executeScript("PF('lectorVsBlog').hide()");


                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void visualizarRegistro() {
        try {
            if (registroSeleccionado == null && lectorSeleccionado == null && readersVSBlogsSeleccionado == null) {
                mostrarMensajeError("Seleccione un registro");
            } else {
                if (tablaSeleccionada == 1) {
                    txtNombre = (String) lectorSeleccionado.get("name");
                    PrimeFaces.current().ajax().update("EditarRegistro");
                    PrimeFaces.current().executeScript("PF('EditarRegistro').show()");
                } else {
                    if (tablaSeleccionada == 2) {
                        txtTitle = (String) registroSeleccionado.get("title");
                        txtDescription = (String) registroSeleccionado.get("description");
                        PrimeFaces.current().ajax().update("editarBlog");
                        PrimeFaces.current().executeScript("PF('editarBlog').show()");
                    }else {
                        if(tablaSeleccionada == 3){
                            cmbLector = (Integer) readersVSBlogsSeleccionado.get("readers_id");
                            cmbBlog = (Integer) readersVSBlogsSeleccionado.get("blogs_id");
                            PrimeFaces.current().ajax().update("EditarlectorVsBlog");
                            PrimeFaces.current().executeScript("PF('EditarlectorVsBlog').show()");
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void editarRegistro() {
        try {
            if (tablaSeleccionada == 1) {
                Readers oReaders = oListaLectoresService.obtenerIdReaders((Integer) lectorSeleccionado.get("id"));
                oReaders.setName(txtNombre);
                oListaLectoresService.actualizar(oReaders);
                mostrarMensajeSuccess("Se Modificó con Éxio");
                cargarLectores();
                limpiar();
                PrimeFaces.current().ajax().update("formReaders");
                PrimeFaces.current().executeScript("PF('EditarRegistro').hide()");

            } else {
                if (tablaSeleccionada == 2) {
                    Blogs oBlog = oBlogService.obtenerIdBlog((Integer) registroSeleccionado.get("id"));
                    oBlog.setTitle(txtTitle);
                    oBlog.setDescription(txtDescription);
                    oBlogService.actualizar(oBlog);
                    mostrarMensajeSuccess("Se Modificó con Éxio");
                    cargarBlogs();
                    limpiar();
                    PrimeFaces.current().ajax().update("formReaders");
                    PrimeFaces.current().executeScript("PF('EditarRegistro').hide()");
                }else{
                    if(tablaSeleccionada == 3){
                        BlogsReader oBlogsReader = oBlogsReadersService.ObtenerIdReaderBlog((Integer) readersVSBlogsSeleccionado.get("id"));
                        oBlogsReader.setReaders(oListaLectoresService.obtenerIdReaders(cmbLector));
                        oBlogsReader.setBlog(oBlogService.obtenerIdBlog(cmbBlog));
                        oBlogsReadersService.actualizar(oBlogsReader);
                        mostrarMensajeSuccess("Se Modificó con Éxio");
                        cargarReaders();
                        limpiar();
                        PrimeFaces.current().ajax().update("EditarlectorVsBlog");
                        PrimeFaces.current().executeScript("PF('EditarlectorVsBlog').hide()");

                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void borrarRegistros() throws Exception {
        if (registroSeleccionado == null && lectorSeleccionado == null && readersVSBlogsSeleccionado == null) {
            mostrarMensajeError("Seleccione un registro");
        }else{
            if(tablaSeleccionada == 1){
               Readers oReadersDelete = oListaLectoresService.obtenerIdReaders((Integer) lectorSeleccionado.get("id"));
                oListaLectoresService.eliminar(oReadersDelete);
                mostrarMensajeSuccess("Éxito al eliminar");
                cargarLectores();
                PrimeFaces.current().ajax().update(":formLectores");
            }else {
                if(tablaSeleccionada == 2){
                    Blogs oBlogs = oBlogService.obtenerIdBlog((Integer) registroSeleccionado.get("id"));
                    oBlogService.eliminar(oBlogs);
                    mostrarMensajeSuccess("Éxito al eliminar");
                    cargarBlogs();
                    PrimeFaces.current().ajax().update(":formLectores");
                }else {
                    if(tablaSeleccionada == 3){

                        BlogsReader oBlogsReader = oBlogsReadersService.ObtenerIdReaderBlog((Integer) readersVSBlogsSeleccionado.get("id"));
                        oBlogsReadersService.eliminar(oBlogsReader);
                        mostrarMensajeSuccess("Éxito al eliminar");
                        cargarReaders();
                        PrimeFaces.current().ajax().update(":formLectores");
                    }
                }
            }
        }


    }
}
