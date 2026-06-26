package com.minimarket.service.impl;

import com.minimarket.entity.Inventario;
import com.minimarket.repository.InventarioRepository;
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
class InventarioServiceImplTest {

    @Mock
    private InventarioRepository inventarioRepository;

    @InjectMocks
    private InventarioServiceImpl inventarioService;

    @Test
    void deberiaListarInventario() {

        when(inventarioRepository.findAll())
                .thenReturn(Arrays.asList(new Inventario(), new Inventario()));

        assertEquals(2, inventarioService.findAll().size());

        verify(inventarioRepository).findAll();
    }

    @Test
    void deberiaBuscarInventarioPorId() {

        Inventario inventario = new Inventario();

        when(inventarioRepository.findById(1L))
                .thenReturn(Optional.of(inventario));

        assertNotNull(inventarioService.findById(1L));

        verify(inventarioRepository).findById(1L);
    }

    @Test
    void deberiaRetornarNullCuandoInventarioNoExiste() {

        when(inventarioRepository.findById(100L))
                .thenReturn(Optional.empty());

        assertNull(inventarioService.findById(100L));

        verify(inventarioRepository).findById(100L);
    }

    @Test
    void deberiaGuardarInventario() {

        Inventario inventario = new Inventario();

        when(inventarioRepository.save(inventario))
                .thenReturn(inventario);

        assertEquals(inventario, inventarioService.save(inventario));

        verify(inventarioRepository).save(inventario);
    }

    @Test
    void deberiaEliminarInventario() {

        inventarioService.deleteById(8L);

        verify(inventarioRepository).deleteById(8L);
    }
}
