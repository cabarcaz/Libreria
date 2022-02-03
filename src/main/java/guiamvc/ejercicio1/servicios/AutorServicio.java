package guiamvc.ejercicio1.servicios;

import java.util.List;
import guiamvc.ejercicio1.entidades.Autor;
import guiamvc.ejercicio1.interfases.IAutor;
import guiamvc.ejercicio1.repositorios.AutorRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutorServicio implements IAutor {
  @Autowired
  private AutorRepositorio autorRepositorio;

  @Override
  public List<Autor> listarTodos() {
    return (List<Autor>) autorRepositorio.findAll();
  }
  @Override
  public void guardar(Autor autor) {
    autorRepositorio.save(autor);
  }
  @Override
  public Autor buscarPorId(String id) {
    return autorRepositorio.findById(id).orElse(null);
  }
  @Override
  public void eliminar(String id) {
    autorRepositorio.deleteById(id);
  }
}
