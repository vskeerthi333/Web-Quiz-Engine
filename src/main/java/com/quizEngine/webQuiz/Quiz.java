package com.quizEngine.webQuiz;

public class Quiz {

	static private Quiz quiz = null;
	private String title;
	private String text;
	private String[] options;
	private String answer;

	public Quiz(String title, String text, String[] options, String answer) {
		this.title = title;
		this.text = text;
		this.options = options;
		this.answer = answer; 
	}

	public String getTitle() { return title; }

	public String getText() { return text; } 

	public String[] getOptions() { return options; }

	public void setTitle() { this.title = title; }

	public void setText() { this.text = text; }

	public void setOptions() { this.options = options; }

}



