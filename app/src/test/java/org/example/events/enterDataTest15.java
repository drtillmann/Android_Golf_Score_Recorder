package org.example.events;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.test.suitebuilder.annotation.LargeTest;
import org.junit.Test;

import static org.junit.Assert.*;

@LargeTest
public class enterDataTest15 {

    private enterData enter = new enterData();
    private SQLiteDatabase db;

  /*  @Test
    public void onCreate() {
    }

    @Test
    public void onClick() {

    }
*/
    @Test
    public void checkInputTestPass(){

        String courseName = "White Eagle", numPutts = "30", numPenalties = "5", numScore = "75", playerName = "John Smith";
        boolean result, expected = true;

        result = enter.checkInput(courseName, numPutts, numPenalties, numScore, playerName);

        assertEquals(expected, result);
    }

    @Test
    public void checkInputTestFailPuttBelowZero(){

        String courseName = "White Eagle", numPutts = "-1", numPenalties = "5", numScore = "75", playerName = "John Smith";
        boolean result, expected = false;

        result = enter.checkInput(courseName, numPutts, numPenalties, numScore, playerName);

        assertEquals(expected, result);
    }

    @Test
    public void checkInputTestFailPuttAboveNinetynine(){
        String courseName = "White Eagle", numPutts = "100", numPenalties = "5", numScore = "75", playerName = "John Smith";
        boolean result, expected = false;

        result = enter.checkInput(courseName, numPutts, numPenalties, numScore, playerName);
        assertEquals(expected, result);
    }

    @Test
    public void checkInputTestFailPenaltyBelowZero(){
        String courseName = "White Eagle", numPutts = "30", numPenalties = "-1", numScore = "75", playerName = "John Smith";
        boolean result, expected = false;

        result = enter.checkInput(courseName, numPutts, numPenalties, numScore, playerName);
        assertEquals(expected, result);
    }

    @Test
    public void checkInputTestFailPenaltyAboveFifty(){
        String courseName = "White Eagle", numPutts = "30", numPenalties = "51", numScore = "75", playerName = "John Smith";
        boolean result, expected = false;

        result = enter.checkInput(courseName, numPutts, numPenalties, numScore, playerName);
        assertEquals(expected, result);
    }

    @Test
    public void isDigitTestPass() {
        String input = "5";
        boolean result,  expected = true;

        result = enter.isDigit(input);
        assertEquals(expected, result);
    }

    @Test
    public void isDigitTestFail() {
        String input = "a";
        boolean result,  expected = false;

        result = enter.isDigit(input);
        assertEquals(expected, result);
    }

    @Test
    public void convertToIntTestPass() {
        String input = "5";
        int result, expected = 5;

        result = enter.convertToInt(input);
        assertEquals(expected, result);
    }

    @Test
    public void convertToIntTestFail() {
        String input = "a";
        int result, expected = 0;

        result = enter.convertToInt(input);
        assertEquals(expected, result);
    }

    @Test
    public void createSQLiteCreateTable(){
        boolean result, expected = true;

        result = enter.connectSQLiteTable();
        assertEquals(expected, result);
    }

   @Test
    public void testRecordInsertion() throws SQLException{
        String courseName = "White Eagle", numPutts = "28", numPenalties = "5", numScore = "76", playerName = "John Smith";
        boolean result = true, expected = true;

        try {
            enter.connectSQLiteTable();
            result = enter.insertRecords(courseName, numPutts, numPenalties, numScore, playerName);
        }catch(SQLException sqlE){
            result = false;
        }
        assertEquals(expected, result);
    }
}