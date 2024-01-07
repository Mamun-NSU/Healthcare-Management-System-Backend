
package com.healthcareapp.communityportalservice.controllers;

import com.healthcareapp.communityportalservice.entities.Post;
import com.healthcareapp.communityportalservice.exceptions.PostNotFoundException;
import com.healthcareapp.communityportalservice.models.AddPostDTO;
import com.healthcareapp.communityportalservice.services.interfaces.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/add/{patientId}")
    public ResponseEntity<String> addPost(@PathVariable String patientId, @RequestBody AddPostDTO addPostDTO) {
        postService.addPost(patientId, addPostDTO);
        return new ResponseEntity<>("Post added successfully", HttpStatus.CREATED);
    }

    @PutMapping("/update/{postId}")
    public ResponseEntity<String> updatePost(@PathVariable UUID postId, @RequestBody AddPostDTO addPostDTO) {
        try {
            postService.updatePost(postId, addPostDTO);
            return new ResponseEntity<>("Post updated successfully", HttpStatus.OK);
        } catch (PostNotFoundException e) {
            throw e; // Exception handling is done by GlobalExceptionHandler
        }
    }

    @DeleteMapping("/delete/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable UUID postId) {
        try {
            postService.deletePost(postId);
            return new ResponseEntity<>("Post deleted successfully", HttpStatus.OK);
        } catch (PostNotFoundException e) {
            throw e; // Exception handling is done by GlobalExceptionHandler
        }
    }

    @GetMapping("/{postId}")
    public ResponseEntity<Post> getPostById(@PathVariable UUID postId) {
        try {
            Post post = postService.getPostById(postId);
            return new ResponseEntity<>(post, HttpStatus.OK);
        } catch (PostNotFoundException e) {
            throw e; // Exception handling is done by GlobalExceptionHandler
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Post>> getAllPosts() {
        List<Post> posts = postService.getAllPosts();
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/by-patient/{patientId}")
    public ResponseEntity<List<Post>> getPostsByPatientId(@PathVariable String patientId) {
        List<Post> posts = postService.getPostsByPatientId(patientId);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }
}
