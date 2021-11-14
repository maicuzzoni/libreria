package egg.web.libreria.repositorios;

import egg.web.libreria.entidades.Autor;
import egg.web.libreria.entidades.Editorial;
import egg.web.libreria.entidades.Libro;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Mai
 */
@Repository
public interface LibroRepositorio extends JpaRepository<Libro, String> {

    @Modifying
    @Query("UPDATE Libro l SET l.isbn = :isbn, l.titulo = :titulo, l.anio = :anio, l.ejemplares = :ejemplares, l.ejemplaresPrestados = :ejemplaresPrestados, l.ejemplaresRestantes = :ejemplaresRestantes, l.autor = :autor, l.editorial = :editorial, l.alta = :alta WHERE l.id = :id")
    void modificar(@Param("id") String id, @Param("isbn") Long isbn, @Param("titulo") String titulo, @Param("anio") Integer anio, @Param("ejemplares") Integer ejemplares, @Param("ejemplaresPrestados") Integer ejemplaresPrestados, @Param("ejemplaresRestantes") Integer ejemplaresRestantes, @Param("autor") Autor autor, @Param("editorial") Editorial editorial, @Param("alta") Boolean alta);

    @Query("SELECT l FROM Libro l WHERE l.alta = true ORDER BY l.autor.nombre ASC")
    List<Libro> mostrarHabilitados();

    @Modifying
    @Query("UPDATE Libro l SET l.alta = true WHERE l.id = :id")
    void habilitar(@Param("id") String id);

    @Query("SELECT l FROM Libro l WHERE l.alta = false ORDER BY l.autor.nombre ASC")
    public List<Libro> deshabilitados();

    @Query("UPDATE Libro l SET l.alta = false WHERE l.autor.id = :idAutor")
    public void deshabilitarDeAutor(String idAutor);

    @Query("UPDATE Libro l SET l.alta = true WHERE l.autor.id = :idAutor")
    public void habilitarDeAutor(String idAutor);
}
