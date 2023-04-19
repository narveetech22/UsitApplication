package com.narvee.usit.commons;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class PermissionDTO {

	private Long roleid;

	private Long previd;

	private String rolename;

	private String prevname;

	private String url;

}
