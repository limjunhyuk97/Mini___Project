package car;

public enum VanSize{
	
	A("대형"), B("중형"), C("소형");
	
	private String name;
	private VanSize(String str) {
		this.name = str;
	}
	public String toString() {
		return name;
	}
}