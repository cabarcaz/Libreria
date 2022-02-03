package guiamvc.ejercicio1.servicios;

import java.util.List;
import guiamvc.ejercicio1.entidades.Editorial;
import guiamvc.ejercicio1.interfases.IEditorial;
import guiamvc.ejercicio1.repositorios.EditorialRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EditorialServicio implements IEditorial{

  @Autowired
  private EditorialRepositorio editorialRepositorio;

  @Override
  public Editorial buscarPorId(String id) { 
    return editorialRepositorio.findById(id).orElse(null);
  }
  @Override
  public void eliminar(String id) {
    editorialRepositorio.deleteById(id);
  }
  @Override
  public void guardar(Editorial editorial) {
    editorialRepositorio.save(editorial);
  }
  @Override
  public List<Editorial> listarTodos() {
    return editorialRepositorio.findAll();
  } 
}
