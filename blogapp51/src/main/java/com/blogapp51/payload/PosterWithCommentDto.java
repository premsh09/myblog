package com.blogapp51.payload;

import lombok.Data;

import java.util.List;

@Data
public class PosterWithCommentDto {

    private PosterDto poster;
    private List<CommentDto> commentDto;
}
