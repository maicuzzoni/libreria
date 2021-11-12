
package egg.web.libreria.servicios;

import egg.web.libreria.entidades.Autor;
import egg.web.libreria.entidades.Editorial;
import egg.web.libreria.entidades.Libro;
import egg.web.libreria.repositorios.LibroRepositorio;
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
public class LibroService {
    
    @Autowired //Spring genera una clase que implemente esta interfaz
    //Sería la manera de inicializar la interfaz
    private LibroRepositorio repositorio;
    
    
    @Transactional //importar desde org.springframework.transaction.annotation.Transactional;
    //Indico que este metodo va a iniciar una transaccion con la base de datos.
    public void crearLibro(Long isbn, String titulo, Integer anio, Integer ejemplares, Integer ejemplaresPrestados, Autor autor, Editorial editorial){
        Libro libro = new Libro();
        libro.setIsbn(isbn);
        libro.setTitulo(titulo);
        libro.setAutor(autor);
        libro.setAnio(anio);
        libro.setEjemplares(ejemplares);
        libro.setEjemplaresPrestados(ejemplaresPrestados);
        libro.setEjemplaresRestantes(ejemplares - ejemplaresPrestados);
        libro.setEditorial(editorial);
        libro.setAlta(true);
        repositorio.save(libro);
    }
    
    @Transactional(readOnly = true)
    public Libro obtenerPorId(String id){
        Optional<Libro> libroOptional = repositorio.findById(id);
        return libroOptional.orElse(null);
    }
    
    @Transactional
    public void modificarLibro(String id, Long isbn, String titulo, Integer anio, Integer ejemplares, Integer ejemplaresPrestados, Integer ejemplaresRestantes, Autor autor, Editorial editorial, Boolean alta){ //completar parametros
        //Libro libro = repositorio.findById(id).get(); // busca el libro por id
        repositorio.modificar(id, isbn, titulo, anio, ejemplares, ejemplaresPrestados, ejemplaresRestantes, autor, editorial, alta);
    }
    
    @Transactional(readOnly = true)
    public List<Libro> obtenerLibros(){
        return repositorio.mostrarHabilitados();
    }

    @Transactional
    public void eliminar(String id) {
        repositorio.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<Libro> obtenerDeshabilitados() {
        return repositorio.deshabilitados();
    }

    @Transactional
    public void habilitar(String id) {
        repositorio.habilitar(id);
    }
    
    @Transactional
    public void deshabilitarPorAutor(String idAutor){
        repositorio.deshabilitarDeAutor(idAutor);
    }
    
    @Transactional
    public void habilitarPorAutor(String idAutor){
        repositorio.habilitarDeAutor(idAutor);
    }

}
