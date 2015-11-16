/*
 * Author: Abhishek Naik
 * Date: 2/1/2014
 * Class: CS 280 section 004
 * Purpose: Search for pattern within input or a file
 */

/*****************************************************************************************
 * This class has three different ways to create an object:
 * 1. PatternMatching(String pat)
 * 2. PatternMatching(String Pat, String option2)
 * 3. PatternMatching(String pat, String option2, String option3)
 * 
 * The minimum requirement for this class to work is a pattern. The user has option to
 * include a file name, or a context that must follow the condition that it begins with
 * +n where n is a positive number between 1 and 10. The user can also include all three
 * of the things. 
 * 
 * In case where the user enters just a pattern, it will prompt the user to enter a
 * few lines, and press 0 to stop adding lines. 
 * In case where the user enters a file name, it will open and read the file content.
 * 
 * The user has to option to include a number, which will be the number of lines above, and
 * below the matched line that will be printed onto the screen.
 * 
 * There are four methods in the program:
 * 1. input()		--> if no file name was included then prompt user to enter a few lines
 * 2. matchPattern	--> Will start looking for the pattern within the content
 * 3. output()		--> Print the lines prior to and after the matched line
 * 4. readFile()	--> if a file name was provided, then read the file into content
 * 
 * There was a setter added so can change pattern and search for it within the same
 * content, or can change the file name to search for the pattern within the new content.
 * You must commend read file after setting the file name to update the search content.
 *****************************************************************************************/

import java.io.*;
import java.util.*;
public class PatternMatching{
	private String pat;							  //This is the pattern that will be searched for
	Elist <String> content = new Elist <String>();//This will be used to store all the content of file or data
	private int context;						  //The number of lines to be printed prior to and after the match
	private String fileName;					  //Name of file to be opened and processed
	
	PatternMatching(String pat)
	{
		
		this.pat = pat;		//set pat to pat
		input();			//call the input method, explained in the main comment above
	}
	
	PatternMatching(String pat, String option2) throws FileNotFoundException
	{
		this.pat = pat;						//set pat to pat
		if (option2.charAt(0) == '+')		//if first char in option 2 is + 
		{									//then the user has entered context
			this.context = Integer.parseInt(option2); //therefore set option2 to context
			if (context < 0 || context > 10) //if the number is invalid
			{
				System.out.println("Context cannot be less 1 and 10"); //send error message
				System.exit(0); 			//and end the program 
			}
			input();				//if no error was found then prompt for input
		}
		else						//if the option2 was not ment to be context
		{							//then it must have been a file.
			this.fileName = option2;	//set option2 to fileName
			this.context = 0;			//set context to 0
			readFile();					//call readfile method to fill up the content
		}
	}
	
	PatternMatching(String pat, String option2, String option3) throws FileNotFoundException
	{
		this.pat = pat;						//set pat to pat
		if (option2.charAt(0) == '+')		//if the first char in string is '+' then its 
		{									//a number we have to set to context
			this.context = Integer.parseInt(option3);
			this.fileName = option3;
			if (context < 0 || context > 10)
			{
				System.out.println("Context cannot be less 1 and 10");
				System.exit(0);
			}
			readFile();
		}
		else
		{
			this.fileName = option2;
			this.context = Integer.parseInt(option3);
			readFile();
		}
	}
	
	void input()
	{
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter lines and enter 0 when ready to stop:");
		while(scan.hasNext())
		{
			String line = scan.nextLine();
			if (line.equals("0"))
				break;
			content.addToEnd(line);
		}
		
		scan.close();
	}
	
	void readFile() throws FileNotFoundException
	{
		File file = new File(fileName);
		Scanner inFile = new Scanner(file);
		
		while(inFile.hasNext())
		{
			String x = inFile.nextLine();	
			content.addToEnd(x);
		}
		inFile.close();
	}	
	
	void matchPattern()
	{
		if ((pat.contains("\\.") || !(pat.contains("."))))	//if the string has a \. and not just a .
		{
			pat = pat.replace("\\.", ".");				 	//replace the \. with . to remove the \
			for (int i = 0; i < content.size(); i++)		//loop through the content 
			{
				if(content.get(i).contains(pat))			//if the pattern is found
				{
					output(i);								//output is called to print the lines
				}
			}
		}
		else
		{
			int temp = pat.indexOf('.');					//findout the index of '.'
			for(int i = 32; i < 127; i++)					//loop through each character from ascii table
			{												//replace the string to each character one by one.
				pat = pat.substring(0, temp) + (char)i + pat.substring(temp+1); 
				for (int j = 0; j < content.size(); j++)
				{
					if(content.get(j).contains(pat))
					{
						output(j);
					}
				}
			}
		}
	}
	
	void output(int index)
	{
		if (index - context < 0)
		{
			for(int i = 0; i <= index + context; i++)
			{
				System.out.println(content.get(i));
				if(i == content.size()-1)
					break;
			}
		}
		else
		{
			for(int i = index - context; i <= index + context; i++)
			{
				System.out.println(content.get(i));
				if(i == content.size()-1)
					break;
			}
		}
	}

	public String getPat() {
		return pat;
	}

	public void setPat(String pat) {
		this.pat = pat;
	}

	public int getContext() {
		return context;
	}

	public void setContext(int context) {
		this.context = context;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}