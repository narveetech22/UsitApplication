package com.narvee.usit.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.narvee.usit.entity.Users;
import com.narvee.usit.helper.GetRecruiter;

public interface IUsersRepository extends JpaRepository<Users, Long> {

	public Users findByEmail(String email);

	public Users findByEmailAndUseridNot(String email, Long id);

	public Users findByRoleRoleid(long roleid);

	@Modifying
	@Query("UPDATE Users c SET c.status = :status,c.remarks = :rem  WHERE c.userid = :id")
	public int toggleStatus(@Param("status") String status, @Param("id") Long id, @Param("rem") String rem);

	@Query("SELECT u FROM Users u WHERE u.email=:email AND u.password=:password ")
	public Users finByEmailAndPassword(@Param("email") String email, @Param("password") String password);

	@Query(" from  Users t where CONCAT(t.fullname, t.email, t.personalcontactnumber, t.designation,  t.status) like %?1%")
	public List<Users> getAll(String search);

	@Query("SELECT u FROM Users u")
	Page<List<Users>> getallbypagination(Pageable pageable);

	public Users findByRole(long role);

	@Query("SELECT firstname FROM Users where firstname like %:keyword%")
	public List<String> search(@Param("keyword") String keyword);

	// @Query(value="select u.userid, u.fullname from users u where u.role_roleid =
	// 9 and u.status = 'Active'", nativeQuery = true)
	@Query(value = "select u.userid, u.fullname from users u where u.status = 'Active' and u.department='Recruiting' ", nativeQuery = true)
	public List<Object[]> getUser();

	// GetRecruiter
	@Query(value = "select u.userid, u.fullname from users u where u.status = 'Active' and u.department='Recruiting' ", nativeQuery = true)
	public List<GetRecruiter> getRecruiter();

}
