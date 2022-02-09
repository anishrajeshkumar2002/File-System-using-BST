import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.*;

public class FileSystemTest {
	@Test
	public void testAddSame() {
		FileSystem yum = new FileSystem();
		yum.add(null, null, null);

		yum.add("hello", "root", "2021/09/25");
		yum.add("hello", "root", "2021/09/25");
		yum.add("hello", "root", "2023/09/25");
//		yum.add("hello", "home", "2021/09/25");
//		yum.add("hello", "home", "2021/10/25");
//		yum.add("hello", "root", "2021/09/25");
//		yum.add("hello", "yessir", "2021/09/25");
//		yum.add("hello", "root", "2021/10/25");
//		yum.add("hello", "yessir", "2021/09/25");
//		
		
		
		System.out.println(yum.nameTree.keys().toString());
		System.out.println(yum.dateTree.keys().toString());
		System.out.println(yum.dateTree.get("2023/09/25").toString());
		
		
	}
	@Test
	public void testInpputFile() {
		FileSystem yum = new FileSystem("input.txt");
		
		
		
		System.out.println(yum.nameTree.keys().toString());
		System.out.println(yum.dateTree.keys().toString());
		//System.out.println(yum.nameTree.get("mySample1.txt").toString());
		//System.out.println(yum.dateTree.get("2021/02/02").toString());
		//System.out.println(yum.dateTree.get("2024/02/02").toString());
		ArrayList<String> list = (ArrayList<String>) yum.outputNameTree();
	    System.out.println(list.toString());
		ArrayList<String> list2 = (ArrayList<String>) yum.outputDateTree();
	    System.out.println(list2.toString());
		//System.out.println(yum.nameTree.get("mySample.txt").toString());
		//System.out.println(yum.dateTree.get("2021/02/05").toString());
		
		
		
	}
	@Test
	public void testAdddifferent() {
		FileSystem yum = new FileSystem();

		yum.add("anish", "root", "2021/09/25");
		yum.add("raj", "root", "2021/09/25");
		yum.add("raj", "root", "2021/10/25");
		yum.add("hello", "home", "2021/09/25");
		yum.add("hell", "home", "2021/10/25");
		yum.add("ab", "root", "2021/09/25");
		yum.add("cd", "yessir", "2021/09/25");
		yum.add("ef", "root", "2021/10/25");
		yum.add("gh", "yessir", "2021/09/25");
		
		
		
		System.out.println(yum.nameTree.keys().toString());
		System.out.println(yum.dateTree.keys().toString());
		System.out.println(yum.dateTree.get("2021/09/25").toString());
		
		
	}
	@Test
	public void testFindFileNamesbyDate() {
		FileSystem yum = new FileSystem();
		yum.add("hello", "root", "2021/9/25");
		yum.add("hello", "/root", "2021/6/25");
		yum.add("hell", "root", "2021/8/25");
		yum.add("hello", "root", "2021/8/26");
		System.out.println(yum.nameTree.keys().toString());
		System.out.println(yum.dateTree.keys().toString());
		System.out.println(yum.findFileNamesByDate("2021/8/25").toString());
		System.out.println(yum.dateTree.get("2021/8/25").toString());
	}
	@Test
	public void testFilter() {
		FileSystem yum = new FileSystem();
		yum.add("hello", "root", "2021/9/25");
		yum.add("hel", "/root", "2021/6/25");
		yum.add("hell", "root", "2021/8/25");
		yum.add("hbar", "root", "2021/7/26");
		yum.add("h", "root", "2021/5/26");
		System.out.println(yum.nameTree.keys().toString());
		System.out.println(yum.dateTree.keys().toString());
		FileSystem yum2 = yum.filter("2021/6/25", "2021/9/25");
		System.out.println(yum2.nameTree.keys().toString());
		System.out.println(yum2.dateTree.keys().toString());
		
	}
	@Test
	public void testFilter2() {
		FileSystem yum = new FileSystem();
		yum.add("hello", "root", "2021/9/25");
		yum.add("hel", "/root", "2021/6/25");
		yum.add("hell", "root", "2021/8/25");
		yum.add("hbar", "root", "2021/7/26");
		yum.add("h", "root", "2021/5/26");
		System.out.println(yum.nameTree.keys().toString());
		System.out.println(yum.dateTree.keys().toString());
		FileSystem yum2 = yum.filter("hell");
		System.out.println(yum2.nameTree.keys().toString());
		System.out.println(yum2.dateTree.keys().toString());
		
	}
	@Test
	public void testoutputnames() {
		FileSystem yum = new FileSystem();
		yum.add("he", "root", "2021/9/25");
		yum.add("l", "/root", "2021/6/25");
		yum.add("ell", "root", "2021/8/25");
		yum.add("bar", "root", "2021/7/26");
		yum.add("h", "root", "2021/5/26");
		System.out.println(yum.nameTree.keys().toString());
		System.out.println(yum.dateTree.keys().toString());
		ArrayList<String> list = (ArrayList<String>) yum.outputNameTree();
		System.out.println(list.toString());
		
	}
	@Test
	public void testoutputdates() {
		FileSystem yum = new FileSystem();
		yum.add("hell", "root", "2021/9/25");
		yum.add("l", "/root", "2021/6/25");
		yum.add("hel", "root", "2021/9/25");
		yum.add("bar", "root", "2021/7/26");
		yum.add("h", "root", "2021/5/26");
		System.out.println(yum.nameTree.keys().toString());
		System.out.println(yum.dateTree.keys().toString());
		ArrayList<String> list = (ArrayList<String>) yum.outputDateTree();
		System.out.println(list.toString());
		
	}
	
	

}