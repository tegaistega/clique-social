package com.clique.social.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Profile implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long profileid;

    @NotNull
    private String gender;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String firstname;

    @NotBlank
    private String lastname;

    @DateTimeFormat(pattern = "dd-mm-yyyy")
    @NotNull
    @Past
    private LocalDate dateOfBirth;

    @DateTimeFormat(pattern = "dd-mm-yyyy")
    private LocalDate dateJoined;

    @JsonBackReference
    @OneToOne(mappedBy = "profile")
    private User user;

    @Valid
    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    private String profilePhoto;

    @Size(min =3, max = 30)
    private String occupation;

    public Profile(@NotNull String gender, @NotBlank @Email String email, @NotBlank String firstname,
                   @NotBlank String lastname, @NotNull @Past LocalDate dateOfBirth, LocalDate dateJoined) {
        this.gender = gender;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.dateOfBirth = dateOfBirth;
        this.dateJoined = dateJoined;
    }







}
