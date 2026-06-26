package com.minimarket.service.impl;

import com.minimarket.entity.Venta;
import com.minimarket.repository.VentaRepository;
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
class VentaServiceImplTest {

    @Mock
    private VentaRepository ventaRepository;

    @InjectMocks
    private VentaServiceImpl ventaService;

    @Test
    void deberiaListarVentas() {

        when(ventaRepository.findAll())
                .thenReturn(Arrays.asList(new Venta(), new Venta()));

        assertEquals(2, ventaService.findAll().size());

        verify(ventaRepository).findAll();
    }

    @Test
    void deberiaBuscarVentaPorId() {

        Venta venta = new Venta();

        when(ventaRepository.findById(1L))
                .thenReturn(Optional.of(venta));

        assertNotNull(ventaService.findById(1L));

        verify(ventaRepository).findById(1L);
    }

    @Test
    void deberiaRetornarNullCuandoVentaNoExiste() {

        when(ventaRepository.findById(50L))
                .thenReturn(Optional.empty());

        assertNull(ventaService.findById(50L));

        verify(ventaRepository).findById(50L);
    }

    @Test
    void deberiaGuardarVenta() {

        Venta venta = new Venta();

        when(ventaRepository.save(venta))
                .thenReturn(venta);

        assertEquals(venta, ventaService.save(venta));

        verify(ventaRepository).save(venta);
    }

    @Test
    void deberiaBuscarVentasPorUsuario() {

        when(ventaRepository.findByUsuarioId(1L))
                .thenReturn(Arrays.asList(new Venta(), new Venta()));

        assertEquals(2, ventaService.findByUsuarioId(1L).size());

        verify(ventaRepository).findByUsuarioId(1L);
    }
}