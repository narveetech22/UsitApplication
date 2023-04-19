package com.narvee.usit.service;

import java.util.List;

import com.narvee.usit.entity.States;

public interface IStatesService {

	public States saveStates(States states);

	public List<States> getAllStates();

	public void deleteStates(long id);

	public States getStatesById(long id);

	public List<Object[]> getstates();

}
