package issac.demo.mapper;

import java.util.List;

import issac.demo.bo.params.MenuParams;
import issac.demo.model.MenuBean;

public interface MenuMapperDao {

	public List<MenuBean> getAll();

	public List<MenuBean> getMenuByUserId(Integer userId);

	public List<MenuBean> getAllMenus(MenuParams menuParams);

	int insert(MenuBean record);

	int deleteByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(MenuBean record);

	int deleteAll(List<Integer> ids);

}