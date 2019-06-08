package com.datacenter.dcsclient.extract.repository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.datacenter.dcsclient.domain.Person;


public interface ExtractUsersRepository extends JpaRepository<Person, Long> {
}
