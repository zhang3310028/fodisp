package test;

import java.util.ArrayList;

import com.celula.tmtool.entity.Hotspot;

public class ObjectArrayCastTest {
	public static void main(String[] args) {
		
		test2();
		
	}
	private static void test1() {
		Hotspot hotspot = new Hotspot();
		ArrayList<Hotspot> arrayList = new ArrayList<Hotspot>();
		arrayList.add(hotspot);
		Object array = (Object)arrayList.toArray();
		Hotspot[] hs = (Hotspot[]) array;
	}
	private static void test2() {
		ArrayList arrayList2 = new ArrayList();
		int[] a = {1,2,3};
		arrayList2.add(a);
		Object array2 = (Object)arrayList2.toArray();

	}
}
