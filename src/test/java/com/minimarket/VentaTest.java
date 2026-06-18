package com.minimarket;

import com.minimarket.entity.DetalleVenta;
import com.minimarket.entity.Usuario;
import com.minimarket.entity.Venta;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class VentaTest {

    @Test
    public void testCrearVenta() {

        Usuario usuario = new Usuario();
        usuario.setUsername("cliente1");

        Venta venta = new Venta();

        venta.setUsuario(usuario);
        venta.setFecha(new Date());

        List<DetalleVenta> detalles = new ArrayList<>();
        venta.setDetalles(detalles);

        assertNotNull(venta);

        assertEquals("cliente1",
                venta.getUsuario().getUsername());

        assertNotNull(venta.getFecha());

        assertEquals(0,
                venta.getDetalles().size());
    }

    @Test
    public void testAgregarDetalles() {

        Venta venta = new Venta();

        List<DetalleVenta> detalles = new ArrayList<>();

        DetalleVenta detalle = new DetalleVenta();

        detalles.add(detalle);

        venta.setDetalles(detalles);

        assertEquals(1,
                venta.getDetalles().size());
    }
}