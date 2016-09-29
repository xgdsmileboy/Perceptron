package code;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		
		//pre-process, segmentation, using NLPIR tool
		try {
			NLPIR.preHandle();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		/**
		 * training using data s1-s4, and write results into file
		 * keyword\\train1.txt :  extracted keywords
		 * weight\\train1.txt :   coefficient for each training cycle
		 */
		//extract all keywords in PathName.train1_baseballPath
		List<String> keyWord1_1 = GetKeyWord.getKeyWords(IPathName.train1_baseballPath);
		
		//extract all keywords in PathName.train1_hockeyPath
		List<String> keyWord1_2 = GetKeyWord.getKeyWords(IPathName.train1_hockeyPath);
		
		//union keywords and delete duplicate keywords
		for(String key : keyWord1_1){
			if(!keyWord1_2.contains(key)){
				keyWord1_2.add(key);
			}
		}
		
		//array of keywords, the indices of keywords and frequencies are consistent
		String[] keyWordArray1 = new String[keyWord1_2.size()];
		
		//write kwywords into file
		File resultFile1 = new File("keyword\\train1.txt");
		FileWriter bufrd1;
		try {
			int i = 0;
			bufrd1 = new FileWriter(resultFile1,true);
			for(String key : keyWord1_2){
				keyWordArray1[i++] = key;
				bufrd1.write(key+System.getProperty("line.separator"));
			}
			bufrd1.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		//weight of each keyword
		double[] weight1 = new double[keyWordArray1.length];
		
		//training
		Training train1 = new Training();
		train1.setWeight(weight1);               //set coefficient from first training
		train1.setKeyWordArray(keyWordArray1);   //set keywords from first training
		train1.setTrainFilePathBaseball(IPathName.train1_baseballPath); 
		train1.setTrianFilePathHockey(IPathName.train1_hockeyPath);  
		train1.Train("weight\\train1.txt"); 
		
		System.out.println("ѵ��һ    finished!");
		
		/**
		 * training using data s1,s2,s4,s5, and write results into file
		 * keyword\train2.txt :  store keywords
		 * weight\train2.txt :   store coefficient for each cycle
		 */
		List<String> keyWord2_1 = GetKeyWord.getKeyWords(IPathName.train2_baseballPath);
		
		List<String> keyWord2_2 = GetKeyWord.getKeyWords(IPathName.train2_hockeyPath);
		
		for(String key : keyWord2_1){
			if(!keyWord2_2.contains(key)){
				keyWord2_2.add(key);
			}
		}
		String[] keyWordArray2 = new String[keyWord2_2.size()];
		
		File resultFile2 = new File("keyword\\train2.txt");
		FileWriter bufrd2;
		try {
			int j = 0;
			bufrd2 = new FileWriter(resultFile2,true);
			for(String key : keyWord2_2){
				keyWordArray2[j++] = key;
				bufrd2.write(key+System.getProperty("line.separator"));
			}
			bufrd2.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		double[] weight2 = new double[keyWordArray2.length];
		
		Training train2 = new Training();
		train2.setWeight(weight2);
		train2.setKeyWordArray(keyWordArray2);
		train2.setTrainFilePathBaseball(IPathName.train2_baseballPath);
		train2.setTrianFilePathHockey(IPathName.train2_hockeyPath);
		train2.Train("weight\\train2.txt");

		System.out.println("ѵ����    finished!");
		
		/**
		 * test model using data from s5
		 */
		//test using baseball in s5 
		Perceptron perceptron1_1 = new Perceptron();   //new perceptron
		perceptron1_1.setKeyWordArray(keyWordArray1);  //set keywords
		perceptron1_1.setWeight(train1.getWeight());   //set coefficient
		perceptron1_1.setOriginalClass(IClassification.BASEBALL);  //set validation result
		perceptron1_1.testPerceptron(IPathName.test1_baseballPath);//set file path to be classified 
		
		System.out.println("ʹ��ѵ��һ�õ��ķ���������   baseball �ļ�������"+ perceptron1_1.getTotalNum() 
				+ "  ʶ����ȷ����" + perceptron1_1.getCorrectNum());  //print classification result
		
		//test using hockey in s5
		Perceptron perceptron1_2 = new Perceptron();
		perceptron1_2.setKeyWordArray(keyWordArray1);
		perceptron1_2.setWeight(train1.getWeight());
		perceptron1_2.setOriginalClass(IClassification.HOCKEY);
		perceptron1_2.testPerceptron(IPathName.test1_hockeyPath);
		
		System.out.println("ʹ��ѵ��һ�õ��ķ���������   hockey �ļ�������"+ perceptron1_2.getTotalNum() 
				+ "  ʶ����ȷ����" + perceptron1_2.getCorrectNum());
		
		
		/**
		 * test model using data s3
		 */
		//test baseball in data s3
		Perceptron perceptron2_1 = new Perceptron();
		perceptron2_1.setKeyWordArray(keyWordArray2);
		perceptron2_1.setWeight(train2.getWeight());
		perceptron2_1.setOriginalClass(IClassification.BASEBALL);
		perceptron2_1.testPerceptron(IPathName.test2_baseballPath);
		
		System.out.println("ʹ��ѵ�����õ��ķ���������   baseball �ļ�������"+ perceptron2_1.getTotalNum() 
				+ "  ʶ����ȷ����" + perceptron2_1.getCorrectNum());
		
		//test  hockey in data s3
		Perceptron perceptron2_2 = new Perceptron();
		perceptron2_2.setKeyWordArray(keyWordArray2);
		perceptron2_2.setWeight(train2.getWeight());
		perceptron2_2.setOriginalClass(IClassification.HOCKEY);
		perceptron2_2.testPerceptron(IPathName.test2_hockeyPath);
		
		System.out.println("ʹ��ѵ�����õ��ķ���������   hockey �ļ�������"+ perceptron2_2.getTotalNum() 
				+ "  ʶ����ȷ����" + perceptron2_2.getCorrectNum());
		
		System.out.println("������ɣ�");
		
	}
	
	
}
