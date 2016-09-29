package code;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * function:
 * 		training class
 * time:
 * 		2014.5.16
 * @author Jiajun Jiang
 *
 */
public class Training {
	
	//training cycles
	private static final int TRAINTINGIMES = 1000;
	
	//learning rate
	private static final double alpha = 0.25;
	
	//coefficient of model
	private double[] weight;
	
	//keywords
	private String[] keyWordArray;
	
	//directory of training data hockey
	private String[] trianFilePathHockey;
	
	//directory of training data baseball
	private String[] trainFilePathBaseball;

	//get coefficient
	public double[] getWeight() {
		return weight;
	}
	
	//set coefficient
	public void setWeight(double[] weight) {
		this.weight = weight;
	}
	
	//set keywords
	public void setKeyWordArray(String[] keyWordArray) {
		this.keyWordArray = keyWordArray;
	}
	
	//set directory of training data hockey
	public void setTrianFilePathHockey(String[] trianFilePathHockey) {
		this.trianFilePathHockey = trianFilePathHockey;
	}

	//set directory of training data baseball
	public void setTrainFilePathBaseball(String[] trainFilePathBaseball) {
		this.trainFilePathBaseball = trainFilePathBaseball;
	}

	/**
	 * function:
	 * 				training process
	 * @param fileName	(String)
	 * 				path of training data
	 * @param type	(int)
	 * 				file class:  HOCKEYL or BASEBALL, refer to Classification
	 */
	private void fileTrain(String fileName, int type){
		
		int[] mkeyWordFrequencyArray = GetKeyWord.getKeyWordFrequency(keyWordArray, fileName);
		double p = 0;
		
		//compute file class
		for(int i = 0; i < mkeyWordFrequencyArray.length; i++){
			p += weight[i] * mkeyWordFrequencyArray[i];
		}
		// wrongly classified, update coefficient of model
		if(type*p <= 0){
			for(int j = 0; j < mkeyWordFrequencyArray.length; j++){
				weight[j] += alpha * type * mkeyWordFrequencyArray[j];
			}
		}
		
	}
	
	/**
	 * function:
	 * 				training and writing the result into file
	 * @param saveDataFilePath	(String)
	 * 				path of file to store coefficient
	 */
	public void Train(String saveDataFilePath){
		
		//training cycles  TRAINTINGIMES=1000
		for(int train_cycle = 0; train_cycle < TRAINTINGIMES; train_cycle++){
			
			System.out.println("ѭ��    "+train_cycle);
			
			//read all files
			ReadDirector rdir = new ReadDirector();
			//training using baseball data
			for(int dir_num = 0; dir_num < trainFilePathBaseball.length; dir_num++){
				List<String> fileNameList = rdir.getFiles(trainFilePathBaseball[dir_num]);
				//training using each file
				for(int file_num = 0; file_num < fileNameList.size(); file_num++){
					String fileName = trainFilePathBaseball[dir_num]+fileNameList.get(file_num);
					fileTrain(fileName, IClassification.BASEBALL);
				}
			}
			//training using hockey data
			for(int dir_num = 0; dir_num < trianFilePathHockey.length; dir_num++){
				List<String> fileNameList = rdir.getFiles(trianFilePathHockey[dir_num]);
				//training using each file
				for(int file_num = 0; file_num < fileNameList.size(); file_num++){
					String fileName = trianFilePathHockey[dir_num]+fileNameList.get(file_num);
					fileTrain(fileName, IClassification.HOCKEY);
				}
			}
			
			//write new coefficient into file
			File resultFile = new File(saveDataFilePath);
			try {
				if(!resultFile.exists()) {   
					resultFile.getParentFile().mkdirs();
		        	try{
		        		resultFile.createNewFile();
		            } catch (IOException ioe) {
		            	ioe.printStackTrace();
		        	}
		        }
				//append new coefficient into file
				FileWriter bufrd = new FileWriter(resultFile,true);
				bufrd.write("cycle:  "+ train_cycle +System.getProperty("line.separator"));
				for(int i = 0; i < weight.length; i++){
					bufrd.append(String.valueOf(weight[i])+"  ");
				}
				bufrd.write(System.getProperty("line.separator"));
				bufrd.write(System.getProperty("line.separator"));
				bufrd.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
	
}
