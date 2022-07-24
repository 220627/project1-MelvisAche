package com.melvis.daos;

import java.util.ArrayList;

import com.melvis.models.Status;

public interface StatusDAOInterface {

	Status getStatusById(int id);

	boolean insertStatus(Status stats);

	ArrayList<Status> getStatus();

}
