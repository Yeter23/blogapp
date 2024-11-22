package com.yeter.blogapp.responses;

import com.yeter.blogapp.entities.Comment;
import com.yeter.blogapp.entities.Like;
import lombok.Data;

@Data
public class CommentResponse {
     Long id;
     Long userId;
     String userName;
     String text;
     public CommentResponse(Comment entity) {
          this.id=entity.getId();
          this.text=entity.getText();
          this.userId=entity.getUser().getId();
          this.userName=entity.getUser().getUserName();
     }
}
