package com.narvee.usit.controller;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.narvee.usit.commons.RestAPIResponse;
import com.narvee.usit.entity.ExceRec;
import com.narvee.usit.entity.GetVendor;
import com.narvee.usit.entity.Recruiter;
import com.narvee.usit.helper.Helper;
import com.narvee.usit.helper.ListRecruiter;
import com.narvee.usit.service.IRecruiterService;
import com.narvee.usit.service.IVendorService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/recruiter")
@CrossOrigin
public class RecruiterController {
	public static final Logger logger = LoggerFactory.getLogger(VisaController.class);

	@Autowired
	public IRecruiterService service;

	@Autowired
	IVendorService vservice;

	@ApiOperation("To get all vendors")
	@ApiResponses({ @ApiResponse(code = 200, message = "Fetched all roles"),
			@ApiResponse(code = 404, message = "Entities not found"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/getall", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse> getAllRoles() {
		logger.info("UsersController.getAllRoles()");
		List<GetVendor> getall = vservice.getvendordetails();
		return new ResponseEntity<>(new RestAPIResponse("Success", "All Roles Fetched", getall), HttpStatus.OK);
//List<Recruiter> recruiterinfobyVmsid(long id)
	}

	@ApiOperation("To Fetch Recruiter Info By Company")
	@ApiResponses({ @ApiResponse(code = 200, message = "Details Fetched"),
			@ApiResponse(code = 404, message = "url not found"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/recrbycompany/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse> getrecruiterinfobycompanyid(@PathVariable long id) {
		// System.out.println(service.getbyId(id)+" ===================");
		logger.info("VendorManagementController.getbyId()");
		return new ResponseEntity<>(new RestAPIResponse("Success", "Recruiter Info Fetched By ID Successfully ",
				service.recruiterinfobyVmsid(id)), HttpStatus.OK);
	}

	@ApiOperation("To save Details")
	@ApiResponses({ @ApiResponse(code = 200, message = "Vendor Details saved"),
			@ApiResponse(code = 404, message = "url not found"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestAPIResponse> saveVendor(@RequestBody ExceRec ent) {
		List<ExceRec> vm = service.duprecru(ent.getEmail());// service.dupvendor(ent.getEmail());
		logger.info("VendorManagementController.saveVendor()");
		if (vm == null || vm.isEmpty()) {
			logger.info("VMSController.saveVMS() => Record Inserted");
			ExceRec save = service.save(ent);
			if (save != null) {
				return new ResponseEntity<>(new RestAPIResponse("Success", "Vendor Saved", save), HttpStatus.CREATED);
			} else {
				return new ResponseEntity<>(new RestAPIResponse("Fail", "Vendor not Saved", ""), HttpStatus.OK);
			}

		} else {
			logger.info("VMSController.saveVMS() => Record Not Inserted");
			return new ResponseEntity<>(
					new RestAPIResponse("Fail", "Recruiter Already Exist", "User Email Already Exist"), HttpStatus.OK);
		}

	}

	@ApiOperation("To get Details")
	@ApiResponses({ @ApiResponse(code = 200, message = " Details fetched"),
			@ApiResponse(code = 404, message = "url not found"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse> gateall() {
		logger.info("VendorManagementController.gateall()");
		// System.out.println(service.getall());
		List<ListRecruiter> getallrecruiter = service.getallrecruiter();
		Comparator<ListRecruiter> comparator = (c1, c2) -> {
			return Long.valueOf(c2.getId()).compareTo(c1.getId());
		};
//
		Collections.sort(getallrecruiter, comparator);
		return new ResponseEntity<>(new RestAPIResponse("Success", "Vendor Added Successfully", getallrecruiter),
				HttpStatus.CREATED);
	}

	// @CrossOrigin(origins = "http://localhost:4200")
	@ApiOperation("To Delete entity")
	@ApiResponses({ @ApiResponse(code = 200, message = " Deleted"),
			@ApiResponse(code = 404, message = " entity not found"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<RestAPIResponse> delete(@PathVariable long id) {
		System.out.println("hello");
		logger.info("VendorManagementController.delete()");
		return new ResponseEntity<>(
				new RestAPIResponse("Success", "Record Deleted Successfully", service.deleteById(id)), HttpStatus.OK);
	}

	@ApiOperation("To Fetch entity By ID")
	@ApiResponses({ @ApiResponse(code = 200, message = "VMS Fetched"),
			@ApiResponse(code = 404, message = "url not found"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/recruiter/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse> getbyId(@PathVariable long id) {
		System.out.println(service.getbyId(id));
		logger.info("VendorManagementController.getbyId()");
		return new ResponseEntity<>(
				new RestAPIResponse("Success", "VMS Fetched By ID Successfully ", service.getbyId(id)), HttpStatus.OK);
	}

	@ApiOperation("Update Entity")
	@ApiResponses({ @ApiResponse(code = 200, message = "Updating Vendor"),
			@ApiResponse(code = 404, message = "url not found"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/recruiter", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<RestAPIResponse> updateVMS(@RequestBody Recruiter vms) {
		// System.out.println(vms);
		logger.info("VendorManagementController.updateVMS()");
		return new ResponseEntity<>(new RestAPIResponse("Success", "VMS Successfully Update", service.save(vms)),
				HttpStatus.OK);
	}

	@ApiOperation("Recruiter Status Change ")
	@ApiResponses({ @ApiResponse(code = 200, message = "Change Recruiter Status"),
			@ApiResponse(code = 404, message = "Status not found"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/status", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse> changeStatus(@RequestParam("id") Long id,
			@RequestParam("status") String status, @RequestParam("remarks") String remarks) {

		logger.info("VMSController.changeStatus()");

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
	}

	@ApiOperation("Approve VMS")
	@ApiResponses({ @ApiResponse(code = 200, message = "VMS Edited"),
			@ApiResponse(code = 404, message = "VMS entity not found"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/approve", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse> approveVMS(@RequestParam("role") long role,
			@RequestParam("stat") String stat, @RequestParam("id") long id) {
		logger.info("VMSController.updateVMS()");
		int changestat = 0;
		changestat = service.approve(stat, id, role);
		if (changestat != 0) {
			System.out.println("status Successfully");
			logger.info("VMSController.changeStatus() => Status changed succvessfully ");
			return new ResponseEntity<>(new RestAPIResponse("Success", "Status Change Successfully", "Done"),
					HttpStatus.OK);
		} else {
			System.out.println("Not Chnaged");
			logger.info("VMSController.changeStatus() => Status not changed ");
			return new ResponseEntity<>(new RestAPIResponse("fail", "Status not Change ", "Done"), HttpStatus.OK);
		}
	}

	@ApiOperation("To fetch VMS By PageNo ")
	@ApiResponses({ @ApiResponse(code = 200, message = "Fetched VMS"),
			@ApiResponse(code = 404, message = "VMS entity not found"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/upload", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestAPIResponse> uploadexcel(@RequestParam("file") MultipartFile file) {
		logger.info("VMSController.findPaginated()");
		if (Helper.checkExcelFormat(file)) {
			// this.productService.save(file);
			service.saveexcel(file);
			return new ResponseEntity<>(new RestAPIResponse("success", "fetche", ""), HttpStatus.OK);
		}
		return new ResponseEntity<>(new RestAPIResponse("success", "fetche", ""), HttpStatus.BAD_REQUEST);
	}

}
