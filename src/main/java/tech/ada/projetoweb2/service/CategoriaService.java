package tech.ada.projetoweb2.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.ada.projetoweb2.dto.CategoriaDTO;
import tech.ada.projetoweb2.mapper.CategoriaMapper;
import tech.ada.projetoweb2.model.Categoria;
import tech.ada.projetoweb2.repository.CategoriaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    CategoriaRepository categoriaRepository;

    @Autowired
    CategoriaMapper categoriaMapper;

    public List<CategoriaDTO> listar(){
        return categoriaMapper.updateListCategoriaEntity(categoriaRepository.findAll());
    }

    public CategoriaDTO getById(Long id){
        Optional<Categoria> categoriaOptional = categoriaRepository.findById(id);

        if (categoriaOptional.isPresent()){
            return categoriaMapper.updateCategoria(categoriaOptional.get());
        }

        throw new EntityNotFoundException("Categoria não encontrada.");
    }

    public void deletar(Long id){
        Optional<Categoria> categoriaOptional = categoriaRepository.findById(id);

        if (categoriaOptional.isPresent()){
             categoriaRepository.delete(categoriaOptional.get());
        }

        throw new EntityNotFoundException("Categoria não encontrada.");
    }

    public CategoriaDTO editar(CategoriaDTO categoria, Long id){
        if (categoriaRepository.existsById(id)){
            Categoria categoriaEntity = categoriaMapper.updateCategoria(categoria);
            categoriaEntity.setId(categoria.getId());
            Categoria categoriaSave = categoriaRepository.save(categoriaEntity);
            return categoriaMapper.updateCategoria(categoriaSave);
        }
        throw new EntityNotFoundException("Categoria não encontrada.");
    }

    public CategoriaDTO criar(CategoriaDTO categoriaDTO){
        Categoria categoriaSave = categoriaRepository.save(categoriaMapper.updateCategoria(categoriaDTO));
        return categoriaMapper.updateCategoria(categoriaSave);
    }
}
