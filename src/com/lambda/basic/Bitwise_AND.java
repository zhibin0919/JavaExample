
public class Bitwise_AND {
	static int DEFAULT_OPACITY = 16;
	static String table[] = new String[DEFAULT_OPACITY];
	public static void main(String args[]){
		int index = 0;
		String str1 = "123";
		String str2 = "456";
		int BIT_MASK_COLOR = 0x00f;
		int BIT_MASK_SHAPE = 0xff0;
		index = indexFor(str1.hashCode(), table.length-1);
		table[index] = "Hello";
		System.out.println("str1 mapping value => table index is "+index);
		index = indexFor(str2.hashCode(), table.length-1);
		table[index] = "Java!";
		System.out.println("str2 mapping value => table index is "+index);
		System.out.println("str1 mapping value is "+getValue(str1.hashCode(), table.length-1));
		System.out.println("str2 mapping value is "+getValue(str2.hashCode(), table.length-1));
		System.out.println(16 & 15);
		System.out.println(0x001 & BIT_MASK_COLOR);
		System.out.println(BIT_MASK_SHAPE);
	}
	public static int indexFor(int hash, int length){
		return hash & length;
	}
	public static String getValue(int hash, int length){
		int i = indexFor(hash, length);
		return table[i];
	}
}
