package code;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * function:
 * 		keyword process, including keyword extraction and frequency computation
 * time:
 * 		2014.5.15
 * @author Jiajun Jiang
 * 
 */

public class GetKeyWord {
	
	/**
	 * function: 
	 * 			extract words from string, during which delete useless words
	 * @param txt	(String)
	 * 			string to be processed, which should be segmented with words
	 * @return mList	(List<String>)
	 * 			word list contain all the words in the given string
	 */
	private static List<String> getTextWords(String txt){
		List<String> mList = new ArrayList<String>();
		
		int index = 0;
		while((index = txt.indexOf("/"))>0){
			if(txt.charAt(index-1) == '~'
				||txt.charAt(index+1) == 'w'  //sign words     //this word cannot be the keyword
//				||txt.charAt(index+1) == 'o'  //echoic
				||txt.charAt(index+1) == 'y'  //modal
				||txt.charAt(index+1) == 'e'  //̾interjection
				||txt.charAt(index+1) == 'u'  //auxiliary word
				||txt.charAt(index+1) == 'c'  //conj.
				||txt.charAt(index+1) == 'p'  //pre.
				||txt.charAt(index+1) == 'q'  //quantifier
				||txt.charAt(index+1) == 'm'  //numeral
				||txt.charAt(index+1) == 'r') //pro.
			{
				int start = txt.indexOf(" ")+1;
				if(start == 0) break;
				txt = txt.substring(start);
				continue;
			}else{
				String subStr = txt.substring(0, index);
				
				if(subStr.equalsIgnoreCase("is")  //these words cannot be keywords as well
					||subStr.equalsIgnoreCase("are")
					||subStr.equalsIgnoreCase("was")
					||subStr.equalsIgnoreCase("were")
					||subStr.equalsIgnoreCase("From")
					||subStr.equalsIgnoreCase("subject")
					||subStr.equalsIgnoreCase("has")
					||subStr.equalsIgnoreCase("have")
					||subStr.length() == 1){
						int start = txt.indexOf(" ")+1;
						if(start == 0) break;
						txt = txt.substring(start);
						continue;
					}else{
						mList.add(subStr);
						int start = txt.indexOf(" ")+1;
						if(start == 0) break;
						txt = txt.substring(start);
					}
			}
		}
		
		return mList;
	}
	
	/**
	 * function: 
	 * 			get top five keywords in given file
	 * @param fileName	(String)
	 * 			file name, which contains complete path
	 * @return mList	(List<String>)
	 * 			list of keywords
	 */
	private static List<String> getFileKeyWords(String fileName){
		List<String> mList = new ArrayList<String>();
		//keywords and their frequencies
		HashMap<String, Integer> wordsMap = new HashMap<String, Integer>(); 
		File file = new File(fileName);
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String txt = null;
			//extract keywords
			while((txt = br.readLine())!=null)
			{
				List<String> tempList = getTextWords(txt);
				for(String word : tempList){
					if(wordsMap.containsKey(word)){
						//duplicate word, increase the frequency
						wordsMap.put(word, wordsMap.get(word)+1);
					}else{
						//first appear keyword
						wordsMap.put(word, 1);
					}
				}
			}
			br.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//convert HashMap to List, in order to sort by frequency
		List<Entry<String,Integer>> list =
				new ArrayList<Entry<String,Integer>>(wordsMap.entrySet());

		//sort list by keywords' frequencies
		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Map.Entry<String, Integer> o1,
					Map.Entry<String, Integer> o2) {
				return (o2.getValue() - o1.getValue());
			}
		});
		
		//get top 5 keywords
		for(int i = 0; i < 5 && i < list.size(); i++){
			mList.add(list.get(i).getKey());
		}
		
		return mList;
	}
	
	/**
	 * function:
	 * 			get all keywords in the given path
	 * @param dirName	(String)
	 * 			file path
	 * @return mList	(List<String>)
	 * 			list of keywords
	 */
	private static List<String> getDirKeyWord(String dirName){
		List<String> mList = new ArrayList<String>();
		ReadDirector rdir = new ReadDirector();
		//get files in the directory
		List<String> filenames=rdir.getFiles(dirName);
		//extract keywords for each file
	    for(int i =0;i<filenames.size();i++){
	    	String str=(String)filenames.get(i);
	    	List<String> tempList;
	    	tempList = getFileKeyWords(dirName+str);
	    	mList.addAll(tempList);
	   	}
		return mList;
	}
	
	/**
	 * function:
	 * 			get frequency of given keywords, the index should be consistent
	 * @param keyWordArray	(String[])
	 * 			list of keywords to be query
	 * @param fileName	(String)
	 * 			file to be calculated, which contains complete path
	 * @return frequency	(int[])
	 * 			frequencies for each keywords
	 */
	public static int[] getKeyWordFrequency(String[] keyWordArray, String fileName){

		//frequencies
		int[] frequency = new int[keyWordArray.length];
		try {
			BufferedReader freader = new BufferedReader(new FileReader(new File(fileName)));
			String txt = null;
			//read string from file and query keywords
			while((txt = freader.readLine()) != null){
				List<String> wordsList = GetKeyWord.getTextWords(txt);
				for(String word:wordsList){
					//compare the given keywords and those in file
					for(int i = 0; i < keyWordArray.length; i++){
						if(word.equalsIgnoreCase(keyWordArray[i])){
							frequency[i] ++;
							break;
						}
					}
				}
			}
			freader.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return frequency;

	}
	
	/**
	 * function:
	 * 			get all keywords of given directory
	 * @param dirList	(String[])
	 * 			list of directory
	 * @return mlist	(List<String>)
	 * 			list of keywords
	 */
	public static List<String> getKeyWords(String[] dirList) {
		
		List<String> mlist = new ArrayList<>();
		//get all directory
		for(int dir_num = 0; dir_num < dirList.length; dir_num++){
			//all keywords for a directory
			List<String> keyWordList = getDirKeyWord(dirList[dir_num]);
			//store each keywords with filtering the duplicate ones
			for(String keyword : keyWordList){
				if(!mlist.contains(keyword)){
					mlist.add(keyword);
				}
			}
		}
		
		return mlist;
	}

}
