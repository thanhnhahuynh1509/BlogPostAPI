package com.htn.blog.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.htn.blog.entity.User;
import com.htn.blog.repository.UserRepository;

@Service
public class CustomerUserDetailService implements UserDetailsService {

	private final UserRepository userRepository;

	public CustomerUserDetailService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsernameOrEmail(username, username).orElseThrow(() -> new UsernameNotFoundException("Not found user with username or email: " + username));
		return new CustomerUserDetail(user);
	}

}
