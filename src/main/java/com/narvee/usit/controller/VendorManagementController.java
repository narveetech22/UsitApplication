package com.narvee.usit.controller;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.narvee.usit.commons.RestAPIResponse;
import com.narvee.usit.commons.RestAPIResponse2;
import com.narvee.usit.entity.Recruiter;
import com.narvee.usit.entity.Users;
import com.narvee.usit.entity.VendorDetails;
import com.narvee.usit.entity.Visa;
import com.narvee.usit.entity.Vms;
import com.narvee.usit.helper.Helper;
import com.narvee.usit.helper.ListVendor;
import com.narvee.usit.service.IVendorService;
import com.narvee.usit.service.IVisaService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/vendor")
@CrossOrigin
public class VendorManagementController {
	public static final Logger logger = LoggerFactory.getLogger(VisaController.class);

	@Autowired
	public IVendorService service;

	@ApiOperation("To save Vendor Details")
	@ApiResponses({ @ApiResponse(code = 200, message = "Vendor Details saved"),
			@ApiResponse(code = 404, message = "url not found"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestAPIResponse> saveVendor(@RequestBody VendorDetails vms) {// @RequestBody VendorDetails vms
		// System.out.println(vms);
		List<VendorDetails> vendor = service.findByCompanyAndCityIdAndStatesId(vms.getCompany(), vms.getCity().getId(),
				vms.getStates().getId());
		if (vendor == null || vendor.isEmpty()) {
			logger.info("VendorManagementController.saveVendor()");
			return new ResponseEntity<>(new RestAPIResponse("Success", "Vendor Added Successfully", service.save(vms)),
					HttpStatus.CREATED);
		} else {
			logger.info("VendorManagementController.saveVendor()");
			return new ResponseEntity<>(new RestAPIResponse("Fail", "Vendor Already Exists ", "Duplicate vendor"),
					HttpStatus.CREATED);
		}

	}

	// Vms

	@ApiOperation("Approve VMS")
	@ApiResponses({ @ApiResponse(code = 200, message = "VMS Edited"),
			@ApiResponse(code = 404, message = "VMS entity not found"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/approve", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse> approveVMS(@RequestParam("role") long role,
			@RequestParam("stat") String stat, @RequestParam("id") long id) {
		// System.out.println("=======role="+role+"&stat="+stat+"&id="+id);
		logger.info("VMSController.updateVMS()");
		int changestat = 0;
		String msg = "";
		if (stat.equalsIgnoreCase("Approved"))
			msg = "Approved";
		else
			msg = "Rejected";
		changestat = service.approve(stat, id, role);
		if (changestat != 0) {
			System.out.println("status Successfully");
			logger.info("VMSController.changeStatus() => Status changed succvessfully ");
			return new ResponseEntity<>(new RestAPIResponse("Success", "Status Change Successfully", msg),
					HttpStatus.OK);
		} else {
			System.out.println("Not Chnaged");
			logger.info("VMSController.changeStatus() => Status not changed ");
			return new ResponseEntity<>(new RestAPIResponse("fail", "Status not Change ", "Done"), HttpStatus.OK);
		}
	}

	@ApiOperation("To get Vendor Details")
	@ApiResponses({ @ApiResponse(code = 200, message = "Vendor Details fetched"),
			@ApiResponse(code = 404, message = "url not found"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse> gateall() {
		// System.out.println("ooooooooooo " + service.getall());
		logger.info("VendorManagementController.gateall()");
		List<ListVendor> getvendor = service.getvendor();
		Comparator<ListVendor> comparator = (c1, c2) -> {
			return Long.valueOf(c2.getId()).compareTo(c1.getId());
		};
//
		Collections.sort(getvendor, comparator);
		return new ResponseEntity<>(new RestAPIResponse("Success", "Vendor fetched Successfully", getvendor),
				HttpStatus.OK);
	}

	// @CrossOrigin(origins = "http://localhost:4200")
	@ApiOperation("To Delete VMS")
	@ApiResponses({ @ApiResponse(code = 200, message = "VMS Deleted"),
			@ApiResponse(code = 404, message = "VMS entity not found"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<RestAPIResponse> delete(@PathVariable long id) {
		logger.info("VendorManagementController.delete()");
		return new ResponseEntity<>(
				new RestAPIResponse("Success", "Record Deleted Successfully", service.deleteById(id)), HttpStatus.OK);
	}

	@ApiOperation("To Fetch entity By ID")
	@ApiResponses({ @ApiResponse(code = 200, message = "VMS Fetched"),
			@ApiResponse(code = 404, message = "url not found"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/vendor/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse> getbyId(@PathVariable long id) {
		// System.out.println(service.getbyId(id));
		logger.info("VendorManagementController.getbyId()");
		return new ResponseEntity<>(
				new RestAPIResponse("Success", "VMS Fetched By ID Successfully ", service.getbyId(id)), HttpStatus.OK);
	}

	@ApiOperation("Update Entity")
	@ApiResponses({ @ApiResponse(code = 200, message = "Updating Vendor"),
			@ApiResponse(code = 404, message = "url not found"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/vendor", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<RestAPIResponse> updateVMS(@RequestBody VendorDetails vms) {
		// System.out.println(vms);
		logger.info("VendorManagementController.updateVMS()");
		return new ResponseEntity<>(new RestAPIResponse("Success", "VMS Successfully Update", service.save(vms)),
				HttpStatus.OK);
	}

	@ApiOperation("Vendor Status Change ")
	@ApiResponses({ @ApiResponse(code = 200, message = "Change Vendor Status"),
			@ApiResponse(code = 404, message = "Status not found"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/status", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse> changeStatus(@RequestParam("id") Long id,
			@RequestParam("status") String status, @RequestParam("remarks") String remarks

	) {// id="+vmsid+"status="+status+"remarks
		logger.info("VMSController.changeStatus()");

		// System.out.println(id + " === " + status + " == " + remarks);

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

	@ApiOperation("To fetch VMS By PageNo ")
	@ApiResponses({ @ApiResponse(code = 200, message = "Fetched VMS"),
			@ApiResponse(code = 404, message = "VMS entity not found"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/vms/{pageNo}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse2> findPaginated(@PathVariable("pageNo") int pageNo) {
		logger.info("VMSController.findPaginated()");
		int pageSize = 2;

		Page<VendorDetails> findPaginated = service.findPaginated(pageNo, pageSize);
		List<VendorDetails> findAlltech = findPaginated.getContent();
		int totalPages = findPaginated.getTotalPages();
		// System.out.println("kkkkk " + Integer.toString(totalPages));
		return new ResponseEntity<>(new RestAPIResponse2("success", "fetche", totalPages, findAlltech), HttpStatus.OK);
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
