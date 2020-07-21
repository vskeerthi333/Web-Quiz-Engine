package engine;

import javax.persistence.*;
import java.time.LocalDateTime;  
import javax.validation.constraints.*;
import com.fasterxml.jackson.annotation.*;
import java.util.*;
import java.io.*;

@Entity
public class CompletedQuiz {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "primaryid", unique = true, nullable = false)
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private long primaryid;

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String email;

	private long id;

	private LocalDateTime completedAt;  

	public CompletedQuiz() {
	}

	public void setId(long id) { this.id = id; }

	public long getId() { return id; }

	public void setEmail(String email) { this.email = email; }

	public String getEmail() { return email; }

	public void setPrimaryid(long primaryid) { this.primaryid = primaryid; }

	public long getPrimaryid() { return primaryid; }

	public void setCompletedAt(LocalDateTime completedAt) { this.completedAt = completedAt; }

	public LocalDateTime getCompletedAt() { return completedAt; }
}