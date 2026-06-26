package com.minimarket.entity;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class VentaTest {

    @Test
    void deberiaCrearVentaCorrectamente() {

        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setUsername("cajero");

        Venta venta = new Venta();
        venta.setId(100L);
        venta.setUsuario(usuario);
        venta.setFecha(new Date());

        assertEquals(100L, venta.getId());
        assertEquals(usuario, venta.getUsuario());
        assertNotNull(venta.getFecha());
    }
}