package com.inventario.inventario.service;

import com.inventario.inventario.model.Categoria.CategoriaDTO;
import com.inventario.inventario.model.Categoria.ConsultaCategoria;
import com.inventario.inventario.model.Categoria.InsertarCategoria;
import com.inventario.inventario.model.Categoria.UpdateCategoria;
import com.inventario.inventario.repository.CategoriaRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Transactional(readOnly = true)
    public List<ConsultaCategoria> MostrarNombreCategoria(){
        return categoriaRepository.MostrarNombreCategia();
    }

    public List<ConsultaCategoria> ConsultarCategoria(String descripcion){
        return categoriaRepository.ConsultarCategoria(descripcion);
    }

    public CategoriaDTO RecuperarCategoria(int id){
        return categoriaRepository.RecuperaCategoria(id);
    }

    public void insertarCategoria(InsertarCategoria categoria) {
        categoriaRepository.InsertarCategoria(
                categoria.getNombreCategoria(),
                categoria.getDescripcion(),
                categoria.getVigencia(),
                categoria.getUsuarioCreacion()
        );
    }
    public void updateCategoria(UpdateCategoria categoria) {
        categoriaRepository.actualizarCategoria(
                categoria.getIdCategoria(),
                categoria.getNombreCategoria(),
                categoria.getDescripcion(),
                categoria.getVigencia(),
                categoria.getUsuarioCreacion()
        );
    }





}
