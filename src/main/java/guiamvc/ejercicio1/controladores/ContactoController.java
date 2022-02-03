package guiamvc.ejercicio1.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/contacto")
public class ContactoController {
  @GetMapping("/")
  public String index(Model model) {
    model.addAttribute("titulo", "Contacto");
    return "contacto.html";

  }
}
