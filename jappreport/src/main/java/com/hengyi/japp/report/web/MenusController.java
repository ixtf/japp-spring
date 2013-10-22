package com.hengyi.japp.report.web;

import java.io.Serializable;
import java.util.List;

import javax.inject.Named;

import org.primefaces.model.LazyDataModel;
import org.springframework.context.annotation.Scope;

import com.hengyi.japp.common.web.model.LazyNeo4jModel;
import com.hengyi.japp.report.domain.Menu;

@Named
@Scope("view")
public class MenusController extends AbstractController implements Serializable {
	private static final long serialVersionUID = -7807995349596234400L;
	private String nameSearch;
	private LazyDataModel<Menu> menus;
	private List<Menu> searchResult;
	private Menu menu;

	public void edit() {
		redirect(menuService.getUpdatePath(getMenu().getNodeId()));
	}

	public void delete() {
		try {
			menuService.delete(getMenu());
			if (searchResult != null)
				searchResult.remove(getMenu());
			operationSuccessMessage();
		} catch (Exception e) {
			errorMessage(e);
		}
	}

	public void search() {
		try {
			searchResult = menuService.findAllByQuery(nameSearch);
			menus = new LazyNeo4jModel<>(searchResult);
		} catch (Exception e) {
			errorMessage(e);
		}
	}

	public LazyDataModel<Menu> getMenus() {
		if (menus == null)
			menus = new LazyNeo4jModel<>(menuService);
		return menus;
	}

	public String getNameSearch() {
		return nameSearch;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setNameSearch(String nameSearch) {
		this.nameSearch = nameSearch;
	}

	public void setMenus(LazyDataModel<Menu> menus) {
		this.menus = menus;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public List<Menu> getSearchResult() {
		return searchResult;
	}

	public void setSearchResult(List<Menu> searchResult) {
		this.searchResult = searchResult;
	}
}
