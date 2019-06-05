package com.datacenter.dcsclient.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.datacenter.dcsclient.domain.User;

public interface UsersRepository extends JpaRepository<User, Long> {
}
