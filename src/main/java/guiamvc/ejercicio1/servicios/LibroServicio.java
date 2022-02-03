package guiamvc.ejercicio1.servicios;

import java.util.List;
import guiamvc.ejercicio1.entidades.Libro;
import guiamvc.ejercicio1.interfases.ILibro;
import guiamvc.ejercicio1.repositorios.LibroRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibroServicio implements ILibro{
  
@Autowired
private LibroRepositorio libroRepositorio;

@Override
public Libro buscarPorId(String id) {
  return libroRepositorio.findById(id).orElse(null);
}
@Override
public void eliminar(String id) {
libroRepositorio.deleteById(id);
}

@Override
public void guardar(Libro libro) {
  libroRepositorio.save(libro);
}

@Override
public List<Libro> listarTodos() {
  return libroRepositorio.findAll() ;
}

public List<Libro> lista(){ 
  
  return libroRepositorio.listaCorta();
}
}
