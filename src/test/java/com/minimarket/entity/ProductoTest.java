package com.minimarket.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductoTest {

    @Test
    void deberiaCrearProductoCorrectamente() {

        Categoria categoria = new Categoria();
        categoria.setId(1L);
        categoria.setNombre("Lácteos");

        Producto producto = new Producto();
        producto.setId(10L);
        producto.setNombre("Leche");
        producto.setPrecio(1500.0);
        producto.setStock(25);
        producto.setCategoria(categoria);

        assertEquals(10L, producto.getId());
        assertEquals("Leche", producto.getNombre());
        assertEquals(1500.0, producto.getPrecio());
        assertEquals(25, producto.getStock());
        assertEquals(categoria, producto.getCategoria());
    }
}
