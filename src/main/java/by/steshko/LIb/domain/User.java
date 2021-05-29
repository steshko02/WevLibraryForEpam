package by.steshko.LIb.domain;

import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "usr")
@Data
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
  //  https://java-master.com/%D1%81%D0%B2%D1%8F%D0%B7%D1%8C-manytomany-%D0%B2-hibernate/
 //   https://question-it.com/questions/343723/spring-data-jpa-zapros-manytomany-kak-ja-mogu-poluchit-dannye-iz-sopostavlennogo-klassa
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


    public boolean isActive() {
        return active;
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

    public User(String username, String password, String password2, String email) {
        this.username = username;
        this.password = password;
        this.password2 = password2;
        this.email = email;
    }
}