package com.narvee.usit.controller; /* created By Swamy   			*/

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
import com.narvee.usit.entity.Interview;
import com.narvee.usit.helper.ListInterview;
import com.narvee.usit.service.IinterviewService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/sales/interview")
@CrossOrigin
public class InterviewController {

	private static final Logger logger = LoggerFactory.getLogger(InterviewController.class);

	@Autowired
	public IinterviewService service;

	@ApiOperation("To Save Interview")
	@ApiResponses({ @ApiResponse(code = 200, message = "Interview Saved"),
			@ApiResponse(code = 404, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestAPIResponse> saveSalesInterview(@RequestBody Interview interview) {
		logger.info("!!! inside class : saveSalesInterview, !! method : saveSalesInterview");
		return new ResponseEntity<>(
				new RestAPIResponse("Success", "Interview Save Succesfully", service.saveIterview(interview)),
				HttpStatus.CREATED);
	}

	@ApiOperation("To Delete Interview")
	@ApiResponses({ @ApiResponse(code = 200, message = "Interview Deleted"),
			@ApiResponse(code = 404, message = "Interview entity not found"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<RestAPIResponse> deleteSalesInterview(@PathVariable int id) {
		logger.info("!!! inside class : saveSalesInterview, !! method : deleteSalesInterview");
		return new ResponseEntity<>(
				new RestAPIResponse("Success", "Interview Deleted Successfully", service.deleteInterviewById(id)),
				HttpStatus.OK);
	}

	@ApiOperation("To getAll Interview")
	@ApiResponses({ @ApiResponse(code = 200, message = " getAllInterview"),
			@ApiResponse(code = 404, message = "Interview entity not found"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse> getAllInterview() {
		logger.info("!!! inside class : saveSalesInterview, !! method : getAllInterview");
		return new ResponseEntity<>(
				new RestAPIResponse("Success", "Interview Deleted Successfully", service.getAllDetailsInterview()),
				HttpStatus.OK);
	}

	@ApiOperation("To Fetch Interview By InterviewID")
	@ApiResponses({ @ApiResponse(code = 200, message = "Interview Fetched"),
			@ApiResponse(code = 404, message = "Interview entity not found"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/getinterview/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse> getSalesInterviewByID(@PathVariable int id) {
		logger.info("!!! inside class : saveSalesInterview, !! method : getSalesInterviewByID");
		return new ResponseEntity<>(
				new RestAPIResponse("Success", "Interview Fetched Successfully", service.getInterviewByID(id)),
				HttpStatus.OK);
	}

	@ApiOperation("To fetch Interview By PageNo ")
	@ApiResponses({ @ApiResponse(code = 200, message = "Fetched Interviews"),
			@ApiResponse(code = 404, message = "Interviews entity not found"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/getpageinterview/{pageNo}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse> findPaginated(@PathVariable("pageNo") int pageNo) {
		int pageSize = 1;
		logger.info("!!! inside class : saveSalesInterview, !! method : findPaginated");
		Page<Interview> findPaginated = service.findPaginated(pageNo, pageSize);
		List<Interview> findAlltech = findPaginated.getContent();
		return new ResponseEntity<>(
				new RestAPIResponse("success", "fetching Interview By Page No Successfully", findAlltech),
				HttpStatus.OK);
	}

	@ApiOperation("To fetch Interview By PageNo ")
	@ApiResponses({ @ApiResponse(code = 200, message = "Fetched Interviews"),
			@ApiResponse(code = 404, message = "Interviews entity not found"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/pagination/{pageNo}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse> findPaginatednative(@PathVariable("pageNo") int pageNo) {
		int pageSize = 1;
		// Page<List<ListInterview>> findPaginateded(int pageNo, int pageSize);
		logger.info("!!! inside class : saveSalesInterview, !! method : findPaginated");
		Page<List<ListInterview>> findPaginated = service.findPaginateded(pageNo, pageSize);// //service.findPaginateded(pageNo,
																							// pageSize);
		List<List<ListInterview>> findAlltech = findPaginated.getContent();

		// List<ListInterview> findAlltech = findPaginated.getContent();
		return new ResponseEntity<>(
				new RestAPIResponse("success", "fetching Interview By Page No Successfully", findAlltech),
				HttpStatus.OK);
	}

	@ApiOperation("To Edit/Update Interview")
	@ApiResponses({ @ApiResponse(code = 200, message = "Interview updated"),
			@ApiResponse(code = 404, message = "Interview entity not found"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/editinterview", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestAPIResponse> editSalesInterview(@RequestBody Interview interview) {
		logger.info("!!! inside class : saveSalesInterview, !! method : editSalesInterview");
		return new ResponseEntity<>(
				new RestAPIResponse("Success", "Interview Edited Succesdfully", service.saveIterview(interview)),
				HttpStatus.ACCEPTED);
	}

	@ApiOperation("To fetch salesconsultant By id  ")
	@ApiResponses({ @ApiResponse(code = 200, message = "Fetched salesconsultant"),
			@ApiResponse(code = 404, message = "salesconsultant entity not found"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/getconsultant/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse> togetSalesConsByID(@PathVariable long id) {
		return new ResponseEntity<>(
				new RestAPIResponse("Success", "salesCons By id success", service.getSalesConsById(id)), HttpStatus.OK);
	}

	@ApiOperation("To fetch salesconsultant By id  ")
	@ApiResponses({ @ApiResponse(code = 200, message = "Fetched salesconsultant"),
			@ApiResponse(code = 404, message = "salesconsultant entity not found"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/submissiondetails", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse> getsubmission() {
		return new ResponseEntity<>(
				new RestAPIResponse("Success", "salesCons By id success", service.submissiondetails()), HttpStatus.OK);
	}

}
