package guiamvc.ejercicio1.repositorios;

import guiamvc.ejercicio1.entidades.Editorial;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EditorialRepositorio extends JpaRepository<Editorial, String> {}