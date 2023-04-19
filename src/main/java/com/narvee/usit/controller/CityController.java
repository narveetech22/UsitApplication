package com.narvee.usit.controller;

import java.util.Iterator;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.narvee.usit.commons.RestAPIResponse;
import com.narvee.usit.entity.City;
import com.narvee.usit.service.ICityService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/city")
@CrossOrigin
public class CityController {

	private static final Logger logger = LoggerFactory.getLogger(CityController.class);

	@Autowired
	private ICityService service;

	@ApiOperation("To save City")
	@ApiResponses({ @ApiResponse(code = 200, message = "city saved"), @ApiResponse(code = 404, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "save", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestAPIResponse> saveCity(@RequestBody City city) {
		logger.info("!!! inside class: CityController, !! method: saveCity");
		return new ResponseEntity<>(new RestAPIResponse("success", "save City Entity", service.saveCity(city)),
				HttpStatus.CREATED);
	}

	@ApiOperation("To getAll City")
	@ApiResponses({ @ApiResponse(code = 200, message = "getAll cities"),
			@ApiResponse(code = 404, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "all", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse> getAllCities() {
		logger.info("!!! inside class: CityController, !! method: getAllCities");
		return new ResponseEntity<>(new RestAPIResponse("success", "getall Cities", service.getAllCity()),
				HttpStatus.OK);
	}

	@ApiOperation("To get CityId, CityName")
	@ApiResponses({ @ApiResponse(code = 200, message = "getAll cities"),
			@ApiResponse(code = 404, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "city", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse> getCities() {
		logger.info("!!! inside class: CityController, !! method: getAllCities");
		return new ResponseEntity<>(new RestAPIResponse("Success", "getall Cities", service.getcities()),
				HttpStatus.OK);
	}

	@ApiOperation("To delete City")
	@ApiResponses({ @ApiResponse(code = 200, message = "delete cities"),
			@ApiResponse(code = 404, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<RestAPIResponse> deleteCityByID(@PathVariable long id) {
		logger.info("!!! inside class: CityController, !! method: deleteCityByID");
		service.deletecityByID(id);
		return new ResponseEntity<>(new RestAPIResponse("success", "delet Cities"), HttpStatus.OK);
	}

	@ApiOperation("To get City")
	@ApiResponses({ @ApiResponse(code = 200, message = "get cities"), @ApiResponse(code = 404, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "getcity/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse> getcityById(@PathVariable long id) {
		logger.info("!!! inside class: CityController, !! method: getcityById");
		return new ResponseEntity<>(new RestAPIResponse("success", "get Cities", service.getCityByID(id)),
				HttpStatus.OK);
	}

	@ApiOperation("To update City")
	@ApiResponses({ @ApiResponse(code = 200, message = "city updated"),
			@ApiResponse(code = 404, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "updatecity", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestAPIResponse> updateCity(@RequestBody City city) {
		logger.info("!!! inside class: CityController, !! method: updateCity");
		return new ResponseEntity<>(new RestAPIResponse("success", "update City Entity", service.saveCity(city)),
				HttpStatus.CREATED);
	}

}
