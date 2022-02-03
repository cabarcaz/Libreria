package guiamvc.ejercicio1.repositorios;

import guiamvc.ejercicio1.entidades.Autor;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutorRepositorio extends CrudRepository<Autor, String> {}
