package lunEx;

public class LunExServiceUnavailableException extends RuntimeException {
	public String getMessage() {
		return "Sorry, sunspot activity today...please try again later";
	}
}
