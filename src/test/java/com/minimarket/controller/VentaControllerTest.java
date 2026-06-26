package com.minimarket.controller;

import com.minimarket.entity.Venta;
import com.minimarket.service.VentaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VentaControllerTest {

    @Mock
    private VentaService ventaService;

    @InjectMocks
    private VentaController ventaController;

    @Test
    void deberiaListarVentas() {

        when(ventaService.findAll())
                .thenReturn(Arrays.asList(new Venta(), new Venta()));

        assertEquals(2, ventaController.listarVentas().size());

        verify(ventaService).findAll();
    }

    @Test
    void deberiaObtenerVentaPorId() {

        Venta venta = new Venta();

        when(ventaService.findById(1L)).thenReturn(venta);

        ResponseEntity<Venta> respuesta = ventaController.obtenerVentaPorId(1L);

        assertEquals(200, respuesta.getStatusCode().value());

        verify(ventaService).findById(1L);
    }

    @Test
    void deberiaGuardarVenta() {

        Venta venta = new Venta();

        when(ventaService.save(venta)).thenReturn(venta);

        Venta resultado = ventaController.guardarVenta(venta);

        assertNotNull(resultado);

        verify(ventaService).save(venta);
    }

    @Test
    void deberiaRetornarNotFoundCuandoVentaNoExiste() {

        when(ventaService.findById(1L)).thenReturn(null);

        ResponseEntity<Venta> respuesta = ventaController.obtenerVentaPorId(1L);

        assertEquals(404, respuesta.getStatusCode().value());

        verify(ventaService).findById(1L);
    }
}