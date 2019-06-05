package com.datacenter.dcsclient.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class User {
	
	@Id
	private Long userId;
	
	private String name;
	
	private String dept;
	
	private BigDecimal account;
}
