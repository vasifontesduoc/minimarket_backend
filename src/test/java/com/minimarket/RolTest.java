package com.minimarket;

import com.minimarket.entity.Rol;
import com.minimarket.entity.Usuario;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class RolTest {

    @Test
    public void testConstructorConNombre() {

        Rol rol = new Rol("ADMIN");

        assertNotNull(rol);
        assertEquals("ADMIN", rol.getNombre());
    }

    @Test
    public void testConstructorCompleto() {

        Usuario usuario = new Usuario();
        usuario.setUsername("admin");

        Set<Usuario> usuarios = new HashSet<>();
        usuarios.add(usuario);

        Rol rol = new Rol(1L, "ADMIN", usuarios);

        assertEquals(1L, rol.getId());
        assertEquals("ADMIN", rol.getNombre());
        assertEquals(1, rol.getUsuarios().size());
    }

    @Test
    public void testGettersYSetters() {

        Rol rol = new Rol("TEMP");

        rol.setId(10L);
        rol.setNombre("USER");

        Set<Usuario> usuarios = new HashSet<>();
        usuarios.add(new Usuario());

        rol.setUsuarios(usuarios);

        assertEquals(10L, rol.getId());
        assertEquals("USER", rol.getNombre());
        assertNotNull(rol.getUsuarios());
        assertEquals(1, rol.getUsuarios().size());
    }
}