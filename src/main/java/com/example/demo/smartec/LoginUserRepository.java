package com.example.demo.smartec;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class LoginUserRepository {

	private static final String SQL_FIND_BY_USERNAME = """
			SELECT
			  u.name AS user_name,
			  u.dsp_name,
			  u.password,
			  r.name AS role_name
			FROM login_user u
			JOIN user_role ur
			  ON u.id = ur.user_id
			JOIN roles r
			  ON ur.role_id = r.id
			WHERE u.name = :user_name
			""";

	private static final ResultSetExtractor<LoginUser> LOGIN_USER_EXTRACTOR = (rs) -> {
		String userName = null;
		String dspName = null;
		String password = null;
		List<String> roleList = new ArrayList<>();
		while (rs.next()) {
			if (userName == null) {
				userName = rs.getString("user_name");
				dspName = rs.getString("dsp_name");
				password = rs.getString("password");
			}
			roleList.add(rs.getString("role_name"));
		}
		if (userName == null) {
			return null;
		}
		return new LoginUser(userName, dspName, password, roleList);
	};

	private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public LoginUserRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	public Optional<LoginUser> findByUserName(String userName) {
		MapSqlParameterSource params = new MapSqlParameterSource("user_name", userName);
		LoginUser loginUser = namedParameterJdbcTemplate.query(SQL_FIND_BY_USERNAME, params, LOGIN_USER_EXTRACTOR);
		return Optional.ofNullable(loginUser);
	}
}