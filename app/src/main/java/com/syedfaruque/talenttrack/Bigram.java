package com.syedfaruque.talenttrack;

/**
 * This Bigram class represents an individual bigram within a String to be encrypted or decrypted
 * The Bigram is a two letter substring from a string
 *
 * @author Syed Faruque
 *      e-mail:syed.faruque@stonybrook.edu
 *      Stony Brook ID: 116340094
 *      Recitation: CSE 214.R03
 */

public class Bigram{
    /**
     * attributes of a bigram. Literally just the first and second letter of the bigram
     */
    private char first;
    private char second;

    /**
     * creates a Bigram object with default values for the two letters
     */
    public Bigram() {
        first = '\0';
        second = '\0';
    }

    /**
     * params constructor that creates a Bigram object with given parameters for the two letters
     * @param first   the char holding the value to be assigned to the first letter
     * @param second   the char holding the value to be assigned to the second letter
     */
    public Bigram(char first, char second) {
        this.first = first;
        this.second = second;
    }

    /**
     * @return the first letter of a bigram
     */
    public char getFirst() {
        return first;
    }

    /**
     * @return the second letter of a bigram
     */
    public char getSecond() {
        return second;
    }

    /**
     * @param first   the char to be assigned to the first letter in the bigram
     */
    public void setFirst(char first) {
        this.first = first;
    }

    /**
     * @param second   the char to be assigned to the second letter in the bigram
     */
    public void setSecond(char second) {
        this.second = second;
    }

    /**
     * @return a stringified version of the Bigram. Basically just the two letters on one line
     */
    public String toString() {
        return (Character.toString(first) + Character.toString(second));
    }

}