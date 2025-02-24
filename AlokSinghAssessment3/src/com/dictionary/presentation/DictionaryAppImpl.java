package com.dictionary.presentation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.dictionary.exceptions.DuplicateEntryException;
import com.dictionary.persistance.DictionaryEntity;
import com.dictionary.service.DictionaryService;


/*
 * Create a program to depict the usage of the dictionary 
 * where words along with the meanings are stored. 
 * When the user gives a word, its meaning and all synonyms should be displayed.
 */



public class DictionaryAppImpl implements DictionaryApp {
	
	
    public void showDictionary() {
        Scanner scanner = new Scanner(System.in);
        
        DictionaryEntity dictionaryDB = new DictionaryEntity();

        DictionaryService service = new DictionaryService(dictionaryDB);

        while (true) {
            System.out.println("Dictionary Menu:");
            System.out.println("1. Add Word (for adding new words in dictionary)");
            System.out.println("2. Get Meaning OR Synonyms");
            System.out.println("3. Show All Words");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  

            switch (choice) {
                case 1:
                    System.out.print("Enter word: ");
                    String word = scanner.nextLine();
                    System.out.print("Enter meaning: ");
                    String meaning = scanner.nextLine();
                    System.out.print("Enter synonyms (SEPRATE THEM BY COMMAS): ");
                    String[] synonymArray = scanner.nextLine().split(",");
                    List<String> synonyms = new ArrayList<>();
                    for (String synWords : synonymArray) {
                        synonyms.add(synWords);
                    }
				try {
					service.addWord(word, meaning, synonyms);
				} catch (DuplicateEntryException e) {
					System.out.println("Duplicate entry for same word is not allowed!!!");
					break;
				}
                    System.out.println("Word added successfully!");
                    break;

                case 2:
                    System.out.print("Enter word to search: ");
                    String searchWord = scanner.nextLine();
                    List<String> details = service.getWordDetails(searchWord);
                    
                    // Synonyms and Meaning stored in List
                    if (details != null) {
                        System.out.println("Details for '" + searchWord + "':");
                        for (int i = 0; i < details.size(); i++) {
                            if (i == 0) {
                                System.out.println(details.get(i));  // Meaning
                            } else {
                                System.out.println("Synonym: " + details.get(i)); 
                            }
                        }
                    } else {
                        System.out.println("Word not found.");
                    }
                    break;

                case 3:
                    System.out.println(" All Words in Dictionary:");
                    Map<String, List<String>> allWords = service.getAllWords();
                    if (allWords.isEmpty()) {
                        System.out.println("Dictionary is empty.");
                    } else {
                        for (Map.Entry<String, List<String>> entry : allWords.entrySet()) {
                        	 System.out.println("Printing the data FROM DICTIONARY");
                            System.out.println( entry.getKey() + ": " + entry.getValue());
                        }
                    }
                    break;

                case 4:
                    System.out.println("Exiting dictionary. Goodbye!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}