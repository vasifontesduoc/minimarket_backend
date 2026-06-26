package com.minimarket;

import com.minimarket.entity.DetalleVenta;
import com.minimarket.entity.Producto;
import com.minimarket.entity.Venta;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DetalleVentaTest {

    @Test
    public void testCrearDetalleVenta() {

        Producto producto = new Producto();
        producto.setNombre("Aceite");

        Venta venta = new Venta();

        DetalleVenta detalle = new DetalleVenta();

        detalle.setVenta(venta);
        detalle.setProducto(producto);
        detalle.setCantidad(3);
        detalle.setPrecio(2500.0);

        assertNotNull(detalle);

        assertEquals("Aceite",
                detalle.getProducto().getNombre());

        assertEquals(3,
                detalle.getCantidad());

        assertTrue(detalle.getPrecio() > 0);

        assertNotNull(detalle.getVenta());
    }
}