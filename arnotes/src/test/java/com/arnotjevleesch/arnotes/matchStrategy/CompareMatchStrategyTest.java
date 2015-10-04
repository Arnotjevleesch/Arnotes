package com.arnotjevleesch.arnotes.matchStrategy;

import com.arnotjevleesch.arnotes.exception.UserException;
import org.junit.Assert;
import org.junit.Test;

public class CompareMatchStrategyTest extends GWTCommonMatchStrategy{

    @Test
    public void match() {
        givenSoundNotesList1();
        givenGraphicalNotesList1();
        try {
            whenChooseMatchStrategy();
        } catch (UserException e) {
            Assert.fail();
        }
        thenMatch();
    }

    @Test
    public void notMatch(){
        givenSoundNotesList1();
        givenGraphicalNotesList2();
        try {
            whenChooseMatchStrategy();
        } catch (UserException e) {
            Assert.fail();
        }
        thenNotMatch();
    }

    @Test
    public void badNumberOfGraphical(){
        givenSoundNotesList2();
        givenGraphicalNotesList2();
        try {
            whenChooseMatchStrategy();
        } catch (UserException e) {
            Assert.assertTrue(true);
        }
    }

    @Override
    public void whenChooseMatchStrategy() throws UserException {
        matchStrategy = new CompareMatchStrategy(soundNotes,graphicalNotes);
    }
}
