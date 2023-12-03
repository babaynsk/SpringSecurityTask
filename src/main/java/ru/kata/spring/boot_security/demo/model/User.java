package ru.kata.spring.boot_security.demo.model;


import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "department")
    private String department;
    @Column(name = "salary")
    private int salary;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id")
            , inverseJoinColumns = @JoinColumn(name = "roles_id"))
    @Fetch(FetchMode.JOIN)
    private Set<Role> roles;

    public User() {
    }

    public User(String name , String surname , String department , int salary ){
        this.name=name;
        this.surname=surname;
        this.department=department;
        this.salary=salary;
    }

    public User(int id, String name, String surname, String department, int salary){
        this.id=id;
        this.name=name;
        this.surname=surname;
        this.department=department;
        this.salary=salary;
    }

    public User(int id,String name, String surname, String department, int salary,String username,String password){
        this.id=id;
        this.name=name;
        this.surname=surname;
        this.department=department;
        this.salary=salary;
        this.username=username;
        this.password=password;
    }

    public User(int id,String name, String surname, String department, int salary,String username,String password,String role){
        this.id=id;
        this.name=name;
        this.surname=surname;
        this.department=department;
        this.salary=salary;
        this.username=username;
        this.password=password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    @Transactional
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getAuthority()));
        }
        return authorities;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
        return true;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return
                "Your id=" + id + "\n" +
                "Your name='" + name + '\'' + "\n"+
                "Your surname='" + surname + '\'' +"\n" +
                "Your department='" + department + '\'' + "\n" +
                "Your salary=" + salary;
    }
}