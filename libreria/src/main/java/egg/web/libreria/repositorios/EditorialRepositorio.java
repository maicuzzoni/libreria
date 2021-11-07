/*

 */
package egg.web.libreria.repositorios;

import egg.web.libreria.entidades.Autor;
import egg.web.libreria.entidades.Editorial;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Mai
 * 
 */

@Repository
public interface EditorialRepositorio extends JpaRepository<Editorial, String> {
    
    @Modifying
    @Query("UPDATE Editorial e SET e.nombre = :nombre, e.alta = :alta WHERE e.id = :id")
    void modificar(@Param("id") String id, @Param("nombre") String nombre, @Param("alta") Boolean alta);
    
    @Query("SELECT e FROM Editorial e WHERE e.alta = true")
    List<Editorial> mostrarHabilitados();

    @Modifying
    @Query("UPDATE Editorial e SET e.alta = true WHERE e.id = :id")
    void habilitar(@Param("id") String id);
    
    @Query("SELECT e FROM Editorial e WHERE e.alta = false")
    public List<Editorial> deshabilitadas();
}
