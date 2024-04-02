package com.quiz.main.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quiz.main.entity.Quiz;

@Repository
public interface QuizDao extends JpaRepository<Quiz, Integer> {
	

}
