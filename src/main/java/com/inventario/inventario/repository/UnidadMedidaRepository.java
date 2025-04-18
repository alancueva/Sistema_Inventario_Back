package com.inventario.inventario.repository;

import com.inventario.inventario.model.Unidad_Medida.UnidadMedida;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

@Repository
public class UnidadMedidaRepository {

    /**
     * Este proyecto utiliza JdbcTemplate como ejemplo para demostrar operaciones con bases de datos.
     * La elección se basa en las siguientes razones:
     * 1. **Simplicidad conceptual**: Para alguien que recién comienza, es más claro ver exactamente qué código
     *    ejecuta cada operación, sin depender de la "magia" detrás de Spring Data JPA.
     * 2. **Menor abstracción**: JdbcTemplate está más cerca del SQL puro y los procedimientos almacenados, lo que facilita
     *    entender cómo se ejecutan realmente las consultas y operaciones en la base de datos.
     * 3. **Menos configuración**: No es necesario configurar un proveedor de JPA, mapeos de entidades ni otros componentes
     *    adicionales, lo que simplifica el desarrollo y mantenimiento.
     *
     * Este enfoque es solo un ejemplo para facilitar la comprensión inicial. En proyectos más complejos, otras herramientas
     * como Spring Data JPA podrían ser más adecuadas dependiendo de los requisitos.
     */


    private final JdbcTemplate jdbcTemplate;

    // Creamos un RowMapper reutilizable para mapear resultados a objetos UnidadMedida
    private final RowMapper<UnidadMedida> unidadMedidaRowMapper = (rs, rowNum) -> {
        UnidadMedida unidadMedida = new UnidadMedida();
        unidadMedida.setIdUnidadMedida(rs.getInt("id_unidadmedida"));
        unidadMedida.setDescripcion(rs.getString("descripcion"));
        unidadMedida.setSimbolo(rs.getString("simbolo"));
        return unidadMedida;
    };

    @Autowired
    public UnidadMedidaRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Método para recuperar una unidad de medida por ID
    public UnidadMedida getUnidadMedidaById(Integer id) {
        String sql = "CALL sp_unidad_medida_recuperar(?)";
        List<UnidadMedida> resultados = jdbcTemplate.query(sql, new Object[]{id}, unidadMedidaRowMapper);
        return resultados.isEmpty() ? null : resultados.get(0);
    }

    // Método simplificado para buscar unidades de medida por descripción
    public List<UnidadMedida> buscarPorDescripcion(String descripcion) {
        String searchTerm = "%" + descripcion + "%";
        String sql = "CALL sp_unidad_medida_buscar_descripcion(?)";
        return jdbcTemplate.query(sql, new Object[]{searchTerm}, unidadMedidaRowMapper);
    }


    public void insertUnidadMedida(UnidadMedida unidadMedida) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("sp_unidad_medida_insert");

        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("um_descripcion", unidadMedida.getDescripcion())
                .addValue("um_simbolo", unidadMedida.getSimbolo());

        jdbcCall.execute(parameters);
    }

    public void updateUnidadMedida(UnidadMedida unidadMedida) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("sp_unidad_medida_update");

        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("um_id_unidadmedida", unidadMedida.getIdUnidadMedida())
                .addValue("um_descripcion", unidadMedida.getDescripcion())
                .addValue("um_simbolo", unidadMedida.getSimbolo());

        jdbcCall.execute(parameters);
    }
}
