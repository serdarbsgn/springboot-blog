package com.folksdev.blog.service;


import com.folksdev.blog.dto.BlogDto;
import com.folksdev.blog.dto.requests.CreateBlogRequest;
import com.folksdev.blog.dto.converter.BlogDtoConverter;
import com.folksdev.blog.exception.BlogNotFoundException;
import com.folksdev.blog.model.Blog;
import com.folksdev.blog.model.User;
import com.folksdev.blog.repository.BlogRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BlogService {


    private final BlogRepository blogRepository;
    private final BlogDtoConverter blogDtoConverter;
    private final UserService userService;


    public BlogService(BlogRepository blogRepository, BlogDtoConverter blogDtoConverter, UserService userService) {
        this.blogRepository = blogRepository;
        this.blogDtoConverter = blogDtoConverter;
        this.userService = userService;
    }

    public BlogDto createBlog(CreateBlogRequest createBlogRequest,String userId) {
        User user = userService.findUserById(userId);
        Blog blog = new Blog(
                createBlogRequest.getTitle(),
                createBlogRequest.getDescription(),
                createBlogRequest.getContent(),
                createBlogRequest.getDate(),
                user,
                Collections.emptySet()
        );
        return blogDtoConverter.convert(blogRepository.save(blog));
    }

    public List<BlogDto> getBlogs() {
        return blogRepository.findAll().stream().map(blogDtoConverter::convert)
                .collect(Collectors.toList());
    }

    public BlogDto getBlogByUserId(String userId) {
        return blogDtoConverter.convert(blogRepository.findByUserId(userId));

    }

    public Blog findBlogById(String id) {
        return blogRepository.findById(id)
                .orElseThrow(() -> new BlogNotFoundException("Couldn't find blog by id: " + id));
    }

}