package com.healthcareapp.communityportalservice;

import com.healthcareapp.communityportalservice.entities.Post;
import com.healthcareapp.communityportalservice.exceptions.PostNotFoundException;
import com.healthcareapp.communityportalservice.models.AddPostDTO;
import com.healthcareapp.communityportalservice.repositories.PostRepository;
import com.healthcareapp.communityportalservice.services.implementations.PostServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PostServiceImplTest {

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private PostServiceImpl postService;

    @Test
    void testAddPost() {
        // Arrange
        String patientId = "testPatientId";
        AddPostDTO addPostDTO = new AddPostDTO();
        addPostDTO.setTitle("Test Title");
        addPostDTO.setText("Test Text");

        // Act
        postService.addPost(patientId, addPostDTO);

        // Assert
        ArgumentCaptor<Post> postCaptor = ArgumentCaptor.forClass(Post.class);
        verify(postRepository).save(postCaptor.capture());

        Post capturedPost = postCaptor.getValue();
        assertNotNull(capturedPost);
        assertEquals(patientId, capturedPost.getPatientId());
        assertEquals(addPostDTO.getTitle(), capturedPost.getTitle());
        assertEquals(addPostDTO.getText(), capturedPost.getText());
        assertNotNull(capturedPost.getPostTime()); // Assuming postTime is set in the service
    }

    @Test
    void testUpdatePost() {
        // Arrange
        UUID postId = UUID.randomUUID();
        AddPostDTO addPostDTO = new AddPostDTO();
        addPostDTO.setTitle("Updated Title");
        addPostDTO.setText("Updated Text");

        Post existingPost = new Post();
        existingPost.setPostId(postId);

        when(postRepository.findById(eq(postId))).thenReturn(Optional.of(existingPost));

        // Act
        assertDoesNotThrow(() -> postService.updatePost(postId, addPostDTO));

        // Assert
        verify(postRepository).save(existingPost); // Verify that save method is called with the existing post
        assertEquals(addPostDTO.getTitle(), existingPost.getTitle());
        assertEquals(addPostDTO.getText(), existingPost.getText());
        assertNotNull(existingPost.getPostTime()); // Assuming postTime is set in the service
    }

    @Test
    void testUpdatePost_PostNotFoundException() {
        // Arrange
        UUID postId = UUID.randomUUID();
        AddPostDTO addPostDTO = new AddPostDTO();
        addPostDTO.setTitle("Updated Title");
        addPostDTO.setText("Updated Text");

        when(postRepository.findById(eq(postId))).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(PostNotFoundException.class, () -> postService.updatePost(postId, addPostDTO));
    }
    @Test
    void testDeletePost() {

        UUID postId = UUID.randomUUID();
        Post existingPost = new Post();
        existingPost.setPostId(postId);
        when(postRepository.findById(eq(postId))).thenReturn(Optional.of(existingPost));
        assertDoesNotThrow(() -> postService.deletePost(postId));

        verify(postRepository).delete(existingPost); // Verify that delete method is called with the existing post
    }

    @Test
    void testDeletePost_PostNotFoundException() {

        UUID postId = UUID.randomUUID();
        when(postRepository.findById(eq(postId))).thenReturn(Optional.empty());
        assertThrows(PostNotFoundException.class, () -> postService.deletePost(postId));
    }

    @Test
    void testGetPostById() {
        // Arrange
        UUID postId = UUID.randomUUID();
        Post expectedPost = new Post();
        expectedPost.setPostId(postId);

        when(postRepository.findById(eq(postId))).thenReturn(Optional.of(expectedPost));
        Post actualPost = assertDoesNotThrow(() -> postService.getPostById(postId));
        assertEquals(expectedPost, actualPost);
    }    @Test
    void testGetPostById_PostNotFoundException() {
        UUID postId = UUID.randomUUID();
        when(postRepository.findById(eq(postId))).thenReturn(Optional.empty());

        assertThrows(PostNotFoundException.class, () -> postService.getPostById(postId));
    }


}

