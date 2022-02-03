package guiamvc.ejercicio1.controladores;

import java.util.List;

import guiamvc.ejercicio1.entidades.Libro;
import guiamvc.ejercicio1.servicios.LibroServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexControlador {

  @Autowired
  private LibroServicio libroServicio;

  @GetMapping("/")
  public String index(Model model) {
    model.addAttribute("titulo", "Libreria");
    
    List<Libro> listadoLibros = libroServicio.lista();
    model.addAttribute("libros", listadoLibros);

    return "index.html";

  }

}
