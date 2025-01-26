package com.dhruv.prod_ready_features.prod_ready_features.services;

import com.dhruv.prod_ready_features.prod_ready_features.dto.PostDTO;
import com.dhruv.prod_ready_features.prod_ready_features.entities.PostEntity;
import com.dhruv.prod_ready_features.prod_ready_features.exceptions.ResourceNotFoundException;
import com.dhruv.prod_ready_features.prod_ready_features.repositories.PostRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    private final ModelMapper modelMapper;

    @Override
    public List<PostDTO> getAllPosts() {
        return postRepository
                .findAll()
                .stream()
                .map(postEntity -> modelMapper.map(postEntity , PostDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public PostDTO createNewPost(PostDTO inputPost) {
        PostEntity postEntity = modelMapper.map(inputPost , PostEntity.class);
        return modelMapper.map(postRepository.save(postEntity) , PostDTO.class);

    }

    @Override
    public PostDTO getPostById(Long id) {
        PostEntity post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post Not Found" + id));
        return modelMapper.map( post , PostDTO.class);
    }
}
