package com.yeter.blogapp.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "comment")

public class Comment {
    @Id
    Long id;
    Long postId;
    Long userId;
    @Lob
    @Column(columnDefinition = "text")
    String text;

}
