package com.curso.services;

import java.util.List;
import java.util.stream.Collectors;

import com.curso.domains.dtos.ProdutoDTO;
import com.curso.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepo;

    public List<ProdutoDTO> findAll(){
        //retorna uma lista de ProdutoDTO
        return produtoRepo.findAll().stream()
                .map(obj -> new ProdutoDTO(obj))
                .collect(Collectors.toList());
    }
}