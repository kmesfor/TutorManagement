package com.gmail.kianmesforush.tutormanagement.datatypes;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

public abstract class User implements Serializable {
	//https://www.tutorialspoint.com/java/java_serialization.htm
	@Serial
	private static final long serialVersionUID = 1L;
	private final String name;
	public final ArrayList<TutoringSession> availability = new ArrayList<>();
	public final ArrayList<String> notes = new ArrayList<>();
	
	public User(String name) {
		this.name = name;
	}
	
	public String getName() { return this.name; }
}