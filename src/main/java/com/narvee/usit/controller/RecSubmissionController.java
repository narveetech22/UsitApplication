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
import org.springframework.web.bind.annotation.RestController;

import com.narvee.usit.commons.RestAPIResponse;
import com.narvee.usit.entity.RecConsultant;
import com.narvee.usit.entity.ReqSubmission;
import com.narvee.usit.entity.Requirements;
import com.narvee.usit.helper.ListRecSubmissions;
import com.narvee.usit.service.IRecSubmissionService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/recruiting/submission")
@CrossOrigin
public class RecSubmissionController {

	private static final Logger logger = LoggerFactory.getLogger(RecSubmissionController.class);

	@Autowired
	private IRecSubmissionService service;

	// delete entity
	@ApiOperation("delete Entity")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<RestAPIResponse> deleteRequirmentByID(@PathVariable long id) {
		logger.info("!!! inside class :RecSubmissionController, !! method : deleteRequirmentByID");
		boolean val = service.deleteSubmissionByID(id);
		if (val == true) {
			return new ResponseEntity<>(new RestAPIResponse("success", "Submission Deleted", ""), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(
					new RestAPIResponse("fail", "Interview Schedule for the submission ,submission not Deleted", ""),
					HttpStatus.OK);
		}

	}

	// get all entities
	@ApiOperation("Get all Entities")
	@RequestMapping(value = "/getsubdetails", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse> getSubmissionDetails() {
		logger.info("!!! inside class :RecSubmissionController, !! method : getAllSubmission");
		return new ResponseEntity<>(new RestAPIResponse("Success", "fetch All Submissions", service.getsubdetails()),
				HttpStatus.OK);
	}

	// saving entity
	@ApiOperation("To Save entity")
	@ApiResponses({ @ApiResponse(code = 200, message = "Object Saved"),
			@ApiResponse(code = 404, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestAPIResponse> saveRequirement(@RequestBody ReqSubmission submission) {
		logger.info("!!! inside class :RecSubmissionController, !! method : saveRequirement");
		System.out.println(submission.getSubmittedby());
		if (submission.getSubmissionid() == null) {
			List<ReqSubmission> consultant1 = service.findByConsultantIdAndRequirementsRequirementidAndEndclient(
					submission.getConsultant().getId(), submission.getRequirements().getRequirementid(),
					submission.getEndclient());
			if (consultant1 == null || consultant1.isEmpty()) {
				/// service.saveConsultantss(salesConsultants);

				// service.saveSubmission(submission)
				return new ResponseEntity<>(new RestAPIResponse("success", "Consultant Submitted Successfully",
						service.saveSubmission(submission)), HttpStatus.CREATED);
			} else {
				return new ResponseEntity<>(new RestAPIResponse("fail", "Consultant Already Submitted", ""),
						HttpStatus.OK);
			}
		} else {
			return new ResponseEntity<>(new RestAPIResponse("success", "Consultant added Successfully",
					service.updateSubmission(submission)), HttpStatus.OK);

		}

	}

	// get all entities
	@ApiOperation("Get all Entities")
	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse> getAllSubmission() {
		logger.info("!!! inside class :RecSubmissionController, !! method : getAllSubmission");

		List<ListRecSubmissions> getall = service.getall();

		Comparator<ListRecSubmissions> comparator = (c1, c2) -> {
			return Long.valueOf(c2.getSub_id()).compareTo(c1.getSub_id());
		};
		//
		Collections.sort(getall, comparator);

		return new ResponseEntity<>(new RestAPIResponse("Success", "fetch All Submissions", getall), HttpStatus.OK);
	}

	// fetch single entity by id
	@ApiOperation("Get Single Entity By Id")
	@ApiResponses({ @ApiResponse(code = 200, message = "Submission Fetched"),
			@ApiResponse(code = 404, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/getbyid/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse> getRequirmentByID(@PathVariable long id) {
		logger.info("!!! inside class :RecSubmissionController, !! method : getRequirmentByID");
		return new ResponseEntity<>(
				new RestAPIResponse("Success", "Submission Fetch Success Fully", service.getSubmissionByID(id)),
				HttpStatus.OK);
	}

	// update entity//
	@ApiOperation("Updating Entity")
	@RequestMapping(value = "/updateSubmission", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestAPIResponse> editRequirmentByID(@RequestBody ReqSubmission submission) {
		logger.info("!!! inside class :RecSubmissionController, !! method : editRequirmentByID");
		return new ResponseEntity<>(
				new RestAPIResponse("Success", "updated Requirments Successfully", service.saveSubmission(submission)),
				HttpStatus.ACCEPTED);
	}

	// pagination entity
	@ApiOperation("all entities with pagination")
	@RequestMapping(value = "/pagesubmission/{pageNo}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse> getConsultalntByPage(@PathVariable int pageNo) {
		int pageSize = 2;
		logger.info("!!! inside class :RecSubmissionController, !! method : getConsultalntByPage");
		Page<List<ReqSubmission>> findPaginated = service.findPaginated(pageNo, pageSize);
		List<List<ReqSubmission>> findAlltech = findPaginated.getContent();
		return new ResponseEntity<>(
				new RestAPIResponse("Success", "Fetch consultant By pageNo Successfully", findAlltech), HttpStatus.OK);
	}

}
