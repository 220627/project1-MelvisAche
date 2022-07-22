package com.melvis.models;

public class Status {
	private int id;
	private String status;
	
	@Override
	public String toString() {
		return "Status [id=" + id + ", status=" + status + "]";
	}

	private Status(int id, String status) {
		super();
		this.id = id;
		this.status = status;
	}

	private Status(String status) {
		super();
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	

}
