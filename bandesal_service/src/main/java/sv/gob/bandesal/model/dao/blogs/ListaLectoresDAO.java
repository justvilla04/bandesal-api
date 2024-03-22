package sv.gob.bandesal.bandesal.model.dao.blogs;

import sv.gob.bandesal.barista.model.dao.BaseGenericDAO;
import sv.gob.bandesal.bandesal.model.entity.blogs.Readers;

import java.util.List;
import java.util.Map;


public interface ListaLectoresDAO extends BaseGenericDAO<Readers, Integer> {

    List<Map<String, Object>> listReaders(String prBuscar);
    List<Map<String, Object>> listlectores(String prBuscar);
}
