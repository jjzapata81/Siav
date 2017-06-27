package co.com.techandsolve.lazy.dto;

import java.util.List;

public class Schedule {
	
	private int days;
	
	public Schedule(int days, List<Tasks> tasks) {
		super();
		this.days = days;
		this.tasks = tasks;
	}

	private List<Tasks> tasks;

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}

	public List<Tasks> getTasks() {
		return tasks;
	}

	public void setTasks(List<Tasks> tasks) {
		this.tasks = tasks;
	}

}
