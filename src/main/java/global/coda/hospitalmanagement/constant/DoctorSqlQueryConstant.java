package global.coda.hospitalmanagement.constant;

/**
 *
 * @author Vishal
 */
public final class DoctorSqlQueryConstant {

	/**
	 * Instantiates a new doctor sql query constant.
	 */
	private DoctorSqlQueryConstant() {
	}


	public static final String USER_DETAIL_INSERT = "INSERT INTO `t_user_details` (`username`, `password`, `fk_role_id`,"
			+ " `firstname`, `lastname`, `city`, `state`,`phone_number`) VALUES (#{username}, #{password}, 3, #{firstName}, #{lastName}, #{city}, #{state},#{phoneNumber});";


	public static final String DOCTOR_INSERT = "INSERT INTO `t_doctor` (`doctor_specialization`, `fk_user_id`) VALUES (#{specilization}, #{id})";

	public static final String DOCTOR_SELECT_ALL = "select * from hms.t_user_details as user LEFT JOIN hms.t_doctor as doctor ON user.pk_user_id ="
			+ " doctor.fk_user_id where user.is_active = 1 and user.fk_role_id = 3";

	public static final String DOCTOR_SELECT_BY_ID = "<script> select * from hms.t_user_details "
			+ "as user LEFT JOIN hms.t_doctor as doctor "
			+ "ON user.pk_user_id = doctor.fk_user_id where user.is_active = 1 "
			+ "and user.fk_role_id = 3  <if test = 'pk_user_id!=0'>  and user.pk_user_id=#{id} </if> </script>";

	public static final String DOCTOR_DELETE_BY_ID = "update t_doctor set is_active = 0 where fk_user_id = #{id} and is_active = 1";

	public static final String DOCTOR_UPDATE_BY_ID = "UPDATE `t_doctor` SET `doctor_specialization` "
			+ "= #{specilization} WHERE (`fk_user_id` = #{id} and is_active = 1)";

}
