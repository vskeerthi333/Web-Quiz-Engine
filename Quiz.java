package engine;

import com.fasterxml.jackson.annotation.*;
import javax.validation.constraints.*;
import javax.persistence.*;

@Entity
public class Quiz {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;

	@NotBlank @NotNull private String title;

	@NotBlank @NotNull private String text;

	@Size(min=2) @NotNull private String[] options;

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private int[] answer;

	@JsonIgnore
	private String userName;

	public Quiz() {
	}

	public int[] getAnswer() { return answer; }

	public long getId() { return id; }

	public String getTitle() { return title; }

	public String getText() { return text; } 

	public String[] getOptions() { return options; }

	public String getUserName() { return userName; }

	public void setUserName(String userName) { this.userName = userName; }

	public void setId(long id) { this.id = id; }

	public void setTitle(String title) { this.title = title; }

	public void setText(String text) { this.text = text; }

	public void setOptions(String[] options) { this.options = options; }

	public void setAnswer(int[] answer) { this.answer = answer; }


}



