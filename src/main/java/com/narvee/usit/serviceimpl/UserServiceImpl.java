package com.narvee.usit.serviceimpl;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.narvee.usit.serviceimpl.EmailService;
import com.narvee.usit.entity.Technologies;
import com.narvee.usit.entity.Users;
import com.narvee.usit.helper.GetRecruiter;
import com.narvee.usit.repository.IUsersRepository;
import com.narvee.usit.service.IUserService;

@Transactional
@Service
public class UserServiceImpl implements IUserService {
	public static final Logger logger = LoggerFactory.getLogger(TechnologyServiceImpl.class);

	@Autowired
	private IUsersRepository iUsersRepo;

	@Autowired
	private EmailService emailService;

	@Override
	public int changeStatus(String status, Long id, String rem) {
		logger.info("UserServiceImpl.changeStatus()");
		return iUsersRepo.toggleStatus(status, id, rem);
	}

	public Users saveUser(Users users) {
		String AlphaNumericString = "kiran";// "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" +
											// "abcdefghijklmnopqrstuvxyz";
		StringBuilder sb = new StringBuilder(8);
		for (int i = 0; i < 8; i++) {
			int index = (int) (AlphaNumericString.length() * Math.random());
			sb.append(AlphaNumericString.charAt(index));
		}
		System.out.println(sb.toString() + " your password is");
		String generatedpsw = null;// sb.toString();
		if (users.getPassword() != null) {
			generatedpsw = users.getPassword();
		} else {
			generatedpsw = "Kiran123$";// sb.toString();
		}
		MessageDigest m = null;
		try {
			m = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		if (users.getPassword() != null) {
			m.update(generatedpsw.toString().getBytes());
		} else {
			m.update(generatedpsw.toString().getBytes());
		}
		// m.update(sb.toString().getBytes());
		// commented for mail error
		emailService.sendMailWithInlineResources(users.getEmail(), "Your Narvee Portal Password", generatedpsw);

		byte[] bytes = m.digest();

		StringBuilder s = new StringBuilder();
		for (int i = 0; i < bytes.length; i++) {
			s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
		}
		String encryptedpassword = s.toString();
		// save encoded password into database
		users.setPassword(encryptedpassword);
		// users.setFirstname(users.getFullname());
		// users.setFullname(users.getFirstname()+" "+users.getLastname());
		Users saveUser = iUsersRepo.save(users);
		return saveUser;
	}

	@Override
	public Users findUserByEmail(String email) {
		logger.info("UserServiceImpl.findUserByEmail()");
		return iUsersRepo.findByEmail(email);
	}

	@Override
	public Users findUserByEmailandUid(String email, Long id) {
		logger.info("UserServiceImpl.findUserByEmailandUid()");
		return iUsersRepo.findByEmailAndUseridNot(email, id);
	}

	@Override
	public List<Users> getAllUsers() {
		logger.info("UserServiceImpl.getAllUsers()");
		return iUsersRepo.findAll();
	}

	@Override
	public Users finduserById(Long id) {
		logger.info("UserServiceImpl.finduserById()");
		return iUsersRepo.findById(id).get();
	}

	@Override
	public boolean deleteUsers(Long id) {
		logger.info("UserServiceImpl.deleteUsers()");
		iUsersRepo.deleteById(id);
		return true;
	}

	@Override
	public List<Users> filterEmployee(String search) {
		logger.info("UserServiceImpl.filterEmployee()");
		List<Users> findAlln = new ArrayList();
		System.out.println(search);
		if (!search.equals("dummysearch")) {
			findAlln = iUsersRepo.getAll(search.trim());
		} else if (search.equals("dummysearch")) {
			findAlln = iUsersRepo.findAll();
		} else {
			findAlln = iUsersRepo.findAll();
		}
		return findAlln;
	}

	@Override
	public List<String> filterEmployee2(String search) {
		logger.info("UserServiceImpl.filterEmployee()");
		List<String> findAlln = new ArrayList();
		System.out.println(search);
		findAlln = iUsersRepo.search(search.trim());
		return findAlln;
	}

	@Override
	public Page<List<Users>> findPaginated(int pageNo, int pageSize) {
		logger.info("UserServiceImpl.findPaginated()");
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		Page<List<Users>> findAll = iUsersRepo.getallbypagination(pageable);
		return findAll;
	}

	@Override
	public Users updateUser(Users users) {
		return iUsersRepo.save(users);
	}

	@Override
	public List<Object[]> getUser() {
		// TODO Auto-generated method stub
		return iUsersRepo.getUser();
	}

	@Override
	public List<GetRecruiter> getRecruiter() {
		return iUsersRepo.getRecruiter();
	};

}
