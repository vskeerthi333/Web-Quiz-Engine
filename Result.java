package engine;

import org.springframework.stereotype.Component;

@Component
public class Result {

	private boolean success; 
	private String feedback;

	public Result(){
	}

	public boolean getSuccess() { return success; }

	public String getFeedback() { return feedback; }

	public void setSuccess(boolean success) { this.success = success; }

	public void setFeedback(String feedback) { this.feedback = feedback; }
}