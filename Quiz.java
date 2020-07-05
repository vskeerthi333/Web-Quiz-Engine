package com.quizEngine.webQuiz;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import javax.validation.constraints.*;

public class Quiz {

	private long id;
	@NotBlank @NotNull private String title;
	@NotBlank @NotNull private String text;
	@Size(min=2) @NotNull private String[] options;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private int[] answer;

	public Quiz() {
		answer = new int[0];
	}

	public boolean validateAnswer(int[] answer) { 
		if (this.answer.length != 0 && (answer == null || answer.length == 0))
			return false;
		if ( this.answer.length != answer.length)
			return false;
		for (int i = 0; i < this.answer.length; i++) {
			if (answer[i] != this.answer[i])
				return false;
		}
		return true; 
	}

	public int[] getAnswer() { return answer; }

	public void setAnswer(int[] answer) { this.answer = answer; }

	public long getId() { return id; }

	public String getTitle() { return title; }

	public String getText() { return text; } 

	public String[] getOptions() { return options; }

	public void setId(long id) { this.id = id; }

	public void setTitle(String title) { this.title = title; }

	public void setText(String text) { this.text = text; }

	public void setOptions(String[] options) { this.options = options; }

}



