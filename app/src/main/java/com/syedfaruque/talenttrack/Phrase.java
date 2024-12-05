package com.syedfaruque.talenttrack;

/**
 * This Phrase class represents the queue of Bigrams used to portray a String
 * This class contains utility for generating the queue of Bigrams given the string
 * This class contains utility for both encrypting and decrypting another Phrase
 *
 *
 * @author Syed Faruque
 *      e-mail:syed.faruque@stonybrook.edu
 *      Stony Brook ID: 116340094
 *      Recitation: CSE 214.R03
 */

public class Phrase extends LinkedListQueue{

    /**
     * Builds and returns a new Phrase object, which is a queue containing bigrams representing s
     * @param s   the String to represent as a Bigram queue
     * @return The new Phrase object which contains a queue of Bigram objects representing s
     */
    public static Phrase buildPhraseFromStringForEnc(String s) {
        s = s.toUpperCase().replaceAll("[^A-Z]", "").replace("J", "I");
        Phrase newPhrase = new Phrase();
        int numRepeatedBigrams = 0;
        for (int i = 0; i < s.length()-1; i+=2) {
            char first = s.charAt(i);
            char second = s.charAt(i+1);
            if (first == second) {
                numRepeatedBigrams++;
                second = 'X';
                i--;
            }
            newPhrase.enqueue(new Bigram(first, second));
        }
        if ((s.length()+numRepeatedBigrams)%2 != 0) {
            newPhrase.enqueue(new Bigram(s.charAt(s.length()-1), 'X'));
        }
        return newPhrase;
    }

    /**
     * Encrypts an individual bigram using a given key matrix
     * @param bigram   the bigram to be encrypted
     * @param key   the key matrix used to encrypt the bigram
     * @return the encrypted bigram
     */
    private Bigram encryptBigram(Bigram bigram, KeyTable key) {
        char first = bigram.getFirst();
        char second = bigram.getSecond();
        int firstCharRow = key.findRow(first);
        int secondCharRow = key.findRow(second);
        int firstCharCol = key.findCol(first);
        int secondCharCol = key.findCol(second);
        char[][] keyTable = key.getKeyTable();

        //case 1
        if (firstCharRow == secondCharRow) {
            first = keyTable[firstCharRow][(firstCharCol+1)%5];
            second = keyTable[secondCharRow][(secondCharCol+1)%5];
        }

        //case 2
        else if (firstCharCol == secondCharCol) {
            first = keyTable[(firstCharRow+1)%5][firstCharCol];
            second = keyTable[(secondCharRow+1)%5][secondCharCol];
        }

        //case 3
        else {
            first = keyTable[firstCharRow][secondCharCol];
            second = keyTable[secondCharRow][firstCharCol];
        }

        Bigram encryptedBigram = new Bigram(first, second);
        return encryptedBigram;
    }

    /**
     * Decrypts an individual bigram using a given key matrix
     * @param bigram   the bigram to be decrypted
     * @param key   the key matrix used to decrypt the bigram
     * @return the decrypted bigram
     */
    private Bigram decryptBigram(Bigram bigram, KeyTable key) {
        char first = bigram.getFirst();
        char second = bigram.getSecond();
        int firstCharRow = key.findRow(first);
        int secondCharRow = key.findRow(second);
        int firstCharCol = key.findCol(first);
        int secondCharCol = key.findCol(second);
        int updatedFirstCharRow = (firstCharRow > 0) ? (firstCharRow - 1) : 4;
        int updatedSecondCharRow = (secondCharRow > 0) ? (secondCharRow - 1) : 4;
        int updatedFirstCharCol = (firstCharCol > 0) ? (firstCharCol - 1) : 4;
        int updatedSecondCharCol = (secondCharCol > 0) ? (secondCharCol - 1) : 4;
        char[][] keyTable = key.getKeyTable();

        //case 1
        if (firstCharRow == secondCharRow) {
            first = keyTable[firstCharRow][updatedFirstCharCol];
            second = keyTable[secondCharRow][updatedSecondCharCol];
        }

        //case 2
        else if (firstCharCol == secondCharCol) {
            first = keyTable[updatedFirstCharRow][firstCharCol];
            second = keyTable[updatedSecondCharRow][secondCharCol];
        }

        //case 3
        else {
            first = keyTable[firstCharRow][secondCharCol];
            second = keyTable[secondCharRow][firstCharCol];
        }
        Bigram decryptedBigram = new Bigram(first, second);
        return decryptedBigram;
    }


    /**
     * Encrypts this Phrase, storing the encrypted bigrams in a new Phrase queue object and returning it
     * @param key   the KeyTable to use to encrypt this Phrase
     * @custom.precondition key is not null
     * @returns The new Phrase object which contains a queue of Bigram objects representing the encrypted version of this Phrase
     * @throws IllegalArgumentException if key is null.
     */
    public Phrase encrypt(KeyTable key) throws IllegalArgumentException {
        if (key == null) {
            throw new IllegalArgumentException("the key is null");
        }
        Phrase encryptedPhrase = new Phrase();
        while (!isEmpty()) {
            Bigram bigram = dequeue();
            encryptedPhrase.enqueue(encryptBigram(bigram, key));
        }
        return encryptedPhrase;
    }


    /**
     * Decrypts this Phrase, storing the decrypted bigrams in a new Phrase queue object and returning it
     * @param key   the KeyTable to use to decrypt this Phrase
     * @custom.precondition key is not null
     * @returns The new Phrase object which contains a queue of Bigram objects representing the decrypted version of this Phrase
     * @throws IllegalArgumentException if key is null.
     */
    public Phrase decrypt(KeyTable key) throws IllegalArgumentException {
        if (key == null) {
            throw new IllegalArgumentException("the key is null");
        }
        Phrase decryptedPhrase = new Phrase();
        while (!isEmpty()) {
            Bigram bigram = dequeue();
            decryptedPhrase.enqueue(decryptBigram(bigram, key));
        }
        return decryptedPhrase;
    }

    /**
     * @returns a String representation of the Phrase formed by the collection of Bigrams
     */
    public String toString() {
        Phrase temp = new Phrase();
        String stringifiedPhrase = "";
        while (!isEmpty()) {
            Bigram bigram = dequeue();
            temp.enqueue(bigram);
            stringifiedPhrase += bigram.toString();
        }
        while (!temp.isEmpty()) {
            enqueue(temp.dequeue());
        }
        return stringifiedPhrase;
    }



}