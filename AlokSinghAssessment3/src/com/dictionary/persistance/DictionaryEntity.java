package com.dictionary.persistance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dictionary.exceptions.DuplicateEntryException;

public class DictionaryEntity {
	private final Map<String, List<String>> dictionary;

    public DictionaryEntity() {
        this.dictionary = new HashMap<>();
    }

    public void saveWord(String word, String meaning, List<String> synonyms) throws DuplicateEntryException {
        String key = word.toLowerCase();
        
        // Check if word already exists
        if (dictionary.containsKey(key)) {
            throw new DuplicateEntryException();
        }
        
        
        

        // Store word with meaning and synonyms
        List<String> details = new ArrayList<>();
        details.add("Meaning: " + meaning);
        details.addAll(synonyms);
        dictionary.put(key, details);
    }


    public List<String> getWordDetails(String word) {
        return dictionary.getOrDefault(word.toLowerCase(), null);
    }

    public Map<String, List<String>> getAllWords() {
        return dictionary;
    }
}



    