package com.narvee.usit.serviceimpl;

import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import com.narvee.usit.entity.Users;
import com.narvee.usit.repository.IUsersRepository;
import com.narvee.usit.service.ILoginService;

@Service
public class LoginServiceImpl implements ILoginService {
	public static final Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);
	@Autowired
	private IUsersRepository userRepo;

	@Override
	public Users getByuserCredentials(String email, String password) {
		logger.info("LoginServiceImpl.getByuserCredentials()");
		Users userDetails = userRepo.finByEmailAndPassword(email, password);
		System.out.println("============================  " + userDetails);
		if (userDetails == null) {
			logger.info("VisaServiceImpl.saveVms()");
			throw new NoSuchElementException("No value present");
		}
		return userDetails;
	}

	@Override
	public Users findByEmail(String email) {
		logger.info("LoginServiceImpl.findByEmail()");
		Users fingbyEmail = userRepo.findByEmail(email);
		return fingbyEmail;
	}

	@Override
	@Modifying
	public void updatePassword(Users user, String pwd) {
		logger.info("LoginServiceImpl.updatePassword()");
		user.setPassword(pwd);
		userRepo.save(user);
	}

}
