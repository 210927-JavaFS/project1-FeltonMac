package com.revature.models;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Type {
	@Id
	private int type_id;
	private String typestring;

	public Type() {
		super();
	}

	public Type(int type_id, String typestring) {
		super();
		this.type_id = type_id;
		this.typestring = typestring;
	}

	public Type(String typestring) {
		super();
		this.typestring = typestring;
	}

	public int getType_id() {
		return type_id;
	}

	public String getTypestring() {
		return typestring;
	}

	public void setType_id(int type_id) {
		this.type_id = type_id;
	}

	public void setTypestring(String typestring) {
		this.typestring = typestring;
	}

	@Override
	public int hashCode() {
		return Objects.hash(type_id, typestring);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Type other = (Type) obj;
		return type_id == other.type_id && Objects.equals(typestring, other.typestring);
	}

	@Override
	public String toString() {
		return "Type [type_id=" + type_id + ", typestring=" + typestring + "]";
	}

}
