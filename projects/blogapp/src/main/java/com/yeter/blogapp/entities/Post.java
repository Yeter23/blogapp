package com.yeter.blogapp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;

@Entity
@Data
@Table(name = "post")

public class Post {
    @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    //postu cagiranda user de gelmesin deye lazy
    @JoinColumn(name = "user_id",nullable = false) // null olmasin bu hisse
    @OnDelete(action = OnDeleteAction.CASCADE) //bir user silinende butun postlar da silinsin


   User user;
    String title;
    @Lob
    @Column(columnDefinition = "text")
    String text;
    @Temporal(TemporalType.TIMESTAMP)
    Date createDate;
}
