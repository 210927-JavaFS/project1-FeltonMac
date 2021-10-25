package com.revature.models;

import java.sql.Blob;
import java.time.LocalDateTime;
import java.util.Objects;




public class Reimbursement {
//  The back-end system shall use Hibernate to connect to an AWS RDS Postgres database.
//	The middle tier shall use Javalin technology for dynamic Web application development.
//	The front-end view shall use HTML/JavaScript to make an application that can call server-side 
//	components RESTfully. Passwords shall be encrypted in Java and securely stored in the database.
//	The middle tier shall follow proper layered architecture, have reasonable
//	(~70%) test coverage of the service layer, and implement Logback for appropriate logging. 
private	int re_id;
double amount;//could change to int 
LocalDateTime  submitted;
LocalDateTime  resolved;
String description;
//Blob receipt;
// Author?
// resolver?
String status;
String type;
public Reimbursement(int re_id, double amount, LocalDateTime submitted, LocalDateTime resolved, String description,
		String status, String type) {
	super();
	this.re_id = re_id;
	this.amount = amount;
	this.submitted = submitted;
	this.resolved = resolved;
	this.description = description;
	//this.reciept = receipt;
	this.status = status;
	this.type = type;
}
public Reimbursement(double amount, LocalDateTime submitted, LocalDateTime resolved, String description,
		String status, String type) {
	super();
	this.amount = amount;
	this.submitted = submitted;
	this.resolved = resolved;
	this.description = description;
	//this.reciept = receipt;
	this.status = status;
	this.type = type;
}
public Reimbursement() {
	super();
}
public int getRe_id() {
	return re_id;
}
public double getAmount() {
	return amount;
}
public LocalDateTime getSubmitted() {
	return submitted;
}
public LocalDateTime getResolved() {
	return resolved;
}
public String getDescription() {
	return description;
}
//public Blob getReciept() {
//	return reciept;
//}
public String getStatus() {
	return status;
}
public String getType() {
	return type;
}
public void setRe_id(int re_id) {
	this.re_id = re_id;
}
public void setAmount(double amount) {
	this.amount = amount;
}
public void setSubmitted(LocalDateTime submitted) {
	this.submitted = submitted;
}
public void setResolved(LocalDateTime resolved) {
	this.resolved = resolved;
}
public void setDescription(String description) {
	this.description = description;
}
//public void setReciept(Blob reciept) {
	//this.reciept = reciept;
//}
public void setStatus(String status) {
	this.status = status;
}
public void setType(String type) {
	this.type = type;
}



@Override
public int hashCode() {
	return Objects.hash(amount, description, re_id, resolved, status, submitted, type);
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Reimbursement other = (Reimbursement) obj;
	return Double.doubleToLongBits(amount) == Double.doubleToLongBits(other.amount)
			&& Objects.equals(description, other.description) && re_id == other.re_id
			&& Objects.equals(resolved, other.resolved) && Objects.equals(status, other.status)
			&& Objects.equals(submitted, other.submitted) && Objects.equals(type, other.type);
}
@Override
public String toString() {
	return "Reimbursement [re_id=" + re_id + ", amount=" + amount + ", submitted=" + submitted + ", resolved="
			+ resolved + ", description=" + description + ", status=" + status + ", type=" + type + "]";
}


}
