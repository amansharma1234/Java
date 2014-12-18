import static org.junit.Assert.*;

import org.junit.Test;

public class TestCases {

	@Test
	public void stringToLongTesting() {

		// Tests
		assertEquals("string to long coversion of 123 should be 123", 
				123,StringToLongUtil.stringToLong("123"));
		
		assertEquals("string to long coversion of 0 should be 0", 
				0,StringToLongUtil.stringToLong("0"));
		
		assertEquals("string to long coversion of -123 should be -123", 
				-123,StringToLongUtil.stringToLong("-123"));
				
		assertEquals("string to long coversion of 9223372036854775807 should be 9223372036854775807", 
				9223372036854775807L,StringToLongUtil.stringToLong("9223372036854775807"));
		
		assertEquals("string to long coversion of 9223372036854775807 should be 9223372036854775805", 
				9223372036854775805L,StringToLongUtil.stringToLong("9223372036854775805"));

		assertEquals("string to long coversion of -9223372036854775808 should be -9223372036854775807", 
				-9223372036854775807L,StringToLongUtil.stringToLong("-9223372036854775807"));
		
		assertEquals("string to long coversion of -9223372036854775805 should be -9223372036854775805", 
				-9223372036854775805L,StringToLongUtil.stringToLong("-9223372036854775805"));
		
		try {
			StringToLongUtil.stringToLong("9223372036854775808");
			fail();
		} catch (Exception e) {
			assertEquals("string to long coversion of 9223372036854775808 should be an exception", 
					"For given string: 9223372036854775808",e.getMessage());
		} 
		
		try {
			StringToLongUtil.stringToLong("1.00");
			fail();
		} catch (Exception e) {
			assertEquals("string to long coversion of 1.00 should be an exception", 
					"For given string: 1.00",e.getMessage());
		} 
		
		try {
			StringToLongUtil.stringToLong("");
			fail();
		} catch (Exception e) {
			assertEquals("string to long coversion of \"\" should be an exception", 
					"For given string: \"\" ",e.getMessage());
		} 
	}
	
	@Test
	public void trinaryTreeTesting() {
		TrinaryTree<Integer> trinaryTree = new TrinaryTree<Integer>();
		trinaryTree.add(5);
		trinaryTree.add(4);
		trinaryTree.add(9);
		trinaryTree.add(7);
		trinaryTree.add(8);
		trinaryTree.add(5);
		trinaryTree.add(11);
		trinaryTree.add(2);
		trinaryTree.add(2);
		
		assertEquals("inorder traversersal should be", 
				"2 2 4 5 5 7 8 9 11 ",trinaryTree.inOrder());
		
		trinaryTree.delete(5);
		
		assertEquals("inorder traversersal should be", 
				"2 2 4 5 7 8 9 11 ",trinaryTree.inOrder());
		
		trinaryTree.delete(5);
		
		assertEquals("inorder traversersal should be", 
				"2 2 4 7 8 9 11 ",trinaryTree.inOrder());
		
		trinaryTree.delete(9);
		
		assertEquals("inorder traversersal should be", 
				"2 2 4 7 8 11 ",trinaryTree.inOrder());
		
		trinaryTree.delete(9);
		
		assertEquals("inorder traversersal should be", 
				"2 2 4 7 8 11 ",trinaryTree.inOrder());
		
		trinaryTree.delete(2);
		trinaryTree.delete(4);
		
		assertEquals("inorder traversersal should be", 
				"2 7 8 11 ",trinaryTree.inOrder());
		
		trinaryTree.delete(8);
		assertEquals("inorder traversersal should be", 
				"2 7 11 ",trinaryTree.inOrder());
		
		trinaryTree.delete(2);
		assertEquals("inorder traversersal should be", 
				"7 11 ",trinaryTree.inOrder());
		
		trinaryTree.delete(7);
		assertEquals("inorder traversersal should be", 
				"11 ",trinaryTree.inOrder());
		
		trinaryTree.delete(11);
		assertEquals("inorder traversersal should be", 
				"",trinaryTree.inOrder());
	}
}
