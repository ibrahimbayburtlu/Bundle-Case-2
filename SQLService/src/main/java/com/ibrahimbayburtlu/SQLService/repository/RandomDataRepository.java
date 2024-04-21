package com.ibrahimbayburtlu.SQLService.repository;

import com.ibrahimbayburtlu.SQLService.Entity.RandomData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RandomDataRepository extends JpaRepository<RandomData,Long> {
}
