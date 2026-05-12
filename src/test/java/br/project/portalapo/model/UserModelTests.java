package br.project.portalapo.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("User Model Tests")
class UserModelTests {

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
    }

    @Test
    @DisplayName("Should create user with all properties")
    void testUserCreation() {
        user.setId(1L);
        user.setUsername("testuser");
        user.setPassword("password123");
        user.setRole("ALUNO");
        user.setAtivo(true);

        assertEquals(1L, user.getId());
        assertEquals("testuser", user.getUsername());
        assertEquals("password123", user.getPassword());
        assertEquals("ALUNO", user.getRole());
        assertTrue(user.isAtivo());
    }

    @Test
    @DisplayName("Should set and get username")
    void testSetAndGetUsername() {
        user.setUsername("john_doe");
        assertEquals("john_doe", user.getUsername());
    }

    @Test
    @DisplayName("Should set and get password")
    void testSetAndGetPassword() {
        user.setPassword("securePassword123!");
        assertEquals("securePassword123!", user.getPassword());
    }

    @Test
    @DisplayName("Should set and get role")
    void testSetAndGetRole() {
        user.setRole("ADMIN");
        assertEquals("ADMIN", user.getRole());
    }

    @Test
    @DisplayName("Should set and check if ativo")
    void testSetAndGetAtivo() {
        user.setAtivo(true);
        assertTrue(user.isAtivo());

        user.setAtivo(false);
        assertFalse(user.isAtivo());
    }

    @Test
    @DisplayName("Should use AllArgsConstructor")
    void testAllArgsConstructor() {
        User newUser = new User(2L, "newuser", "password456", "COORDENADOR", true);

        assertEquals(2L, newUser.getId());
        assertEquals("newuser", newUser.getUsername());
        assertEquals("password456", newUser.getPassword());
        assertEquals("COORDENADOR", newUser.getRole());
        assertTrue(newUser.isAtivo());
    }

    @Test
    @DisplayName("Should use NoArgsConstructor")
    void testNoArgsConstructor() {
        User newUser = new User();
        assertNotNull(newUser);
        assertNull(newUser.getId());
    }

    @Test
    @DisplayName("Should have default ativo value as true")
    void testDefaultAtivoValue() {
        User newUser = new User();
        newUser.setUsername("testuser");
        assertTrue(newUser.isAtivo());
    }
}
