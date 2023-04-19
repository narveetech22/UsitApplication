package com.narvee.usit.service;

import java.util.List;

import com.narvee.usit.entity.PinCode;

public interface IPincodeService {

	public PinCode savePincode(PinCode code);

	public List<PinCode> getAllPincode();

	public void deletePincodeByID(long id);

	public PinCode pincodeGetByID(long id);

	public List<Object[]> getpin();
}
