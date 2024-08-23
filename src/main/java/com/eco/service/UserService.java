package com.eco.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.eco.model.User;

public interface UserService {
	public User saveUser(User user);

	public User getUserByEmail(String email);

	public List<User> getUsers(String role);

	public Boolean updateAccountStatus(Integer id, Boolean status);

	public void increaseFailedAttempt(User user);

	public void userAccountLock(User user);

	public boolean unlockAccountTimeExpired(User user);

	public void resetAttempt(int userId);
	//

	public void updateUserResetToken(String email, String resetToken);

	public User getUserByToken(String token);

	public User updateUser(User user);

	public User updateUserProfile(User user, MultipartFile img);
	
	public User saveAdmin(User user);
	
	public Boolean existsEmail(String email);
}
