package com.narvee.usit.service;

import java.util.List;
import com.narvee.usit.entity.GetRoles;
import com.narvee.usit.entity.Roles;

public interface IRoleService {
	public Roles saveRole(Roles roles);

	public List<String> finaAllRolByRolName(String rolename);

	public List<Long> finabyrolenumber(long rolename);

	public Roles findbyrolenameandid(String name, Long id);

	public List<Roles> getAllRoles();

	public Roles getRole(Long id);

	public boolean deleteRole(Long id);

	public int changeStatus(String status, Long id, String remarks);

	public List<GetRoles> getRoles();
}
