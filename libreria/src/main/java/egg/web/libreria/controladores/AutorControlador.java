/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package egg.web.libreria.controladores;

import egg.web.libreria.entidades.Autor;
import egg.web.libreria.entidades.Libro;
import egg.web.libreria.servicios.AutorService;
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
@RequestMapping("/autores")
public class AutorControlador {

    @Autowired
    private AutorService servicio;

    @GetMapping("/todos")
    public ModelAndView mostrarAutores() {
        ModelAndView mav = new ModelAndView("autores-lista");
        List<Autor> autores = servicio.obtenerAutores();
        mav.addObject("autores", autores);
        return mav;
    }
    
    @GetMapping("/libros/{id}") //Traigo los libros del autor seleccionado
    public ModelAndView librosDeAutor(@PathVariable String id) {
        ModelAndView mav = new ModelAndView("libros-lista");
        List<Libro> libros = servicio.obtenerLibros(id);
        mav.addObject("libros", libros);
        mav.addObject("action", "habilitados");
        return mav;
    }
    
    
    @GetMapping("/deshabilitados")
    public ModelAndView mostrarDeshabilitados() {
        ModelAndView mav = new ModelAndView("autores-deshabilitados");
        List<Autor> autores = servicio.obtenerDeshabilitados();
        mav.addObject("autores",autores);
        return mav;
    }
    

    @GetMapping("/crear")
    public ModelAndView crearAutor() {
        ModelAndView mav = new ModelAndView("autores-form");
        mav.addObject("autor", new Autor());
        mav.addObject("title", "Crear Autor");
        mav.addObject("action", "guardar");
        return mav;
    }
    
    @PostMapping("/guardar")
    public RedirectView guardar(@RequestParam String nombre){
            servicio.crearAutor(nombre);
        return new RedirectView("/autores/todos");
    }
    @GetMapping("/editar/{id}")
    public ModelAndView editarAutor(@PathVariable String id) {
        ModelAndView mav = new ModelAndView("autores-form");
        mav.addObject("autor", servicio.obtenerPorId(id));
        mav.addObject("title", "Modificar Autor");
        mav.addObject("action", "modificar");
        return mav;
    }
    
    @GetMapping("/habilitar/{id}")
    public RedirectView habilitar(@PathVariable String id) {
        servicio.habilitar(id);
        return new RedirectView("/autores/deshabilitados");
    }
    
    @PostMapping("/modificar")
    public RedirectView modificar(@RequestParam String id, @RequestParam String nombre, @RequestParam Boolean alta){
        servicio.modificarAutor(id, nombre, alta);
        return new RedirectView("/autores/todos");
    }
    
    @PostMapping("/eliminar/{id}")
    public RedirectView eliminar(@PathVariable String id){
        servicio.eliminar(id);
        return new RedirectView("/autores/todos");
    }

}
