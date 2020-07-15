package engine;

import javax.validation.constraints.*;
import javax.persistence.*;

@Entity
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