package com.narvee.usit.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.springframework.http.HttpHeaders;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.core.io.Resource;
import org.apache.commons.compress.utils.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.narvee.usit.commons.RestAPIResponse;
import com.narvee.usit.entity.ConsultantFileUploads;
import com.narvee.usit.entity.RecConsultant;
import com.narvee.usit.entity.SalesConsultants;
import com.narvee.usit.helper.ListRecruitingConsultant;
import com.narvee.usit.service.IRecruitingConsultingService;
import com.narvee.usit.service.IfileStorageService;
import com.narvee.usit.serviceimpl.RecConsultantServiceImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.nio.file.Path;
import java.nio.file.Files;

@RestController
@RequestMapping("/recruiting/consultant")
@CrossOrigin
public class RecConsultantController {

	private static final Logger logger = LoggerFactory.getLogger(RecConsultantController.class);

	@Autowired
	private IRecruitingConsultingService service;

	/* to save Recruiting Consultant Entity getconsultants */

	/* to Get All Recruiting Consultant Entity */

	@ApiOperation("To get All Recruiting Consultant entity")
	@ApiResponses({ @ApiResponse(code = 200, message = "Recruiting Consultant fetched"),
			@ApiResponse(code = 404, message = "Recruiting Consultant entity not found"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse> getAllRecruitingConsultant() {
		logger.info("!!! inside class: RecConsultantController, method : getAllRecruitingConsultant");

		List<ListRecruitingConsultant> allRecruitingConsultant = service.getAllRecruitingConsultant();

		Comparator<ListRecruitingConsultant> comparator = (c1, c2) -> {
			return Long.valueOf(c2.getId()).compareTo(c1.getId());
		};

		Collections.sort(allRecruitingConsultant, comparator);

		return new ResponseEntity<>(

				new RestAPIResponse("Success", "Consultant fetched Successfully", allRecruitingConsultant),
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
		int changestat = 0;
		String result;
		if (status.equalsIgnoreCase("Active")) {
			result = "InActive";
		} else {
			result = "Active";
		}

		changestat = service.toggleStatus(result, id, remarks);

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

	@ApiOperation("To Delete Recruiting Consultant entity By ID")
	@ApiResponses({ @ApiResponse(code = 200, message = "Recruiting Consultant Deleted"),
			@ApiResponse(code = 404, message = "Recruiting Consultant entity not found"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<RestAPIResponse> deleteConsultantByID(@PathVariable long id) {
		System.out.println("  =============  " + id);
		// service.deleteRecruitingConsultantByID(id);
		logger.info("!!! inside class: RecConsultantController, method : deleteConsultantByID");
		boolean val = service.deleteRecruitingConsultantByID(id);
		if (val == true) {
			return new ResponseEntity<>(new RestAPIResponse("success", "Consultant Deleted", ""), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new RestAPIResponse("fail",
					"Consultant Submitted  for the requirement ,Consultant Not Deleted", ""), HttpStatus.OK);
		}

	}

	@ApiOperation("To Save Requirment")
	@ApiResponses({ @ApiResponse(code = 200, message = "Requirment Saved"),
			@ApiResponse(code = 404, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/getconsultants", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse> getconsultant() {
		logger.info("!!! inside class : RecRequirmentsController, !! method : getAllRequirments");
		return new ResponseEntity<>(new RestAPIResponse("Success", "fetch All Requirments", service.getconsultants()),
				HttpStatus.OK);
	}

	@ApiOperation("To Save Requirment")
	@ApiResponses({ @ApiResponse(code = 200, message = "Requirment Saved"),
			@ApiResponse(code = 404, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/getconsultantsbyId/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse> getconsultantByRequirements(@PathVariable long id) {
		logger.info("!!! inside class : RecRequirmentsController, !! method : getAllRequirments");

		return new ResponseEntity<>(
				new RestAPIResponse("Success", "fetch All Requirments", service.getconsultantsbyRequirementId(id)),
				HttpStatus.OK);
	}

	@ApiOperation("To save Recruiting Consultant entity")
	@ApiResponses({ @ApiResponse(code = 200, message = "Recruiting Consultant saved"),
			@ApiResponse(code = 404, message = "Recruiting Consultant entity not found"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestAPIResponse> saveRecrutingConsultant(@RequestBody RecConsultant consultant) {
		logger.info("!!! inside class: RecConsultantController, method : saveRecrutingConsultant");
		if (consultant.getId() == null) {
			List<RecConsultant> consultant1 = service.findByEmail(consultant.getEmail());
			if (consultant1 == null || consultant1.isEmpty()) {
				/// service.saveConsultantss(salesConsultants);
				return new ResponseEntity<>(new RestAPIResponse("success", "Consultant added Successfully",
						service.saveRecruitingConsutant(consultant)), HttpStatus.CREATED);
			} else {
				return new ResponseEntity<>(new RestAPIResponse("fail", "Consultant Already Exists", ""),
						HttpStatus.OK);
			}
		} else {
			return new ResponseEntity<>(new RestAPIResponse("success", "Consultant added Successfully",
					service.saveRecruitingConsutant(consultant)), HttpStatus.CREATED);

		}

	}

	@Autowired
	private IfileStorageService fileStorageService;

	@RequestMapping(value = "/uploadMultiple/{id}", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<RestAPIResponse> uploadMultipleFile2(
			@RequestParam(required = false, value = "resumename") MultipartFile resume, @PathVariable long id)
			throws IOException {
		System.out.println(id);

		RecConsultant doc = service.getConsultantByID(id);
		System.out.println(doc);

		String nresumne = doc.getResume();
		try {
			String firstfileName = fileStorageService.storeFile(resume);
			// nresumne = StringUtils.cleanPath(resume.getOriginalFilename());
		} catch (NullPointerException e) {
			// TODO: handle exception
		}
		System.out.println(nresumne);
		service.update(nresumne, id);
		return new ResponseEntity(new RestAPIResponse("Success", "Uploaded files", ""), HttpStatus.OK);

	}

	@PostMapping("/upload/{id}")
	public ResponseEntity<RestAPIResponse> uploadFiles(
			@RequestParam(required = false, value = "resume") MultipartFile resume,
			@RequestParam(required = false, value = "files") MultipartFile[] files, @PathVariable long id) {
		String message = "";
		String resumen = "";
		try {
			String resumename = fileStorageService.storeFile(resume);
			service.update(resumename, id);
		} catch (NullPointerException e) {
		}

		try {
			List<String> fileNames = new ArrayList<>();

			if (files.length != 0) {
				Arrays.asList(files).stream().forEach(file -> {

					String filename = fileStorageService.storemultiplefiles(file);
					service.uploadfiles(filename, id);
					fileNames.add(filename);
				});
			}

			message = "Uploaded the files successfully: ";
			return new ResponseEntity(new RestAPIResponse("Success", "Uploaded files", ""), HttpStatus.OK);
		} catch (Exception e) {
			message = "Fail to upload files!";
			return new ResponseEntity(new RestAPIResponse("Success", "Uploaded files", ""), HttpStatus.OK);
		}
	}

	/* to Get Recruiting Consultant Entity By ID */

	@ApiOperation("To get Recruiting Consultant entity ByID")
	@ApiResponses({ @ApiResponse(code = 200, message = "Recruiting Consultant fetched"),
			@ApiResponse(code = 404, message = "Recruiting Consultant entity not found"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/getbyid/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse> getConsultantByID(@PathVariable long id) {
		logger.info("!!! inside class: RecConsultantController, method : getConsultantByID");
		return new ResponseEntity<>(
				new RestAPIResponse("Success", "fetch Successfullt by Consutlant ID", service.getConsultantByID(id)),
				HttpStatus.OK);
	}

	/* to Delete Recruiting Consultant Entity By ID */

	/*
	 * to Get Recruiting Consultant Entity By Name
	 * 
	 * @ApiOperation("To get Recruiting Consultant entity By Name")
	 * 
	 * @ApiResponses({ @ApiResponse(code = 200, message =
	 * "Recruiting Consultant Fetched"),
	 * 
	 * @ApiResponse(code = 404, message = "Recruiting Consultant entity not found"),
	 * 
	 * @ApiResponse(code = 500, message = "Internal Server error") })
	 * 
	 * @RequestMapping(value = "/consultan/{name}/{id}", method = RequestMethod.GET,
	 * produces = "application/json") public ResponseEntity<RestAPIResponse>
	 * getDetailsConsultantByName(@PathVariable String name,@PathVariable long id) {
	 * return new ResponseEntity<>(new RestAPIResponse("Success",
	 * "Fetched Consultant by Name Successfully",
	 * service.getDetailsByConsultantName(name,id)),HttpStatus.OK); }
	 */

	/* to Edit Recruiting Consultant Entity By ID */

	@ApiOperation("To edit/update Recruiting Consultant entity By ID")
	@ApiResponses({ @ApiResponse(code = 200, message = "Recruiting Consultant Fetched"),
			@ApiResponse(code = 404, message = "Recruiting Consultant entity not found"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/consultants/edit", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestAPIResponse> updateRecruitingConsultantByID(@RequestBody RecConsultant consultant) {
		logger.info("!!! inside class: RecConsultantController, method : updateRecruitingConsultantByID");
		return new ResponseEntity<>(new RestAPIResponse("Sucess", "Updated Consultant By ID Successfully",
				service.saveRecruitingConsutant(consultant)), HttpStatus.ACCEPTED);
	}

	/* to Get Recruiting Consultant Entity By pageNo */

	@ApiOperation("To get Recruiting Consultant entity By pageNo")
	@ApiResponses({ @ApiResponse(code = 200, message = "Recruiting Consultant Fetched"),
			@ApiResponse(code = 404, message = "Recruiting Consultant entity not found"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/consultant/page/{pageNo}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse> getConsultalntByPage(@PathVariable int pageNo) {
		int pageSize = 2;
		logger.info("!!! inside class: RecConsultantController, method : getConsultalntByPage");
		Page<List<RecConsultant>> findPaginated = service.findPaginated(pageNo, pageSize);
		List<List<RecConsultant>> findAlltech = findPaginated.getContent();
//		List<RecrutingConsutantHelper> findAllSalesCon = service.getAllRecruitingConsultant();
		return new ResponseEntity<>(
				new RestAPIResponse("Success", "Fetch consultant By pageNo Successfully", findAlltech), HttpStatus.OK);
	}

	/* to Get Recruiting Consultant Entity By pageNo */

	@ApiOperation("To get Recruiting Consultant entity By filter")
	@ApiResponses({ @ApiResponse(code = 200, message = "Recruiting Consultant Fetched"),
			@ApiResponse(code = 404, message = "Recruiting Consultant entity not found"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/consultants/search/{keyword}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse> getAllRecConsultantByFilter(@PathVariable String keyword) {
		return new ResponseEntity<>(new RestAPIResponse("Success", "Fetched RecConsultant by filter",
				service.getAllRecConsultantByFilter(keyword)), HttpStatus.OK);
	}

	/* to change the Recruiting consultant status */

	/*
	 * @ApiOperation("To chage Recruiting Consultant entity Status")
	 * 
	 * @ApiResponses({ @ApiResponse(code = 200, message =
	 * "Recruiting Consultant Status Changed"),
	 * 
	 * @ApiResponse(code = 404, message = "Recruiting Consultant entity not found"),
	 * 
	 * @ApiResponse(code = 500, message = "Internal Server error") })
	 * 
	 * @RequestMapping(value="/editstatus",method = RequestMethod.POST, produces =
	 * "application/json") public ResponseEntity<RestAPIResponse>
	 * changeStatus(HttpServletRequest req){ long id =
	 * Long.parseLong(req.getParameter("id")); String status =
	 * req.getParameter("status");
	 * 
	 * long changestat= 0; String result; if(status.equals("Active")) result =
	 * "InActive"; else result = "Active"; changestat = service.changeStatus(result,
	 * id); if(changestat!=0) { System.out.println("status Successfully"); } else {
	 * System.out.println("Not Chnaged"); } service.changeStatus(result, id);
	 * 
	 * return new ResponseEntity<>(new RestAPIResponse("Success",
	 * "Status Change Successfully","Done" ),HttpStatus.OK);
	 * 
	 * }
	 */

	@Autowired
	private ServletContext sc;

	/// @GetMapping("/download/{id}/{filetype}")
	@ApiOperation("Vendor Status Change ")
	@ApiResponses({ @ApiResponse(code = 200, message = "Change Vendor Status"),
			@ApiResponse(code = 404, message = "Status not found"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/download/{id}/{consid}", method = RequestMethod.GET, produces = "application/json")
	public String fileDownload(HttpServletResponse res, @PathVariable long id, @PathVariable long consid)
			throws Exception {
		System.out.println(id + " =======  " + consid);
		// , @PathVariable("filetype") String type
		// System.out.println(id+" idd "+type);
		String filePath = null;

		ConsultantFileUploads model = service.getFile(id);// iConService.getFile(id);
		filePath = "D:/stores2/" + model.getFilename();
		System.out.println(filePath);
		File file = new File(filePath);
		// get the length of the file and make it as the response content length
		res.setContentLengthLong(file.length());

		// get MIME of the file and make it as the resposen content type
		String mimeType = sc.getMimeType(filePath);
		mimeType = mimeType == null ? "application/octet-stream" : mimeType;
		res.setContentType(mimeType);

		// create InputStream pointing to the file
		InputStream is = new FileInputStream(file);
		// create OutputStream pointing to response obj
		OutputStream os = res.getOutputStream();
		// instruct the browser to give file content as downloadble file
		res.setHeader("Content-Disposition", "attachment;fileName=" + file.getName());
		// write file content to response obj
		IOUtils.copy(is, os);
		// close streams
		is.close();
		os.close();
		// System.out.println(mimeType+" file g "+filePath);

		return null;
	}

	@ApiOperation("Vendor Status Change ")
	@ApiResponses({ @ApiResponse(code = 200, message = "Change Vendor Status"),
			@ApiResponse(code = 404, message = "Status not found"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/downloadfiles/{id}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseEntity<Resource> downloadFile(@PathVariable long id) throws IOException {
		System.out.println(id + " =================");
		Resource file = service.download(id);
		Path path = file.getFile().toPath();

		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, Files.probeContentType(path))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}

}
