package com.melvis.models;

import java.util.Date;

public class Reimbursement {
	private int id;
	private double amount;
	private String description;
	private int author;
	private int resolver;
	private int status_id_fk;
	private int type_id_fk;
	private String receipt;
	private Date submitted;
	private Date resolved;
	private Status status;
	private Type type;
	@Override
	public String toString() {
		return "Reimbursement [id=" + id + ", amount=" + amount + ", description=" + description + ", author=" + author
				+ ", resolver=" + resolver + ", status_id_fk=" + status_id_fk + ", type_id_fk=" + type_id_fk
				+ ", receipt=" + receipt + ", submitted=" + submitted + ", resolved=" + resolved + ", status=" + status
				+ ", type=" + type + "]";
	}
	
	public Reimbursement(int id, double amount, String description, int author, int resolver, String receipt, Date submitted, Date resolved, Status status, Type type) {
		super();
		this.id = id;
		this.amount = amount;
		this.description = description;
		this.author = author;
		this.resolver = resolver;
		
		this.receipt = receipt;
		this.submitted = submitted;
		this.resolved = resolved;
		this.status = status;
		this.type = type;
	}

	public Reimbursement(double amount, String description, int author, int resolver, int status_id_fk, int type_id_fk,
			String receipt, Date submitted, Date resolved, Status status, Type type) {
		super();
		this.amount = amount;
		this.description = description;
		this.author = author;
		this.resolver = resolver;
		this.status_id_fk = status_id_fk;
		this.type_id_fk = type_id_fk;
		this.receipt = receipt;
		this.submitted = submitted;
		this.resolved = resolved;
		this.status = status;
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getAuthor() {
		return author;
	}

	public void setAuthor(int author) {
		this.author = author;
	}

	public int getResolver() {
		return resolver;
	}

	public void setResolver(int resolver) {
		this.resolver = resolver;
	}

	public int getStatus_id_fk() {
		return status_id_fk;
	}

	public void setStatus_id_fk(int status_id_fk) {
		this.status_id_fk = status_id_fk;
	}

	public int getType_id_fk() {
		return type_id_fk;
	}

	public void setType_id_fk(int type_id_fk) {
		this.type_id_fk = type_id_fk;
	}

	public String getReceipt() {
		return receipt;
	}

	public void setReceipt(String receipt) {
		this.receipt = receipt;
	}

	public Date getSubmitted() {
		return submitted;
	}

	public void setSubmitted(Date submitted) {
		this.submitted = submitted;
	}

	public Date getResolved() {
		return resolved;
	}

	public void setResolved(Date resolved) {
		this.resolved = resolved;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}
	
	
	

}
