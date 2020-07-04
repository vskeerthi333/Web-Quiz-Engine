package com.quizEngine.webQuiz;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Quiz {

	private long id;
	private String title;
	private String text;
	private String[] options;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private int answer;

	public Quiz() {
	}

	public Quiz(String title, String text, String[] options, int answer) {
		this.title = title;
		this.text = text;
		this.options = options;
		this.answer = answer; 
	}

	public boolean validateAnswer(String answer) { 
		String ans = "answer="+this.answer;
		return ans.equals(answer); 
	}

	public int getAnswer() { return answer; }

	public void setAnswer(int answer) { this.answer = answer; }

	public long getId() { return id; }

	public String getTitle() { return title; }

	public String getText() { return text; } 

	public String[] getOptions() { return options; }

	public void setId(long id) { this.id = id; }

	public void setTitle(String title) { this.title = title; }

	public void setText(String text) { this.text = text; }

	public void setOptions(String[] options) { this.options = options; }

}



