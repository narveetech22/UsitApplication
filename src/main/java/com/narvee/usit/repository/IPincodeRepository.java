package com.narvee.usit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.narvee.usit.entity.PinCode;

@Repository
public interface IPincodeRepository extends JpaRepository<PinCode, Long> {

	public PinCode findByPincode(String pincode);

	@Query(value = "select p.id, p.pincode from pin_code p where p.status='Active'", nativeQuery = true)
	public List<Object[]> getpin();
}
