package com.minimarket.service.impl;

import com.minimarket.entity.Usuario;
import com.minimarket.repository.UsuarioRepository;
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
class UsuarioServiceImplTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioServiceImpl usuarioService;

    @Test
    void deberiaListarUsuarios() {
        when(usuarioRepository.findAll())
                .thenReturn(Arrays.asList(new Usuario(), new Usuario()));

        assertEquals(2, usuarioService.findAll().size());

        verify(usuarioRepository).findAll();
    }

    @Test
    void deberiaBuscarUsuarioPorId() {
        Usuario usuario = new Usuario();

        when(usuarioRepository.findById(1L))
                .thenReturn(Optional.of(usuario));

        assertTrue(usuarioService.findById(1L).isPresent());

        verify(usuarioRepository).findById(1L);
    }

    @Test
    void deberiaBuscarUsuarioPorUsername() {
        Usuario usuario = new Usuario();
        usuario.setUsername("valeria");

        when(usuarioRepository.findByUsername("valeria"))
                .thenReturn(Optional.of(usuario));

        assertEquals("valeria",
                usuarioService.findByUsername("valeria").get().getUsername());

        verify(usuarioRepository).findByUsername("valeria");
    }

    @Test
    void deberiaGuardarUsuario() {
        Usuario usuario = new Usuario();

        when(usuarioRepository.save(usuario)).thenReturn(usuario);

        assertEquals(usuario, usuarioService.save(usuario));

        verify(usuarioRepository).save(usuario);
    }

    @Test
    void deberiaEliminarUsuario() {
        usuarioService.deleteById(5L);

        verify(usuarioRepository).deleteById(5L);
    }
}
