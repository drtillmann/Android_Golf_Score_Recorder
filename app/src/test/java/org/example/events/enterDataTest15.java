package org.example.events;

import org.junit.Test;

import static org.junit.Assert.*;

public class enterDataTest15 {

    @Test
    public void onCreate() {
    }

    @Test
    public void onClick() {

    }

    @Test
    public void checkInputTest(){

        String name = "Geneva National", putts = "30", penalties = "5", score = "75", playerName = "John Smith";
        boolean result, expected = true;

        enterData enter = new enterData();
        result = enter.checkInput(name, putts, penalties, score, playerName);
        assertEquals(expected, result);

    }

    @Test
    public void isDigitTestPass() {
        String input = "5";
        boolean result,  expected = true;

        enterData enter = new enterData();
        result = enter.isDigit(input);
        assertEquals(expected, result);
    }

    @Test
    public void convertToIntTestPass() {
        String input = "5";
        int result, expected = 5;

        enterData enter = new enterData();
        result = enter.convertToInt(input);
        assertEquals(expected, result);
    }
}