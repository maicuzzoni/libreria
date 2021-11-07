/*

 */
package egg.web.libreria.servicios;
import egg.web.libreria.entidades.Autor;
import egg.web.libreria.repositorios.AutorRepositorio;
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
public class AutorService {
    @Autowired
    private AutorRepositorio repositorio;
    
    @Transactional
    public void crearAutor(String nombre){
        Autor autor = new Autor();
        autor.setAlta(true);
        autor.setNombre(nombre);
        repositorio.save(autor);
    }
    
    @Transactional(readOnly = true)
    public Autor obtenerPorId(String id){
        Optional<Autor> autorOptional = repositorio.findById(id);
        return autorOptional.orElse(null);
    }
    
    @Transactional
    public void modificarAutor(String id, String nombre, Boolean alta){
       repositorio.modificar(id, nombre, alta); 
    }
    
    @Transactional(readOnly = true)
    public List<Autor> obtenerAutores(){
        return repositorio.mostrarHabilitados();
    }
    
    @Transactional
    public void eliminar(String id) {
        repositorio.deleteById(id);
    }
    
    @Transactional
    public void habilitar(String id){
        repositorio.habilitar(id);
    }
    
    @Transactional(readOnly = true)    
    public List<Autor> obtenerDeshabilitados() {
        return repositorio.deshabilitados();
    }
}
