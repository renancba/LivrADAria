package tech.ada.projetoweb2.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.ada.projetoweb2.dto.LivroDTO;
import tech.ada.projetoweb2.mapper.CategoriaMapper;
import tech.ada.projetoweb2.mapper.EditoraMapper;
import tech.ada.projetoweb2.mapper.LivroMapper;
import tech.ada.projetoweb2.model.Categoria;
import tech.ada.projetoweb2.model.Editora;
import tech.ada.projetoweb2.model.Livro;
import tech.ada.projetoweb2.repository.CategoriaRepository;
import tech.ada.projetoweb2.repository.EditoraRepository;
import tech.ada.projetoweb2.repository.LivroRepository;

import java.util.List;
import java.util.Optional;

@Service
public class LivroService {

    @Autowired
    LivroRepository livroRepository;

    @Autowired
    LivroMapper livroMapper;

    @Autowired
    CategoriaRepository categoriaRepository;

    @Autowired
    EditoraRepository editoraRepository;

    @Autowired
    CategoriaMapper categoriaMapper;

    @Autowired
    EditoraMapper editoraMapper;

    public List<LivroDTO> listar() {
        return livroMapper.updateListLivroEntity(livroRepository.findAll());
    }

    public LivroDTO getById(Long id) {
        Optional<Livro> editoraOptional = livroRepository.findById(id);
        if (editoraOptional.isPresent()) {
            return livroMapper.updateLivro(editoraOptional.get());
        }
        throw new EntityNotFoundException("Livro não encontrado.");
    }

    public LivroDTO criar(LivroDTO livroDTO) {
        Livro LivroSave = livroRepository.save(livroMapper.updateLivro(livroDTO));
        return livroMapper.updateLivro(LivroSave);
    }

    public void deletar(Long id){
        Optional<Livro> editoraOptional = livroRepository.findById(id);
        if (editoraOptional.isPresent()) {
            livroRepository.deleteById(id);
        }
        throw new EntityNotFoundException("Livro não encontrado.");
    }

    public LivroDTO editar(LivroDTO livroDTO, Long id){
        if (livroRepository.existsById(id)){
            Livro livro = livroMapper.updateLivro(livroDTO);
            livro.setId(livroDTO.getId());
            livroRepository.save(livro);
            return livroMapper.updateLivro(livro);
        }
        throw new EntityNotFoundException("Livro não encontrado.");
    }

    public List<LivroDTO> getByCategoria(Long categoriaId){
        Optional<Categoria> categoriaOptional = categoriaRepository.findById(categoriaId);
        if (categoriaOptional.isPresent()){
        List<Livro> livros = livroRepository.findLivroByCategoria(categoriaOptional.get());
        return livroMapper.updateListLivroEntity(livros);
        }
        throw new EntityNotFoundException("Categoria não encontrada.");
    }

    public List<LivroDTO> getByEditora(Long editoraId){
        Optional<Editora> editoraOptional = editoraRepository.findById(editoraId);
        if (editoraOptional.isPresent()){
            List<Livro> livros = livroRepository.findLivroByEditora(editoraOptional.get());
            return livroMapper.updateListLivroEntity(livros);
        }
        throw new EntityNotFoundException("Editora não encontrada.");
    }

    public List<LivroDTO> getByNomeAndIsbn(String nomeLivro, String isbn){
        List<Livro> livros = livroRepository.findByNomeAndIsbn(nomeLivro, isbn);
        return livroMapper.updateListLivroEntity(livros);
    }
}

