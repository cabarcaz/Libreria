package guiamvc.ejercicio1.repositorios;

import java.util.List;

import guiamvc.ejercicio1.entidades.Libro;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



@Repository
public interface LibroRepositorio extends JpaRepository<Libro, String> {

  @Query("SELECT l FROM Libro l ORDER BY l.id ASC ")
  
  List<Libro> listaCorta();
}
