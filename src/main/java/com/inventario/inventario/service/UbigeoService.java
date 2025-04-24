package com.inventario.inventario.service;

import com.inventario.inventario.model.Ubigeo.UbigeoDTO;
import com.inventario.inventario.repository.UbigeoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UbigeoService {

    @Autowired
    private final UbigeoRepository ubigeoRepository;

    public UbigeoService(UbigeoRepository ubigeoRepository) {
        this.ubigeoRepository = ubigeoRepository;
    }

    public List<UbigeoDTO> obtenerUbigeos() {
        try {
            return ubigeoRepository.seleccionarUbigeos();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public UbigeoDTO buscarUbigeo(String ubigeoNombre) {
        try{
            return ubigeoRepository.BusquedaUbigeo(ubigeoNombre);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
