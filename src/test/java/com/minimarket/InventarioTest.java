package com.minimarket;

import com.minimarket.entity.Inventario;
import com.minimarket.entity.Producto;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class InventarioTest {

    @Test
    public void testCrearInventario() {

        Producto producto = new Producto();
        producto.setNombre("Azúcar");

        Inventario inventario = new Inventario();

        inventario.setProducto(producto);
        inventario.setCantidad(10);
        inventario.setTipoMovimiento("Entrada");
        inventario.setFechaMovimiento(new Date());

        assertNotNull(inventario);

        assertEquals("Azúcar",
                inventario.getProducto().getNombre());

        assertEquals(10,
                inventario.getCantidad());

        assertEquals("Entrada",
                inventario.getTipoMovimiento());

        assertNotNull(inventario.getFechaMovimiento());
    }

    @Test
    public void testCantidadInventario() {

        Inventario inventario = new Inventario();

        inventario.setCantidad(8);

        assertTrue(inventario.getCantidad() > 0);
    }

    @Test
    public void testTipoMovimiento() {

        Inventario inventario = new Inventario();

        inventario.setTipoMovimiento("Salida");

        assertEquals("Salida",
                inventario.getTipoMovimiento());
    }
}