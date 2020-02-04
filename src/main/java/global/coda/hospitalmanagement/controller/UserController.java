package global.coda.hospitalmanagement.controller;

import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import global.coda.hospitalmanagement.constant.ApplicationConstant;
import global.coda.hospitalmanagement.constant.HttpStatusConstant;
import global.coda.hospitalmanagement.delegate.UserOperationDelegate;
import global.coda.hospitalmanagement.exception.BusinessException;
import global.coda.hospitalmanagement.exception.SystemException;
import global.coda.hospitalmanagement.model.CustomResponseBody;
import global.coda.hospitalmanagement.model.UserDetails;

@CrossOrigin
@RestController
@RequestMapping("/hms")
public class UserController {

	/** The logger. */
	private final Logger logger = LogManager.getLogger(UserController.class);

	@Autowired
	UserOperationDelegate userOperationDelegate;

	@RequestMapping(value="/login",method = RequestMethod.POST)
	public CustomResponseBody<UserDetails> getUserDetails(@RequestBody UserDetails user,HttpServletResponse httpServletResponse) throws BusinessException, SystemException {
		logger.entry(user);
		CustomResponseBody<UserDetails> response = new CustomResponseBody<UserDetails>();
		response.setStatus(HttpStatusConstant.OK);
		response.setMessage(userOperationDelegate.getUserDeatils(user));
		httpServletResponse.setHeader("auth","vishal");
		logger.traceExit(response);
		return response;
	}

	@DeleteMapping("user/delete/{id}")
	public CustomResponseBody<String> deleteUser(@PathVariable("id") int id)
			throws BusinessException, SystemException {
		logger.entry(id);
		userOperationDelegate.deleteUser(id);
		CustomResponseBody<String> response = new CustomResponseBody<String>();
		response.setStatus(HttpStatusConstant.OK_NO_CONTENT);
		response.setMessage(ApplicationConstant.USER_DELETED);
		logger.traceExit(response);
		return response;
	}

}
