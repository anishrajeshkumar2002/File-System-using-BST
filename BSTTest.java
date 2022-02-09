import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.junit.*;

public class BSTTest {
	
	/* TODO: Add your own tests */
	@Test
	public void dummyTest() {
		DefaultMap<String, Integer> yum = new BST();
		yum.set("e", 5);
		yum.set("c", 5);
		yum.set("b", 5);
		System.out.println(yum.get("b"));
		
		yum.set("a", 5);
		yum.set("d", 5);
		yum.remove("c");
		yum.remove("a");
		//System.out.println();

		System.out.println(yum.keys().toString());
		System.out.println(yum.put("b", 7));
		System.out.println(yum.get("b"));
		yum.replace("b", 9);
		System.out.println(yum.replace("b", 10));
		System.out.println(yum.get("b"));
		//yum.set(null, 5);
	
		}
	@Test
	public void removetest() {
		System.out.println("break");
		DefaultMap<String, Integer> yummy = new BST();
		yummy.set("e", 5);
	
		System.out.println(yummy.keys().toString());
		System.out.println(yummy.remove("g"));
		System.out.println(yummy.keys().toString());
		System.out.println(yummy.remove("e"));
		System.out.println(yummy.keys().toString());
	}
}
