package sv.gob.bandesal.bandesal.businesslogic.serviceImpl.blogs;

import sv.gob.bandesal.bandesal.businesslogic.service.blogs.ListaLectoresService;
import sv.gob.bandesal.bandesal.model.dao.blogs.ListaLectoresDAO;
import sv.gob.bandesal.bandesal.model.entity.blogs.BlogsReader;
import sv.gob.bandesal.bandesal.model.entity.blogs.Readers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
public class ListaLectoresServiceImpl implements ListaLectoresService {

    private final ListaLectoresDAO oListaLectoresDAO;

    @Override
    public void guardar(Readers oReaders) throws Exception {
        oListaLectoresDAO.save(oReaders);
    }

    @Override
    public void actualizar(Readers oReaders) throws Exception {
        oListaLectoresDAO.update(oReaders);
    }

    @Override
    public void eliminar(Readers oReaders) throws Exception {
        oListaLectoresDAO.remove(oReaders);
    }

    public ListaLectoresServiceImpl(ListaLectoresDAO oListaLectoresDAO) {
        this.oListaLectoresDAO = oListaLectoresDAO;
    }

    @Override
    public List<Map<String, Object>> listReaders(String prBuscar) {
        return oListaLectoresDAO.listReaders(prBuscar);
    }

    @Override
    public Readers obtenerIdReaders(int id) throws Exception {
        return oListaLectoresDAO.find(id);
    }



    @Override
    public List<Map<String, Object>> listlectores(String prBuscar) {
        return oListaLectoresDAO.listlectores(prBuscar);
    }

}
