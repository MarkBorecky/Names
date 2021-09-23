package com.example.application.data.service;

import com.example.application.data.entity.DataDao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DataDaoRepository extends JpaRepository<DataDao, Integer> {
}
