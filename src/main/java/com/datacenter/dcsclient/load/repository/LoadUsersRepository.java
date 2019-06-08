package com.datacenter.dcsclient.load.repository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;

import com.datacenter.dcsclient.domain.Person;

@Qualifier("loadrepo")
public interface LoadUsersRepository extends JpaRepository<Person, Long> {
}
