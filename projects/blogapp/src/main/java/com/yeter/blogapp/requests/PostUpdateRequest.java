package com.yeter.blogapp.requests;

import lombok.Data;

@Data
public class PostUpdateRequest {
    String title;
    String text;
}
