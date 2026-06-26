package com.minimarket;

import com.minimarket.entity.Categoria;
import com.minimarket.entity.Producto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProductoTest {

    @Test
    public void testCrearProducto() {

        Categoria categoria = new Categoria();
        categoria.setId(1L);
        categoria.setNombre("Abarrotes");

        Producto producto = new Producto();

        producto.setId(1L);
        producto.setNombre("Leche");
        producto.setPrecio(2500.0);
        producto.setStock(15);
        producto.setCategoria(categoria);

        assertNotNull(producto);

        assertEquals(1L, producto.getId());
        assertEquals("Leche", producto.getNombre());
        assertEquals(2500.0, producto.getPrecio());
        assertEquals(15, producto.getStock());

        assertNotNull(producto.getCategoria());
        assertEquals("Abarrotes",
                producto.getCategoria().getNombre());
    }

    @Test
    public void testModificarProducto() {

        Producto producto = new Producto();

        producto.setNombre("Arroz");
        producto.setPrecio(1800.0);
        producto.setStock(30);

        assertEquals("Arroz", producto.getNombre());
        assertEquals(1800.0, producto.getPrecio());
        assertEquals(30, producto.getStock());
    }
}
