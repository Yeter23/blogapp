package com.yeter.blogapp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;

@Data
@Entity
@Table
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;


    @ManyToOne(fetch = FetchType.LAZY)
            @JoinColumn(name = "user_id",nullable = false)
            @OnDelete(action = OnDeleteAction.CASCADE)
            @JsonIgnore
    User user;
    @Column(nullable = false)
    String token;
    @Column
            @Temporal(TemporalType.TIMESTAMP)
    Date expiryDate;
}
