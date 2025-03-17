package com.ajay.gurukulX.userService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.ajay.gurukulX.adminException.AdminException;
import com.ajay.gurukulX.userDomin.User;
import com.ajay.gurukulX.userException.UserException;
import com.ajay.gurukulX.userRepositry.UserRepositry;



@Service
public class UserServiceImplementation implements UserService{

	
	@Autowired
	private UserRepositry userRepository;
	




	@Override
	public String saveUser(User user) {
		try {
			userRepository.save(user);
			return "College saved successfully!";

		} catch (Exception e) {
			return "Error saving category: " + e.getMessage();
		}
	}





	@Override
	public List<User> getAllUser() {
		try {
			List<User> users = userRepository.findAll();

			if (users == null || users.isEmpty()) {
				throw new UserException("College not found");

			}
			return users;
		} catch (Exception e) {
			throw new UserException("Getting error while fetching college details" + e.getMessage(), e);
		}
	}





	@Override
	public String UpdateUser(String id, User user) {
	    try {
	        Optional<User> existingUser = userRepository.findById(id);
	        if (existingUser.isPresent()) {
	            User userdetailsUser = existingUser.get();

	            // Update user fields
	            userdetailsUser.setUsername(user.getUsername());
	            userdetailsUser.setFirstName(user.getFirstName());
	            userdetailsUser.setLastName(user.getLastName());
	            userdetailsUser.setEmail(user.getEmail());
	            userdetailsUser.setPassword(user.getPassword());
	            userdetailsUser.setMobileNumber(user.getMobileNumber());
	            userdetailsUser.setFatherName(user.getFatherName());
	            userdetailsUser.setMotherName(user.getMotherName());
	            userdetailsUser.setFatherMobileNumber(user.getFatherMobileNumber());
	            userdetailsUser.setDob(user.getDob());
	            userdetailsUser.setJoiningDate(user.getJoiningDate());
	            userdetailsUser.setDepartment(user.getDepartment());
	            userdetailsUser.setRoles(user.getRoles());
	            userdetailsUser.setGender(user.getGender());

	            // Save updated user
	            userRepository.save(userdetailsUser);
	            return "User updated successfully!";
	        } else {
	            throw new UserException("User not found with ID: " + id);
	        }
	    } catch (Exception e) {
	        throw new UserException("Error while updating user details: " + e.getMessage(), e);
	    }
	}





	@Override
	public String deleteUser(String id) {
		try {
			Optional<User> user = userRepository.findById(id);
			if (user.isPresent()) {
				userRepository.deleteById(id);
				return "College deleted successfully!";
			} else {
				throw new AdminException("User not found with ID: " + id);
			}
		} catch (Exception e) {
			throw new UserException("User not found with ID: " + id);
		}
	}





	

	

  

	

}
