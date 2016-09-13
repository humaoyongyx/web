package issac.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import issac.demo.common.IPage;
import issac.demo.model.UserInfoBean;

public interface UserInfoMapperDao extends IPage {
	List<UserInfoBean> selectAll();

	List<UserInfoBean> getList(@Param("name") String name);

	List<UserInfoBean> getPageList(@Param("start") Integer start, @Param("end") Integer end, @Param("name") String name);

	int getTotal(@Param("name") String name);

	int insertSelective(UserInfoBean userInfoBean);
}
