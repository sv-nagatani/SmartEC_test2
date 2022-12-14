package com.example.demo.smartec;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.formLogin(login -> login
				.loginProcessingUrl("/login")
				.loginPage("/login")
				.defaultSuccessUrl("/", true)	// 2番目の引数trueによって、ログイン成功後は必ず1番目の引数のパスを表示する
				.failureUrl("/login?error")
				.permitAll()
		).logout(logout -> logout
				.logoutSuccessUrl("/")
		).authorizeHttpRequests(authz -> authz
				.requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
				.mvcMatchers("/").permitAll()
//				.mvcMatchers("/general").hasRole("GENERAL")
//				.mvcMatchers("/admin").hasRole("ADMIN")
//				.anyRequest().authenticated()
		);
		http.authorizeHttpRequests(authz -> authz.mvcMatchers("/general").hasRole("GENERAL"));
		http.authorizeHttpRequests(authz -> authz.mvcMatchers("/admin").hasRole("ADMIN"));
		http.authorizeHttpRequests(authz -> authz.anyRequest().authenticated());
		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}