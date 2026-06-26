package com.minimarket.controller;

import com.minimarket.entity.Usuario;
import com.minimarket.service.UsuarioService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class UsuarioControllerTest {

    @Mock
    private UsuarioService usuarioService;

    @InjectMocks
    private UsuarioController usuarioController;

    @Test
    void deberiaListarUsuarios() {

        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setUsername("admin");

        when(usuarioService.findAll()).thenReturn(List.of(usuario));

        List<Usuario> resultado = usuarioController.listarUsuarios();

        assertEquals(1, resultado.size());
        assertEquals("admin", resultado.get(0).getUsername());

        verify(usuarioService).findAll();
    }

    @Test
    void deberiaObtenerUsuarioExistente() {

        Usuario usuario = new Usuario();
        usuario.setId(1L);

        when(usuarioService.findById(1L)).thenReturn(Optional.of(usuario));

        ResponseEntity<Usuario> response = usuarioController.obtenerUsuarioPorId(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(usuario, response.getBody());
    }

    @Test
    void deberiaRetornarNotFoundSiUsuarioNoExiste() {

        when(usuarioService.findById(1L)).thenReturn(Optional.empty());

        ResponseEntity<Usuario> response = usuarioController.obtenerUsuarioPorId(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void deberiaGuardarUsuario() {

        Usuario usuario = new Usuario();

        when(usuarioService.save(usuario)).thenReturn(usuario);

        Usuario resultado = usuarioController.guardarUsuario(usuario);

        assertEquals(usuario, resultado);

        verify(usuarioService).save(usuario);
    }

    @Test
    void deberiaActualizarUsuarioExistente() {

        Usuario usuario = new Usuario();
        usuario.setId(1L);

        when(usuarioService.findById(1L)).thenReturn(Optional.of(usuario));
        when(usuarioService.save(any(Usuario.class))).thenReturn(usuario);

        ResponseEntity<Usuario> response = usuarioController.actualizarUsuario(1L, usuario);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        verify(usuarioService).save(usuario);
    }

    @Test
    void deberiaRetornarNotFoundAlActualizarUsuarioInexistente() {

        Usuario usuario = new Usuario();

        when(usuarioService.findById(1L)).thenReturn(Optional.empty());

        ResponseEntity<Usuario> response = usuarioController.actualizarUsuario(1L, usuario);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

        verify(usuarioService, never()).save(any());
    }

    @Test
    void deberiaEliminarUsuarioExistente() {

        Usuario usuario = new Usuario();

        when(usuarioService.findById(1L)).thenReturn(Optional.of(usuario));

        ResponseEntity<Void> response = usuarioController.eliminarUsuario(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

        verify(usuarioService).deleteById(1L);
    }

    @Test
    void deberiaRetornarNotFoundAlEliminarUsuarioInexistente() {

        when(usuarioService.findById(1L)).thenReturn(Optional.empty());

        ResponseEntity<Void> response = usuarioController.eliminarUsuario(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

        verify(usuarioService, never()).deleteById(anyLong());
    }
}