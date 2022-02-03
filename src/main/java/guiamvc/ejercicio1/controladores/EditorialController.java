package guiamvc.ejercicio1.controladores;

import java.util.List;

import guiamvc.ejercicio1.entidades.Editorial;
import guiamvc.ejercicio1.servicios.EditorialServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/editorial")
@SessionAttributes("editorial")
public class EditorialController {

  private static final String ERROR = "error";
  private static final String REDIRECT = "redirect:/editorial/";
  private static final String EDITORIAL2 = "editorial";
  private static final String TITULO = "titulo";

  @Autowired
  private EditorialServicio editorialServicio;

  @GetMapping("/")
  public String listarEditoriales(Model model) {
    List<Editorial> listadoEditoriales = editorialServicio.listarTodos();
    model.addAttribute("editoriales", listadoEditoriales);
    model.addAttribute(TITULO, "Listado de Editoriales");
    model.addAttribute("h1", "Lista de Editoriales");
    return "/editorial/listadoEditoriales";
  }

  @GetMapping("/create")
  public String crear(Model model) {
    Editorial editorial = new Editorial();

    model.addAttribute(TITULO, "Formulario");
    model.addAttribute("h1", "Formulario : Nueva Editorial");
    model.addAttribute(EDITORIAL2, editorial);
    editorial.setAlta(true);
    return "/editorial/nuevaEditorial";
  }

  @PostMapping("/save")
  public String guardar(@ModelAttribute Editorial editorial, Model model, SessionStatus ss, RedirectAttributes attribute) {

    editorialServicio.guardar(editorial);
    attribute.addFlashAttribute("success", "Registro guardado con exito!");
    ss.setComplete();
    return REDIRECT;
  }

  @GetMapping("/edit/{id}")
  public String editar(@PathVariable("id") String id, Model model, RedirectAttributes attribute) {
    Editorial editorial = null;

    if (id.length() > 0) {
      editorial = editorialServicio.buscarPorId(id);
      if (editorial == null) {
        attribute.addFlashAttribute(ERROR, "ATENCION: El ID de la editorial no existe!");
        return REDIRECT;
      }
    } else {
      attribute.addFlashAttribute(ERROR, "ATENCION: Error con el ID de la editorial");
      return REDIRECT;
    }
    model.addAttribute(TITULO, "Editar");
    model.addAttribute("h1", "Formulario : Editar Editorial");
    model.addAttribute(EDITORIAL2, editorial);
    return "/editorial/editarEditorial";
  }

  @GetMapping("/delete/{id}")
  public String eliminar(@PathVariable("id") String id, RedirectAttributes attribute) {
    Editorial editorial = null;

    if (id.length() > 0) {
      editorial = editorialServicio.buscarPorId(id);
      if (editorial == null) {
        attribute.addFlashAttribute(ERROR, "ATENCION: El ID de la editorial no existe!");
        return REDIRECT;
      }
    } else {
      attribute.addFlashAttribute(ERROR, "ATENCION: Error con el ID de la editorial");
      return REDIRECT;
    }
    editorialServicio.eliminar(id);
    attribute.addFlashAttribute("warning", "Registro Eliminado con Exito!");
    return REDIRECT;
  }
}
