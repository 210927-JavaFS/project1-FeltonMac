package com.revature.models;

//import java.sql.Blob;
import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="reimbursements")
public class Reimbursement {
//  The back-end system shall use Hibernate to connect to an AWS RDS Postgres database.
//	The middle tier shall use Javalin technology for dynamic Web application development.
//	The front-end view shall use HTML/JavaScript to make an application that can call server-side 
//	components RESTfully. Passwords shall be encrypted in Java and securely stored in the database.
//	The middle tier shall follow proper layered architecture, have reasonable
//	(~70%) test coverage of the service layer, and implement Logback for appropriate logging.
	
@Id
@GeneratedValue()
private	int re_id;
double amount;//could change to int 
LocalDateTime  submitted;
LocalDateTime  resolved;
String description;
//Blob receipt;
@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
@JoinColumn(name="id")
User author;
@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
@JoinColumn(name="id")
User resolver;
@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
@JoinColumn(name="status_id")
Status status;
@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
@JoinColumn(name="type_id")
Type type;
;


public Reimbursement(int re_id, double amount, LocalDateTime submitted, LocalDateTime resolved, String description,
		User author, User resolver, Status status, Type type) {
	super();
	this.re_id = re_id;
	this.amount = amount;
	this.submitted = submitted;
	this.resolved = resolved;
	this.description = description;
	this.author = author;
	this.resolver = resolver;
	this.status = status;
	this.type = type;
}

public Reimbursement(double amount, LocalDateTime submitted, LocalDateTime resolved, String description, User author,
		User resolver, Status status, Type type) {
	super();
	this.amount = amount;
	this.submitted = submitted;
	this.resolved = resolved;
	this.description = description;
	this.author = author;
	this.resolver = resolver;
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

public User getAuthor() {
	return author;
}

public User getResolver() {
	return resolver;
}

public Status getStatus() {
	return status;
}

public Type getType() {
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

public void setAuthor(User author) {
	this.author = author;
}

public void setResolver(User resolver) {
	this.resolver = resolver;
}

public void setStatus(Status status) {
	this.status = status;
}

public void setType(Type type) {
	this.type = type;
}

@Override
public int hashCode() {
	return Objects.hash(amount, author, description, re_id, resolved, resolver, status, submitted, type);
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
			&& Objects.equals(author, other.author) && Objects.equals(description, other.description)
			&& re_id == other.re_id && Objects.equals(resolved, other.resolved)
			&& Objects.equals(resolver, other.resolver) && Objects.equals(status, other.status)
			&& Objects.equals(submitted, other.submitted) && Objects.equals(type, other.type);
}
@Override
public String toString() {
	return "Reimbursement [re_id=" + re_id + ", amount=" + amount + ", submitted=" + submitted + ", resolved="
			+ resolved + ", description=" + description + ", author=" + author + ", resolver=" + resolver + ", status="
			+ status + ", type=" + type + "]";
}


}
