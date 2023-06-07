package com.basanta.letter.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.basanta.letter.entity.letterDetails;




public interface LetterRepository extends JpaRepository<letterDetails, Integer> {
	@Query("select u from letterDetails u where u.email= :email")
	public letterDetails getUserByUserName(@Param("email") String email);

}
