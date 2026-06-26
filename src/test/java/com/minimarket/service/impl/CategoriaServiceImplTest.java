package com.minimarket.service.impl;

import com.minimarket.entity.Categoria;
import com.minimarket.repository.CategoriaRepository;
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
class CategoriaServiceImplTest {

    @Mock
    private CategoriaRepository categoriaRepository;

    @InjectMocks
    private CategoriaServiceImpl categoriaService;

    @Test
    void deberiaListarCategorias() {
        when(categoriaRepository.findAll())
                .thenReturn(Arrays.asList(new Categoria(), new Categoria()));

        assertEquals(2, categoriaService.findAll().size());

        verify(categoriaRepository).findAll();
    }

    @Test
    void deberiaBuscarCategoriaPorId() {
        Categoria categoria = new Categoria();

        when(categoriaRepository.findById(1L))
                .thenReturn(Optional.of(categoria));

        assertNotNull(categoriaService.findById(1L));

        verify(categoriaRepository).findById(1L);
    }

    @Test
    void deberiaGuardarCategoria() {
        Categoria categoria = new Categoria();

        when(categoriaRepository.save(categoria)).thenReturn(categoria);

        assertEquals(categoria, categoriaService.save(categoria));

        verify(categoriaRepository).save(categoria);
    }

    @Test
    void deberiaEliminarCategoria() {
        categoriaService.deleteById(1L);

        verify(categoriaRepository).deleteById(1L);
    }
}