package com.minimarket.security.model;

import com.minimarket.entity.Rol;
import com.minimarket.entity.Usuario;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CustomUserDetailsTest {

    @Test
    void deberiaCrearCustomUserDetailsCorrectamente() {

        Rol rol = new Rol("ADMIN");

        Usuario usuario = new Usuario();
        usuario.setUsername("admin");
        usuario.setPassword("123456");
        usuario.setRoles(Set.of(rol));

        CustomUserDetails details = new CustomUserDetails(usuario);

        assertEquals("admin", details.getUsername());
        assertEquals("123456", details.getPassword());

        Collection<? extends GrantedAuthority> authorities = details.getAuthorities();

        assertEquals(1, authorities.size());
        assertTrue(authorities.stream()
                .anyMatch(a -> a.getAuthority().equals("ADMIN")));

        assertTrue(details.isAccountNonExpired());
        assertTrue(details.isAccountNonLocked());
        assertTrue(details.isCredentialsNonExpired());
        assertTrue(details.isEnabled());
    }
}