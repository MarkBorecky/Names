package com.example.application.data.service;

import com.example.application.data.entity.DataDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DataDaoRepository extends JpaRepository<DataDao, Integer> {


	@Query(
			"select d from DataDao d " +
			"where lower(d.name) like lower(concat('%', :name, '%')) " +
			"and lower(d.surname) like lower(concat('%', :surname, '%')) " +
			"and lower(d.patronus) like lower(concat('%', :patronus, '%')) " +
			"and lower(d.goverment) like lower(concat('%', :goverment, '%')) " +
			"and lower(d.uyezd) like lower(concat('%', :uyezd, '%')) " +
			"and lower(d.selo) like lower(concat('%', :selo, '%')) " +
			"and lower(d.fatherOccupation) like lower(concat('%', :fatherOccupation, '%')) " +
			"and lower(d.number) like lower(concat('%', :number, '%')) " +
			"and lower(d.school) like lower(concat('%', :school, '%')) " +
			"and lower(d.year) like lower(concat('%', :year, '%')) "
	)
	List<DataDao> findAll(
			@Param("name") String name,
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
