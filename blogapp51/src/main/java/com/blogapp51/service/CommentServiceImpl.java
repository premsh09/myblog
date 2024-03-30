package com.blogapp51.service;

import com.blogapp51.entity.Comment;
import com.blogapp51.entity.Poster;
import com.blogapp51.exception.ResourceNotFound;
import com.blogapp51.payload.CommentDto;
import com.blogapp51.payload.PosterDto;
import com.blogapp51.payload.PosterWithCommentDto;
import com.blogapp51.repository.CommentRepository;
import com.blogapp51.repository.PosterRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService{

    private CommentRepository commentRepository;
    private PosterRepository posterRepository;
    private ModelMapper modelMapper;

    @Override
    public CommentDto createComment(CommentDto commentDto, long posterId) {

        Optional<Poster> byId = posterRepository.findById(posterId);
        Poster poster = byId.get();

        Comment comment = mapToEntity(commentDto);
        comment.setPoster(poster);
        Comment savedComment = commentRepository.save(comment);

        CommentDto dto = mapToDto(savedComment);

        return dto;
    }

    @Override
    public PosterWithCommentDto getAllCommentByPosterId(long id) {
        Poster poster = posterRepository.findById(id).get();
        PosterDto dto = new PosterDto();
        dto.setId(poster.getId());
        dto.setTitle(poster.getTitle());
        dto.setContent(poster.getContent());
        dto.setDescription(poster.getDescription());
        List<Comment> comments = commentRepository.findByPosterId(id);
        List<CommentDto> dtos = comments.stream().map(c -> mapToDto(c)).collect(Collectors.toList());
        PosterWithCommentDto posterWithCommentDto = new PosterWithCommentDto();

        posterWithCommentDto.setCommentDto(dtos);
        posterWithCommentDto.setPoster(dto);
        return posterWithCommentDto;
    }

    @Override
    public CommentDto getCommentById(long id) {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Comment not found with id :" + id));
        return mapToDto(comment);
    }

    Comment mapToEntity(CommentDto dto){
       Comment comment = modelMapper.map(dto, Comment.class);
       return comment;
    }

    CommentDto mapToDto(Comment comment){
      return  modelMapper.map(comment, CommentDto.class);
    }
}
