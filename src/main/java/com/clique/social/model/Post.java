package com.clique.social.model;
/**
 * @author Tega Isiboge
 * */
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Post implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postid;

    private String postText;

    private String postPhoto;

    private String postVideo;

    private Date postCreationDate = new Date();

    @DateTimeFormat(pattern = "dd-mm-yyyy")
    private LocalDate postDeletionDate;

    private Integer postLikeCount =0;

    private Integer postCommentCount =0;

    private boolean notifyFollowers = true;

    private boolean postEnabled = true;

    @JsonManagedReference
    @ManyToOne(cascade = CascadeType.PERSIST)
    private User user;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "post")
    private Set<Likes> likes = new HashSet<Likes>();

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "post", fetch = FetchType.EAGER)
    @OrderBy("descending")
    private List<Comment> comments = new ArrayList<Comment>();

    public void addLike(Likes like){
        likes.add(like);
        this.postLikeCount++;
    }

    public void removeLike(Likes like){
        likes.remove(like);
        this.postLikeCount--;
    }

    public void addComment(Comment comment){
        comments.add(comment);
        this.postCommentCount++;
    }


}
