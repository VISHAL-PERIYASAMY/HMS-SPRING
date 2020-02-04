package global.coda.hospitalmanagement.delegate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import global.coda.hospitalmanagement.constant.ApplicationConstant;
import global.coda.hospitalmanagement.constant.HttpStatusConstant;
import global.coda.hospitalmanagement.exception.BusinessException;
import global.coda.hospitalmanagement.exception.DoctorNotFoundException;
import global.coda.hospitalmanagement.exception.SystemException;
import global.coda.hospitalmanagement.exception.UserNotFoundException;
import global.coda.hospitalmanagement.mapper.UserMapper;
import global.coda.hospitalmanagement.model.UserDetails;

@Component
public class UserOperationDelegate {

	@Autowired
	private UserMapper userMapper;

	private final Logger logger = LogManager.getLogger(UserOperationDelegate.class);

	public UserDetails getUserDeatils(UserDetails user) throws SystemException,BusinessException
	{
		try {
			logger.entry(user);
			UserDetails userDetails = userMapper.login(user);
			if(userDetails==null)
			{
				throw new UserNotFoundException("No User Found");
			}
			userDetails.setPassword(null);
			return userDetails;

		} catch (UserNotFoundException error) {
			throw new BusinessException(error.getMessage());
		}catch (Exception error) {
			throw new SystemException(error.getMessage());
		}
	}

	public boolean deleteUser(int id) throws BusinessException, SystemException {
		try {
			logger.entry(id);
			int result = userMapper.deleteUserById(id);
			if (result == 0) {
				throw new DoctorNotFoundException(HttpStatusConstant.BAD_REQUEST + ApplicationConstant.SPACE
						+ ApplicationConstant.DOCTOR_NOT_FOUND);
			}
			logger.traceExit();
			return true;
		} catch (DoctorNotFoundException error) {
			throw new BusinessException(error.getMessage());
		} catch (Exception error) {
			throw new SystemException(error.getMessage());
		}
	}

}
