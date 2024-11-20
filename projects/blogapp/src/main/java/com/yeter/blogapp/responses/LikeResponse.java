package com.yeter.blogapp.responses;

import com.yeter.blogapp.entities.Like;
import lombok.Data;

import java.util.List;

@Data
public class LikeResponse {
    Long id;
    Long userId;
    Long postId;

    public LikeResponse(Like entity) {
        this.id=entity.getId();
        this.postId=entity.getPost().getId();
        this.userId=entity.getUser().getId();
    }
}
