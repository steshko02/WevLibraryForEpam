package com.asqint.webLib.domain;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void isAdmin() {
        User user = new User();
        user.setRoles(Collections.singleton(Role.ADMIN));
        assertTrue(user.isAdmin());
    }

    @Test
    void getId() {
        User user = new User();
        long expectedId = 1;
        user.setId(expectedId);
        assertEquals(expectedId, user.getId());
    }

    @Test
    void setId() {
        User user = new User();
        long expectedId = 1;
        user.setId(expectedId);
        assertEquals(expectedId, user.getId());
    }

    @Test
    void getUsername() {
        User user = new User();
        String expectedUsername = "Name";
        user.setUsername(expectedUsername);
        assertEquals(expectedUsername,user.getUsername());
    }

    @Test
    void isAccountNonExpired() {
        User user = new User();
        assertTrue(user.isAccountNonExpired());
    }

    @Test
    void isAccountNonLocked() {
        User user = new User();
        assertTrue(user.isAccountNonLocked());
    }

    @Test
    void isCredentialsNonExpired() {
        User user = new User();
        assertTrue(user.isCredentialsNonExpired());
    }

    @Test
    void isEnabled() {
        User user = new User();
        user.setActive(true);
        assertTrue(user.isEnabled());
    }

    @Test
    void setUsername() {
        User user = new User();
        String expectedUsername = "Name";
        user.setUsername(expectedUsername);
        assertEquals(expectedUsername,user.getUsername());
    }

    @Test
    void getPassword() {
        User user = new User();
        String expectedPass = "12345";
        user.setPassword(expectedPass);
        assertEquals(expectedPass,user.getPassword());
    }

    @Test
    void setPassword() {
        User user = new User();
        String expectedPass = "12345";
        user.setPassword(expectedPass);
        assertEquals(expectedPass,user.getPassword());
    }

    @Test
    void isActive() {
        User user = new User();
        user.setActive(true);
        assertTrue(user.isActive());
    }

    @Test
    void setActive() {
        User user = new User();
        user.setActive(true);
        assertTrue(user.isActive());
    }

    @Test
    void getRoles() {
        User user = new User();
        Set<Role> expectedRoles = Collections.singleton(Role.ADMIN);
        user.setRoles(expectedRoles);
        assertEquals(expectedRoles,user.getRoles());
    }

    @Test
    void setRoles() {
        User user = new User();
        Set<Role> expectedRoles = Collections.singleton(Role.ADMIN);
        user.setRoles(expectedRoles);
        assertEquals(expectedRoles,user.getRoles());
    }

    @Test
    void orderedBook() {
        User user = new User();
        List<Long> orderedBooks = new ArrayList<>();
        orderedBooks.add((long) 3);
        user.setOrderedBooksId(orderedBooks);
        assertTrue(user.orderedBook((long) 3));
    }

    @Test
    void bookInLib() {
        User user = new User();
        List<Long> booksInLib = new ArrayList<>();
        booksInLib.add((long) 3);
        user.setLibBooksId(booksInLib);
        assertTrue(user.bookInLib((long) 3));
    }

    @Test
    void addOrderedBook() {
        User user = new User();
        List<Long> orderedBooks = new ArrayList<>();
        user.setOrderedBooksId(orderedBooks);
        user.addOrderedBook((long) 3);
        assertTrue(user.orderedBook((long) 3));
    }

    @Test
    void getOrderedBooksId() {
        User user = new User();
        List<Long> orderedBooks = new ArrayList<>();
        orderedBooks.add((long) 3);
        user.setOrderedBooksId(orderedBooks);
        assertEquals(orderedBooks,user.getOrderedBooksId());
    }

    @Test
    void setOrderedBooksId() {
        User user = new User();
        List<Long> orderedBooks = new ArrayList<>();
        orderedBooks.add((long) 3);
        user.setOrderedBooksId(orderedBooks);
        assertEquals(orderedBooks,user.getOrderedBooksId());
    }

    @Test
    void getLibBooksId() {
        User user = new User();
        List<Long> booksInLib = new ArrayList<>();
        booksInLib.add((long) 3);
        user.setLibBooksId(booksInLib);
       assertEquals(booksInLib,user.getLibBooksId());
    }

    @Test
    void setLibBooksId() {
        User user = new User();
        List<Long> booksInLib = new ArrayList<>();
        booksInLib.add((long) 3);
        user.setLibBooksId(booksInLib);
        assertEquals(booksInLib,user.getLibBooksId());
    }

    @Test
    void getPassword2() {
        User user = new User();
        String expectedPass2 = "54321";
        user.setPassword2(expectedPass2);
        assertEquals(expectedPass2,user.getPassword2());
    }

    @Test
    void setPassword2() {
        User user = new User();
        String expectedPass2 = "54321";
        user.setPassword2(expectedPass2);
        assertEquals(expectedPass2,user.getPassword2());
    }

    @Test
    void getEmail() {
        User user = new User();
        String expectedEmail = "ex@ex.ex";
        user.setEmail(expectedEmail);
        assertEquals(expectedEmail,user.getEmail());
    }

    @Test
    void setEmail() {
        User user = new User();
        String expectedEmail = "ex@ex.ex";
        user.setEmail(expectedEmail);
        assertEquals(expectedEmail,user.getEmail());
    }

    @Test
    void getActivationCode() {
        User user = new User();
        String expectedCode = "jhg423g5hg";
        user.setActivationCode(expectedCode);
        assertEquals(expectedCode,user.getActivationCode());
    }

    @Test
    void setActivationCode() {
        User user = new User();
        String expectedCode = "jhg423g5hg";
        user.setActivationCode(expectedCode);
        assertEquals(expectedCode,user.getActivationCode());
    }
}