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
/*  software platform��  myeclipse 2014                    */
/*                                                         */
/***********************************************************/
1.Run the program
  Copy this folder to a place you want,then using the software eclipse or myeclipse import this project into workspace.The main function is in the file Main.java.

2.Function of the project
  The project uses the Perceptron algorithm and with the training examples establishing a simple linear text classifier classifying the text between hockey and baseball.

3.Project architecture
  There are five folders s1-s5, which are the original file that had been classified and placed into different folders; While running the project, there will be three new folders created,predata1,keyword and weight.In the predata1,there are five folders just like the original five folders s1-s5,which are the pre-processed datasets.The tree graphic of the file system as bellow:

����.settings            ---------property files of the project
����bin                  ---------class files
��  ����code
��  ����utils
����Data                 ---------words dictionary
��  ����English
����data1                ---------original datasets
��  ����s1
��  ��  ����baseball
��  ��  ����hockey
��  ����s2
��  ��  ����baseball
��  ��  ����hockey
��  ����s3
��  ��  ����baseball
��  ��  ����hockey
��  ����s4
��  ��  ����baseball
��  ��  ����hockey
��  ����s5
��      ����baseball
��      ����hockey
����keyword              -----------the key word selected from the training datasets
����lib                  -----------lib library
����predata1             -----------pre-processed data set from original datasets
��  ����s1
��  ��  ����baseball
��  ��  ����hockey
��  ����s2
��  ��  ����baseball
��  ��  ����hockey
��  ����s3
��  ��  ����baseball
��  ��  ����hockey
��  ����s4
��  ��  ����baseball
��  ��  ����hockey
��  ����s5
��      ����baseball
��      ����hockey
����src                  ------------source code
��  ����code
��  ����utils
����weight               ------------weights records while training
����win32                ------------dll,used for pre-process the datasets
                         ------------from Pro. Huaping Zhang of Beijing University

