package code;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.sun.jna.Library;
import com.sun.jna.Native;

/**
 * function:
 * 		word segmentation NLPIR
 * @author Jiajun Jiang
 *
 */

public class NLPIR {

	/**
	 * define CLibrary interface，extends com.sun.jna.Library
	 * @author Jiajun Jiang
	 *
	 */
	public interface CLibrary extends Library {
		// define and initialize static variables and load dynamic link libraries
		// the path of .dll can be absolute or relative
		// name needed but suffix
		
		CLibrary Instance = (CLibrary) Native.loadLibrary( 
				System.getProperty("user.dir")+"\\win32\\NLPIR", CLibrary.class);
		
		//initialization
		public int NLPIR_Init(String sDataPath, int encoding,
				String sLicenceCode);
		//execute word splitting
		public String NLPIR_ParagraphProcess(String sSrc, int bPOSTagged);

		//extract keywords from file
		public Double NLPIR_FileProcess(String sSourceFilename,String sResultFilename,int bPOStagged);
		
		//extract keywords from string
		public String NLPIR_GetKeyWords(String sLine, int nMaxKeyLimit,
				boolean bWeightOut);
		//add user-defined keywords
		public int NLPIR_AddUserWord(String sWord);
		
		//delete user-defined keywords
		public int NLPIR_DelUsrWord(String sWord);
		
		//exit
		public void NLPIR_Exit();
	}


	/**
	 * function:
	 * 			file process and write result into file
	 * @throws Exception
	 */
	public static void preHandle() throws Exception {
		String argu = System.getProperty("user.dir");
		int charset_type = 0;
		//initialize
		int init_flag = CLibrary.Instance.NLPIR_Init(argu, charset_type, "0");

		if (0 == init_flag) {
			System.err.println("初始化失败！");
			return;
		}
		
		//files to be processed
		String[] sourcePath = {"data1\\s1\\baseball\\",
								"data1\\s1\\hockey\\",
								"data1\\s2\\baseball\\",
								"data1\\s2\\hockey\\",
								"data1\\s3\\baseball\\",
								"data1\\s3\\hockey\\",
								"data1\\s4\\baseball\\",
								"data1\\s4\\hockey\\",
								"data1\\s5\\baseball\\",
								"data1\\s5\\hockey\\"};
		//files to store the result
		String[] resultPath = {"predata1\\s1\\baseball\\",
								"predata1\\s1\\hockey\\",
								"predata1\\s2\\baseball\\",
								"predata1\\s2\\hockey\\",
								"predata1\\s3\\baseball\\",
								"predata1\\s3\\hockey\\",
								"predata1\\s4\\baseball\\",
								"predata1\\s4\\hockey\\",
								"predata1\\s5\\baseball\\",
								"predata1\\s5\\hockey\\"};

		try {
			//split words and write into file
			ReadDirector rd=new ReadDirector();
			for(int cyc = 0; cyc < sourcePath.length; cyc ++){
			    List<String> filenames=rd.getFiles(sourcePath[cyc]);  // get all files in given directory
			    for(int i =0;i<filenames.size();i++){
			    	String str=(String)filenames.get(i);
					File file = new File(resultPath[cyc],"pre"+str);   //write result into file
			        if(!file.exists()) {                               //create new file if not exists
			        	file.getParentFile().mkdirs();
			        	try{
			        		file.createNewFile();
			            } catch (IOException ioe) {
			            	ioe.printStackTrace();
			        	}
			        }
			        //invoke process function and write result into given file
			        CLibrary.Instance.NLPIR_FileProcess(sourcePath[cyc]+str, resultPath[cyc]+"pre"+str, 1);      
			    }
			}

			CLibrary.Instance.NLPIR_Exit();

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}
	
//	public static void main(String[] args) {
//		try {
//			NLPIR.preHandle();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

}
