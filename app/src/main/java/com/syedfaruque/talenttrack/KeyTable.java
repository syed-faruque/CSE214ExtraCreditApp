package com.syedfaruque.talenttrack;

/**
 * This KeyTable class holds the key table generated from the key phrase.
 * This class contains utility for generating a new matrix table from a key phrase
 * This class also contains utility for finding information related to the key table
 *
 * @author Syed Faruque
 *      e-mail:syed.faruque@stonybrook.edu
 *      Stony Brook ID: 116340094
 *      Recitation: CSE 214.R03
 */


public class KeyTable {
    /**
     * attribute of the KeyTable. The 5 by 5 matrix holding the KeyTable that is used to encrypt and decrypt
     */
    private char[][] key;

    /**
     * default constructor which creates a KeyTable with a default 5 by 5 matrix
     */
    public KeyTable(){
        key = new char[5][5];
    }

    /**
     * Builds a new KeyTable object from the provided string and returns it
     * @param keyPhrase   The String to use as the key
     * @custom.precondition keyPhrase is not null
     * @returns The new KeyTable object
     * @throws IllegalArgumentException if keyPhrase is null.
     */
    public static KeyTable buildFromString(String keyPhrase) throws IllegalArgumentException{
        if (keyPhrase == null) {
            throw new IllegalArgumentException("keyPhrase can not be null");
        }
        KeyTable newKeyTable = new KeyTable();
        String noDuplicates = "";
        String remainingAlphabet = "ABCDEFGHIKLMNOPQRSTUVWXYZ";
        int rowCount = 0;
        int colCount = 0;
        keyPhrase = keyPhrase.toUpperCase().replaceAll("[^A-Z]", "").replaceAll(" ", "");
        for (int i = 0; i < keyPhrase.length(); i++) {
            String currentLetter = keyPhrase.substring(i, i+1);
            if (noDuplicates.indexOf(currentLetter) == -1) {
                noDuplicates += currentLetter;
                remainingAlphabet = remainingAlphabet.replace(currentLetter, "");
                if (colCount == 5) {
                    rowCount++;
                    colCount = 0;
                }
                if (rowCount < 5) {
                    newKeyTable.key[rowCount][colCount] = currentLetter.charAt(0);
                    colCount++;
                }
            }
        }
        for (int i = 0; i < remainingAlphabet.length(); i++) {
            if (colCount == 5) {
                rowCount++;
                colCount = 0;
            }
            if (rowCount < 5) {
                newKeyTable.key[rowCount][colCount] = remainingAlphabet.charAt(i);
                colCount++;
            }
        }
        return newKeyTable;
    }

    /**
     * Returns the key matrix
     * @return the key matrix
     */
    public char[][] getKeyTable() {
        return key;
    }

    /**
     * Finds and returns the location of a char within the key matrix in an array representation
     * @param c   the char to be located within the key matrix
     * @return an array holding the row and column in which the char was found. First element is row, second column
     * @throws IllegalArgumentException if the char is not a valid letter within the key matrix
     */
    private int[] findCharLocation(char c) throws IllegalArgumentException{
        int[] location = new int[2];
        boolean found = false;
        for (int i = 0; i < key.length; i++) {
            for (int j = 0; j < key[i].length; j++) {
                if (key[i][j] == c) {
                    location[0] = i;
                    location[1] = j;
                    found = true;
                }
            }
        }
        if (!found) {
            throw new IllegalArgumentException("the provided argument is not a valid letter");
        }
        return location;
    }

    /**
     * Returns the row in which c occurs
     * @param c   The character to locate within the key matrix
     * @custom.precondition c is a valid letter
     * @return The index of the row in which c occurs
     * @throws IllegalArgumentException if c is not a valid letter in the key matrix
     */
    public int findRow(char c) throws IllegalArgumentException{
        int row = findCharLocation(c)[0];
        return row;
    }

    /**
     * Returns the column in which c occurs
     * @param c   The character to locate within the key matrix
     * @custom.precondition c is a valid letter
     * @return The index of the column in which c occurs
     * @throws IllegalArgumentException if c is not a valid letter in the key matrix
     */
    public int findCol(char c) throws IllegalArgumentException{
        int col = findCharLocation(c)[1];
        return col;
    }

    /**
     * @return a stringified version of the KeyTable
     */
    public String toString() {
        String keyMatrixRepresentation = "";
        for (int i = 0; i < key.length; i++) {
            for (int j = 0; j < key[i].length; j++) {
                keyMatrixRepresentation+=key[i][j]+" ";
            }
            if (i < key.length - 1) {
                keyMatrixRepresentation += "\n";
            }
        }
        return keyMatrixRepresentation;
    }
}