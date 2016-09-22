package issac.demo.mapper;

import java.util.List;

import issac.demo.bo.params.MenuParams;
import issac.demo.model.Menu;
import issac.demo.model.MenuBean;

public interface MenuMapperDao {

	public List<MenuBean> getAll();

	public List<MenuBean> getAllMenus(MenuParams menuParams);

	int insert(Menu record);
}