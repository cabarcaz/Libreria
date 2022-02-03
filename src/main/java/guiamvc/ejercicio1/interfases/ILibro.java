package guiamvc.ejercicio1.interfases;

import java.util.List;

import guiamvc.ejercicio1.entidades.Libro;

public interface ILibro {
  public List<Libro>listarTodos();
  public void guardar(Libro libro);
  public Libro buscarPorId(String id);
  public void eliminar(String id); 
}
