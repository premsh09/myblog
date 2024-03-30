package com.blogapp51.controller;

import com.blogapp51.payload.CommentDto;
import com.blogapp51.payload.PosterWithCommentDto;
import com.blogapp51.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/comment")
@AllArgsConstructor
public class CommentController {

    private CommentService commentService;

    // http://localhost:8080/api/comment/1
    @PostMapping("/{posterId}")
    public ResponseEntity<CommentDto> createComment(
            @RequestBody CommentDto commentDto,
            @PathVariable long posterId){

        CommentDto dto = commentService.createComment(commentDto, posterId);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @GetMapping("/{posterId}")
    public ResponseEntity<PosterWithCommentDto> getAllCommentByPosterId(@PathVariable long posterId){
        PosterWithCommentDto allCommentByPosterId = commentService.getAllCommentByPosterId(posterId);
        return new ResponseEntity<>(allCommentByPosterId, HttpStatus.OK);
    }


    // http://localhost:8080/api/comment?id=2
    @GetMapping
    public ResponseEntity<CommentDto> getCommentById(@RequestParam long id){
        CommentDto dto  = commentService.getCommentById(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
