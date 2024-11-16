package com.yeter.blogapp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Data
@Table(name = "p_like")

public class Like {
    @Id
    Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    // cagiranda post de gelmesin deye lazy
    @JoinColumn(name = "post_id",nullable = false) // null olmasin bu hisse
    @OnDelete(action = OnDeleteAction.CASCADE) //bir post silinende butun likeleri  da silinsin
    @JsonIgnore
    Post post;



    @ManyToOne(fetch = FetchType.LAZY)
    // cagiranda user de gelmesin deye lazy
    @JoinColumn(name = "user_id",nullable = false) // null olmasin bu hisse
    @OnDelete(action = OnDeleteAction.CASCADE) //bir user silinende butun likeleri da silinsin
    @JsonIgnore
    User user;
}
