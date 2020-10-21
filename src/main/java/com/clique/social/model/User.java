package com.clique.social.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    private String password;

    private boolean enabled = true;

    @Enumerated(EnumType.STRING)
    private Role role;

    @JsonManagedReference
    @OneToOne(cascade = CascadeType.ALL)
    private Profile profile;

    @JsonBackReference
    @ManyToMany()
    @JoinTable(name = "user_following",
            joinColumns = {@JoinColumn(name = "user")},
            inverseJoinColumns = {@JoinColumn(name = "following")})
    List<User> following = new ArrayList<>();

    public void addFollowing(User user){
        following.add(user);
    }



}