package com.narvee.usit.service;

import java.util.List;

import com.narvee.usit.commons.PermissionDTO;
import com.narvee.usit.entity.Previleges;

public interface IPreviligesService {
	public Previleges savePrevileges(Previleges prev);

	public List<PermissionDTO> findAllperv();

	public List<PermissionDTO> findAllbyuid(Long uid);

	public List<PermissionDTO> findAllbyroleid(Long roleid);

	public List<PermissionDTO> findAllbyroleid(Long roleid, String url);
}
