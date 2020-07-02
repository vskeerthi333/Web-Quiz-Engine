package com.quizEngine.webQuiz;

public class Result {

	private boolean success; 
	private String feedback;

	public Result(boolean success, String feedback) {
		this.success = success;
		this.feedback = feedback;
	}

	public boolean getSuccess() { return success; }

	public String getFeedback() { return feedback; }

	public void setSuccess(boolean success) { this.success = success; }

	public void setFeedback(String feedback) { this.feedback = feedback; }
}