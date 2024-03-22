package sv.gob.bandesal.bandesal.model.daoImpl.blogs;

import sv.gob.bandesal.barista.model.daoImpl.BaseGenericDAOImpl;

import sv.gob.bandesal.bandesal.model.dao.blogs.ListaLectoresDAO;
import sv.gob.bandesal.bandesal.model.entity.blogs.Readers;
import org.hibernate.Query;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class ListaLectoresDAOImpl extends BaseGenericDAOImpl<Readers,Integer> implements ListaLectoresDAO {

    @Override
    public List<Map<String, Object>> listReaders(String prBuscar) {
        Query query = sessionFactory.getCurrentSession()
                .createNativeQuery("select * from salvador.fn_readers(:prBuscar)");
        query.setParameter("prBuscar", prBuscar);
        ((NativeQueryImpl) query).setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
        return query.list();

    }

    @Override
    public List<Map<String, Object>> listlectores(String prBuscar) {
        Query query = sessionFactory.getCurrentSession()
                .createNativeQuery("select * from salvador.fn_lectores(:prBuscar)");
        query.setParameter("prBuscar", prBuscar);
        ((NativeQueryImpl) query).setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
        return query.list();

    }
}
