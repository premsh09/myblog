package com.blogapp51.service;

import com.blogapp51.payload.CommentDto;
import com.blogapp51.payload.PosterWithCommentDto;

import java.util.List;

public interface CommentService {

    CommentDto createComment(CommentDto commentDto, long posterId);

    PosterWithCommentDto getAllCommentByPosterId(long id);

    CommentDto getCommentById(long id);
}
