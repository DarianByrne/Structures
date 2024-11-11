public class Main {
	public static void main(String[] args) {
		System.out.println("Hello world!");
		LeftJustifiedArray<Integer> myArr = new LeftJustifiedArray<>(10);
		for (int i = 1; i <= 8; i++) {
			myArr.insert(i - 1, i * 10);
		}
		System.out.println("LeftJustifiedArray: " + myArr);
		myArr.delete(3);
		System.out.println("LeftJustifiedArray: " + myArr);
	}
}
