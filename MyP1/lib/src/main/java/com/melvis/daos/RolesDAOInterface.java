package com.melvis.daos;

import java.util.ArrayList;

import com.melvis.models.Roles;

public interface RolesDAOInterface {

	Roles getRoleById(int id);

	boolean insertRole(Roles role);

	ArrayList<Roles> getRole();

}
