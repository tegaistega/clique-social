package com.clique.social.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

/**
 * @author Tadi's
 * Tega Isiboge
 * */

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long commentid;

    @NotEmpty
    private String commentText;

    @Transient
    private String postid;

    @DateTimeFormat(pattern = "dd-mm-yyyy")
    private LocalDate commentCreationDate = LocalDate.now();

    @JsonBackReference
    @ManyToOne
    private User user;

    @JsonBackReference
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Post post;

    public void addPost(Post postToBeAdded){
        this.setPost(postToBeAdded);
        postToBeAdded.addComment(this);
    }
}
