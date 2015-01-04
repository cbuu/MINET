package cbuu.minet.util;

import cbuu.minet.R;

public class NameToIconNum {
	private String name;
	private int iconNum;

	private static NameToIconNum instance = null;

	public static NameToIconNum getInstance() {
		if (instance == null) {
			instance = new NameToIconNum(null, 0);
		}
		return instance;
	}

	public NameToIconNum(String n, int num) {
		name = n;
		iconNum = num;
	}

	public int nameToIconNum(String n) {
		if (n.equals("superman")) {
			iconNum = R.drawable.superman;
			return iconNum;
		}
		if (n.equals("ic_launcher")) {
			iconNum = R.drawable.ic_launcher;
			return iconNum;
		}
		if (n.equals("setting")) {
			iconNum = R.drawable.setting;
			return iconNum;
		}
		if (n.equals("back")) {
			iconNum = R.drawable.back;
			return iconNum;
		}
		if (n.equals("angry")) {
			iconNum = R.drawable.angry;
			return iconNum;
		}
		if (n.equals("astroboy")) {
			iconNum = R.drawable.astroboy;
			return iconNum;
		}
		if (n.equals("bigyellowdog")) {
			iconNum = R.drawable.bigyellowdog;
			return iconNum;
		}
		if (n.equals("cool")) {
			iconNum = R.drawable.cool;
			return iconNum;
		}
		if (n.equals("cry")) {
			iconNum = R.drawable.cry;
			return iconNum;
		}
		if (n.equals("doraemon")) {
			iconNum = R.drawable.doraemon;
			return iconNum;
		}
		if (n.equals("girl")) {
			iconNum = R.drawable.girl;
			return iconNum;
		}
		if (n.equals("hellokitty")) {
			iconNum = R.drawable.hellokitty;
			return iconNum;
		}
		if (n.equals("nikefootball")) {
			iconNum = R.drawable.nikefootball;
			return iconNum;
		}
		if (n.equals("programmer")) {
			iconNum = R.drawable.programmer;
			return iconNum;
		}
		if (n.equals("qq")) {
			iconNum = R.drawable.qq;
			return iconNum;
		}
		if (n.equals("simpson")) {
			iconNum = R.drawable.simpson;
			return iconNum;
		}
		if (n.equals("simpsontwo")) {
			iconNum = R.drawable.simpsontwo;
			return iconNum;
		}
		if (n.equals("smile")) {
			iconNum = R.drawable.smile;
			return iconNum;
		}
		if (n.equals("southpark")) {
			iconNum = R.drawable.southpark;
			return iconNum;
		}
		if (n.equals("southparktwo")) {
			iconNum = R.drawable.southparktwo;
			return iconNum;
		}
		if (n.equals("southparkthree")) {
			iconNum = R.drawable.southparkthree;
			return iconNum;
		}
		if (n.equals("southparkfour")) {
			iconNum = R.drawable.southparkfour;
			return iconNum;
		}

		// more...
		return 0;
	}

	public String iconNumToName(int n) {
		if (n == R.drawable.superman) {
			name = "superman";
			return name;
		}
		if (n == R.drawable.ic_launcher) {
			name = "ic_launcher";
			return name;
		}
		if (n == R.drawable.setting) {
			name = "setting";
			return name;
		}
		if (n == R.drawable.angry) {
			name = "angry";
			return name;
		}
		if (n == R.drawable.astroboy) {
			name = "astroboy";
			return name;
		}
		if (n == R.drawable.bigyellowdog) {
			name = "bigyellowdog";
			return name;
		}
		if (n == R.drawable.cool) {
			name = "cool";
			return name;
		}
		if (n == R.drawable.cry) {
			name = "cry";
			return name;
		}
		if (n == R.drawable.doraemon) {
			name = "doraemon";
			return name;
		}
		if (n == R.drawable.girl) {
			name = "girl";
			return name;
		}
		if (n == R.drawable.hellokitty) {
			name = "hellokitty";
			return name;
		}
		if (n == R.drawable.nikefootball) {
			name = "nikefootball";
			return name;
		}
		if (n == R.drawable.programmer) {
			name = "programmer";
			return name;
		}
		if (n == R.drawable.qq) {
			name = "qq";
			return name;
		}
		if (n == R.drawable.simpson) {
			name = "simpson";
			return name;
		}
		if (n == R.drawable.simpsontwo) {
			name = "simpsontwo";
			return name;
		}
		if (n == R.drawable.smile) {
			name = "smile";
			return name;
		}
		if (n == R.drawable.southpark) {
			name = "southpark";
			return name;
		}
		if (n == R.drawable.southparktwo) {
			name = "southparktwo";
			return name;
		}
		if (n == R.drawable.southparkthree) {
			name = "southparkthree";
			return name;
		}
		if (n == R.drawable.southparkfour) {
			name = "southparkfour";
			return name;
		}

		// more...
		return null;
	}

}
