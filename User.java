package engine;

import javax.validation.constraints.*;
import com.fasterxml.jackson.annotation.*;
import java.time.LocalDateTime;  
import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "User")
public class User {

	@Id
	@NotBlank private String email; 

	@Size(min=5) @NotNull private String password;

	public User() {
	}

	public void setEmail(String email) { this.email = email; }

	public void setPassword(String password) { this.password = password; }

	public String getEmail() { return email; }

	public String getPassword() { return password; }

}