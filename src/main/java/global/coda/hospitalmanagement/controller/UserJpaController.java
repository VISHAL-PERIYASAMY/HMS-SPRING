package global.coda.hospitalmanagement.controller;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import global.coda.hospitalmanagement.constant.HttpStatusConstant;
import global.coda.hospitalmanagement.exception.BusinessException;
import global.coda.hospitalmanagement.exception.SystemException;
import global.coda.hospitalmanagement.jpa.UserRecord;
import global.coda.hospitalmanagement.jpa.UserService;
import global.coda.hospitalmanagement.model.CustomResponseBody;

@CrossOrigin
@RestController
@RequestMapping("/hms/user")
public class UserJpaController {

	/** The logger. */
	private final Logger logger = LogManager.getLogger(UserJpaController.class);

	@Autowired
	private UserService userService;

	@RequestMapping(value="/all",method = RequestMethod.GET)
	public CustomResponseBody<List<UserRecord>> getAllUser() throws BusinessException, SystemException {
		CustomResponseBody<List<UserRecord>> response = new CustomResponseBody<List<UserRecord>>();
		response.setStatus(HttpStatusConstant.OK);
		response.setMessage(userService.getAllUsers());
		logger.traceExit(response);
		return response;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public CustomResponseBody<UserRecord> addUser(@RequestBody UserRecord userRecord)
			throws BusinessException, SystemException {
		logger.traceEntry();
		userService.addUser(userRecord);
		CustomResponseBody<UserRecord> response = new CustomResponseBody<UserRecord>();
		response.setStatus(HttpStatusConstant.OK);
		response.setMessage(userRecord);
		logger.traceExit(response);
		return response;
	}


	@RequestMapping(value = "/read/{id}", method = RequestMethod.GET)
	public CustomResponseBody<Optional<UserRecord>> getUser(@PathVariable int id)
			throws BusinessException, SystemException {
		logger.entry(id);
		CustomResponseBody<Optional<UserRecord>> response = new CustomResponseBody<Optional<UserRecord>>();
		response.setStatus(HttpStatusConstant.OK);
		response.setMessage(userService.getUser(id));
		logger.traceExit(response);
		return response;
	}

	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public @ResponseBody String greeting() {
		return "Hello";
	}

}