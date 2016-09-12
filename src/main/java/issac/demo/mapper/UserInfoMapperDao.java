package issac.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import issac.demo.common.IPage;
import issac.demo.model.UserInfo;

public interface UserInfoMapperDao extends UserInfoMapper, IPage {

	int updateByPrimaryKey(UserInfo record);

	List<UserInfo> selectAll();

	List<UserInfo> getList(@Param("name") String name);

	List<UserInfo> getPageList(@Param("start") Integer start, @Param("end") Integer end, @Param("name") String name);

	int getTotal(@Param("name") String name);
}
