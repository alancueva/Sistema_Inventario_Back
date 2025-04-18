package com.inventario.inventario.service;


import com.inventario.inventario.model.Unidad_Medida.UnidadMedida;
import com.inventario.inventario.model.Unidad_Medida.UnidadMedidaDTO;
import com.inventario.inventario.repository.UnidadMedidaRepository;
import com.inventario.inventario.repository.Unidad_medidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnidadMedidaService {

    @Autowired
    private Unidad_medidaRepository unidadMedidaRepository;

    @Autowired
    public UnidadMedidaService(Unidad_medidaRepository repository) {
        this.unidadMedidaRepository = repository;
    }

    public List<UnidadMedidaDTO> buscarUnidadMedida(String um_descripcion) {
        try {
            return unidadMedidaRepository.buscarUnidadMedida(um_descripcion);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public UnidadMedidaDTO getUnidadMedidaById(Integer id) {
        List<UnidadMedidaDTO> resultado = unidadMedidaRepository.RecuperarUnidadMedida(id);
        return resultado.isEmpty() ? null : resultado.get(0);
    }

    // Buscar unidades de medida por descripción
    public List<UnidadMedidaDTO> buscarPorDescripcion(String descripcion) {
        return unidadMedidaRepository.buscarUnidadMedida("%" + descripcion + "%");
    }

    public List<UnidadMedidaDTO> RecuperarUnidadMedida(int um_id_unidadmedida) {
        try {
            return unidadMedidaRepository.RecuperarUnidadMedida(um_id_unidadmedida);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUnidadMedida(UnidadMedidaDTO unidadMedida) {
        if (unidadMedida.getIdunidadmedida() == null) {
            unidadMedidaRepository.insertarUnidadMedida(
                    unidadMedida.getDescripcion(),
                    unidadMedida.getSimbolo()
            );
        } else {
            unidadMedidaRepository.actualizarUnidadMedida(
                    unidadMedida.getIdunidadmedida(),
                    unidadMedida.getDescripcion(),
                    unidadMedida.getSimbolo()
            );
        }
    }
}
