package egg.web.libreria.controladores;

import egg.web.libreria.entidades.Editorial;
import egg.web.libreria.servicios.EditorialService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author Mai
 */

@Controller
@RequestMapping("/editoriales")
public class EditorialControlador {

    @Autowired
    private EditorialService edServ;

    @GetMapping("/todas")
    public ModelAndView mostrarEditoriales() {
        ModelAndView mav = new ModelAndView("editoriales-lista");
        List<Editorial> editoriales = edServ.obtenerEditoriales();
        mav.addObject("editoriales", editoriales);
        return mav;
    }

    @GetMapping("/crear")
    public ModelAndView crearEditorial() {
        ModelAndView mav = new ModelAndView("editoriales-form");
        mav.addObject("editorial", new Editorial());
        mav.addObject("title", "Crear Editorial");
        mav.addObject("action", "guardar");
        return mav;
    }

    @PostMapping("/guardar")
    public RedirectView guardar(@RequestParam String nombre) {
        edServ.crearEditorial(nombre);
        return new RedirectView("/editoriales/todas");
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editarAutor(@PathVariable String id) {
        ModelAndView mav = new ModelAndView("editoriales-form");
        mav.addObject("autor", edServ.obtenerPorId(id));
        mav.addObject("title", "Modificar Autor");
        mav.addObject("action", "modificar");
        return mav;
    }

    @PostMapping("/modificar")
    public RedirectView modificar(@RequestParam String id, @RequestParam String nombre, @RequestParam Boolean alta) {
        edServ.modificarEditorial(id, nombre, alta);
        return new RedirectView("/editorial/todas");
    }

    @PostMapping("/eliminar/{id}")
    public RedirectView eliminar(@PathVariable String id) {
        edServ.eliminar(id);
        return new RedirectView("/editoriales/todas");
    }
    
    @GetMapping("/deshabilitadas")
    public ModelAndView mostrarDeshabilitadas() {
        ModelAndView mav = new ModelAndView("editoriales-deshabilitadas");
        List<Editorial> editoriales = edServ.obtenerDeshabilitadas();
        mav.addObject("editoriales",editoriales);
        return mav;
    }
    
    @GetMapping("/habilitar/{id}")
    public RedirectView habilitar(@PathVariable String id) {
        edServ.habilitar(id);
        return new RedirectView("/editoriales/deshabilitadas");
    }
}
