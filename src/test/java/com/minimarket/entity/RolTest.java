package com.minimarket.entity;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class RolTest {

    @Test
    void deberiaCrearRolCorrectamente() {

        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setUsername("admin");

        Rol rol = new Rol(10L, "ADMIN", Set.of(usuario));

        assertEquals(10L, rol.getId());
        assertEquals("ADMIN", rol.getNombre());
        assertEquals(1, rol.getUsuarios().size());
        assertTrue(rol.getUsuarios().contains(usuario));
    }

    @Test
    void deberiaModificarRolConSetters() {

        Rol rol = new Rol("USER");

        Usuario usuario = new Usuario();
        usuario.setId(2L);
        usuario.setUsername("cliente");

        rol.setId(20L);
        rol.setNombre("ADMIN");
        rol.setUsuarios(Set.of(usuario));

        assertEquals(20L, rol.getId());
        assertEquals("ADMIN", rol.getNombre());
        assertEquals(1, rol.getUsuarios().size());
    }
}