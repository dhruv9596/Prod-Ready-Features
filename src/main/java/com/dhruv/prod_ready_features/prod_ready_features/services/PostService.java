package com.dhruv.prod_ready_features.prod_ready_features.services;

import com.dhruv.prod_ready_features.prod_ready_features.dto.PostDTO;
import org.springframework.stereotype.Service;

import java.util.List;


public interface PostService {

    List<PostDTO> getAllPosts();

    PostDTO createNewPost(PostDTO inputPost);


    PostDTO getPostById(Long id);

    PostDTO updatePost(PostDTO inputPost, Long postId);
}
