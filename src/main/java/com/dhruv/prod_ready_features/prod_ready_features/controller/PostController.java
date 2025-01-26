package com.dhruv.prod_ready_features.prod_ready_features.controller;


import com.dhruv.prod_ready_features.prod_ready_features.dto.PostDTO;
import com.dhruv.prod_ready_features.prod_ready_features.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/prod-ready-features/posts")
public class PostController {

    private final PostService postService;

    @GetMapping
    public List<PostDTO> getAllPost(){
        return postService.getAllPosts();
    }


    @GetMapping("/{postId}")
    public PostDTO getPostById(@PathVariable(name = "postId")Long id){
        return postService.getPostById(id);
    }

    @PostMapping
    public PostDTO createNewPost(@RequestBody PostDTO inputPost){
        return postService.createNewPost(inputPost);
    }

}

