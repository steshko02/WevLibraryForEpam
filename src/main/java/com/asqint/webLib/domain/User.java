package com.asqint.webLib.domain;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "usr")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String password;
    @Transient
    private String password2;

    private boolean active;
    private String email;
    private String activationCode;

    @ElementCollection
    @LazyCollection(LazyCollectionOption.FALSE)
    @CollectionTable(name = "user_orders", joinColumns = @JoinColumn(name = "user_id"))
    private List<Long> orderedBooksId;

    @ElementCollection
    @LazyCollection(LazyCollectionOption.FALSE)
    @CollectionTable(name = "user_lib", joinColumns = @JoinColumn(name = "user_id"))
    private List<Long> libBooksId;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    public User() {
    }

    public boolean isAdmin() {
        return this.roles.contains(Role.ADMIN);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isActive();
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public boolean orderedBook(Long bookId){
        return this.orderedBooksId.contains(bookId);
    }

    public boolean bookInLib(Long bookId){
        return this.libBooksId.contains(bookId);
    }

    public void addOrderedBook(Long id) {
        this.orderedBooksId.add(id);
    }

    public List<Long> getOrderedBooksId() {
        return orderedBooksId;
    }

    public void setOrderedBooksId(List<Long> booksId) {
        this.orderedBooksId = booksId;
    }

    public List<Long> getLibBooksId() {
        return libBooksId;
    }

    public void setLibBooksId(List<Long> libBooksId) {
        this.libBooksId = libBooksId;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }

    public User(String username, String password, String password2, String email) {
        this.username = username;
        this.password = password;
        this.password2 = password2;
        this.email = email;
    }
}