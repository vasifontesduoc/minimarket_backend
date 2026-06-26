package com.minimarket.service.impl;

import com.minimarket.entity.Producto;
import com.minimarket.repository.ProductoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductoServiceImplTest {

    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private ProductoServiceImpl productoService;

    @Test
    void deberiaListarProductos() {
        when(productoRepository.findAll()).thenReturn(Arrays.asList(new Producto(), new Producto()));

        assertEquals(2, productoService.findAll().size());

        verify(productoRepository).findAll();
    }

    @Test
    void deberiaBuscarProductoPorId() {
        Producto producto = new Producto();

        when(productoRepository.findById(1L)).thenReturn(Optional.of(producto));

        assertNotNull(productoService.findById(1L));

        verify(productoRepository).findById(1L);
    }

    @Test
    void deberiaRetornarNullCuandoNoExisteProducto() {

        when(productoRepository.findById(10L)).thenReturn(Optional.empty());

        assertNull(productoService.findById(10L));

        verify(productoRepository).findById(10L);
    }

    @Test
    void deberiaGuardarProducto() {

        Producto producto = new Producto();

        when(productoRepository.save(producto)).thenReturn(producto);

        assertEquals(producto, productoService.save(producto));

        verify(productoRepository).save(producto);
    }

    @Test
    void deberiaEliminarProducto() {

        productoService.deleteById(5L);

        verify(productoRepository).deleteById(5L);
    }
}