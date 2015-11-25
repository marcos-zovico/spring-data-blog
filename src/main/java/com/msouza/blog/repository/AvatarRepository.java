package com.msouza.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.msouza.blog.entity.Avatar;

public interface AvatarRepository extends JpaRepository<Avatar, Long> {

}
