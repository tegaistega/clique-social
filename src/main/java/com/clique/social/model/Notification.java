package com.clique.social.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.Valid;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author Tadi's
 * Tega Isiboge
 * */

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Notification implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long messageid;

    private boolean messageViewed = false;

    private boolean messageSent = false;

    @DateTimeFormat(pattern = "dd-mm-yyyy")
    private LocalDate messageCreationDate = LocalDate.now();

    @Valid
    @JsonBackReference
    @ManyToOne(fetch =FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private User user;

    @Valid
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, cascade = {
            CascadeType.MERGE, CascadeType.PERSIST
    })
    private Post post;
}
