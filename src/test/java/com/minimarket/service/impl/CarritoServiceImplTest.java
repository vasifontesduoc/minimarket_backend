package com.minimarket.service.impl;

import com.minimarket.entity.Carrito;
import com.minimarket.repository.CarritoRepository;
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
class CarritoServiceImplTest {

    @Mock
    private CarritoRepository carritoRepository;

    @InjectMocks
    private CarritoServiceImpl carritoService;

    @Test
    void deberiaListarCarritos() {
        when(carritoRepository.findAll())
                .thenReturn(Arrays.asList(new Carrito(), new Carrito()));

        assertEquals(2, carritoService.findAll().size());

        verify(carritoRepository).findAll();
    }

    @Test
    void deberiaBuscarCarritoPorId() {
        Carrito carrito = new Carrito();

        when(carritoRepository.findById(1L))
                .thenReturn(Optional.of(carrito));

        assertNotNull(carritoService.findById(1L));

        verify(carritoRepository).findById(1L);
    }

    @Test
    void deberiaGuardarCarrito() {
        Carrito carrito = new Carrito();

        when(carritoRepository.save(carrito)).thenReturn(carrito);

        assertEquals(carrito, carritoService.save(carrito));

        verify(carritoRepository).save(carrito);
    }

    @Test
    void deberiaBuscarCarritosPorUsuario() {
        when(carritoRepository.findByUsuarioId(1L))
                .thenReturn(Arrays.asList(new Carrito(), new Carrito()));

        assertEquals(2, carritoService.findByUsuarioId(1L).size());

        verify(carritoRepository).findByUsuarioId(1L);
    }
}