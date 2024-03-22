package sv.gob.bandesal.bandesal.model.dao.blogs;

import sv.gob.bandesal.barista.model.dao.BaseGenericDAO;
import sv.gob.bandesal.bandesal.model.entity.blogs.Blogs;

import java.util.List;
import java.util.Map;

public interface BlogDAO extends BaseGenericDAO<Blogs,Integer> {
    List<Map<String, Object>> listblogs(String prBuscar);
}
