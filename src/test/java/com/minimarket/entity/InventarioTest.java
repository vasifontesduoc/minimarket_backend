package com.minimarket.entity;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class InventarioTest {

    @Test
    void deberiaCrearInventarioCorrectamente() {

        Categoria categoria = new Categoria();
        categoria.setId(1L);
        categoria.setNombre("Lácteos");

        Producto producto = new Producto();
        producto.setId(10L);
        producto.setNombre("Leche");
        producto.setPrecio(1500.0);
        producto.setStock(20);
        producto.setCategoria(categoria);

        Inventario inventario = new Inventario();
        inventario.setId(1L);
        inventario.setProducto(producto);
        inventario.setCantidad(15);
        inventario.setTipoMovimiento("Entrada");
        inventario.setFechaMovimiento(new Date());

        assertEquals(1L, inventario.getId());
        assertEquals(producto, inventario.getProducto());
        assertEquals(15, inventario.getCantidad());
        assertEquals("Entrada", inventario.getTipoMovimiento());
        assertNotNull(inventario.getFechaMovimiento());
    }
}