package sv.gob.bandesal.bandesal.businesslogic.service.blogs;

import sv.gob.bandesal.bandesal.model.entity.blogs.Blogs;

import java.util.List;
import java.util.Map;

public interface BlogService {

    void guardar(Blogs oBlog) throws Exception;

    void actualizar(Blogs oBlog) throws Exception;

    void eliminar(Blogs oBlog) throws Exception;

    Blogs obtenerIdBlog(int id) throws Exception;

    List<Map<String, Object>> listblogs(String prBuscar);
}
