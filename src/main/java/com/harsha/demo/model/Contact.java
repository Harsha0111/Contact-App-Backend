package com.harsha.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String number;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private ContactUser contactUser;
}
