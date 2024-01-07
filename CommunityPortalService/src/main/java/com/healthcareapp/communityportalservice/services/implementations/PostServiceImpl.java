package com.healthcareapp.communityportalservice.services.implementations;

import com.healthcareapp.communityportalservice.entities.Post;
import com.healthcareapp.communityportalservice.exceptions.PostNotFoundException;
import com.healthcareapp.communityportalservice.models.AddPostDTO;
import com.healthcareapp.communityportalservice.repositories.PostRepository;
import com.healthcareapp.communityportalservice.services.interfaces.PostService;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public void addPost(String patientId,AddPostDTO addPostDTO) {
        // Create a new Post entity
        Post post = new Post();
        post.setPatientId(patientId);
        post.setText(addPostDTO.getText());
        post.setTitle(addPostDTO.getTitle());
        post.setPostTime(new Date()); // Set the current timestamp

        // Save the post to the repository
        postRepository.save(post);
    }
    @Override
    public void updatePost(UUID postId, AddPostDTO addPostDTO) throws PostNotFoundException {
        Post existingPost = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException(postId));

        // Update fields
        existingPost.setText(addPostDTO.getText());
        existingPost.setTitle(addPostDTO.getTitle());
        existingPost.setPostTime(new Date()); // Set the current timestamp

        // Save the updated post
        postRepository.save(existingPost);
    }

    @Override
    public void deletePost(UUID postId) throws PostNotFoundException {
        Post existingPost = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException(postId));

        // Delete the post
        postRepository.delete(existingPost);
    }

    @Override
    public Post getPostById(UUID postId) throws PostNotFoundException {
        return postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException(postId));
    }

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public List<Post> getPostsByPatientId(String patientId) {
        // Implementing the method to retrieve posts by patientId
        return postRepository.findByPatientId(patientId);
    }

}


