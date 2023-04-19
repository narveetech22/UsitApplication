package com.narvee.usit.service;

import java.util.Optional;

import com.narvee.usit.entity.Users;

public interface ILoginService {
	public Users getByuserCredentials(String email, String password);

	public Users findByEmail(String email);

	public void updatePassword(Users user, String pwd);
}
