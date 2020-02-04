package global.coda.hospitalmanagement.jpa;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public List<UserRecord> getAllUsers(){
		List<UserRecord>userRecords = new ArrayList<>();
		userRepository.findAll().forEach(userRecords::add);
		return userRecords;
	}

	public Optional<UserRecord> getUser(int id) {
		return userRepository.findById(id);
	}

	public void addUser(UserRecord userRecord) {
		userRepository.save(userRecord);
	}

	public void delete(int id) {
		userRepository.deleteById(id);
	}
}