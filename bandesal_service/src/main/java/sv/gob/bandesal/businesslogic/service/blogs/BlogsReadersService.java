package sv.gob.bandesal.bandesal.businesslogic.service.blogs;

import sv.gob.bandesal.bandesal.model.entity.blogs.Blogs;
import sv.gob.bandesal.bandesal.model.entity.blogs.BlogsReader;

public interface BlogsReadersService {

    void guardar(BlogsReader oBlogsReader) throws Exception;

    void actualizar(BlogsReader oBlogsReader) throws Exception;

    void eliminar(BlogsReader oBlogsReader) throws Exception;

    BlogsReader ObtenerIdReaderBlog (int id) throws Exception;

}
