package com.narvee.usit.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.narvee.usit.commons.RestAPIResponse;
import com.narvee.usit.commons.RestAPIResponse2;
import com.narvee.usit.entity.Users;
import com.narvee.usit.entity.Vms;
import com.narvee.usit.service.IVmsService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/vms")
@CrossOrigin
public class VMSController {
	public static final Logger logger = LoggerFactory.getLogger(VMSController.class);
	@Autowired
	public IVmsService service;

	@ApiOperation("To save VMS")
	@ApiResponses({ @ApiResponse(code = 200, message = "VMS saved"),
			@ApiResponse(code = 404, message = "VMS entity not found"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/vms", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestAPIResponse> saveVMS(@RequestBody Vms vms) {
		logger.info("VMSController.saveVMS()");
		System.out.println("save vms " + vms);
		List<Vms> vm = service.dupvendor(vms.getAddedbyname());
		if (vm == null || vm.isEmpty()) {
			logger.info("VMSController.saveVMS() => Record Inserted");
			boolean sv = service.saveVms(vms);
			if (sv) {
				return new ResponseEntity<>(new RestAPIResponse("Success", "Vendor Saved", service.saveVms(vms)),
						HttpStatus.CREATED);
			} else {
				return new ResponseEntity<>(new RestAPIResponse("Fail", "Vendor not Saved", service.saveVms(vms)),
						HttpStatus.OK);
			}

		} else {
			logger.info("VMSController.saveVMS() => Record Not Inserted");
			return new ResponseEntity<>(new RestAPIResponse("Fail", "Vendor Already Exist", "User Email Already Exist"),
					HttpStatus.OK);
		}
	}

	@ApiOperation("To Fetch List Of VMS")
	@ApiResponses({ @ApiResponse(code = 200, message = "VMS Fetched"),
			@ApiResponse(code = 404, message = "VMS entity not found"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse> getAllVMSList() {
		logger.info("VMSController.getAllVMSList()");
		System.out.println("kkk " + service.findAllVms());
		return new ResponseEntity<>(new RestAPIResponse("Success", "VMS Fetched Successfully", service.findAllVms()),
				HttpStatus.OK);
	}

	@ApiOperation("Update VMS")
	@ApiResponses({ @ApiResponse(code = 200, message = "VMS Edited"),
			@ApiResponse(code = 404, message = "VMS entity not found"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/vms", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<RestAPIResponse> updateVMS(@RequestBody Vms vms) {
		System.out.println(vms);
		logger.info("VMSController.updateVMS()");
		return new ResponseEntity<>(new RestAPIResponse("Success", "VMS Successfully Update", service.saveVms(vms)),
				HttpStatus.OK);
	}

	@ApiOperation("Approve VMS")
	@ApiResponses({ @ApiResponse(code = 200, message = "VMS Edited"),
			@ApiResponse(code = 404, message = "VMS entity not found"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/approve", method = RequestMethod.PATCH, produces = "application/json")
	public ResponseEntity<RestAPIResponse> approveVMS(@RequestBody Vms vms) {
		System.out.println(vms);
		logger.info("VMSController.updateVMS()");
		int changestat = 0;
		changestat = service.approve(vms);
		if (changestat != 0) {
			System.out.println("status Successfully");
			logger.info("VMSController.changeStatus() => Status changed succvessfully ");
			return new ResponseEntity<>(new RestAPIResponse("Success", "Status Change Successfully", "Done"),
					HttpStatus.OK);
		} else {
			System.out.println("Not Chnaged");
			logger.info("VMSController.changeStatus() => Status not changed ");
			return new ResponseEntity<>(new RestAPIResponse("fail", "Status Change Successfully", "Done"),
					HttpStatus.OK);
		}
	}

	// @CrossOrigin(origins = "http://localhost:4200")
	@ApiOperation("To Delete VMS")
	@ApiResponses({ @ApiResponse(code = 200, message = "VMS Deleted"),
			@ApiResponse(code = 404, message = "VMS entity not found"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<RestAPIResponse> deleteVMSByID(@PathVariable long id) {
		logger.info("VMSController.deleteVMSByID()");
		return new ResponseEntity<>(
				new RestAPIResponse("Success", "Record Deleted Successfully", service.deleteById(id)), HttpStatus.OK);
	}

	@ApiOperation("To Fetch VMS By ID")
	@ApiResponses({ @ApiResponse(code = 200, message = "VMS Fetched"),
			@ApiResponse(code = 404, message = "VMS entity not found"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/vmsbyid/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse> getVMSByID(@PathVariable long id) {
		System.out.println(service.getbyId(id));
		logger.info("VMSController.getVMSByID()");
		return new ResponseEntity<>(
				new RestAPIResponse("Success", "VMS Fetched By ID Successfully ", service.getbyId(id)), HttpStatus.OK);
	}

	@ApiOperation("To Fetch VMS By filter")
	@ApiResponses({ @ApiResponse(code = 200, message = "VMS Fetched Records"),
			@ApiResponse(code = 404, message = "VMS entity not found"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/search/{keyword}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse> filterVMS(@PathVariable String keyword) {
		logger.info("VMSController.filterVMS()");
		return new ResponseEntity<>(new RestAPIResponse("Success", "VMS Fetched Records", service.filterVms(keyword)),
				HttpStatus.OK);
	}

	@ApiOperation("To fetch VMS By PageNo ")
	@ApiResponses({ @ApiResponse(code = 200, message = "Fetched VMS"),
			@ApiResponse(code = 404, message = "VMS entity not found"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/vms/{pageNo}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse2> findPaginated(@PathVariable("pageNo") int pageNo) {
		logger.info("VMSController.findPaginated()");
		int pageSize = 2;

		Page<Vms> findPaginated = service.findPaginated(pageNo, pageSize);
		List<Vms> findAlltech = findPaginated.getContent();
		int totalPages = findPaginated.getTotalPages();
		System.out.println("kkkkk " + Integer.toString(totalPages));
		return new ResponseEntity<>(new RestAPIResponse2("success", "fetche", totalPages, findAlltech), HttpStatus.OK);
	}

	@ApiOperation("Vms Status Change ")
	@ApiResponses({ @ApiResponse(code = 200, message = "Change VMS Status"),
			@ApiResponse(code = 404, message = "Status not found"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/status", method = RequestMethod.PATCH, produces = "application/json")
	public ResponseEntity<RestAPIResponse> changeStatus(@RequestBody Vms vms) {
		logger.info("VMSController.changeStatus()");
		System.out.println(vms + " ================");
		Long id = vms.getVmsid();
		String status = vms.getStatus();
		String remarks = vms.getRemarks();

		int changestat = 0;
		String result;
		if (status.equalsIgnoreCase("Active")) {
			result = "InActive";
		} else {
			result = "Active";
		}

		changestat = service.changeStatus(result, id, remarks);

		if (changestat != 0) {
			System.out.println("status Successfully");
			logger.info("VMSController.changeStatus() => Status changed succvessfully ");
			return new ResponseEntity<>(new RestAPIResponse("Success", "Status Change Successfully", "Done"),
					HttpStatus.OK);
		} else {
			System.out.println("Not Chnaged");
			logger.info("VMSController.changeStatus() => Status not changed ");
			return new ResponseEntity<>(new RestAPIResponse("fail", "Status Change Successfully", "Done"),
					HttpStatus.OK);
		}

		// service.changeStatus(result, id, remarks);

	}

}
