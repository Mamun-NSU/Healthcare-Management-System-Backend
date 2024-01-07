package com.healthcareapp.communityportalservice.services.interfaces;

import com.healthcareapp.communityportalservice.entities.Post;
import com.healthcareapp.communityportalservice.exceptions.PostNotFoundException;
import com.healthcareapp.communityportalservice.models.AddPostDTO;

import java.util.List;
import java.util.UUID;

public interface PostService {
    void addPost(String patientId, AddPostDTO addPostDTO);

    void updatePost(UUID postId, AddPostDTO addPostDTO) throws PostNotFoundException;

    void deletePost(UUID postId) throws PostNotFoundException;

    Post getPostById(UUID postId) throws PostNotFoundException;

    List<Post> getAllPosts();

    List<Post> getPostsByPatientId(String patientId);
}
