package guiamvc.ejercicio1.controladores;

import java.util.List;
import javax.validation.Valid;
import guiamvc.ejercicio1.entidades.Autor;
import guiamvc.ejercicio1.servicios.AutorServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/autor")
@SessionAttributes("autor")
public class AutorController {

  private static final String ERROR = "error";
  private static final String BACK = "redirect:/autor/";
  private static final String AUTOR2 = "autor";
  private static final String TITULO = "titulo";

  @Autowired
  private AutorServicio autorServicio;

  // @Secured("ROLE_USER")
  @GetMapping("/")
  public String listarAutores(Model model) {
    List<Autor> listadoAutores = autorServicio.listarTodos();
    model.addAttribute("autores", listadoAutores);
    model.addAttribute(TITULO, "Listado de Autores");
    model.addAttribute("h1", "Lista de Autores");
    return "/autor/listadoAutores";
  }
  
  @Secured("ROLE_ADMIN")
  @GetMapping("/create")
  public String crear(Model model) {
    Autor autor = new Autor();
    model.addAttribute(TITULO, "Formulario");
    model.addAttribute("h1", "Formulario : Nuevo Autor");
    model.addAttribute(AUTOR2, autor);
    autor.setAlta(true);
    return "/autor/nuevoAutor";
  }
  @Secured("ROLE_ADMIN")
  @PostMapping("/save")
  public String guardar(@Valid @ModelAttribute Autor autor, SessionStatus ss,Model model,RedirectAttributes attribute){
    autorServicio.guardar(autor);
    attribute.addFlashAttribute("success", "Registro guardado con exito!");
    ss.setComplete();  
    return BACK;
  }
  @Secured("ROLE_ADMIN")
  @GetMapping("/edit/{id}")
  public String editar(@PathVariable("id") String id, Model model, RedirectAttributes attribute) {
    Autor autor = null;
	
		if (id.length() > 0) {
			autor = autorServicio.buscarPorId(id);
			if (autor == null) {
				attribute.addFlashAttribute(ERROR, "ATENCION: El ID del autor no existe!");
				return BACK;
			}
		}else {
			attribute.addFlashAttribute(ERROR, "ATENCION: Error con el ID del autor");
			return BACK;
		}
    model.addAttribute(TITULO, "Editar");
    model.addAttribute("h1", "Formulario : Editar Autor");
    model.addAttribute(AUTOR2, autor);
    return "/autor/editarAutor";
  }

  @Secured("ROLE_ADMIN")
  @GetMapping("/delete/{id}")
  public String eliminar(@PathVariable("id") String id, RedirectAttributes attribute) {
    Autor autor = null;
		
		if (id.length() > 0) {
			autor = autorServicio.buscarPorId(id);
			if (autor == null) {
				attribute.addFlashAttribute(ERROR, "ATENCION: El ID del autor no existe!");
				return BACK;
			}
		}else {
			attribute.addFlashAttribute(ERROR, "ATENCION: Error con el ID del autor");
			return BACK;
		}
    autorServicio.eliminar(id);
    attribute.addFlashAttribute("warning", "Registro Eliminado con Exito!");
    return BACK;
  }
}
