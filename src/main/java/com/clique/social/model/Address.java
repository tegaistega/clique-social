package com.clique.social.model;
/**
 * @author Tega Isiboge
 * */
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressid;

    @NotBlank
    private String country;

    @NotBlank
    private String state;

    @NotBlank
    private String city;
}
