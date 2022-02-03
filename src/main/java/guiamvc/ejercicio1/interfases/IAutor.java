package guiamvc.ejercicio1.interfases;

import java.util.List;

import guiamvc.ejercicio1.entidades.Autor;

public interface IAutor {
  public List<Autor>listarTodos();
  public void guardar(Autor autor);
  public Autor buscarPorId(String id);
  public void eliminar(String id);
}
