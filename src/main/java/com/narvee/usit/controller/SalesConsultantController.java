package com.narvee.usit.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.narvee.usit.commons.RestAPIResponse;
import com.narvee.usit.entity.City;
import com.narvee.usit.entity.SalesConsultants;
import com.narvee.usit.entity.SalesSubmission;
import com.narvee.usit.service.ISalesConsultantsService;
import com.narvee.usit.service.IfileStorageService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/sales/consultant")
@CrossOrigin
public class SalesConsultantController {

	private static final Logger logger = LoggerFactory.getLogger(SalesConsultantController.class);

	@Autowired
	private ISalesConsultantsService service;

	/* save sales Consultant Entity */

	@ApiOperation("To save SalesConsultance")
	@ApiResponses({ @ApiResponse(code = 200, message = "consultance saved"),
			@ApiResponse(code = 404, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "save", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestAPIResponse> saveCons(@RequestBody SalesConsultants salesConsultants) {
		logger.info("!!! inside class: SalesConsultantController, !!method: saveCons");

		List<SalesConsultants> consultant = service.findByEmail(salesConsultants.getEmail());
		if (consultant == null || consultant.isEmpty()) {
			/// service.saveConsultantss(salesConsultants);
			return new ResponseEntity<>(new RestAPIResponse("success", "Consultant added Successfully",
					service.saveConsultantss(salesConsultants)), HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(new RestAPIResponse("fail", "Consultant Already Exists", ""), HttpStatus.OK);
		}

	}

	@ApiOperation("To save SalesConsultance")
	@ApiResponses({ @ApiResponse(code = 200, message = "consultance saved"),
			@ApiResponse(code = 404, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "update", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestAPIResponse> updateCons(@RequestBody SalesConsultants salesConsultants) {
		logger.info("!!! inside class: SalesConsultantController, !!method: saveCons");

		List<SalesConsultants> consultant = service.findByEmailAndSidNotEqual(salesConsultants.getEmail(),
				salesConsultants.getSid());
		if (consultant == null || consultant.isEmpty()) {
			service.saveConsultantss(salesConsultants);
			return new ResponseEntity<>(new RestAPIResponse("success", "Consultant Updated Successfully",
					service.saveConsultantss(salesConsultants)), HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(new RestAPIResponse("fail", "Consultant Already Exists", ""), HttpStatus.OK);
		}

	}

	/* get All sales Consultant Entity */

	@ApiOperation("To getAll SalesConsultance")
	@ApiResponses({ @ApiResponse(code = 200, message = "get All Sales Consultance"),
			@ApiResponse(code = 404, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "all", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse> getAllConsultant() {

		List<SalesConsultants> findAllConsultants = service.findAllConsultants();

		Comparator<SalesConsultants> comparator = (c1, c2) -> {
			return Long.valueOf(c2.getSid()).compareTo(c1.getSid());
		};
		Collections.sort(findAllConsultants, comparator);

		logger.info("!!! inside class: SalesConsultantController, !!method: getAllConsultant");
		return new ResponseEntity<>(
				new RestAPIResponse("Success", "Successfuuly fetched Sales consultant details", findAllConsultants),
				HttpStatus.OK);
	}

	/* get sales Consultant Entity By ID */

	@ApiOperation("To get sales consultance by id")
	@ApiResponses({ @ApiResponse(code = 200, message = "get sales consultance by id"),
			@ApiResponse(code = 404, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/getconsultant/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse> getSalesConsByID(@PathVariable long id) {
		logger.info("!!! inside class: SalesConsultantController, !!method: getSalesConsByID");
		return new ResponseEntity<>(
				new RestAPIResponse("Success", "Successfully fetched By Cons By Id", service.getbyId(id)),
				HttpStatus.OK);
	}

	/* Delete sales Consultant Entity By ID */

	@ApiOperation("To delete Sales consultance by id")
	@ApiResponses({ @ApiResponse(code = 200, message = "delete sales consultancr"),
			@ApiResponse(code = 404, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<RestAPIResponse> deleteConsByID(@PathVariable long id) {
		logger.info("!!! inside class: SalesConsultantController, !!method: deleteConsByID");
		boolean val = service.deleteById(id);
		if (val == true) {
			return new ResponseEntity<>(new RestAPIResponse("Success", "Consulatant Deleted", ""), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new RestAPIResponse("fail", "Consulatant Submitted, Consultant Not Deleted", ""),HttpStatus.OK);
		}
		
	}

	/* get sales Consultant Entity By Page No */

	@RequestMapping(value = "/consutlant/{pageNo}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse> getSalesConsByPageNo(@PathVariable int pageNo) {
		int pageSize = 2;
		Page<SalesConsultants> findPaginated = service.findPaginated(pageNo, pageSize);
		List<SalesConsultants> findAlltech = findPaginated.getContent();
//		List<RecrutingConsutantHelper> findAllSalesCon = service.getAllRecruitingConsultant();
		return new ResponseEntity<>(
				new RestAPIResponse("Success", "Fetch salesCons By pageNo Successfully", findAlltech), HttpStatus.OK);
	}
//	
	/* Edit sales Consultant Entity By ID */

	@RequestMapping(value = "editConsultants", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestAPIResponse> editSalesConsByID(@RequestBody SalesConsultants consultants) {
		logger.info("!!! inside class: SalesConsultantController, !!method: editSalesConsByID");
		return new ResponseEntity<>(new RestAPIResponse("Success", "Succesfully sales consultant edited",
				service.saveConsultantss(consultants)), HttpStatus.ACCEPTED);
	}

	ObjectMapper objectMapper = new ObjectMapper();

//	@ApiOperation("To save City")
//	@ApiResponses({ @ApiResponse(code = 200, message = "city saved"), @ApiResponse(code = 404, message = "Bad Request"),
//			@ApiResponse(code = 500, message = "Internal Server error") })
//	@RequestMapping(value = "saveexp", method = RequestMethod.POST, produces = "application/json")
//	public ResponseEntity<RestAPIResponse> saveCity(@RequestBody SalesConsultants city) {
//		logger.info("!!! inside class: CityController, !! method: saveCity");
//		return new ResponseEntity<>(new RestAPIResponse("Success", "save City Entity", service.saveConsultantss(city)),
//				HttpStatus.CREATED);
//	}

	@Autowired
	private IfileStorageService fileStorageService;

	/*
	 * @RequestMapping(value = "/uploadMultiple/{id}", method = RequestMethod.POST,
	 * consumes = MediaType.MULTIPART_FORM_DATA_VALUE) public
	 * ResponseEntity<RestAPIResponse> uploadMultipleFile(
	 * 
	 * @RequestParam(required = false, value = "resumename") MultipartFile resume,
	 * 
	 * @RequestParam(required = false, value = "hibname") MultipartFile h1b,
	 * 
	 * @RequestParam(required = false, value = "dlname") MultipartFile
	 * dl, @PathVariable long id) throws IOException { System.out.println(id);
	 * 
	 * SalesConsultants doc = service.getbyId(id);
	 * 
	 * String nresumne = null; String nh1b = null; String ndl = null; try { String
	 * firstfileName = fileStorageService.storeFile(resume); nresumne =
	 * StringUtils.cleanPath(resume.getOriginalFilename()); } catch
	 * (NullPointerException e) { // TODO: handle exception }
	 * 
	 * try { String secondfileName = fileStorageService.storeFile(h1b); nh1b =
	 * StringUtils.cleanPath(h1b.getOriginalFilename()); } catch
	 * (NullPointerException e) { // TODO: handle exception }
	 * 
	 * try { String thirdfileName = fileStorageService.storeFile(dl); ndl =
	 * StringUtils.cleanPath(dl.getOriginalFilename()); doc.setDlcopy(ndl); } catch
	 * (NullPointerException e) { // TODO: handle exception }
	 * System.out.println(nresumne+" "+nh1b+" == "+ndl); //service.update(nresumne,
	 * nh1b, ndl, id); return new ResponseEntity(new RestAPIResponse("Success",
	 * "Uploaded files", ""), HttpStatus.OK);
	 * 
	 * }
	 */

	@RequestMapping(value = "/uploadMultiple/{id}", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<RestAPIResponse> uploadMultipleFile2(
			@RequestParam(required = false, value = "resumename") MultipartFile resume,
			@RequestParam(required = false, value = "hibname") MultipartFile h1b,
			@RequestParam(required = false, value = "dlname") MultipartFile dl, @PathVariable long id)
			throws IOException {
		System.out.println(id);

		SalesConsultants doc = service.getbyId(id);
		System.out.println(doc);

		String nresumne = doc.getResumepath();
		String nh1b = doc.getH1bcopy();
		String ndl = doc.getDlcopy();
		try {
			String firstfileName = fileStorageService.storeFile(resume);
			nresumne = StringUtils.cleanPath(resume.getOriginalFilename());
		} catch (NullPointerException e) {
			// TODO: handle exception
		}

		try {
			String secondfileName = fileStorageService.storeFile(h1b);
			nh1b = StringUtils.cleanPath(h1b.getOriginalFilename());
		} catch (NullPointerException e) {
			// TODO: handle exception
		}

		try {
			String thirdfileName = fileStorageService.storeFile(dl);
			ndl = StringUtils.cleanPath(dl.getOriginalFilename());
			doc.setDlcopy(ndl);
		} catch (NullPointerException e) {
			// TODO: handle exception
		}
		System.out.println(nresumne + " " + nh1b + " == " + ndl);
		service.update(nresumne, nh1b, ndl, id); // Recruiter Sample.xlsx Vendor Sample (1).xlsx ==
													// PortalVmsUploadDataFormat (2) (1).xlsx
		return new ResponseEntity(new RestAPIResponse("Success", "Uploaded files", ""), HttpStatus.OK);

	}

	@ApiOperation("To get CityId, CityName")
	@ApiResponses({ @ApiResponse(code = 200, message = "getAll cities"),
			@ApiResponse(code = 404, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/consultantinfo", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse> getCities() {
		logger.info("!!! inside class: CityController, !! method: getAllCities");
		return new ResponseEntity<>(new RestAPIResponse("Success", "getall Cities", service.getconsultInfo()),
				HttpStatus.OK);
	}

	@ApiOperation("Vendor Status Change ")
	@ApiResponses({ @ApiResponse(code = 200, message = "Change Vendor Status"),
			@ApiResponse(code = 404, message = "Status not found"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/status", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse> changeStatus(@RequestParam("id") Long id,
			@RequestParam("status") String status, @RequestParam("remarks") String remarks) {// id="+vmsid+"status="+status+"remarks
		logger.info("VMSController.changeStatus()");
		System.out.println(status + "  == " + remarks + " == " + id);
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
			return new ResponseEntity<>(new RestAPIResponse("success", "Status Change Successfully", "Done"),
					HttpStatus.OK);
		} else {
			System.out.println("Not Chnaged");
			logger.info("VMSController.changeStatus() => Status not changed ");
			return new ResponseEntity<>(new RestAPIResponse("fail", "Status Change Successfully", "Done"),
					HttpStatus.OK);
		}
	}

}
