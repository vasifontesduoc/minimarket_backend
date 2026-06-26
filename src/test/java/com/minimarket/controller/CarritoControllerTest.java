package com.minimarket.controller;

import com.minimarket.entity.Carrito;
import com.minimarket.service.CarritoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class CarritoControllerTest {

    @Mock
    private CarritoService carritoService;

    @InjectMocks
    private CarritoController carritoController;

    @Test
    void deberiaListarCarrito() {

        Carrito carrito = new Carrito();
        carrito.setId(1L);

        when(carritoService.findAll()).thenReturn(List.of(carrito));

        List<Carrito> resultado = carritoController.listarCarrito();

        assertEquals(1, resultado.size());
        verify(carritoService).findAll();
    }

    @Test
    void deberiaObtenerCarritoExistente() {

        Carrito carrito = new Carrito();
        carrito.setId(1L);

        when(carritoService.findById(1L)).thenReturn(carrito);

        ResponseEntity<Carrito> response = carritoController.obtenerCarritoPorId(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(carrito, response.getBody());
    }

    @Test
    void deberiaRetornarNotFoundSiCarritoNoExiste() {

        when(carritoService.findById(1L)).thenReturn(null);

        ResponseEntity<Carrito> response = carritoController.obtenerCarritoPorId(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void deberiaGuardarCarrito() {

        Carrito carrito = new Carrito();

        when(carritoService.save(carrito)).thenReturn(carrito);

        Carrito resultado = carritoController.agregarProductoAlCarrito(carrito);

        assertEquals(carrito, resultado);

        verify(carritoService).save(carrito);
    }

    @Test
    void deberiaActualizarCarritoExistente() {

        Carrito carrito = new Carrito();
        carrito.setId(1L);

        when(carritoService.findById(1L)).thenReturn(carrito);
        when(carritoService.save(any(Carrito.class))).thenReturn(carrito);

        ResponseEntity<Carrito> response = carritoController.actualizarCarrito(1L, carrito);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        verify(carritoService).save(carrito);
    }

    @Test
    void deberiaRetornarNotFoundAlActualizarCarritoInexistente() {

        Carrito carrito = new Carrito();

        when(carritoService.findById(1L)).thenReturn(null);

        ResponseEntity<Carrito> response = carritoController.actualizarCarrito(1L, carrito);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

        verify(carritoService, never()).save(any());
    }

    @Test
    void deberiaEliminarCarritoExistente() {

        Carrito carrito = new Carrito();

        when(carritoService.findById(1L)).thenReturn(carrito);

        ResponseEntity<Void> response = carritoController.eliminarProductoDelCarrito(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

        verify(carritoService).deleteById(1L);
    }

    @Test
    void deberiaRetornarNotFoundAlEliminarCarritoInexistente() {

        when(carritoService.findById(1L)).thenReturn(null);

        ResponseEntity<Void> response = carritoController.eliminarProductoDelCarrito(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

        verify(carritoService, never()).deleteById(anyLong());
    }
}