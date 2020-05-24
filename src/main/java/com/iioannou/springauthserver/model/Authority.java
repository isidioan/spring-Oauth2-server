package com.iioannou.springauthserver.model;

import com.iioannou.springauthserver.domain.enums.Privilege;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * @author ioannou
 */
@Entity
@Table(name = "authority")
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "authority_id")
    private Long authorityId;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "authority_name", unique = true, nullable = false)
    private Privilege authorityName;

    @ManyToMany(mappedBy = "authorities")
    private Set<UserAuthority> userPrivilegies;


    public Authority() {
        super();
        this.userPrivilegies = new HashSet<>();
    }


    public Long getAuthorityId() {
        return authorityId;
    }

    public Set<UserAuthority> getUserPrivilegies() {
        return userPrivilegies;
    }

    public Privilege getAuthorityName() {
        return authorityName;
    }

    public void setAuthorityName(Privilege authorityName) {
        this.authorityName = authorityName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Authority authority = (Authority) o;
        return authorityName != null && authorityName.name().equals(authority.authorityName.name());
    }

    @Override
    public int hashCode() {
        return Objects.hash(authorityName.name());
    }
}
