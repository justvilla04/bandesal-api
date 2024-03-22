package sv.gob.bandesal.bandesal.businesslogic.serviceImpl.blogs;

import sv.gob.bandesal.barista.model.dao.EntityNotFoundException;
import sv.gob.bandesal.bandesal.businesslogic.service.blogs.BlogService;
import sv.gob.bandesal.bandesal.model.dao.blogs.BlogDAO;
import sv.gob.bandesal.bandesal.model.entity.blogs.Blogs;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
public class BlogServiceImpl implements BlogService {

    private final BlogDAO oBlogDAO;

    public BlogServiceImpl(BlogDAO oBlogDAO) {
        this.oBlogDAO = oBlogDAO;
    }

    @Override
    public void guardar(Blogs oBlog) throws Exception {
        oBlogDAO.save(oBlog);
    }

    @Override
    public void actualizar(Blogs oBlog) throws Exception {
        oBlogDAO.update(oBlog);
    }

    @Override
    public void eliminar(Blogs oBlog) throws Exception {
        oBlogDAO.remove(oBlog);
    }

    @Override
    public Blogs obtenerIdBlog(int id) throws EntityNotFoundException {
        return oBlogDAO.find(id);
    }

    @Override
    public List<Map<String, Object>> listblogs(String prBuscar) {
        return oBlogDAO.listblogs(prBuscar);
    }

}
