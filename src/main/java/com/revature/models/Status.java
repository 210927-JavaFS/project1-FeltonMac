package com.revature.models;

import java.util.Objects;

public class Status {
	private int stratus_id;
	private String status;
	
	public Status(){
		super();
	}

	public Status(int stratus_id, String status) {
		super();
		this.stratus_id = stratus_id;
		this.status = status;
	}

	public Status(String status) {
		super();
		this.status = status;
	}

	public int getStratus_id() {
		return stratus_id;
	}

	public String getStatus() {
		return status;
	}

	public void setStratus_id(int stratus_id) {
		this.stratus_id = stratus_id;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		return Objects.hash(status, stratus_id);
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
		return Objects.equals(status, other.status) && stratus_id == other.stratus_id;
	}

	@Override
	public String toString() {
		return "Status [stratus_id=" + stratus_id + ", status=" + status + "]";
	}
	

}
