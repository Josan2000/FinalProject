package com.example.demo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import javax.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.JoinColumn;

@Entity
@ToString
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
		private int userid;
		   @Column(unique=true)
			private String username;
			private String password;
			private String mobileNo;
			@Column(unique=true)
			private String email;
			private String roles;
			private boolean accountNonExpired;
			private boolean accountNonLocked;
			private boolean credentialsNonExpired;
			private boolean enabled;
			@ElementCollection(fetch=FetchType.EAGER)
			@CollectionTable(name="authority",joinColumns=@JoinColumn(name="username"))
		    private Collection<String> authorities=new ArrayList<String>();
			

			@OneToMany
			private Set<Booking> bookings;


			public int getUserid() {
				return userid;
			}


			public void setUserid(int userid) {
				this.userid = userid;
			}


			public String getUsername() {
				return username;
			}


			public void setUsername(String username) {
				this.username = username;
			}


			public String getPassword() {
				return password;
			}


			public void setPassword(String password) {
				this.password = password;
			}


			public String getMobileNo() {
				return mobileNo;
			}


			public void setMobileNo(String mobileNo) {
				this.mobileNo = mobileNo;
			}


			public String getEmail() {
				return email;
			}


			public void setEmail(String email) {
				this.email = email;
			}


			public String getRoles() {
				return roles;
			}


			public void setRoles(String roles) {
				this.roles = roles;
			}


			public boolean isAccountNonExpired() {
				return accountNonExpired;
			}


			public void setAccountNonExpired(boolean accountNonExpired) {
				this.accountNonExpired = accountNonExpired;
			}


			public boolean isAccountNonLocked() {
				return accountNonLocked;
			}


			public void setAccountNonLocked(boolean accountNonLocked) {
				this.accountNonLocked = accountNonLocked;
			}


			public boolean isCredentialsNonExpired() {
				return credentialsNonExpired;
			}


			public void setCredentialsNonExpired(boolean credentialsNonExpired) {
				this.credentialsNonExpired = credentialsNonExpired;
			}


			public boolean isEnabled() {
				return enabled;
			}


			public void setEnabled(boolean enabled) {
				this.enabled = enabled;
			}


			public Collection<String> getAuthorities() {
				return authorities;
			}


			public void setAuthorities(Collection<String> authorities) {
				this.authorities = authorities;
			}


			public Set<Booking> getBookings() {
				return bookings;
			}


			public void setBookings(Set<Booking> bookings) {
				this.bookings = bookings;
			}
			
			
	

}
