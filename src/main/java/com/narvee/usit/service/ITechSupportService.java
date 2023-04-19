package com.narvee.usit.service;

import java.util.List;
import com.narvee.usit.entity.Roles;
import com.narvee.usit.entity.TechAndSupport;

public interface ITechSupportService {
	public TechAndSupport saveTechSupp(TechAndSupport roles);

	public List<TechAndSupport> getAll(String search);

	public List<TechAndSupport> all();

	public TechAndSupport getTechSupp(Long id);

	public boolean deleteSupp(Long id);

	public int changeStatus(String status, Long id, String remarks);
}
