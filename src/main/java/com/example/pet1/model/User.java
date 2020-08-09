package com.example.pet1.model;

import com.example.pet1.model.base.BaseEntity;
import com.example.pet1.util.Const;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "_user")
@Data
public class User extends BaseEntity implements UserDetails {

    private String username;

    @Size(min = Const.Password.maxLenth, max = Const.Password.maxLenth, message = "From "+Const.Password.minLenth+" to "+Const.Password.maxLenth)
    private String password;

    @Transient
    @Size(min = Const.Password.maxLenth, max = Const.Password.maxLenth, message = "From "+Const.Password.minLenth+" to "+Const.Password.maxLenth)
    private String passwordConfirm;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @Column(length = 20, nullable = false)
    @Pattern(regexp = Const.Regex.name)
    private String lastname;


    @Column(length = 20, nullable = false)
    @Pattern(regexp = Const.Regex.name)
    private String firstname;

    private LocalDate birthday;

    @Column(unique = true)
    @Pattern(regexp = Const.Regex.email)
    private String email;

    private boolean active;

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "user_vacancy",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "vacacny_id"))
    private Set<Vacancy> appliedVacancies = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<VacancyView> vacancyViews = new ArrayList<>();

    @Override
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
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        for(Role role:roles ){
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void addRole(Role role){
        if(this.roles.add(role)){
            role.getUsers().add(this);
        }
    }

    public boolean hasRole(String role){
        for (Role r : roles) {
            if (r.getName().equals(role)) return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", passwordConfirm='" + passwordConfirm + '\'' +
                ", roles=" + roles +
                ", lastname='" + lastname + '\'' +
                ", firstname='" + firstname + '\'' +
                ", birthday=" + birthday +
                ", email='" + email + '\'' +
                ", active=" + active +
                ", appliedVacancies=" + appliedVacancies +
                ", vacancyViews=" + vacancyViews +
                '}';
    }
}
