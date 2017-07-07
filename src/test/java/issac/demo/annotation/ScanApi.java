package issac.demo.annotation;

@Scan
public interface ScanApi {
	@ScanMethod("test scan method")
	public void say(@ScanParam("content") String content,String content2);

}
