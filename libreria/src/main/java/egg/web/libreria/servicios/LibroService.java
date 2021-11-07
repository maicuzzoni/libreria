
package egg.web.libreria.servicios;

import egg.web.libreria.entidades.Autor;
import egg.web.libreria.entidades.Editorial;
import egg.web.libreria.entidades.Libro;
import egg.web.libreria.repositorios.LibroRepositorio;
import java.util.List;
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
    
    @Transactional
    public void modificarLibro(String id, String nombre){ //completar parametros
        Libro libro = repositorio.findById(id).get(); // busca el libro por id
        
        // codigo para modificar
        
        repositorio.save(libro);
    }
    
    @Transactional(readOnly = true)
    public List<Libro> obtenerLibros(){
        return repositorio.findAll();
    }

    public Object obtenerPorId(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void eliminar(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Libro> obtenerDeshabilitados() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void habilitar(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
