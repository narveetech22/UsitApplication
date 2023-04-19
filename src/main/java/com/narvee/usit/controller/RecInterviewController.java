package com.narvee.usit.controller;

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
import com.narvee.usit.entity.RecInterviews;
import com.narvee.usit.service.IRecInterviewService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/recruiting/interview")
@CrossOrigin
public class RecInterviewController {

	private static final Logger logger = LoggerFactory.getLogger(RecInterviewController.class);

	@Autowired
	private IRecInterviewService service;

	/* to save RecInterview Entity */

	@ApiOperation("To Save RecInterview entity")
	@ApiResponses({ @ApiResponse(code = 200, message = "Object Saved"),
			@ApiResponse(code = 404, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestAPIResponse> saveRecInterview(@RequestBody RecInterviews interviews) {
		logger.info("!!! inside class : RecInterviewController, !! method : saveRecInterview");
		return new ResponseEntity<>(
				new RestAPIResponse("Success", "Save RecInterview Successfully", service.saveInterviews(interviews)),
				HttpStatus.CREATED);
	}

	/* to get All RecInterview Entity */

	@ApiOperation("To getAll RecInterview entity")
	@ApiResponses({ @ApiResponse(code = 200, message = "Object Fetched"),
			@ApiResponse(code = 404, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/getallinterviews", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse> getAllRecInterviewDetails() {
		logger.info("!!! inside class : RecInterviewController, !! method : getAllRecInterviewDetails");
		return new ResponseEntity<>(new RestAPIResponse("Success", "Fetched RecInterviews Details Successfully",
				service.getAllRecInterviewDetails()), HttpStatus.OK);
	}

	/* to get by ID RecInterview Entity */

	@ApiOperation("To get RecInterview entity By ID")
	@ApiResponses({ @ApiResponse(code = 200, message = "Object Fetched"),
			@ApiResponse(code = 404, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "getbyid/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse> getRecInterviewByID(@PathVariable long id) {
		logger.info("!!! inside class : RecInterviewController, !! method : getRecInterviewByID");
		return new ResponseEntity<>(new RestAPIResponse("Success", "Fetched RecInterview ById Successfully",
				service.getRecInterviewById(id)), HttpStatus.OK);
	}

	/* to edit/update RecInterview Entity by ID */

	@ApiOperation("To Edit/Update entity By ID")
	@ApiResponses({ @ApiResponse(code = 200, message = "Object Updated"),
			@ApiResponse(code = 404, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/editinterviews", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestAPIResponse> updateRecInterviewByID(@RequestBody RecInterviews interviews) {
		logger.info("!!! inside class : RecInterviewController, !! method : updateRecInterviewByID");
		return new ResponseEntity<>(new RestAPIResponse("Success", "updated Recruiting Interview Succesfully ",
				service.saveInterviews(interviews)), HttpStatus.ACCEPTED);
	}

	/* to Delete RecInterview Entity By ID */

	@ApiOperation("To Delete RecInterview entity By ID")
	@ApiResponses({ @ApiResponse(code = 200, message = "Object Deleted"),
			@ApiResponse(code = 404, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<RestAPIResponse> deleteRecInterviewByID(@PathVariable long id) {
		logger.info("!!! inside class : RecInterviewController, !! method : deleteRecInterviewByID");
		return new ResponseEntity<>(
				new RestAPIResponse("Success", "RecInterview Deleted Successfully", service.deleteRecInterviewByID(id)),
				HttpStatus.OK);
	}

	/* to get All RecInterview Entity By PageNo */

	@ApiOperation("To get Recinterview entity")
	@ApiResponses({ @ApiResponse(code = 200, message = "Object Fetched"),
			@ApiResponse(code = 404, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/getinterviews/{pageNo}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse> getRecInterviewByPageNo(@PathVariable int pageNo) {
		int pageSize = 2;
		logger.info("!!! inside class : RecInterviewController, !! method : saveRecInterview");
		Page<RecInterviews> findPaginated = service.findPaginated(pageNo, pageSize);
		List<RecInterviews> findAlltech = findPaginated.getContent();
		return new ResponseEntity<>(
				new RestAPIResponse("Success", "Fetch RecInterview By pageNo Successfully", findAlltech),
				HttpStatus.OK);
	}

	/* get Submission Details On consultant for dropdown */

	@ApiOperation("To get RecSubmission entity")
	@ApiResponses({ @ApiResponse(code = 200, message = "Submission Object Fetched"),
			@ApiResponse(code = 404, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/submission", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse> getRecSubmissionDetails() {
		return new ResponseEntity<>(new RestAPIResponse("Success", "Successfully fetched recsub details",
				service.getRecSubmissionDetails()), HttpStatus.OK);
	}

	@ApiOperation("To get RecInterviews entity")
	@ApiResponses({ @ApiResponse(code = 200, message = "RecInterviews Fetched"),
			@ApiResponse(code = 404, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/interviews", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse> getAllRecInterviews() {
		return new ResponseEntity<>(
				new RestAPIResponse("Success", "Successfully fetched recsub details", service.getAllRecInterviews()),
				HttpStatus.OK);
	}

	@ApiOperation("To filter RecInterviews entity")
	@ApiResponses({ @ApiResponse(code = 200, message = "RecInterviews Fetched"),
			@ApiResponse(code = 404, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/filterinterviews/{keyword}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse> filterRecInterviews(@PathVariable String keyword) {
		return new ResponseEntity<>(new RestAPIResponse("Success", "Successfully fetched recsub details",
				service.filterRecInterviews(keyword)), HttpStatus.OK);
	}
}
