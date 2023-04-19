package com.narvee.usit.service;

import java.util.List;

import org.springframework.data.domain.Page;
import com.narvee.usit.entity.Users;
import com.narvee.usit.helper.GetRecruiter;

public interface IUserService {

	public Users saveUser(Users users);

	public Users findUserByEmail(String email);

	public Users findUserByEmailandUid(String email, Long id);

	public List<Users> getAllUsers();

	public Users finduserById(Long id);

	public boolean deleteUsers(Long id);

	public int changeStatus(String status, Long id, String remarks);

	public List<Users> filterEmployee(String keyword);

	public List<String> filterEmployee2(String keyword);

	Page<List<Users>> findPaginated(int pageNo, int pageSize);

	public Users updateUser(Users users);

	public List<Object[]> getUser();

	public List<GetRecruiter> getRecruiter();

}
