package guiamvc.ejercicio1.interfases;

import java.util.List;

import guiamvc.ejercicio1.entidades.Editorial;

public interface IEditorial {

  public List<Editorial>listarTodos();
  public void guardar(Editorial editorial);
  public Editorial buscarPorId(String id);
  public void eliminar(String id);
  
}
