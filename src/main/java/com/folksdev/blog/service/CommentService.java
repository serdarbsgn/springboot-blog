package com.folksdev.blog.service;

import com.folksdev.blog.dto.CommentDto;
import com.folksdev.blog.dto.converter.CommentDtoConverter;
import com.folksdev.blog.dto.requests.CreateCommentRequest;
import com.folksdev.blog.model.Comment;
import com.folksdev.blog.model.Post;
import com.folksdev.blog.model.User;
import com.folksdev.blog.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {


    private final CommentRepository commentRepository;
    private final CommentDtoConverter commentDtoConverter;
    private final PostService postService;
    private final UserService userService;


    public CommentService(CommentRepository commentRepository, CommentDtoConverter commentDtoConverter, PostService postService, UserService userService) {
        this.commentRepository = commentRepository;
        this.commentDtoConverter = commentDtoConverter;
        this.postService = postService;
        this.userService = userService;
    }

    public List<CommentDto> getCommentsByPostId(String postId) {
        return commentRepository.findAllByPostId(postId).stream()
                .map(commentDtoConverter::convert).collect(Collectors.toList());
    }

    public CommentDto createComment(String postId, String userId, CreateCommentRequest createCommentRequest) {
        Post post = postService.findPostById(postId);
        User user = userService.findUserById(userId);
        Comment comment = new Comment(
                createCommentRequest.getBody(),
                createCommentRequest.getDate(),
                post,
                user
        );
        return commentDtoConverter.convert(commentRepository.save(comment));
    }
}
