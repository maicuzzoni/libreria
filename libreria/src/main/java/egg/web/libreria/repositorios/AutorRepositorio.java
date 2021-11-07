
package egg.web.libreria.repositorios;


import egg.web.libreria.entidades.Autor;
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
public interface AutorRepositorio extends JpaRepository<Autor, String> {
    
    @Modifying
    @Query("UPDATE Autor a SET a.nombre = :nombre, a.alta = :alta WHERE a.id = :id")
    void modificar(@Param("id") String id, @Param("nombre") String nombre, @Param("alta") Boolean alta);
  
    @Query("SELECT a FROM Autor a WHERE a.alta = true")
    List<Autor> mostrarHabilitados();

    @Modifying
    @Query("UPDATE Autor a SET a.alta = true WHERE a.id = :id")
    void habilitar(@Param("id") String id);
    
    @Query("SELECT a FROM Autor a WHERE a.alta = false")
    public List<Autor> deshabilitados();
    
}
