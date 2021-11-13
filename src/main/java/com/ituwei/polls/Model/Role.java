package com.ituwei.polls.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

@Entity
@Table(name = "user_roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column(length = 40)
    private RoleName name;

    public Role() {
    }

    public Role(RoleName roleName) {
        this.name = roleName;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        this.Id = id;
    }

    public RoleName getName() {
        return name;
    }

    public void setRoleName(RoleName roleName) {
        this.name = roleName;
    }

}
