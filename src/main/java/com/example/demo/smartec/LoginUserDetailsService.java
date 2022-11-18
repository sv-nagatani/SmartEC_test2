package com.example.demo.smartec;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class LoginUserDetailsService implements UserDetailsService {

	private final LoginUserRepository loginUserRepository;

	public LoginUserDetailsService(LoginUserRepository loginUserRepository) {
		this.loginUserRepository = loginUserRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		Optional<LoginUser> loginUserOptional = loginUserRepository.findByUserName(userName);
		return loginUserOptional.map(loginUser -> new LoginUserDetails(loginUser))
				.orElseThrow(() -> new UsernameNotFoundException("not found"));
	}
}