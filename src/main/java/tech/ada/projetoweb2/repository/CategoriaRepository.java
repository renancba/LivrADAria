package tech.ada.projetoweb2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.ada.projetoweb2.model.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
