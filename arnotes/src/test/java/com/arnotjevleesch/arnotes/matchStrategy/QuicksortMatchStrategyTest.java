package com.arnotjevleesch.arnotes.matchStrategy;

import org.junit.Test;

public class QuicksortMatchStrategyTest extends GWTCommonMatchStrategy{

    @Test
    public void match() {
        givenSoundNotesList1();
        givenGraphicalNotesList1();
        whenChooseMatchStrategy();
        thenMatch();
    }

    @Test
    public void notMatch() {
        givenSoundNotesList1();
        givenGraphicalNotesList2();
        whenChooseMatchStrategy();
        thenNotMatch();
    }

    @Override
    public void whenChooseMatchStrategy() {
        matchStrategy = new QuicksortMatchStrategy(graphicalNotes, soundNotes);
    }
}
