package tests;

import org.testng.annotations.Test;

public class ChildClass extends BaseClass {
	@Test
	public void runTest() {
		driver.get("https://www.google.com/");
		
	}

}
