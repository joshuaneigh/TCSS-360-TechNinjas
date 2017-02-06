/**
 * 
 */
package model;


/**
 * @author Jasmine Dacones
 *
 */
public class TestJob {

	public static void main(String[] args) {
		Job jobOne = new Job();
			
	}
	
	public void testSetDesc(String theDesc) {
		assertTrue(jobOne.setDesc("Rake the leaves at Point Defiance Park."), getDesc());
	}
	
}