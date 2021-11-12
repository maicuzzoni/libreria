/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package egg.web.libreria.controladores;

import egg.web.libreria.entidades.Autor;
import egg.web.libreria.entidades.Editorial;
import egg.web.libreria.entidades.Libro;
import egg.web.libreria.servicios.AutorService;
import egg.web.libreria.servicios.EditorialService;
import egg.web.libreria.servicios.LibroService;
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
 * @author Usuario 1
 */
@Controller
@RequestMapping("/libros")
public class LibroControlador {

    @Autowired
    private LibroService ls;

    @Autowired
    private AutorService as;

    @Autowired
    private EditorialService es;

    @GetMapping("/todos")
    public ModelAndView mostrarLibros() {
        ModelAndView mav = new ModelAndView("libros-lista");
        List<Libro> libros = ls.obtenerLibros();
        mav.addObject("libros", libros);
        mav.addObject("action", "habilitados");
        return mav;
    }

    @GetMapping("/crear")
    public ModelAndView crearLibro() {
        ModelAndView mav = new ModelAndView("libros-form");
        mav.addObject("libro", new Libro());
        mav.addObject("title", "Crear Libro");
        mav.addObject("action", "guardar");
        mav.addObject("editoriales", es.obtenerEditoriales());
        mav.addObject("autores", as.obtenerAutores());
        return mav;
    }

    @PostMapping("/guardar")
    public RedirectView guardar(@RequestParam Long isbn, @RequestParam String titulo, @RequestParam Integer anio, @RequestParam Integer ejemplares, @RequestParam Integer ejemplaresPrestados, @RequestParam Autor autor, @RequestParam Editorial editorial) {
        ls.crearLibro(isbn, titulo, anio, ejemplares, ejemplaresPrestados, autor, editorial);
        return new RedirectView("/libros/todos");
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editarLibro(@PathVariable String id) {
        ModelAndView mav = new ModelAndView("libros-form");
        mav.addObject("libro", ls.obtenerPorId(id));
        mav.addObject("title", "Modificar Libro");
        mav.addObject("action", "modificar");
        mav.addObject("editoriales", es.obtenerEditoriales());
        mav.addObject("autores", as.obtenerAutores());
        return mav;
    }

    @PostMapping("/modificar")
    public RedirectView modificar(@RequestParam String id, @RequestParam Long isbn, @RequestParam String titulo, @RequestParam Integer anio, @RequestParam Integer ejemplares, @RequestParam Integer ejemplaresPrestados, @RequestParam Autor autor, @RequestParam Editorial editorial, @RequestParam Boolean alta) {
        Integer restantes = ejemplares - ejemplaresPrestados;
        ls.modificarLibro(id, isbn, titulo, anio, ejemplares, ejemplaresPrestados, restantes, autor, editorial, alta);
        return new RedirectView("/libros/todos");
    }

    @PostMapping("/eliminar/{id}")
    public RedirectView eliminar(@PathVariable String id) {
        ls.eliminar(id);
        return new RedirectView("/libros/todos");
    }

    @GetMapping("/deshabilitados")
    public ModelAndView mostrarDeshabilitados() {
        ModelAndView mav = new ModelAndView("libros-lista");
        List<Libro> libros = ls.obtenerDeshabilitados();
        mav.addObject("libros", libros);
        mav.addObject("action", "deshabilitados");
        return mav;
    }

    @GetMapping("/habilitar/{id}")
    public RedirectView habilitar(@PathVariable String id) {
        ls.habilitar(id);
        return new RedirectView("/libros/deshabilitados");
    }

}
