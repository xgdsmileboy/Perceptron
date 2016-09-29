package code;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * function:
 * 		get all files in given directory
 * time:
 * 		2014.5.15
 * @author Jiajun Jiang
 *
 */
public class ReadDirector {

	/**
	 * function:
	 * 			get all files in given directory
	 * @param dirName	(String)
	 * 			directory name
	 * @return fileNameList	(List<String>)
	 * 			files in the given diretory
	 */
	public List<String> getFiles(String dirName){
		
	   File dir=new File(dirName);
	   File []files=dir.listFiles();
	   List<String> fileNameList=new ArrayList<String>();
	   //get all visible files
	   for(int i=0;i<files.length;i++){
		    if(files[i].isFile()&&(!files[i].isHidden())){
		     fileNameList.add(files[i].getName());
		    }
	   }
	   return fileNameList;
	}

}

