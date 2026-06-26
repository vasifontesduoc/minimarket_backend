package com.minimarket;

import com.minimarket.entity.Categoria;
import com.minimarket.entity.Producto;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CategoriaTest {

    @Test
    public void testCrearCategoria() {

        Categoria categoria = new Categoria();

        categoria.setId(1L);
        categoria.setNombre("Abarrotes");

        List<Producto> productos = new ArrayList<>();

        Producto producto = new Producto();
        producto.setNombre("Arroz");

        productos.add(producto);

        categoria.setProductos(productos);

        assertNotNull(categoria);

        assertEquals(1L, categoria.getId());
        assertEquals("Abarrotes", categoria.getNombre());
        assertNotNull(categoria.getProductos());
        assertEquals(1, categoria.getProductos().size());
        assertEquals("Arroz",
                categoria.getProductos().get(0).getNombre());
    }
}