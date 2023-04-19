package com.narvee.usit.controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.NoSuchElementException;

import javax.mail.MessagingException;

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

import com.narvee.usit.service.ILoginService;
import com.narvee.usit.serviceimpl.EmailService;
import com.narvee.usit.serviceimpl.LoginServiceImpl;
import com.narvee.usit.commons.RestAPIResponse;
import com.narvee.usit.entity.Users;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import net.bytebuddy.utility.RandomString;

@RestController
@RequestMapping("/login/")
@CrossOrigin
public class LoginController {
	public static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	public ILoginService loginService;

	// commented for mail error
	@Autowired
	private EmailService emailService;

	@SuppressWarnings("unused")
	@RequestMapping(value = "/UserloginCheck", method = RequestMethod.POST, produces = "application/json")
	@ApiResponses({ @ApiResponse(code = 200, message = "User login Successfull"),
			@ApiResponse(code = 404, message = "login page not found"),
			@ApiResponse(code = 500, message = "Internal Server babu") })
	public ResponseEntity<RestAPIResponse> loginCheck(@RequestBody Users user) {
		logger.info("LoginController.loginCheck()");

		String email = user.getEmail();
		String password = user.getPassword();
		System.out.println(email + " === " + password);
		MessageDigest m = null;
		try {
			m = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/* Add plain-text password bytes to digest using MD5 update() method. */
		m.update(password.getBytes());

		/* Convert the hash value into bytes */
		byte[] bytes = m.digest();

		/*
		 * The bytes array has bytes in decimal form. Converting it into hexadecimal
		 * format.
		 */
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < bytes.length; i++) {
			s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
		}

		/* Complete hashed password in hexadecimal format */
		String encryptedpassword = s.toString();
		String message = null;
		try {
			Users userDetails = loginService.getByuserCredentials(email, encryptedpassword);
			System.out.println("Kiran " + userDetails);
			logger.info("LoginController.loginCheck() => User Account is valid");
			return new ResponseEntity<>(new RestAPIResponse("success", "User Account is valid", userDetails),
					HttpStatus.OK);
		} catch (NoSuchElementException exception) {
			logger.info("LoginController.loginCheck() = > User Account is InValid");
			return new ResponseEntity<>(
					new RestAPIResponse("failure", "User Account is InValid", "InValid Credentials"), HttpStatus.OK);

		}
	}

	@RequestMapping(value = "/forgotPassword/{email}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse> forPassdGetResetLink(@PathVariable String email) throws MessagingException {
		System.out.println("email " + email);
		logger.info("LoginController.forPassdGetResetLink() to email => " + email);

		try {
			Users findByEmail = loginService.findByEmail(email);
			// commented for mail error
			emailService.sendMailWithInlineResources(email, "Password reset link", "Resetpassword");
			return new ResponseEntity<>(new RestAPIResponse("Success", "Password Reset link Sent to: " + email),
					HttpStatus.OK);

		} catch (NoSuchElementException exception) {
			return new ResponseEntity<>(new RestAPIResponse("Fail", "Email Verification", email + " Does not Exist"),
					HttpStatus.OK);
		}

	}

	@RequestMapping(value = "/forgotPassword2/{email}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestAPIResponse> forPassdGetResetLink2(@PathVariable String email) throws MessagingException {
		System.out.println("email " + email);

		String token = RandomString.make(30);

//        try {
//            customerService.updateResetPasswordToken(token, email);
//            String resetPasswordLink = Utility.getSiteURL(request) + "/reset_password?token=" + token;
//            sendEmail(email, resetPasswordLink);
//            model.addAttribute("message", "We have sent a reset password link to your email. Please check.");
//             
//        } catch (CustomerNotFoundException ex) {
//            model.addAttribute("error", ex.getMessage());
//        } catch (UnsupportedEncodingException | MessagingException e) {
//            model.addAttribute("error", "Error while sending email");
//        }

		try {
			Users findByEmail = loginService.findByEmail(email);
			// commented for mail error
			emailService.sendMailWithInlineResources(email, "Password reset link", "Resetpassword");
			return new ResponseEntity<>(new RestAPIResponse("Success", "Password Reset link Sent to: " + email),
					HttpStatus.OK);

		} catch (NoSuchElementException exception) {
			return new ResponseEntity<>(new RestAPIResponse("Fail", "Email Verification", email + " Does not Exist"),
					HttpStatus.OK);
		}

	}

	@RequestMapping(value = "/ResetPassword", method = RequestMethod.GET)
	public ResponseEntity<RestAPIResponse> resetPass(@RequestParam("newpassword") String newpassword,
			@RequestParam("email") String email, @RequestParam("confirmpassword") String confirmpassword)
			throws MessagingException {
		logger.info("LoginController.resetPassword() to email => ");
		Users findByEmail = loginService.findByEmail(email);

		MessageDigest m = null;
		try {
			m = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		/* Add plain-text password bytes to digest using MD5 update() method. */
		m.update(newpassword.getBytes());

		/* Convert the hash value into bytes (click)="registerForm.reset()" */
		byte[] bytes = m.digest();

		/*
		 * The bytes array has bytes in decimal form. Converting it into hexadecimal
		 * format.
		 */
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < bytes.length; i++) {
			s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
		}
		/* Complete hashed password in hexadecimal format */
		String encryptedpassword = s.toString();
//	        findByEmail.setPassword(encryptedpassword);
		loginService.updatePassword(findByEmail, encryptedpassword);
//	        userService.updatePassword(findByEmail,newPassword);
		return new ResponseEntity<>(new RestAPIResponse("Success", "Password Updated Successfully"), HttpStatus.OK);

	}
}
