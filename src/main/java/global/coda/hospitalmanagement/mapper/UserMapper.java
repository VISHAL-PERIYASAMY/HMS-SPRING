package global.coda.hospitalmanagement.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import global.coda.hospitalmanagement.constant.UserSqlQueryConstant;
import global.coda.hospitalmanagement.model.UserDetails;

/**
 *
 * @author Vishal
 *
 */
public interface UserMapper {

	/**
	 * Creates the user.
	 *
	 * @param user the user
	 * @return the int
	 */
	@Insert(UserSqlQueryConstant.USER_DETAIL_INSERT)
	@Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "pk_user_id")
	int createUser(UserDetails user);

	/**
	 * Delete user by id.
	 *
	 * @param id the id
	 * @return the int
	 */
	@Update(UserSqlQueryConstant.USER_DELETE_BY_ID)
	int deleteUserById(int id);

	@Select("select * from hms.t_user_details where username=#{username} and password = #{password} ")
	@Results({ @Result(property = "id", column = "pk_user_id"), @Result(property = "roleId", column = "fk_role_id") })
	UserDetails login(UserDetails user);

	/**
	 * Update user.
	 *
	 * @param user the user
	 * @return the int
	 */
	@Update(UserSqlQueryConstant.UPDATE_BY_ID)
	int updateUser(UserDetails user);

}
