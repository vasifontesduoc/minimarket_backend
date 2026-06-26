package com.minimarket.entity;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CategoriaTest {

    @Test
    void deberiaCrearCategoriaCorrectamente() {

        Categoria categoria = new Categoria();
        categoria.setId(1L);
        categoria.setNombre("Lácteos");

        Producto producto = new Producto();
        producto.setId(10L);
        producto.setNombre("Leche");
        producto.setPrecio(1500.0);
        producto.setStock(20);
        producto.setCategoria(categoria);

        categoria.setProductos(List.of(producto));

        assertEquals(1L, categoria.getId());
        assertEquals("Lácteos", categoria.getNombre());
        assertEquals(1, categoria.getProductos().size());
        assertEquals(producto, categoria.getProductos().get(0));
    }

    @Test
    void deberiaModificarCategoriaConSetters() {

        Categoria categoria = new Categoria();

        categoria.setId(2L);
        categoria.setNombre("Bebidas");
        categoria.setProductos(List.of());

        assertEquals(2L, categoria.getId());
        assertEquals("Bebidas", categoria.getNombre());
        assertTrue(categoria.getProductos().isEmpty());
    }
}