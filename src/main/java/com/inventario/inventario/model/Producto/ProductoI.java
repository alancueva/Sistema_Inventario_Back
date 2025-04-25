package com.inventario.inventario.model.Producto;

import lombok.Data;

@Data
public class ProductoI {
    private int id_producto ;
    private int id_Proveedores ;
    private int id_Categoria ;
    private int id_unidadmedida ;
    private String numero_serie ;
    private String Nombre ;
    private String Descripcion ;
    private String fecha_adquisicion ;
    private String fecha_vencimiento ;
    private String Marca ;
    private String Modelo ;
    private String Estado ;
    private String Movimiento ;
    private double precio ;
    private double peso ;
    private double cantidad_entrada ;
    private double cantidad_salida ;
    private double cantidad_final ;
    private String Vigencia ;
    private String usuario_creacion ;

}
