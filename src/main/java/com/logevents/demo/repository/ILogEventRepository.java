package com.logevents.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.logevents.demo.entity.LogEvent;

@Repository
public interface ILogEventRepository extends JpaRepository<LogEvent, Long> {
	
}
