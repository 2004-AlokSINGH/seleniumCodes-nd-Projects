package com.dictionary.client;

import com.dictionary.presentation.DictionaryAppImpl;

/*
 * Create a program to depict the usage of the dictionary 
 * where words along with the meanings are stored. 
 * When the user gives a word, its meaning and all synonyms should be displayed.
 */


/*
 * creating a hashmap of String , List to store the user values
 * after storing them implementing get method to get the word details
 *  form the the dictionary map 
 */

public class MainClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DictionaryAppImpl dictionaryApp = new DictionaryAppImpl();
		dictionaryApp.showDictionary();
		

	}

}
