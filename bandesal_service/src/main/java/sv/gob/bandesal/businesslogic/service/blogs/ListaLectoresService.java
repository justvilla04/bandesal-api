package sv.gob.bandesal.bandesal.businesslogic.service.blogs;

import sv.gob.bandesal.bandesal.model.entity.blogs.Readers;

import java.util.List;
import java.util.Map;

public interface ListaLectoresService {

    void guardar(Readers oReaders) throws Exception;

    void actualizar(Readers oReaders) throws Exception;

    void eliminar(Readers oReaders)throws Exception;

    List<Map<String, Object>> listReaders(String prBuscar);

    Readers obtenerIdReaders(int id) throws Exception;

    List<Map<String, Object>> listlectores(String prBuscar);
}
