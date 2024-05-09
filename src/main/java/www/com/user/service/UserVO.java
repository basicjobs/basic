package www.com.user.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 홈페이지 인증 된 사용자VO
 * 
 * @author cmj
 */

public class UserVO implements UserDetails, Serializable {
	
	private static final long serialVersionUID = 3258562207857181402L;

	private String password;
	private String mberNo;
	private String mberId;
	private String mberNm;
	private String mberPw;
	private String role;
	

	//권한목록
	private Collection<GrantedAuthority> authorities;
	
	public UserVO(String mberNo, String mberId, String mberPw, String mberNm, String role){
		this.password = mberPw; 
		this.mberNo = mberNo;
		this.mberId = mberId;
		this.mberPw = mberPw;
		this.mberNm = mberNm;
		this.role = role;
	}


	public void setAuthorities(Collection<GrantedAuthority> authorities) {
		this.authorities = Collections.unmodifiableCollection(authorities);
	}


	@Override
	public String getPassword() {
		return getPassword();
	}
	
	public void setPassword(String password) {  
		this.password = password;
	}
	
	@Override
	public String getUsername() {
		return getMberNm();
	}

	public String getMberId() {
		return mberId;
	}

	public String getMberNo() {
		return mberNo;
	}


	public void setMberNo(String mberNo) {
		this.mberNo = mberNo;
	}


	public void setMberId(String mberId) {
		this.mberId = mberId;
	}


	public String getMberNm() {
		return mberNm;
	}


	public void setMberNm(String mberNm) {
		this.mberNm = mberNm;
	}
	
	public String getMberPw() {
		return mberPw;
	}


	public void setMberPw(String mberPw) {
		this.mberPw = mberPw;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}
	

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}


}
