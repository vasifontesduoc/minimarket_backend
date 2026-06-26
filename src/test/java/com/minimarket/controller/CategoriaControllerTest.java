package com.minimarket.controller;

import com.minimarket.entity.Categoria;
import com.minimarket.service.CategoriaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class CategoriaControllerTest {

    @Mock
    private CategoriaService categoriaService;

    @InjectMocks
    private CategoriaController categoriaController;

    @Test
    void deberiaListarCategorias() {

        Categoria categoria = new Categoria();
        categoria.setId(1L);
        categoria.setNombre("Lácteos");

        when(categoriaService.findAll()).thenReturn(List.of(categoria));

        List<Categoria> resultado = categoriaController.listarCategorias();

        assertEquals(1, resultado.size());
        assertEquals("Lácteos", resultado.get(0).getNombre());
        verify(categoriaService).findAll();
    }

    @Test
    void deberiaObtenerCategoriaExistente() {

        Categoria categoria = new Categoria();
        categoria.setId(1L);

        when(categoriaService.findById(1L)).thenReturn(categoria);

        ResponseEntity<Categoria> response = categoriaController.obtenerCategoriaPorId(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(categoria, response.getBody());
    }

    @Test
    void deberiaRetornarNotFoundSiCategoriaNoExiste() {

        when(categoriaService.findById(1L)).thenReturn(null);

        ResponseEntity<Categoria> response = categoriaController.obtenerCategoriaPorId(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void deberiaGuardarCategoria() {

        Categoria categoria = new Categoria();

        when(categoriaService.save(categoria)).thenReturn(categoria);

        Categoria resultado = categoriaController.guardarCategoria(categoria);

        assertEquals(categoria, resultado);
        verify(categoriaService).save(categoria);
    }

    @Test
    void deberiaActualizarCategoriaExistente() {

        Categoria categoria = new Categoria();
        categoria.setId(1L);

        when(categoriaService.findById(1L)).thenReturn(categoria);
        when(categoriaService.save(any(Categoria.class))).thenReturn(categoria);

        ResponseEntity<Categoria> response = categoriaController.actualizarCategoria(1L, categoria);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(categoriaService).save(categoria);
    }

    @Test
    void deberiaRetornarNotFoundAlActualizarCategoriaInexistente() {

        Categoria categoria = new Categoria();

        when(categoriaService.findById(1L)).thenReturn(null);

        ResponseEntity<Categoria> response = categoriaController.actualizarCategoria(1L, categoria);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void deberiaEliminarCategoriaExistente() {

        Categoria categoria = new Categoria();

        when(categoriaService.findById(1L)).thenReturn(categoria);

        ResponseEntity<Void> response = categoriaController.eliminarCategoria(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(categoriaService).deleteById(1L);
    }

    @Test
    void deberiaRetornarNotFoundAlEliminarCategoriaInexistente() {

        when(categoriaService.findById(1L)).thenReturn(null);

        ResponseEntity<Void> response = categoriaController.eliminarCategoria(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(categoriaService, never()).deleteById(anyLong());
    }
}
