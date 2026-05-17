package com.soobin.board_back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.soobin.board_back.entity.CommentEntity;

@Repository
public interface CommemtRepository extends JpaRepository<CommentEntity, Integer> {
    
}
