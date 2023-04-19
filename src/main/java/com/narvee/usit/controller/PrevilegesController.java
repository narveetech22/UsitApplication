package com.narvee.usit.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.narvee.usit.commons.RestAPIResponse;
import com.narvee.usit.entity.Previleges;
import com.narvee.usit.entity.Roles;
import com.narvee.usit.entity.Users;
import com.narvee.usit.service.IPreviligesService;
import com.narvee.usit.service.IRoleService;
import com.narvee.usit.service.IUserService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/Previleges")
@CrossOrigin
public class PrevilegesController {
	public static final Logger logger = LoggerFactory.getLogger(PrevilegesController.class);

	@Autowired
	private IPreviligesService iPreviligesService;

	@ApiOperation("To save Previleges")
	@ApiResponses({ @ApiResponse(code = 200, message = "Previleges saved"),
			@ApiResponse(code = 404, message = "entity not found"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/savePrevileges", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestAPIResponse> savePrevg(@RequestBody Previleges previleges) {
		logger.info("PrevilegesController.savePrevg() => saving previleges");
		Previleges savePrevileges = iPreviligesService.savePrevileges(previleges);
		return new ResponseEntity<>(new RestAPIResponse("Success", "Previleges Saved", savePrevileges),
				HttpStatus.CREATED);
	}

}
