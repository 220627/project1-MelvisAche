package com.melvis.daos;

import java.util.ArrayList;

import com.melvis.models.Type;

public interface TypeDAOInterface {

	Type getTypeById(int id);

	boolean insertType(Type type);

	ArrayList<Type> getType();

}
