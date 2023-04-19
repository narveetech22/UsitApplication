package com.narvee.usit.controller;

import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.narvee.usit.commons.RestAPIResponse;
import com.narvee.usit.commons.RestAPIResponse2;
import com.narvee.usit.entity.Excel;
import com.narvee.usit.entity.GetRoles;
import com.narvee.usit.entity.Users;
import com.narvee.usit.service.IRoleService;
import com.narvee.usit.service.IUserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xddf.usermodel.chart.DisplayBlanks;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin
@RequestMapping(value = "/users")
public class UsersController {
	public static final Logger logger = LoggerFactory.getLogger(UsersController.class);

	ObjectMapper objectMapper = new ObjectMapper();
	@Autowired
	private IUserService iUserService;

	@Autowired
	private IRoleService roleService;

	// public List<GetRecruiter> getRecruiter();

	@ApiOperation("To get recruiters")
	@ApiResponses({ @ApiResponse(code = 200, message = "recruiters fetch"),
			@ApiResponse(code = 404, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/recruiterlist", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse> listrecruiter() {
		logger.info("!!! inside class : RecRequirmentsController, !! method : getAllRequirments");
		return new ResponseEntity<>(new RestAPIResponse("Success", "fetch All recruiters", iUserService.getRecruiter()),
				HttpStatus.OK);
	}

	@ApiOperation("To get recruiters")
	@ApiResponses({ @ApiResponse(code = 200, message = "recruiters fetch"),
			@ApiResponse(code = 404, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/getrecruiter", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse> getrecruiter() {
		logger.info("!!! inside class : RecRequirmentsController, !! method : getAllRequirments");
		return new ResponseEntity<>(new RestAPIResponse("Success", "fetch All recruiters", iUserService.getUser()),
				HttpStatus.OK);
	}

	@ApiOperation("To get all Roles")
	@ApiResponses({ @ApiResponse(code = 200, message = "Fetched all roles"),
			@ApiResponse(code = 404, message = "Entities not found"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/getroles", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse> getAllRoles() {
		logger.info("UsersController.getAllRoles()");
		List<GetRoles> getrole = roleService.getRoles();
		return new ResponseEntity<>(new RestAPIResponse("Success", "All Roles Fetched", getrole), HttpStatus.OK);

	}

	@ApiOperation("To save Users")
	@ApiResponses({ @ApiResponse(code = 200, message = "User saved"),
			@ApiResponse(code = 404, message = " entity not found"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestAPIResponse> addUsers(@RequestBody Users users)
			throws JsonMappingException, JsonProcessingException {
		logger.info("UsersController.addUsers()");
		Users email = iUserService.findUserByEmail(users.getEmail());
		System.out.println(users);
		if (email == null) {
			logger.info("UsersController.addUsers() saving user");
			Users saveUser = iUserService.saveUser(users);
			return new ResponseEntity<>(new RestAPIResponse("Success", "User Saved", saveUser), HttpStatus.CREATED);
		} else {
			logger.info("UsersController.addUsers() => user not saved Already exists");
			return new ResponseEntity<>(new RestAPIResponse("Fail", "User Already Exist", "User Email Already Exist"),
					HttpStatus.OK);
		}
	}

//	@ApiOperation("To save Users")
//	@ApiResponses({ @ApiResponse(code = 200, message = "User saved"),
//			@ApiResponse(code = 404, message = " entity not found"),
//			@ApiResponse(code = 500, message = "Internal Server error") })
//	@RequestMapping(value = "/save2", method = RequestMethod.POST, produces = "application/json", consumes = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<RestAPIResponse> addUsers3(@RequestBody Map<String, String> json_Input)
//			throws JsonMappingException, JsonProcessingException {
//		logger.info("UsersController.addUsers()");
//		System.out.println(json_Input);
//		return new ResponseEntity<>(new RestAPIResponse("Success", "User Saved", ""), HttpStatus.CREATED);
//	}

	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/validateToken", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Boolean getIsValidToken(@RequestParam("file") MultipartFile file) throws Exception {
		List<Excel> list = new ArrayList<>();
		XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
		System.out.println(workbook + " " + file.getInputStream());
		XSSFSheet sheet = workbook.getSheet("data");
		System.out.println(sheet);
		// int rowNumber = 0;

		Iterator<Row> iterator = sheet.iterator();
		iterator.next();
		while (iterator.hasNext()) {
			Row nextRow = iterator.next();
			Iterator<Cell> cellIterator = nextRow.cellIterator();
			Excel vms = new Excel();
			while (cellIterator.hasNext()) {
				Cell nextCell = cellIterator.next();
				int columnIndex = nextCell.getColumnIndex();
				switch (columnIndex) {
				case 0:
					vms.setMovie(nextCell.getStringCellValue());
					// vms.set(nextCell.getStringCellValue());
					break;
				case 1:
					vms.setCategory(nextCell.getStringCellValue());
					// vms.setRecruiter_name(nextCell.getStringCellValue());
					break;

				case 2:
					vms.setDirector(nextCell.getStringCellValue());
					break;

				case 3:
					nextCell.setCellType(CellType.STRING);
					vms.setRating(nextCell.getStringCellValue());
					break;
				}
			}
			if (!list.contains(vms)) {
				list.add(vms);
			}
		}

		System.out.println(list);

		workbook.close();
		return true;
	}

	@ApiOperation("To Update Users")
	@ApiResponses({ @ApiResponse(code = 200, message = "User saved"), // c3roqYbE
			@ApiResponse(code = 404, message = " entity not found"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/update", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<RestAPIResponse> updateUsers(@RequestBody Users users) {
		System.out.println("=========== " + users);
		logger.info("UsersController.updateUsers()");
		Users email = iUserService.findUserByEmailandUid(users.getEmail(), users.getUserid());
		if (email == null) {
			logger.info("UsersController.updateUsers() User updated");
			Users saveUser = iUserService.updateUser(users);
			return new ResponseEntity<>(new RestAPIResponse("Success", "User Updated", saveUser), HttpStatus.CREATED);
		} else {
			logger.info("UsersController.updateUsers() User not updated");
			return new ResponseEntity<>(new RestAPIResponse("Fail", "User Already Exist", "User Email Already Exist"),
					HttpStatus.OK);
		}
	}

	@ApiOperation("To Fetch All Users")
	@ApiResponses({ @ApiResponse(code = 200, message = "Users Fetched"),
			@ApiResponse(code = 404, message = " Users not found"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse> getUsers() {
		logger.info("UsersController.getUsers()");
		List<Users> listall = iUserService.getAllUsers();

//		Comparator<Users> comparator = (c1, c2) -> {
//			return Long.valueOf(c2.getCreateddate().getLong(ChronoField.EPOCH_DAY))
//					.compareTo(c1.getCreateddate().getLong(ChronoField.EPOCH_DAY));
//		};

		Comparator<Users> comparator = (c1, c2) -> {
			return Long.valueOf(c2.getUserid()).compareTo(c1.getUserid());
		};

		Collections.sort(listall, comparator);
		// System.out.println(" == " + listall);

		return new ResponseEntity<>(new RestAPIResponse("Fail", "User Already Exist", listall), HttpStatus.OK);
	}

	@ApiOperation("To fetch records By PageNo ")
	@ApiResponses({ @ApiResponse(code = 200, message = "Fetched VMS"),
			@ApiResponse(code = 404, message = "user entity not found"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/pagination/{pageNo}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse2> findPaginated(@PathVariable("pageNo") int pageNo) {
		logger.info("UsersController.getUsers()");
		int pageSize = 1;
		Page<List<Users>> findPaginated = iUserService.findPaginated(pageNo, pageSize);
		List<List<Users>> findAlltech = findPaginated.getContent();
		int totalPages = findPaginated.getTotalPages();
		return new ResponseEntity<>(
				new RestAPIResponse2("success", "fetching users By Page No", totalPages, findAlltech), HttpStatus.OK);
	}

	@ApiOperation("To Fetch user By Id")
	@ApiResponses({ @ApiResponse(code = 200, message = "Users Fetched"),
			@ApiResponse(code = 404, message = " Users not found"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/userbyid/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse> getUserByid(@PathVariable Long id) {
		logger.info("UsersController.getUserByid()");
		return new ResponseEntity<>(new RestAPIResponse("success", "User Details", iUserService.finduserById(id)),
				HttpStatus.OK);
	}

	// to check authorization
	@ApiOperation("To delete Users")
	@ApiResponses({ @ApiResponse(code = 200, message = "delete records"),
			@ApiResponse(code = 404, message = " entity not found"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<RestAPIResponse> editUser(@PathVariable Long id) {
		logger.info("UsersController.editUser()");
		return new ResponseEntity<>(new RestAPIResponse("Success", "User Deleted", iUserService.deleteUsers(id)),
				HttpStatus.OK);
	}

	@ApiOperation("user Status Change ")
	@ApiResponses({ @ApiResponse(code = 200, message = "Change user Status"),
			@ApiResponse(code = 404, message = "Status not found"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/status", method = RequestMethod.PATCH, produces = "application/json")
	public ResponseEntity<RestAPIResponse> changeStatus(@RequestBody Users users) {
		logger.info("UsersController.changeStatus()");
		System.out.println(users);
		Long id = users.getUserid();
		String status = users.getStatus();
		String remarks = users.getRemarks();

		int changestat = 0;
		String result;
		if (status.equals("Active"))
			result = "InActive";
		else
			result = "Active";
		changestat = iUserService.changeStatus(result, id, remarks);
		if (changestat != 0) {
			logger.info("UsersController.getAllRoles() status changed Successfully");
			/// System.out.println("status Successfully");
		} else {
			logger.info("UsersController.getAllRoles() status not changed ");
			System.out.println("Not Chnaged");
		}
		// iUserService.changeStatus(result, id, remarks);
		return new ResponseEntity<>(new RestAPIResponse("Success", "Status Change Successfully", "Done"),
				HttpStatus.OK);
	}

	@ApiOperation("To Fetch Employee By filter")
	@ApiResponses({ @ApiResponse(code = 200, message = "VMS Fetched Records"),
			@ApiResponse(code = 404, message = "VMS entity not found"),
			@ApiResponse(code = 500, message = "Internal Server error") })
	@RequestMapping(value = "/search2/{keyword}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse> filterVMS(@PathVariable String keyword) {
		System.out.println(keyword + " =========================");
		/// List<GetRoles> getrole = roleService.getRoles();
		return new ResponseEntity<>(
				new RestAPIResponse("Success", "Employee Fetched Records", iUserService.filterEmployee2(keyword)),
				HttpStatus.OK);
	}

}
