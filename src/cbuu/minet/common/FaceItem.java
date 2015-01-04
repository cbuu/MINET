package cbuu.minet.common;

public class FaceItem {
	private String name1 = "";
	private String name2 = "";
	private String name3 = "";
	private String name4 = "";

	public FaceItem(String n1, String n2, String n3, String n4) {
		name1 = n1;
		name2 = n2;
		name3 = n3;
		name4 = n4;
	}

	public void setName1(String n) {
		name1 = n;
	}

	public void setName2(String n) {
		name2 = n;
	}

	public void setName3(String n) {
		name3 = n;
	}

	public void setName4(String n) {
		name4 = n;
	}

	public String getName1() {
		return name1;
	}

	public String getName2() {
		return name2;
	}

	public String getName3() {
		return name3;
	}

	public String getName4() {
		return name4;
	}
}
