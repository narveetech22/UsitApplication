package com.narvee.usit.controller;

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
import com.narvee.usit.entity.States;
import com.narvee.usit.service.IStatesService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/states")
@CrossOrigin
public class StatesController {

	private static final Logger logger = LoggerFactory.getLogger(StatesController.class);

	@Autowired
	private IStatesService service;

	@ApiOperation("To save States")
	@ApiResponses({ @ApiResponse(code = 200, message = "states saved"),
			@ApiResponse(code = 404, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "save", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestAPIResponse> saveStates(@RequestBody States states) {
		logger.info("!!! inside class : StatesController, !! method: saveStates ");
		return new ResponseEntity<>(new RestAPIResponse("success", "save States Entity", service.saveStates(states)),
				HttpStatus.CREATED);
	}

	@ApiOperation("To getAll States")
	@ApiResponses({ @ApiResponse(code = 200, message = "getAll States"),
			@ApiResponse(code = 404, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "all", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse> getAllStates() {
		logger.info("!!! inside class : StatesController, !! method: getAllStates ");
		return new ResponseEntity<>(new RestAPIResponse("success", "getAll States Entities", service.getAllStates()),
				HttpStatus.OK);
	}

	@ApiOperation("To Delete States")
	@ApiResponses({ @ApiResponse(code = 200, message = "states deleted"),
			@ApiResponse(code = 404, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<RestAPIResponse> deleteStates(@PathVariable long id) {
		logger.info("!!! inside class : StatesController, !! method: deleteStates ");
		service.deleteStates(id);
		return new ResponseEntity<>(new RestAPIResponse("success", "delete states entity"), HttpStatus.OK);
	}

	@ApiOperation("To get State")
	@ApiResponses({ @ApiResponse(code = 200, message = "get state"), @ApiResponse(code = 404, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/getstate/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse> getStateById(@PathVariable long id) {
		logger.info("!!! inside class : StatesController, !! method: getStateById ");
		return new ResponseEntity<>(new RestAPIResponse("success", "Get states entity", service.getStatesById(id)),
				HttpStatus.OK);
	}

	@ApiOperation("To update States By Id")
	@ApiResponses({ @ApiResponse(code = 200, message = "states updated"),
			@ApiResponse(code = 404, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "updatestate", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestAPIResponse> updateStatesByID(@RequestBody States states) {
		logger.info("!!! inside class : StatesController, !! method: updateStatesByID ");
		return new ResponseEntity<>(new RestAPIResponse("success", "update States Entity", service.saveStates(states)),
				HttpStatus.CREATED);
	}

	@ApiOperation("To get Id, stateName")
	@ApiResponses({ @ApiResponse(code = 200, message = "getAll cities"),
			@ApiResponse(code = 404, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "states", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse> getstates() {
		logger.info("!!! inside class: CityController, !! method: getAllCities");
		return new ResponseEntity<>(new RestAPIResponse("Success", "getall states", service.getstates()),
				HttpStatus.OK);
	}

}
