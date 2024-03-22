package sv.gob.bandesal.bandesal.model.daoImpl.blogs;

import sv.gob.bandesal.barista.model.daoImpl.BaseGenericDAOImpl;
import sv.gob.bandesal.bandesal.model.dao.blogs.BlogDAO;
import sv.gob.bandesal.bandesal.model.entity.blogs.Blogs;
import org.hibernate.query.Query;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class BlogDAOImpl extends BaseGenericDAOImpl<Blogs,Integer> implements BlogDAO {

    @Override
    public List<Map<String, Object>> listblogs(String prBuscar) {
        Query query = sessionFactory.getCurrentSession()
                .createNativeQuery("select * from salvador.fn_blogs(:prBuscar)");
        query.setParameter("prBuscar", prBuscar);
        ((NativeQueryImpl) query).setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
        return query.list();

    }

}
