package code;

import java.util.List;


/**
 * function:
 * 		classify files using perceptron algorithm
 * time:
 * 		2014.5.16
 * @author Jiajun Jiang
 *
 */

public class Perceptron {
	
	//coefficient of model
	private double[] weight;
	
	//list of keywords
	private String[] keyWordArray;
	
	//validation result
	private int originalClass;
	
	//number of files that classified
	private int correctNum;
	
	//total files
	private int totalNum;
	
	//get number of correctly classified files 
	public int getCorrectNum() {
		return correctNum;
	}

	//get total files
	public int getTotalNum() {
		return totalNum;
	}

	//set validation result
	public void setOriginalClass(int originalClass) {
		this.originalClass = originalClass;
	}

	//coefficient of perceptron algorithm
	public void setWeight(double[] weight) {
		this.weight = weight;
	}

	//set keywords
	public void setKeyWordArray(String[] keyWordArray) {
		this.keyWordArray = keyWordArray;
	}
	
	/**
	 * function:
	 * 				file classification
	 * @param fileName	(String)
	 * 				file name to be classified
	 * @return fileClass	(int)
	 * 				file class:  HOCKEY or HOCKEY, refer to Classification
	 */
	public int getFileClassification(String fileName){
		
		int fileClass;
		//get frequencies of given keywords
		int[] mkeyWordFrequencyArray = GetKeyWord.getKeyWordFrequency(keyWordArray, fileName);
		
		//classify
		double p = 0;
		for(int i =0; i < mkeyWordFrequencyArray.length; i++){
			p += mkeyWordFrequencyArray[i]*weight[i];
		}
		
		//if p > 0, result is HOCKY, otherwise BASEBALL
		if(p > 0){
			fileClass = IClassification.HOCKEY;
		}else{
			fileClass = IClassification.BASEBALL;
		}
		
		return fileClass;
		
	}
	
	/**
	 * function:
	 * 			test using given data
	 * @param dirName	(String)
	 * 			directory of test data
	 */
	public void testPerceptron(String dirName){
		ReadDirector rdir = new ReadDirector();
		List<String> fileNameList = rdir.getFiles(dirName);
		for(String fileName : fileNameList){
			//classify the given files
			if(getFileClassification(dirName+fileName) * this.originalClass > 0){
				this.correctNum ++;
			}
			this.totalNum ++;
		}
	}
	
}