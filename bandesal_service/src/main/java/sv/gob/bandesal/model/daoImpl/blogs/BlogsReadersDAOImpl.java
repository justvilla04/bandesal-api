package sv.gob.bandesal.bandesal.model.daoImpl.blogs;

import sv.gob.bandesal.barista.model.daoImpl.BaseGenericDAOImpl;
import sv.gob.bandesal.bandesal.model.dao.blogs.BlogsReadersDAO;
import sv.gob.bandesal.bandesal.model.entity.blogs.BlogsReader;
import org.springframework.stereotype.Repository;

@Repository
public class BlogsReadersDAOImpl extends BaseGenericDAOImpl<BlogsReader,Integer> implements BlogsReadersDAO {


}
