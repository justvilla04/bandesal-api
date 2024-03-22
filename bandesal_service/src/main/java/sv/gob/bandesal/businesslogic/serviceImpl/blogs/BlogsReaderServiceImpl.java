package sv.gob.bandesal.bandesal.businesslogic.serviceImpl.blogs;


import sv.gob.bandesal.barista.model.dao.EntityNotFoundException;
import sv.gob.bandesal.bandesal.businesslogic.service.blogs.BlogsReadersService;
import sv.gob.bandesal.bandesal.model.dao.blogs.BlogsReadersDAO;
import sv.gob.bandesal.bandesal.model.entity.blogs.BlogsReader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
public class BlogsReaderServiceImpl implements BlogsReadersService {

    private final BlogsReadersDAO oBlogsReadersDAO;

    public BlogsReaderServiceImpl(BlogsReadersDAO oBlogsReadersDAO) {
        this.oBlogsReadersDAO = oBlogsReadersDAO;
    }

    @Override
    public void guardar(BlogsReader oBlogsReader) throws Exception {
        oBlogsReadersDAO.save(oBlogsReader);
    }

    @Override
    public void actualizar(BlogsReader oBlogsReader) throws Exception {
        oBlogsReadersDAO.update(oBlogsReader);
    }

    @Override
    public void eliminar(BlogsReader oBlogsReader) throws Exception {
        oBlogsReadersDAO.remove(oBlogsReader);
    }

    @Override
    public BlogsReader ObtenerIdReaderBlog(int id) throws EntityNotFoundException {
        return oBlogsReadersDAO.find(id);
    }

}
