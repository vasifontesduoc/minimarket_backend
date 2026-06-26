package com.minimarket.controller;

import com.minimarket.entity.Inventario;
import com.minimarket.service.InventarioService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InventarioControllerTest {

    @Mock
    private InventarioService inventarioService;

    @InjectMocks
    private InventarioController inventarioController;

    @Test
    void deberiaListarMovimientos() {

        when(inventarioService.findAll())
                .thenReturn(Arrays.asList(new Inventario(), new Inventario()));

        assertEquals(2, inventarioController.listarMovimientosDeInventario().size());

        verify(inventarioService).findAll();
    }

    @Test
    void deberiaObtenerMovimientoPorId() {

        Inventario inventario = new Inventario();

        when(inventarioService.findById(1L))
                .thenReturn(inventario);

        ResponseEntity<Inventario> respuesta = inventarioController.obtenerMovimientoPorId(1L);

        assertEquals(200, respuesta.getStatusCode().value());

        verify(inventarioService).findById(1L);
    }

    @Test
    void deberiaRegistrarMovimiento() {

        Inventario inventario = new Inventario();

        when(inventarioService.save(inventario))
                .thenReturn(inventario);

        Inventario resultado = inventarioController.registrarMovimiento(inventario);

        assertNotNull(resultado);

        verify(inventarioService).save(inventario);
    }

    @Test
    void deberiaActualizarMovimiento() {

        Inventario inventario = new Inventario();

        when(inventarioService.findById(1L))
                .thenReturn(inventario);

        when(inventarioService.save(any()))
                .thenReturn(inventario);

        ResponseEntity<Inventario> respuesta = inventarioController.actualizarMovimiento(1L, inventario);

        assertEquals(200, respuesta.getStatusCode().value());

        verify(inventarioService).save(any());
    }

    @Test
    void deberiaEliminarMovimiento() {

        Inventario inventario = new Inventario();

        when(inventarioService.findById(1L))
                .thenReturn(inventario);

        ResponseEntity<Void> respuesta = inventarioController.eliminarMovimiento(1L);

        assertEquals(204, respuesta.getStatusCode().value());

        verify(inventarioService).deleteById(1L);
    }

    @Test
    void deberiaRetornarNotFoundCuandoMovimientoNoExiste() {

        when(inventarioService.findById(1L)).thenReturn(null);

        ResponseEntity<Inventario> respuesta = inventarioController.obtenerMovimientoPorId(1L);

        assertEquals(404, respuesta.getStatusCode().value());

        verify(inventarioService).findById(1L);
    }

    @Test
    void deberiaRetornarNotFoundAlActualizarMovimiento() {

        Inventario inventario = new Inventario();

        when(inventarioService.findById(1L)).thenReturn(null);

        ResponseEntity<Inventario> respuesta = inventarioController.actualizarMovimiento(1L, inventario);

        assertEquals(404, respuesta.getStatusCode().value());

        verify(inventarioService).findById(1L);

        verify(inventarioService, never()).save(any());
    }

    @Test
    void deberiaRetornarNotFoundAlEliminarMovimiento() {

        when(inventarioService.findById(1L)).thenReturn(null);

        ResponseEntity<Void> respuesta = inventarioController.eliminarMovimiento(1L);

        assertEquals(404, respuesta.getStatusCode().value());

        verify(inventarioService).findById(1L);

        verify(inventarioService, never()).deleteById(any());
    }
}