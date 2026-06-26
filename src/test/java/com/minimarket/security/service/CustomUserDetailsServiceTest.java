package com.minimarket.security.service;

import com.minimarket.entity.Usuario;
import com.minimarket.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomUserDetailsServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private CustomUserDetailsService customUserDetailsService;

    @Test
    void deberiaCargarUsuario() {

        Usuario usuario = new Usuario();
        usuario.setUsername("admin");

        when(usuarioRepository.findByUsername("admin"))
                .thenReturn(Optional.of(usuario));

        assertNotNull(customUserDetailsService.loadUserByUsername("admin"));

        verify(usuarioRepository).findByUsername("admin");
    }

    @Test
    void deberiaLanzarExcepcionCuandoUsuarioNoExiste() {

        when(usuarioRepository.findByUsername("admin"))
                .thenReturn(Optional.empty());

        assertThrows(
                UsernameNotFoundException.class,
                () -> customUserDetailsService.loadUserByUsername("admin"));

        verify(usuarioRepository).findByUsername("admin");
    }
}