package issac.demo.dto;

public class Result {

	public static final int FAIL = 0;
	public static final int SUCCESS = 1;
	public static Result FailBean = getResult(FAIL, "FAIL");
	public static Result SuccessBean = getResult(SUCCESS, "SUCCESS");

	private int status = FAIL;
	private String message;

	public Result() {
	}

	public Result(int status) {
		this.status = status;
		this.message = message;
	}

	public Result(int status, String message) {
		this.status = status;
		this.message = message;
	}

	public int getStatus() {
		return status;
	}

	public Result setStatus(int status) {
		this.status = status;
		return this;
	}

	public String getMessage() {
		return message;
	}

	public Result setMessage(String message) {
		this.message = message;
		return this;
	}

	private static Result getResult(int status, String message) {
		return new Result(status, message);
	}

	@Override
	public String toString() {
		return "Result [status=" + status + ", message=" + message + "]";
	}
}
