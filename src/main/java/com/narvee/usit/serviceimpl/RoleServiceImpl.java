package com.narvee.usit.serviceimpl;

import java.util.List;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.narvee.usit.entity.GetRoles;
import com.narvee.usit.entity.Roles;
import com.narvee.usit.entity.Users;
import com.narvee.usit.repository.IRolesRepository;
import com.narvee.usit.repository.IUsersRepository;
import com.narvee.usit.service.IRoleService;

@Service
@Transactional
public class RoleServiceImpl implements IRoleService {

	public static final Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);

	@Autowired
	private IRolesRepository iRoleRepo;

	@Autowired
	private IUsersRepository userRepo;

	public Roles saveRole(Roles role) {
		logger.info("RoleServiceImpl.saveRole()");
		Roles saveRole = iRoleRepo.save(role);
		return saveRole;
	}

	@Override
	public List<String> finaAllRolByRolName(String rolename) {
		logger.info("RoleServiceImpl.saveRole()");
		return iRoleRepo.findRolByRolName(rolename);
	}

	@Override
	public Roles findbyrolenameandid(String name, Long id) {
		logger.info("RoleServiceImpl.saveRole()");
		return iRoleRepo.findByRolenameAndRoleidNot(name, id);
	}

	@Override
	public List<Roles> getAllRoles() {
		logger.info("RoleServiceImpl.saveRole()");
		return iRoleRepo.findAll();
	}

	@Override
	public Roles getRole(Long id) {
		logger.info("RoleServiceImpl.getRole by id()=> " + id);
		return iRoleRepo.findById(id).get();
	}

	@Override
	public boolean deleteRole(Long id) {
		logger.info("RoleServiceImpl.deleteRole() by id => " + id);
		Users user = userRepo.findByRoleRoleid(id);
		if (user == null) {
			iRoleRepo.deleteById(id);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int changeStatus(String status, Long id, String rem) {
		logger.info("RoleServiceImpl.changeStatus()status " + status + " id  =>" + id + " remarks =>" + rem);
		return iRoleRepo.toggleStatus(status, id, rem);
	}

	@Override
	public List<GetRoles> getRoles() {
		logger.info("RoleServiceImpl.getRoles()");
		return iRoleRepo.getAllRoles();
	}

	@Override
	public List<Long> finabyrolenumber(long roleno) {
		return iRoleRepo.findByRoleno(roleno);
	}

}
