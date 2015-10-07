package com.arnotjevleesch.arnotes.matchStrategy;

import com.arnotjevleesch.arnotes.exception.SizeGraphicalSoundCoherenceException;
import org.junit.Test;

public class QuicksortMatchStrategyTest extends GWTCommonMatchStrategy{

    @Test
    public void match() throws SizeGraphicalSoundCoherenceException {
        givenSoundNotesList1();
        givenGraphicalNotesList1();
        whenChooseMatchStrategy();
        thenMatch();
    }

    @Test
    public void notMatch() throws SizeGraphicalSoundCoherenceException {
        givenSoundNotesList1();
        givenGraphicalNotesList2();
        whenChooseMatchStrategy();
        thenNotMatch();
    }

    @Test(expected=SizeGraphicalSoundCoherenceException.class)
    public void badNumberOfGraphical() throws SizeGraphicalSoundCoherenceException {
        givenSoundNotesList2();
        givenGraphicalNotesList2();
        whenChooseMatchStrategy();
    }

    @Override
    public void whenChooseMatchStrategy() throws SizeGraphicalSoundCoherenceException {
        matchStrategy = new QuicksortMatchStrategy(graphicalNotes, soundNotes);
    }
}
