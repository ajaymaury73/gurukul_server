package com.ajay.gurukulX.userService;

import java.util.ArrayList;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.ws.rs.core.Response;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.ajay.gurukulX.ExaminationDomain.Degree;
import com.ajay.gurukulX.adminDomain.College;
import com.ajay.gurukulX.adminException.AdminException;
import com.ajay.gurukulX.adminRepository.CollegeRepository;
import com.ajay.gurukulX.userDomin.Role;
import com.ajay.gurukulX.userDomin.User;
import com.ajay.gurukulX.userException.UserException;
import com.ajay.gurukulX.userRepositry.UserRepositry;

@Service
public class UserServiceImplementation implements UserService {

	@Autowired
	private UserRepositry userRepository;

	@Autowired
	private CollegeRepository collegeRepo;

	@Autowired
	private Keycloak keycloak;

	private UsersResource getUsersResource() {
		RealmResource realmResource = keycloak.realm("gurukul_realm");
		return realmResource.users();
	}

	@Override
	public String saveUser(User user) {

		try {
            
			UserRepresentation userRep = new UserRepresentation();
			userRep.setUsername(user.getUsername());
			userRep.setEmail(user.getEmail());
			userRep.setEnabled(true);
			userRep.setFirstName(user.getFirstName());
			userRep.setLastName(user.getLastName());
			userRep.setEmailVerified(true);

			// Set password
			CredentialRepresentation credential = new CredentialRepresentation();
			credential.setType(CredentialRepresentation.PASSWORD);
			String defaultPassword="Gurkul123!";
			credential.setValue(defaultPassword);
			credential.setTemporary(false);

			userRep.setCredentials(Collections.singletonList(credential));

			UsersResource usersResource = getUsersResource();
			Response response = usersResource.create(userRep);

			if (response.getStatus() == 201) {
				String userId = response.getLocation().getPath().replaceAll(".*/([^/]+)$", "$1");
				List<String> roleNames = user.getRoles().stream().map(Role::getValue).collect(Collectors.toList());
				   user.setId(userId);
				   user.setPassword(defaultPassword);
		           userRepository.save(user);
		           System.err.print(" user Id "+userId);
				assignRolesToUser(userId, roleNames);
				return "User  saved successfully in Keycloak!";
			} else {
				String errorMessage = response.readEntity(String.class);
				return "Failed to save user in Keycloak: " + response.getStatusInfo().getReasonPhrase() + " - "
						+ errorMessage;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "Error saving user: " + e.getMessage();
		}

	}

	private void assignRolesToUser(String userId, List<String> roleNames) {
		// Get the realm resource
		RealmResource realmResource = keycloak.realm("gurukul_realm"); 
		UsersResource usersResource = realmResource.users();

		List<RoleRepresentation> roles = roleNames.stream()
				.map(roleName -> realmResource.roles().get(roleName).toRepresentation()).collect(Collectors.toList());
		usersResource.get(userId).roles().realmLevel().add(roles);
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

	            // Update Keycloak user
	            UsersResource usersResource = getUsersResource();
	            UserRepresentation keycloakUser = usersResource.get(id).toRepresentation();

	            keycloakUser.setUsername(user.getUsername());
	            keycloakUser.setFirstName(user.getFirstName());
	            keycloakUser.setLastName(user.getLastName());
	            keycloakUser.setEmail(user.getEmail());
	            keycloakUser.setEnabled(true);
	            keycloakUser.setEmailVerified(true);

	            usersResource.get(id).update(keycloakUser);

	            // Update user roles in Keycloak
	            List<String> roleNames = user.getRoles().stream().map(Role::getValue).collect(Collectors.toList());
	            assignRolesToUser(id, roleNames);

	            // Update user in the database
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

	            userRepository.save(userdetailsUser);

	            return "User updated successfully in Keycloak and database!";
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
	            // Delete user from Keycloak
	            UsersResource usersResource = getUsersResource();
	            usersResource.get(id).remove();

	            // Delete user from the database
	            userRepository.deleteById(id);

	            return "User deleted successfully from Keycloak and database!";
	        } else {
	            throw new UserException("User not found with ID: " + id);
	        }
	    } catch (Exception e) {
	        throw new UserException("Error while deleting user: " + e.getMessage(), e);
	    }
	}


	@Override
	public List<String> getAllTenant() {
		List<College> colleges = collegeRepo.findAll();
		List<String> tenantIds = new ArrayList<>();

		for (College college : colleges) {
			tenantIds.add(college.getCollegeTenantId());
		}

		return tenantIds;
	}

	@Override
	public List<String> getCoursesByType(List<String> degreeTypes) {
	    if (degreeTypes == null || degreeTypes.isEmpty()) {
	        throw new AdminException("Degree types list cannot be null or empty.");
	    }

	    try {
	        List<College> colleges = collegeRepo.findAll();

	        if (colleges == null || colleges.isEmpty()) {
	            throw new AdminException("No colleges found in the database.");
	        }

	        List<String> degreeNames = colleges.stream()
	            .flatMap(college -> college.getDegree().stream())
	            .filter(degree -> degree.getDegreeType() != null && degreeTypes.contains(degree.getDegreeType()))
	            .map(Degree::getDegreeName)
	            .distinct()
	            .collect(Collectors.toList());

	        // Instead of throwing an exception, return an empty list
	        return degreeNames.isEmpty() ? Collections.emptyList() : degreeNames;
	    } catch (Exception e) {
	        throw new AdminException("Error while fetching degree names: " + e.getMessage(), e);
	    }
	}



}
