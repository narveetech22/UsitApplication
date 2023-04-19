package com.narvee.usit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.narvee.usit.entity.City;

@Repository
public interface ICityRepository extends JpaRepository<City, Long> {

	public City findByCityname(String cityname);

	@Query(value = "select c.id, c.cityname from city c where c.status='Active' ", nativeQuery = true)
	public List<Object[]> getcities();
}
