package com.clique.social.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author Tadi's
 * Tega Isiboge
 * */

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Likes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long likeid;

    @JsonManagedReference
    @ManyToOne
    private User user;

    @JsonBackReference
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Post post;

    public void addPost(Post postToBeAdded){
        this.setPost(postToBeAdded);
        postToBeAdded.addLike(this);
    }
}
