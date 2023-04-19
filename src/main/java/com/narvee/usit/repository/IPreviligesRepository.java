package com.narvee.usit.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.narvee.usit.commons.PermissionDTO;
import com.narvee.usit.entity.Previleges;

public interface IPreviligesRepository extends JpaRepository<Previleges, Serializable> {

	@Query(value = "SELECT new com.narvee.usit.commons.PermissionDTO(r.roleid,p.previd,r.rolename,p.previlegename,p.url) FROM Roles r,Previleges p WHERE r.roleid =p.roleid ")
	public List<PermissionDTO> listAll();

//	
//	
//	@Query(value = "SELECT new com.narvee.usit.commons.PermissionDTO(r.roleid,p.previd,r.rolename,p.previlegename,p.url) FROM Users u, Roles r,Previleges p WHERE r.roleid =p.roleid AND u.roleid=r.roleid and  u.userid=:userid ")
//	public List<PermissionDTO> listAllbyuid(@Param("userid") Long userid );
//	
	@Query(value = "SELECT new com.narvee.usit.commons.PermissionDTO(r.roleid,p.previd,r.rolename,p.previlegename,p.url) FROM Roles r,Previleges p WHERE r.roleid =p.roleid AND r.roleid=:id ")
	public List<PermissionDTO> listAllbyroleid(@Param("id") Long id);

//	
//	
	@Query(value = "SELECT new com.narvee.usit.commons.PermissionDTO(r.roleid,p.previd,r.rolename,p.previlegename,p.url) FROM Roles r,Previleges p WHERE r.roleid =p.roleid AND r.roleid=:id and p.url like (:url)")
	public List<PermissionDTO> listAllbyroleid(@Param("id") Long id, @Param("url") String url);

}
