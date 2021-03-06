package com.revature.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class User {
	
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
int id;
//@Column(nullable=false)
private String username;
//@Column(nullable=false)
private int password;
private String firstname;
private String lastname;
private String email;

@OneToMany(mappedBy="re_id", fetch=FetchType.EAGER )//cascade=CascadeType.ALL
List<Reimbursement> reimbursements=new ArrayList<>();
@ManyToOne(fetch=FetchType.EAGER )//cascade=CascadeType.ALL
@JoinColumn(name="roleid")
Role role;




public User(int id, String username, int password, String firstname, String lastname, String email,
		List<Reimbursement> reimbursements, Role role) {
	super();
	this.id = id;
	this.username = username;
	this.password = password;
	this.firstname = firstname;
	this.lastname = lastname;
	this.email = email;
	this.reimbursements = reimbursements;
	this.role = role;
}
public User(String username, int password, String firstname, String lastname, String email,
		List<Reimbursement> reimbursements, Role role) {
	super();
	this.username = username;
	this.password = password;
	this.firstname = firstname;
	this.lastname = lastname;
	this.email = email;
	this.reimbursements = reimbursements;
	this.role = role;
}
public List<Reimbursement> getReimbursements() {
	return reimbursements;
}
public User() {
	super();
	// TODO Auto-generated constructor stub
}
public int getId() {
	return id;
}
public String getUsername() {
	return username;
}
public int getPassword() {
	return password;
}
public String getFirstname() {
	return firstname;
}
public String getLastname() {
	return lastname;
}
public String getEmail() {
	return email;
}
public Role getRole() {
	return role;
}
public void setId(int id) {
	this.id = id;
}
public void setUsername(String username) {
	this.username = username;
}
public void setPassword(int password) {
	this.password = password;
}
public void setFirstname(String firstname) {
	this.firstname = firstname;
}
public void setLastname(String lastname) {
	this.lastname = lastname;
}
public void setEmail(String email) {
	this.email = email;
}
public void setRole(Role role) {
	this.role = role;
}
public void setReimbursements(List<Reimbursement> list) {
	this.reimbursements = list;
}
@Override
public int hashCode() {
	return Objects.hash(email, firstname, id, lastname, password, role, username);
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	User other = (User) obj;
	return Objects.equals(email, other.email) && Objects.equals(firstname, other.firstname) && id == other.id
			&& Objects.equals(lastname, other.lastname) && Objects.equals(password, other.password)
			&& Objects.equals(reimbursements, other.reimbursements) 
			&& Objects.equals(role, other.role)
			&& Objects.equals(username, other.username);
}
@Override
public String toString() {
	return "User [id=" + id + ", username=" + username + ", password=" + password + ", firstname=" + firstname
			+ ", lastname=" + lastname + ", email=" + email + ", reimbursements="+ ", role=" + role
			+ "]";
}
//(reimbursements.size()>0)?reimbursements.get(0).getRe_id():null) 


}
