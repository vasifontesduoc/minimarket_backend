package com.minimarket.service.impl;

import com.minimarket.entity.DetalleVenta;
import com.minimarket.repository.DetalleVentaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DetalleVentaServiceImplTest {

    @Mock
    private DetalleVentaRepository detalleVentaRepository;

    @InjectMocks
    private DetalleVentaServiceImpl detalleVentaService;

    @Test
    void testFindAll() {
        DetalleVenta detalle = new DetalleVenta();

        when(detalleVentaRepository.findAll())
                .thenReturn(Arrays.asList(detalle));

        List<DetalleVenta> resultado = detalleVentaService.findAll();

        assertEquals(1, resultado.size());
        verify(detalleVentaRepository).findAll();
    }

    @Test
    void testFindById() {
        DetalleVenta detalle = new DetalleVenta();

        when(detalleVentaRepository.findById(1L))
                .thenReturn(Optional.of(detalle));

        DetalleVenta resultado = detalleVentaService.findById(1L);

        assertNotNull(resultado);
        verify(detalleVentaRepository).findById(1L);
    }

    @Test
    void testSave() {
        DetalleVenta detalle = new DetalleVenta();

        when(detalleVentaRepository.save(detalle))
                .thenReturn(detalle);

        DetalleVenta resultado = detalleVentaService.save(detalle);

        assertNotNull(resultado);
        verify(detalleVentaRepository).save(detalle);
    }

    @Test
    void testDeleteById() {
        detalleVentaService.deleteById(1L);

        verify(detalleVentaRepository).deleteById(1L);
    }

    @Test
    void testFindByVentaId() {
        DetalleVenta detalle = new DetalleVenta();

        when(detalleVentaRepository.findByVentaId(1L))
                .thenReturn(Arrays.asList(detalle));

        List<DetalleVenta> resultado = detalleVentaService.findByVentaId(1L);

        assertEquals(1, resultado.size());
        verify(detalleVentaRepository).findByVentaId(1L);
    }
}