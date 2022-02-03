package guiamvc.ejercicio1.controladores;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;

import javax.validation.Valid;

import guiamvc.ejercicio1.entidades.*;
import guiamvc.ejercicio1.servicios.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/libro")
public class LibroController {

  private static final String REDIRECT = "redirect:/libro/";
  private static final String ERROR = "error";
  private static final String LIBRO_NO_EXISTE = "ATENCION: El ID del libro no existe!";
  private static final String ERROR_ID = "ATENCION: Error con el ID del libro";
  private static final String EDITORIALES = "editoriales";
  private static final String AUTORES = "autores";
  private static final String LIBRO2 = "libro";
  private static final String TITULO = "titulo";

  @Autowired
  private LibroServicio libroServicio;

  @Autowired
  private AutorServicio autorServicio;

  @Autowired
  private EditorialServicio editorialServicio;

  @GetMapping("/")
  public String listarLibros(Model model) {

    List<Libro> listadoLibros = libroServicio.listarTodos();
    model.addAttribute("libros", listadoLibros);
    model.addAttribute(TITULO, "Listado de Libros");
    model.addAttribute("h1", "Libros disponibles");
    return "/libro/listadoLibros";
  }

  @GetMapping("/create")
  public String crear(Model model) {

    Libro libro = new Libro();
    List<Autor> listAutores = autorServicio.listarTodos();
    List<Editorial> listEditoriales = editorialServicio.listarTodos();

    model.addAttribute(TITULO, "Formulario");
    model.addAttribute("h1", "Formulario : Nuevo Libro");
    model.addAttribute(LIBRO2, libro);
    model.addAttribute(AUTORES, listAutores);
    model.addAttribute(EDITORIALES, listEditoriales);
    libro.setAlta(true);
    return "/libro/nuevoLibro";
  }

  @PostMapping("/save")
  public String guardar(@Valid @ModelAttribute Libro libro, SessionStatus ss, Model model, @RequestParam("file") MultipartFile imagen) {

    if(!imagen.isEmpty()){
      Path directorioImagenes = Paths.get("src//main//resources//static//images/libros");
      String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();
      try {
        byte[] bytesImg = imagen.getBytes();
        Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + imagen.getOriginalFilename());
        Files.write(rutaCompleta, bytesImg);
        libro.setImagen(imagen.getOriginalFilename());
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    libro.setEjemplaresRestantes(libro.getEjemplares());
    libroServicio.guardar(libro);
    ss.setComplete();
    return REDIRECT;
  }

  @GetMapping("/detalle/{id}")
  public String detalleLibro(@PathVariable("id") String id, Model model, RedirectAttributes attribute) {

    Libro libro = null;
		if (id.length() > 0) {
			libro = libroServicio.buscarPorId(id);
			if (libro == null) {
				attribute.addFlashAttribute(ERROR, LIBRO_NO_EXISTE);
				return REDIRECT;
			}
		}else {
			attribute.addFlashAttribute(ERROR, ERROR_ID);
			return REDIRECT;
		}
    List<Autor> listAutores = autorServicio.listarTodos();
    List<Editorial> listEditoriales = editorialServicio.listarTodos();

    model.addAttribute(TITULO, "Detalle");
    model.addAttribute("h1", "DetalleS del libro ");
    model.addAttribute(LIBRO2, libro);
    model.addAttribute(AUTORES, listAutores);
    model.addAttribute(EDITORIALES, listEditoriales);
    return "/libro/detalleLibro";
  }

  @GetMapping("/edit/{id}")
  public String editar(@PathVariable("id") String id, Model model, RedirectAttributes attribute) {

    Libro libro = null;
		
		if (id.length() > 0) {
			libro = libroServicio.buscarPorId(id);
			if (libro == null) {
        attribute.addFlashAttribute(ERROR, LIBRO_NO_EXISTE);
				return REDIRECT;
			}
		}else {
      attribute.addFlashAttribute(ERROR, ERROR_ID);
			return REDIRECT;
		}
    List<Autor> listAutores = autorServicio.listarTodos();
    List<Editorial> listEditoriales = editorialServicio.listarTodos();
    model.addAttribute(TITULO, "Editar");
    model.addAttribute("h1", "Formulario : Editar Libro");
    model.addAttribute(LIBRO2, libro);
    model.addAttribute(AUTORES, listAutores);
    model.addAttribute(EDITORIALES, listEditoriales);
    return "/libro/editarLibro";
  }

  @GetMapping("/delete/{id}")
  public String eliminar(@PathVariable("id") String id, RedirectAttributes attribute) {
     Libro libro = null;
		
		if (id.length() > 0) {
			libro = libroServicio.buscarPorId(id);
			if (libro == null) {
				attribute.addFlashAttribute(ERROR, LIBRO_NO_EXISTE);
				return REDIRECT;
			}
		}else {
			attribute.addFlashAttribute(ERROR, ERROR_ID);
			return REDIRECT;
		}
    libroServicio.eliminar(id);
    attribute.addFlashAttribute("warning", "Registro Eliminado con Exito!");
    return REDIRECT;
  }
}
