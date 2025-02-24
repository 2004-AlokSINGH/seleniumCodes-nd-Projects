package com.dictionary.service;

import java.util.List;
import java.util.Map;

import com.dictionary.exceptions.DuplicateEntryException;
import com.dictionary.persistance.DictionaryEntity;

public class DictionaryService {
    private final DictionaryEntity dictionaryDB;

    public DictionaryService(DictionaryEntity repository) {
        this.dictionaryDB = repository;
    }

    public void addWord(String word, String meaning, List<String> synonyms) throws DuplicateEntryException {
    	dictionaryDB.saveWord(word, meaning, synonyms);
    }

    public List<String> getWordDetails(String word) {
        return dictionaryDB.getWordDetails(word);
    }

    public Map<String, List<String>> getAllWords() {
        return dictionaryDB.getAllWords();
    }
}