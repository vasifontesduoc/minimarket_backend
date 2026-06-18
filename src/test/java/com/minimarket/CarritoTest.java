package com.minimarket;

import com.minimarket.entity.Carrito;
import com.minimarket.entity.Producto;
import com.minimarket.entity.Usuario;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CarritoTest {

    @Test
    public void testCrearCarrito() {

        Usuario usuario = new Usuario();
        usuario.setUsername("cliente1");

        Producto producto = new Producto();
        producto.setNombre("Arroz");
        producto.setStock(10);

        Carrito carrito = new Carrito();
        carrito.setUsuario(usuario);
        carrito.setProducto(producto);
        carrito.setCantidad(2);

        assertNotNull(carrito);

        assertEquals("cliente1",
                carrito.getUsuario().getUsername());

        assertEquals("Arroz",
                carrito.getProducto().getNombre());

        assertEquals(2,
                carrito.getCantidad());
    }

    @Test
    public void testCantidadValida() {

        Carrito carrito = new Carrito();

        carrito.setCantidad(5);

        assertTrue(carrito.getCantidad() > 0);
    }

    @Test
    public void testStockDisponible() {

        Producto producto = new Producto();

        producto.setStock(20);

        assertTrue(producto.getStock() > 0);
    }
}
