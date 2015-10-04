package com.arnotjevleesch.arnotes.matchStrategy;

import com.arnotjevleesch.arnotes.exception.UserException;
import com.arnotjevleesch.arnotes.pojo.GraphicalNote;
import com.arnotjevleesch.arnotes.pojo.SoundNote;
import com.arnotjevleesch.arnotes.pojo.SoundNoteSet;
import junit.framework.Assert;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public abstract class GWTCommonMatchStrategy {

    public SoundNoteSet soundNotes;
    public List<GraphicalNote> graphicalNotes;
    public IMatchStrategy matchStrategy;

    public void givenSoundNotesList1(){
        soundNotes = new SoundNoteSet();
        soundNotes.add(new SoundNote(BigDecimal.valueOf(10)));
        soundNotes.add(new SoundNote(BigDecimal.valueOf(20)));
        soundNotes.add(new SoundNote(BigDecimal.valueOf(15)));
        soundNotes.add(new SoundNote(BigDecimal.valueOf(5)));
        soundNotes.add(new SoundNote(BigDecimal.valueOf(25)));
    }

    public void givenSoundNotesList2(){
        soundNotes = new SoundNoteSet();
        soundNotes.add(new SoundNote(BigDecimal.valueOf(10)));
        soundNotes.add(new SoundNote(BigDecimal.valueOf(25)));
        soundNotes.add(new SoundNote(BigDecimal.valueOf(15)));
        soundNotes.add(new SoundNote(BigDecimal.valueOf(20)));
    }

    public void givenGraphicalNotesList1(){
        graphicalNotes = new ArrayList<GraphicalNote>();
        graphicalNotes.add(new GraphicalNote(BigDecimal.valueOf(2),BigDecimal.valueOf(10)));
        graphicalNotes.add(new GraphicalNote(BigDecimal.valueOf(6),BigDecimal.valueOf(20)));
        graphicalNotes.add(new GraphicalNote(BigDecimal.valueOf(9),BigDecimal.valueOf(15)));
        graphicalNotes.add(new GraphicalNote(BigDecimal.valueOf(10),BigDecimal.valueOf(5)));
        graphicalNotes.add(new GraphicalNote(BigDecimal.valueOf(11),BigDecimal.valueOf(25)));
    }

    public void givenGraphicalNotesList2(){
        graphicalNotes = new ArrayList<GraphicalNote>();
        graphicalNotes.add(new GraphicalNote(BigDecimal.valueOf(2),BigDecimal.valueOf(5)));
        graphicalNotes.add(new GraphicalNote(BigDecimal.valueOf(6),BigDecimal.valueOf(10)));
        graphicalNotes.add(new GraphicalNote(BigDecimal.valueOf(9),BigDecimal.valueOf(15)));
        graphicalNotes.add(new GraphicalNote(BigDecimal.valueOf(10),BigDecimal.valueOf(20)));
        graphicalNotes.add(new GraphicalNote(BigDecimal.valueOf(11),BigDecimal.valueOf(25)));
    }

    public void thenMatch(){
        Assert.assertTrue(matchStrategy.isMatching());
    }

    public void thenNotMatch(){
        Assert.assertFalse(matchStrategy.isMatching());
    }

    public abstract void whenChooseMatchStrategy() throws UserException;

}
