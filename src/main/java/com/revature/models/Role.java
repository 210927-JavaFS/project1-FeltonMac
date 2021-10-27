package com.revature.models;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="roles")
public class Role {
	
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
int roleid;
String roleString;

public Role(String roleString) {
	super();
	this.roleString = roleString;
}
public Role(int roleid, String roleString) {
	super();
	this.roleid = roleid;
	this.roleString = roleString;
}

public Role() {
	super();
}
public int getRoleid() {
	return roleid;
}
public String getRoleString() {
	return roleString;
}
public void setRoleid(int roleid) {
	this.roleid = roleid;
}
public void setRoleString(String roleString) {
	this.roleString = roleString;
}

@Override
public int hashCode() {
	return Objects.hash(roleString, roleid);
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Role other = (Role) obj;
	return Objects.equals(roleString, other.roleString) && roleid == other.roleid;
}
@Override
public String toString() {
	return "Role [roleid=" + roleid + ", roleString=" + roleString + "]";
}

}
