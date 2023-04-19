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
import com.narvee.usit.entity.PinCode;
import com.narvee.usit.service.IPincodeService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/pincode")
@CrossOrigin
public class PincodeController {

	private static final Logger logger = LoggerFactory.getLogger(PincodeController.class);

	@Autowired
	private IPincodeService service;

	@ApiOperation("To save pincode")
	@ApiResponses({ @ApiResponse(code = 200, message = "pincode saved"),
			@ApiResponse(code = 404, message = " entity not found"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestAPIResponse> savePincode(@RequestBody PinCode code) {
		logger.info("!!! inside class: PincodeController, !! method: savePincode");
		return new ResponseEntity<>(new RestAPIResponse("success", "Save Pincode Entity", service.savePincode(code)),
				HttpStatus.CREATED);
	}

	@ApiOperation("To get pincode")
	@ApiResponses({ @ApiResponse(code = 200, message = " get pincode"),
			@ApiResponse(code = 404, message = " entity not found"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/getpincode/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse> getPincodeById(@PathVariable long id) {
		logger.info("!!! inside class: PincodeController, !! method: getPincodeById");
		return new ResponseEntity<>(new RestAPIResponse("success", "get Pincode Entity", service.pincodeGetByID(id)),
				HttpStatus.OK);
	}

	@ApiOperation("To delete pincode")
	@ApiResponses({ @ApiResponse(code = 200, message = "delete pincode"),
			@ApiResponse(code = 404, message = " entity not found"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<RestAPIResponse> deletepincode(@PathVariable long id) {
		logger.info("!!! inside class: PincodeController, !! method: deletepincode");
		service.deletePincodeByID(id);
		return new ResponseEntity<>(new RestAPIResponse("success", "delete Pincode Entity"), HttpStatus.OK);
	}

	@ApiOperation("To getAll pincode")
	@ApiResponses({ @ApiResponse(code = 200, message = " getall pincode"),
			@ApiResponse(code = 404, message = " entity not found"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse> getAllPincode() {
		logger.info("!!! inside class: PincodeController, !! method: getAllPincode");
		return new ResponseEntity<>(new RestAPIResponse("success", "getall Pincode Entity", service.getAllPincode()),
				HttpStatus.OK);
	}

	@ApiOperation("To update pincode")
	@ApiResponses({ @ApiResponse(code = 200, message = "pincode updated"),
			@ApiResponse(code = 404, message = " entity not found"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/updatePincode", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestAPIResponse> updatePincode(@RequestBody PinCode code) {
		logger.info("!!! inside class: PincodeController, !! method: updatePincode");
		return new ResponseEntity<>(new RestAPIResponse("success", "updated Pincode Entity", service.savePincode(code)),
				HttpStatus.CREATED);
	}

	@ApiOperation("To get CityId, CityName")
	@ApiResponses({ @ApiResponse(code = 200, message = "getAll cities"),
			@ApiResponse(code = 404, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "pincode", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse> getPin() {
		logger.info("!!! inside class: CityController, !! method: getAllCities");
		return new ResponseEntity<>(new RestAPIResponse("Success", "getall PinCode", service.getpin()), HttpStatus.OK);
	}

}
