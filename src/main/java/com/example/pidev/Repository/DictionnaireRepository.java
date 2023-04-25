package com.example.pidev.Repository;

import com.example.pidev.entity.DictionnaireBadWords;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DictionnaireRepository extends JpaRepository<DictionnaireBadWords, Long> {

	
	
}