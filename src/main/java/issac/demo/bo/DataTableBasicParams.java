package issac.demo.bo;

public class DataTableBasicParams {
	private int draw;
	private int start;
	private int length;


	public int getDraw() {
		return draw;
	}

	public void setDraw(int draw) {
		this.draw = draw;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	@Override
	public String toString() {
		return "DataTableBasicParams [draw=" + draw + ", start=" + start + ", length=" + length + "]";
	}

}
