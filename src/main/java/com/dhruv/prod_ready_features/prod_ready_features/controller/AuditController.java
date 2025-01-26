package com.dhruv.prod_ready_features.prod_ready_features.controller;

import com.dhruv.prod_ready_features.prod_ready_features.entities.PostEntity;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sound.sampled.spi.AudioFileReader;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/prod-ready-features/audit")
public class AuditController {


    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @GetMapping("/posts/{postId}")
    List<PostEntity> getPostRevision(@PathVariable Long postId){
        AuditReader reader = AuditReaderFactory.get(                entityManagerFactory.createEntityManager());

        List<Number> revisions =  reader.getRevisions(PostEntity.class , postId);
        System.out.println("Log : ");
        return revisions.stream().map(
                revisionNumber -> reader.find(PostEntity.class , postId , revisionNumber)
        ).collect(Collectors.toList());

    }

}
