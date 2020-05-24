package com.iioannou.springauthserver.model;

import com.iioannou.springauthserver.domain.enums.Role;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * @author ioannou
 */
@Entity
@Table(name = "user_authority")
public class UserAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_authority_id")
    private Long userAuthorityId;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "role", unique = true, nullable = false)
    private Role role;

    @ManyToMany(mappedBy = "userAuthorities")
    private Set<User> users;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "user_authority_authorities", joinColumns = @JoinColumn(name = "user_authority_id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id"))
    private Set<Authority> authorities;


    public UserAuthority() {
        super();
        this.users = new HashSet<>();
    }

    public void addAuthority(Authority authority) {
        this.authorities.add(authority);
        authority.getUserPrivilegies().add(this);
    }

    public void removeAuthority(Authority authority) {
        this.authorities.remove(authority);
        authority.getUserPrivilegies().remove(this);
    }

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public Long getUserAuthorityId() {
        return userAuthorityId;
    }

    public Set<User> getUsers() {
        return users;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserAuthority that = (UserAuthority) o;
        return role != null && role.name().equals(that.role.name());
    }

    @Override
    public int hashCode() {
        return Objects.hash(role.name());
    }
}
