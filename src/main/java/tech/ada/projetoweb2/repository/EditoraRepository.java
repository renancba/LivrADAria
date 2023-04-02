package tech.ada.projetoweb2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.ada.projetoweb2.model.Editora;

@Repository
public interface EditoraRepository extends JpaRepository<Editora, Long> {
}
