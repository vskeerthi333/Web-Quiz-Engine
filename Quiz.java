package com.quizEngine.webQuiz;

import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.*;
import javax.persistence.*;

@Entity
public class Quiz {

	@Id
	private long id;

	@NotBlank @NotNull private String title;

	@NotBlank @NotNull private String text;

	@Size(min=2) @NotNull private String[] options;

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private int[] answer;

	public Quiz() {
		answer = new int[0];
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



