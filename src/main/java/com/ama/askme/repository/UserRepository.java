package com.ama.askme.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ama.askme.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
