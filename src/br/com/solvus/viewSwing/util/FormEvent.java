package br.com.solvus.viewSwing.util;
import java.util.EventObject;

public class FormEvent extends EventObject{
	
	private String name;
	private String occupation;
	private String date;
	
	public FormEvent(Object source) {
		super(source);
	}

	public FormEvent(Object source, String name, String occupation, String date) {
		super(source);
		this.name = name;
		this.date = date;
	}

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
