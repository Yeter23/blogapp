package com.yeter.blogapp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;

@Entity
@Data
@Table(name = "comment")

public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    // cagiranda post de gelmesin deye lazy
    @JoinColumn(name = "post_id",nullable = false) // null olmasin bu hisse
    @OnDelete(action = OnDeleteAction.CASCADE) //bir post silinende butun commentler da silinsin
    @JsonIgnore
    Post post;




    @ManyToOne(fetch = FetchType.LAZY)
    // cagiranda user de gelmesin deye lazy
    @JoinColumn(name = "user_id",nullable = false) // null olmasin bu hisse
    @OnDelete(action = OnDeleteAction.CASCADE) //bir user silinende butun commentleri da silinsin
    @JsonIgnore
    User user;
    @Lob
    @Column(columnDefinition = "text")
    String text;
    @Temporal(TemporalType.TIMESTAMP)
    Date createDate;

}
