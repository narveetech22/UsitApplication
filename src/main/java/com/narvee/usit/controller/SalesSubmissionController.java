package com.narvee.usit.controller;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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
import com.narvee.usit.entity.Roles;
import com.narvee.usit.entity.SalesConsultants;
import com.narvee.usit.entity.SalesSubmission;
import com.narvee.usit.helper.ListVendor;
import com.narvee.usit.service.ISalesConsultantsService;
import com.narvee.usit.service.ISubmissionService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/sales/submission")
@CrossOrigin
public class SalesSubmissionController {

	private static final Logger logger = LoggerFactory.getLogger(SalesSubmissionController.class);

	@Autowired
	private ISubmissionService service;

	@Autowired
	private ISalesConsultantsService consultantsService;

	@ApiOperation("To save SalesConsultance")
	@ApiResponses({ @ApiResponse(code = 200, message = "consultance saved"),
			@ApiResponse(code = 404, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/addSubmission", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestAPIResponse> saveSalesSubmission(@RequestBody SalesSubmission submission) {
		// System.out.println(submission);
		logger.info("!!! inside class : SalesSubmissionController, !! method : saveSalesSubmission");

		if (submission.getSubid() == null) {
			List<SalesSubmission> consultant = service.findByConsultantSidAndProjectlocationAndEndclient(
					submission.getConsultant().getSid(), submission.getProjectlocation(), submission.getEndclient());
			if (consultant == null || consultant.isEmpty()) {
				// service.saveConsultantss(salesConsultants);
				return new ResponseEntity<>(new RestAPIResponse("success", "Consultant Submitted Successfully",
						service.saveSubmission(submission)), HttpStatus.CREATED);
			} else {
				return new ResponseEntity<>(new RestAPIResponse("fail", "Consultant Already Submitted", ""),
						HttpStatus.OK);
			}

		} else {
			return new ResponseEntity<>(new RestAPIResponse("success", "Consultant Submitted Successfully",
					service.saveSubmission(submission)), HttpStatus.OK);

		}
	}

	@RequestMapping(value = "/getbyid/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse> getSalesSubmissionByID(@PathVariable long id) {
		// findByConsultantSidAndProjectlocationAndEndclient
		logger.info("!!! inside class : SalesSubmissionController, !! method : getSalesSubmissionByID");
		System.out.println(service.getSubmissionByID(id));
		return new ResponseEntity<>(
				new RestAPIResponse("Success", "SuccesfullyFetched ", service.getSubmissionByID(id)), HttpStatus.OK);
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<RestAPIResponse> deleteSalesSubmissionById(@PathVariable long id) {
		logger.info("!!! inside class : SalesSubmissionController, !! method : deleteSalesSubmissionById");

		boolean val = service.deleteSalesSubmission(id);
		if (val == true) {
			return new ResponseEntity<>(new RestAPIResponse("Success", "Submission Deleted", ""), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(
					new RestAPIResponse("fail", "Interview Schedule for Submission, Submission Not Deleted", ""),
					HttpStatus.OK);
		}

	}

	@RequestMapping(value = "/editsubmission", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestAPIResponse> editSaleSubmissionById(@RequestBody SalesSubmission submission) {
		logger.info("!!! inside class : SalesSubmissionController, !! method : editSaleSubmissionById");
		return new ResponseEntity<>(new RestAPIResponse("Success", "Successfully edit SalesSubmission By ID",
				service.saveSubmission(submission)), HttpStatus.ACCEPTED);
	}

	@ApiOperation("To get all Roles")
	@ApiResponses({ @ApiResponse(code = 200, message = "Fetched all roles"),
			@ApiResponse(code = 404, message = "Entities not found"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse> getallsub() {
		logger.info("inside RolesController.getAllRoles()=> fetching all roles");
		System.out.println(service.getAllSalesSubmission());

		List<SalesSubmission> allSalesSubmission = service.getAllSalesSubmission();

		Comparator<SalesSubmission> comparator = (c1, c2) -> {
			return Long.valueOf(c2.getSubid()).compareTo(c1.getSubid());
		};
		Collections.sort(allSalesSubmission, comparator);

		return new ResponseEntity<>(new RestAPIResponse("Success", "All Roles Fetched", allSalesSubmission),
				HttpStatus.OK);

	}

	@RequestMapping(value = "/submission/{pageNo}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse> getSalesSubmissionByPageNo(@PathVariable int pageNo) {
		int pageSize = 2;
		logger.info("!!! inside class : SalesSubmissionController, !! method : getSalesSubmissionByPageNo");
		Page<SalesSubmission> findPaginated = service.findPaginated(pageNo, pageSize);
		List<SalesSubmission> findAlltech = findPaginated.getContent();
//		List<RecrutingConsutantHelper> findAllSalesCon = service.getAllRecruitingConsultant();
		return new ResponseEntity<>(
				new RestAPIResponse("Success", "Fetch submissions By pageNo Successfully", findAlltech), HttpStatus.OK);
	}

//	@RequestMapping(value = "consultants", method = RequestMethod.GET, produces = "application/json")
//	public ResponseEntity<RestAPIResponse> getConsultantWithtechnology() {
//		return new ResponseEntity<>(new RestAPIResponse("Success", "fetch consultant with technology", consultantsService.getConsultantWithtechnology()), HttpStatus.OK);
//	}

	@ApiOperation("Vendor Status Change ")
	@ApiResponses({ @ApiResponse(code = 200, message = "Change Vendor Status"),
			@ApiResponse(code = 404, message = "Status not found"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/status", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse> changeStatus(@RequestParam("id") Long id,
			@RequestParam("status") String status, @RequestParam("remarks") String remarks

	) {// id="+vmsid+"status="+status+"remarks
		logger.info("VMSController.changeStatus()");
		int changestat = 0;
		String result;
		if (status.equalsIgnoreCase("Active")) {
			result = "InActive";
		} else {
			result = "Active";
		}

		changestat = service.toggleStatus(result, remarks, id);

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

}
