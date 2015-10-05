package com.arnotjevleesch.arnotes.matchStrategy;

import com.arnotjevleesch.arnotes.exception.UserException;
import org.junit.Test;

public class QuicksortMatchStrategyTest extends GWTCommonMatchStrategy{

    @Test
    public void match() throws UserException {
        givenSoundNotesList1();
        givenGraphicalNotesList1();
        whenChooseMatchStrategy();
        thenMatch();
    }

    @Test
    public void notMatch() throws UserException {
        givenSoundNotesList1();
        givenGraphicalNotesList2();
        whenChooseMatchStrategy();
        thenNotMatch();
    }

    @Test(expected=UserException.class)
    public void badNumberOfGraphical() throws UserException {
        givenSoundNotesList2();
        givenGraphicalNotesList2();
        whenChooseMatchStrategy();
    }

    @Override
    public void whenChooseMatchStrategy() throws UserException {
        matchStrategy = new QuicksortMatchStrategy(null, graphicalNotes, soundNotes);
    }
}
