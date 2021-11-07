/*

 */
package egg.web.libreria.servicios;

import egg.web.libreria.entidades.Editorial;
import egg.web.libreria.repositorios.EditorialRepositorio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Mai
 */
@Service
public class EditorialService {

    @Autowired
    private EditorialRepositorio repositorio;

    @Transactional
    public void crearEditorial(String nombre) {
        Editorial editorial = new Editorial();
        editorial.setAlta(true);
        editorial.setNombre(nombre);
        repositorio.save(editorial);
    }

    @Transactional(readOnly = true)
    public Editorial obtenerPorId(String id) {
        Optional<Editorial> editorialOptional = repositorio.findById(id);
        return editorialOptional.orElse(null);
    }

    @Transactional
    public void modificarEditorial(String id, String nombre, Boolean alta) {
        repositorio.modificar(id, nombre, alta);
    }

    @Transactional(readOnly = true)
    public List<Editorial> obtenerEditoriales() {
        return repositorio.mostrarHabilitados();
    }

    @Transactional
    public void eliminar(String id) {
        repositorio.deleteById(id);
    }

    @Transactional
    public void habilitar(String id) {
        repositorio.habilitar(id);
    }

    @Transactional(readOnly = true)
    public List<Editorial> obtenerDeshabilitadas() {
        return repositorio.deshabilitadas();
    }

}
