package code;

/**
 * function:
 * 		Auxiliary interface. store all path info.
 * time:
 * 		2014.5.15
 * @author Jiajun Jiang
 *
 */

public interface IPathName {
	
	//path of first training data
	public static final String[] train1_baseballPath = {"predata1\\s1\\baseball\\",
														"predata1\\s2\\baseball\\",
														"predata1\\s3\\baseball\\",
														"predata1\\s4\\baseball\\"};
	
	public static final String[] train1_hockeyPath = {"predata1\\s1\\hockey\\",
														"predata1\\s2\\hockey\\",
														"predata1\\s3\\hockey\\",
														"predata1\\s4\\hockey\\"};
	
	//path of first testing data
	public static final String test1_baseballPath = "predata1\\s5\\baseball\\";
	public static final String test1_hockeyPath = "predata1\\s5\\hockey\\";
	
	
	//path of second training data
	public static final String[] train2_baseballPath = {"predata1\\s1\\baseball\\",
														"predata1\\s2\\baseball\\",
														"predata1\\s4\\baseball\\",
														"predata1\\s5\\baseball\\",};

	public static final String[] train2_hockeyPath = {"predata1\\s1\\hockey\\",
														"predata1\\s2\\hockey\\",
														"predata1\\s4\\hockey\\",
														"predata1\\s5\\hockey\\"};
	
	//path of second testing data
	public static final String test2_baseballPath = "predata1\\s3\\baseball\\";
	public static final String test2_hockeyPath = "predata1\\s3\\hockey\\";
}
