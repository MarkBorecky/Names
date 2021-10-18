package com.example.application.data.repository;

import com.example.application.data.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Integer> {


	@Query(
			"select p from Person p " +
			"where lower(p.name.mainName) like lower(concat('%', :mainName, '%')) " +
			"and lower(p.name.originalName) like lower(concat('%', :originalName, '%')) " +
			"and lower(p.surname) like lower(concat('%', :surname, '%')) " +
			"and lower(p.patronus) like lower(concat('%', :patronus, '%')) " +
			"and lower(p.goverment) like lower(concat('%', :goverment, '%')) " +
			"and lower(p.uyezd) like lower(concat('%', :uyezd, '%')) " +
			"and lower(p.selo) like lower(concat('%', :selo, '%')) " +
			"and lower(p.fatherOccupation) like lower(concat('%', :fatherOccupation, '%')) " +
			"and lower(p.number) like lower(concat('%', :number, '%')) " +
			"and lower(p.school) like lower(concat('%', :school, '%')) " +
			"and lower(p.year) like lower(concat('%', :year, '%')) "
	)
	List<Person> findAll(
			@Param("mainName") String mainName,
			@Param("originalName") String originalName,
			@Param("surname") String surname,
			@Param("patronus") String patronus,
			@Param("goverment") String goverment,
			@Param("uyezd") String uyezd,
			@Param("selo") String selo,
			@Param("fatherOccupation") String fatherOccupation,
			@Param("number") String number,
			@Param("school") String school,
			@Param("year") String year);
}
