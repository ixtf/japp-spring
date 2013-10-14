package com.hengyi.japp.report.web;

import java.io.Serializable;
import java.util.List;

import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.hengyi.japp.report.domain.Role;
import com.hengyi.japp.report.web.data.LazyRoleModel;

@Named
@Scope("view")
public class RolesController extends AbstractController implements Serializable {
	private static final long serialVersionUID = -7807995349596234400L;
	private String nameSearch;
	private LazyRoleModel roles;
	private List<Role> searchResult;
	private Role role;

	public void edit() {
		redirect(roleService.getUpdatePath(getRole().getNodeId()));
	}

	public void delete() {
		try {
			roleService.delete(getRole());
			if (searchResult != null)
				searchResult.remove(getRole());
			operationSuccessMessage();
		} catch (Exception e) {
			errorMessage(e);
		}
	}

	public void search() {
		try {
			searchResult = roleService.findAllByQuery(nameSearch);
			roles = new LazyRoleModel(searchResult);
		} catch (Exception e) {
			errorMessage(e);
		}
	}

	public LazyRoleModel getRoles() {
		if (roles == null)
			roles = new LazyRoleModel(roleService);
		return roles;
	}

	public String getNameSearch() {
		return nameSearch;
	}

	public Role getRole() {
		return role;
	}

	public void setNameSearch(String nameSearch) {
		this.nameSearch = nameSearch;
	}

	public void setRoles(LazyRoleModel roles) {
		this.roles = roles;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<Role> getSearchResult() {
		return searchResult;
	}

	public void setSearchResult(List<Role> searchResult) {
		this.searchResult = searchResult;
	}
}
