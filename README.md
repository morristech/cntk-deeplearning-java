# Deep learning in Java with Microsoft Cognitive Toolkit
This sample demonstrates how to train a model in Python and use the same model from a Java application.
The goal is to recognize handwritten hand digits (who hasn't done this before?) through a basic REST API.

## What's in the code?
There are two folders in this repository:

 * training - Contains the code to train the model as a Python notebook
 * service - Contains a Spring Boot application that uses the trained model

## Quickstart guide
Follow the steps in the sections below to setup the necessary libraries on your machine for this sample.
Make sure you clone this repository to properly view and edit the sources.

### Setting up CNTK
For the sample to work you need to have the Java library for CNTK installed on your machine.
Follow these basic steps to setup CNTK on your machine.

1. [Download a binary release of CNTK For your operating system](https://github.com/Microsoft/CNTK/releases)
2. Unpack the Zip file on your disk
3. Locate cntk.jar (usually in the `cntk/cntk/java` folder)
4. Open a terminal and navigate to the location of the `cntk.jar` file
5. Run the command `mvn install:install-file -Dfile="cntk.jar" -DgroupId="com.microsoft.cntk" -DartifactId=cntk -Dversion="2.5.1" -Dpackaging=jar`.

**Please make sure that you use the correct version number!**

### Setting up Python
Follow these steps to configure Python for this sample:

1. [Download and install the latest release of Anaconda](https://www.anaconda.com/download/)
2. Open the Anaconda prompt or a terminal if you are on Linux
3. Execute the following command `pip install cntk` (or `pip install cntk-gpu` if you have a CUDA compatible graphics card)

### Edit the training code
The sample code for training the model is written in Python as a Python notebook. 
Follow these steps to start the Python notebook server to access the code.

1. Open the Anaconda prompt or a terminal if you are on Linux
2. Navigate to where you cloned this repository
3. Execute the command `jupyter notebook`

A new webpage should be opened in your standard browser. Select the notebook in the 
`training` folder from this webpage to view the training code.

### Edit the Java code
You can edit the Java code in any of your favorite IDEs. I personally configured IntelliJ for this project.
But the code can be edited in Eclipse, Netbeans or any proper programming editor such as Visual Studio Code.

## Issues/Ideas/Comments
Please use the issue tracker for any issues you have with this code.
Or ask me questions on Twitter: [@willem_meints](https://twitter.com/willem_meints)
