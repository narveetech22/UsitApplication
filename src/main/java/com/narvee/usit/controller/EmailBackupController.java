package com.narvee.usit.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.narvee.usit.commons.RestAPIResponse;
import com.narvee.usit.entity.Email;
import com.narvee.usit.service.IEmailBackupService;

@RestController
@RequestMapping("/mail")
@CrossOrigin
public class EmailBackupController {

	@Autowired
	private IEmailBackupService service;

	@GetMapping("/mail")
	public void save() {
		System.out.println("hello kiran");
//		String host = "smtp.narveetech.com";
//        String port = "995";
//        final String username= "saikiran@narveetech.com";  
//		final String password= "Narvee123$";//change accordingly  
//		
		String host = "smtp.narveetech.com";
		String port = "995";// 995 110
		final String username = "saikiran@narveetech.com";
		final String password = "Narvee123$";// change accordingly

		service.saveBackup(host, port, username, password);
	}

//	@RequestMapping(value = "/mail", method = RequestMethod.GET, produces = "multipart/mixed")
//	public ResponseEntity<RestAPIResponse> savemsd() {
//		
//		System.out.println("hello kiran");
//		String host = "smtp.narveetech.com";
//        String port = "995";
//        final String username= "saikiran@narveetech.com";  
//		final String password= "Narvee123$";//change accordingly  
//		//service.saveBackup(host, port, username, password);
//		return new ResponseEntity<>(new RestAPIResponse("Success", "Fetched All Emails", "jjjj"),HttpStatus.OK);
//	}

	@RequestMapping(value = "/getMails", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse> getAllMails() {
		List<Email> email = service.getAllEmails();
//		 email.forEach(ee->{
//			System.out.println(Jsoup.parse(ee.getBody()).wholeText().toString()); 
//			String ss = Jsoup.parse(ee.getBody()).wholeText().toString();
//		});
//		 List<Email> newemail = new ArrayList();
//			//check already exists or not
//			
//			for (ListIterator<Email> it = email.listIterator(); it.hasNext();){
//				Email value = it.next();
//				String ss = Jsoup.parse(value.getBody()).wholeText().toString();
//				value.setBody(ss);
//				newemail.add(value);
//				}

//		 List<Email> squareSet =
//		         email.stream().map(x->Jsoup.parse(x.getBody()).wholeText().toString()).collect(Collectors.toList());

		return new ResponseEntity<>(new RestAPIResponse("Success", "Fetched All Emails", email), HttpStatus.OK);
	}

	@RequestMapping(value = "/getMails/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse> getMail(@PathVariable long id) {

		return new ResponseEntity<>(new RestAPIResponse("Success", "Fethed Emails By ID", service.getEmail(id)),
				HttpStatus.OK);
	}

	@RequestMapping(value = "/getMailsK/{keyword}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse> getMailByKeyord(@PathVariable String keyword) {

		return new ResponseEntity<>(
				new RestAPIResponse("Success", "Successfully Fetched By Keyword", service.findEmailByFilter(keyword)),
				HttpStatus.OK);
	}

}
