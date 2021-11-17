package com.folksdev.blog;

import com.folksdev.blog.dto.*;
import com.folksdev.blog.dto.requests.CreateBlogRequest;
import com.folksdev.blog.dto.requests.CreateCommentRequest;
import com.folksdev.blog.dto.requests.CreatePostRequest;
import com.folksdev.blog.model.*;
import com.sun.istack.Nullable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

public class TestSupport {

    public CreateCommentRequest generateCommentRequest() {
        return new CreateCommentRequest("body");
    }

    public LocalDate generateLocalDate() {
        String now = "2016-11-09 10:30";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDate.parse(now, formatter);
    }

    public LocalDateTime generateLocalDateTime() {
        String now = "2016-11-09 10:30";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.parse(now, formatter);
    }

    public User generateUser(@Nullable String id) {
        return new User(id,
                "name",
                "surname",
                "username",
                "email",
                generateLocalDate(),
                Gender.UNKNOWN,
                Collections.emptySet(),
                null,
                Collections.emptySet());

    }

    public Blog generateBlog(@Nullable String id) {
        return new Blog(id,
                "title",
                "description",
                "content",
                generateLocalDate(),
                generateUser("userId"),
                Collections.emptySet());
    }

    public Blog generateBlogWithUser(@Nullable String id , User user) {
        return new Blog(id,
                "title",
                "description",
                "content",
                generateLocalDate(),
                user,
                Collections.emptySet());
    }

    public Post generatePost(@Nullable String id) {
        return new Post(id,
                "title",
                "content",
                generateLocalDateTime(),
                Collections.emptyList(),
                Collections.emptySet(),
                generateBlog("blogId"));
    }

    public Comment generateComment(@Nullable String id) {
        return new Comment(
                id,
                generateCommentRequest().getBody(),
                generateLocalDateTime(),
                generatePost("postId"),
                generateUser("userId")
        );
    }

    public PostDto generatePostDto(@Nullable String id) {
        Post post = generatePost(id);
        return new PostDto(post.getTitle(),
                post.getContent(),
                post.getTopicsTypes(),
                Collections.emptyList(),
                post.getBlog().getTitle(),
                post.getBlog().getUser().getUsername());
    }

    public CommentDto generateCommentDto() {
        Comment comment = generateComment("commentId");
        return new CommentDto(comment.getBody(),
                comment.getDate().toString(),
                "title",
                "username");
    }

    public List<PostDto> generatePostDtoList() {
       return List.of(generatePostDto("id1"),generatePostDto("id2"));
    }

    public CreatePostRequest generatePostRequest() {

        return new CreatePostRequest("title",
                "content",
                List.of(TopicsType.DEFAULT));
    }

    public BlogDto generateBlogDto(@Nullable String id){
        Blog blog = generateBlog(id);
        return new BlogDto(blog.getTitle(),
                blog.getDescription(),
                blog.getContent(),
                blog.getDate(),
                generateUserDto(),
                Collections.emptyList()
                );
    }

    public List<BlogDto> generateBlogDtoList() {
        return List.of(generateBlogDto("id1"),generateBlogDto("id2"));

    }

    public UserDto generateUserDto(){
        User user = generateUser("userId");
        return new UserDto(
                user.getUsername(),
                user.getDateOfBirth(),
                user.getGender(),
                user.getEmail(),
                List.of(generateGroupDto("groupId"),generateGroupDto("groupId2"))
        );
    }

    public Group generateGroup(@Nullable String id){
        return new Group(id,
                "name",
                "description",
                generateLocalDate(),
                List.of(GroupsType.DEFAULT),
                Collections.emptySet()
                );
    }
    public GroupDto generateGroupDto(String id){
        Group group = generateGroup(id);
        return new GroupDto(group.getId(),
                group.getName(),
                group.getDescription(),
                group.getDate(),
                group.getGroupsTypes(),
                Collections.emptyList());
    }
    public CreateBlogRequest generateBlogRequest() {
        return new CreateBlogRequest("title",
                "description",
                "content");
    }
}

