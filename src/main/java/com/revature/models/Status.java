package com.revature.models;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Entity
public class Status {
	
	@Id
	private int status_id;
	private String status;
	
	public Status(){
		super();
	}

	public Status(int status_id, String status) {
		super();
		this.status_id = status_id;
		this.status = status;
	}

	public Status(String status) {
		super();
		this.status = status;
	}

	public int getStratus_id() {
		return status_id;
	}

	public String getStatus() {
		return status;
	}

	public void setStratus_id(int status_id) {
		this.status_id = status_id;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		return Objects.hash(status, status_id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Status other = (Status) obj;
		return Objects.equals(status, other.status) && status_id == other.status_id;
	}

	@Override
	public String toString() {
		return "Status [status_id=" + status_id + ", status=" + status + "]";
	}
	

}
