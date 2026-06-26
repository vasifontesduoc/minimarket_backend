package com.minimarket.controller;

import com.minimarket.entity.Producto;
import com.minimarket.service.ProductoService;
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
class ProductoControllerTest {

    @Mock
    private ProductoService productoService;

    @InjectMocks
    private ProductoController productoController;

    @Test
    void deberiaListarProductos() {

        when(productoService.findAll())
                .thenReturn(Arrays.asList(new Producto(), new Producto()));

        assertEquals(2, productoController.listarProductos().size());

        verify(productoService).findAll();
    }

    @Test
    void deberiaObtenerProductoPorId() {

        Producto producto = new Producto();

        when(productoService.findById(1L)).thenReturn(producto);

        ResponseEntity<Producto> respuesta = productoController.obtenerProductoPorId(1L);

        assertEquals(200, respuesta.getStatusCode().value());

        verify(productoService).findById(1L);
    }

    @Test
    void deberiaGuardarProducto() {

        Producto producto = new Producto();

        when(productoService.save(producto)).thenReturn(producto);

        Producto resultado = productoController.guardarProducto(producto);

        assertNotNull(resultado);

        verify(productoService).save(producto);
    }

    @Test
    void deberiaActualizarProducto() {

        Producto producto = new Producto();

        when(productoService.findById(1L)).thenReturn(producto);
        when(productoService.save(any())).thenReturn(producto);

        ResponseEntity<Producto> respuesta = productoController.actualizarProducto(1L, producto);

        assertEquals(200, respuesta.getStatusCode().value());

        verify(productoService).save(any());
    }

    @Test
    void deberiaEliminarProducto() {

        Producto producto = new Producto();

        when(productoService.findById(1L)).thenReturn(producto);

        ResponseEntity<Void> respuesta = productoController.eliminarProducto(1L);

        assertEquals(204, respuesta.getStatusCode().value());

        verify(productoService).deleteById(1L);
    }

    @Test
    void deberiaRetornarNotFoundCuandoProductoNoExiste() {

        when(productoService.findById(1L)).thenReturn(null);

        ResponseEntity<Producto> respuesta = productoController.obtenerProductoPorId(1L);

        assertEquals(404, respuesta.getStatusCode().value());

        verify(productoService).findById(1L);
    }

    @Test
    void deberiaRetornarNotFoundAlActualizarProducto() {

        Producto producto = new Producto();

        when(productoService.findById(1L)).thenReturn(null);

        ResponseEntity<Producto> respuesta = productoController.actualizarProducto(1L, producto);

        assertEquals(404, respuesta.getStatusCode().value());

        verify(productoService).findById(1L);

        verify(productoService, never()).save(any());
    }

    @Test
    void deberiaRetornarNotFoundAlEliminarProducto() {

        when(productoService.findById(1L)).thenReturn(null);

        ResponseEntity<Void> respuesta = productoController.eliminarProducto(1L);

        assertEquals(404, respuesta.getStatusCode().value());

        verify(productoService).findById(1L);

        verify(productoService, never()).deleteById(any());
    }
}
