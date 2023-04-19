package com.narvee.usit.service;

import java.util.List;

import com.narvee.usit.entity.City;

public interface ICityService {

	public City saveCity(City city);

	public List<City> getAllCity();

	public void deletecityByID(long id);

	public City getCityByID(long id);

	public List<Object[]> getcities();
}
