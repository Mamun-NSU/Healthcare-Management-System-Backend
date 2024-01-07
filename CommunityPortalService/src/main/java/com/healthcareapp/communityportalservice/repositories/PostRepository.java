package com.healthcareapp.communityportalservice.repositories;

import com.healthcareapp.communityportalservice.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PostRepository extends JpaRepository<Post, UUID> {
    List<Post> findByPatientId(String patientId);
}
