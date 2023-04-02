package tech.ada.projetoweb2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tech.ada.projetoweb2.model.Categoria;
import tech.ada.projetoweb2.model.Editora;
import tech.ada.projetoweb2.model.Livro;

import java.util.List;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {

    List<Livro> findLivroByCategoria(Categoria categoria);

    List<Livro> findLivroByEditora(Editora editora);

    @Query("SELECT l FROM Livro l WHERE l.nome LIKE CONCAT('%',UPPER(:termoNome),'%')AND l.isbn = :termoIsbn")
    List<Livro> findByNomeAndIsbn(@Param("termoNome") String termoNome, @Param("termoIsbn") String termoIsbn);
}
