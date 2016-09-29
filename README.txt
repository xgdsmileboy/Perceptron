/***********************************************************/
/*    Project: Perceptron classifier                       */
/*     Author:  Jiajun Jiang                               */
/*              School of Computer Science                 */
/*              NorthWestern Polytechnical University      */
/*       Date:  16th May 2014                              */              
/***********************************************************/
/*                                                         */
/*           Development Environment                       */
/*                                                         */
/*   operating system:   windows 8[6.2.9200]               */
/*   develop language:   java                              */
/*  software platform£º  myeclipse 2014                    */
/*                                                         */
/***********************************************************/
1.Run the program
  Copy this folder to a place you want,then using the software eclipse or myeclipse import this project into workspace.The main function is in the file Main.java.

2.Function of the project
  The project uses the Perceptron algorithm and with the training examples establishing a simple linear text classifier classifying the text between hockey and baseball.

3.Project architecture
  There are five folders s1-s5, which are the original file that had been classified and placed into different folders; While running the project, there will be three new folders created,predata1,keyword and weight.In the predata1,there are five folders just like the original five folders s1-s5,which are the pre-processed datasets.The tree graphic of the file system as bellow:

©À©¤.settings            ---------property files of the project
©À©¤bin                  ---------class files
©¦  ©À©¤code
©¦  ©¸©¤utils
©À©¤Data                 ---------words dictionary
©¦  ©¸©¤English
©À©¤data1                ---------original datasets
©¦  ©À©¤s1
©¦  ©¦  ©À©¤baseball
©¦  ©¦  ©¸©¤hockey
©¦  ©À©¤s2
©¦  ©¦  ©À©¤baseball
©¦  ©¦  ©¸©¤hockey
©¦  ©À©¤s3
©¦  ©¦  ©À©¤baseball
©¦  ©¦  ©¸©¤hockey
©¦  ©À©¤s4
©¦  ©¦  ©À©¤baseball
©¦  ©¦  ©¸©¤hockey
©¦  ©¸©¤s5
©¦      ©À©¤baseball
©¦      ©¸©¤hockey
©À©¤keyword              -----------the key word selected from the training datasets
©À©¤lib                  -----------lib library
©À©¤predata1             -----------pre-processed data set from original datasets
©¦  ©À©¤s1
©¦  ©¦  ©À©¤baseball
©¦  ©¦  ©¸©¤hockey
©¦  ©À©¤s2
©¦  ©¦  ©À©¤baseball
©¦  ©¦  ©¸©¤hockey
©¦  ©À©¤s3
©¦  ©¦  ©À©¤baseball
©¦  ©¦  ©¸©¤hockey
©¦  ©À©¤s4
©¦  ©¦  ©À©¤baseball
©¦  ©¦  ©¸©¤hockey
©¦  ©¸©¤s5
©¦      ©À©¤baseball
©¦      ©¸©¤hockey
©À©¤src                  ------------source code
©¦  ©À©¤code
©¦  ©¸©¤utils
©À©¤weight               ------------weights records while training
©¸©¤win32                ------------dll,used for pre-process the datasets
                         ------------from Pro. Huaping Zhang of Beijing University

