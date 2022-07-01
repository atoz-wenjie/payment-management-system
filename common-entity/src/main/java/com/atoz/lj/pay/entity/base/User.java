package com.atoz.lj.pay.entity.base;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "userName",nullable = false, unique = true, length = 20)
    private String userName;

    @Column(nullable = false, unique = true, length=45)
    private String email;

    @Column(nullable = false,length = 64)
    private String password;

    @Column(name = "firstName",nullable = false, length = 20)
    private String firstName;

    @Column(name = "lastName",nullable = false, length = 20)
    private String lastName;

    @Column(name = "loginTime", length = 64)
    private String loginTime;

    @Column(name = "verification_code", length = 64)
    private String verificationCode;

    private boolean enabled;

	@ManyToOne
	@JoinColumn(name ="role")
	private Role role;

	@ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	private Set<UserRoles> userRoles =new HashSet<>();

	@ManyToOne
	@JoinColumn(name ="company")
	private Company company;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}

	public String getVerificationCode() {
		return verificationCode;
	}

	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}
