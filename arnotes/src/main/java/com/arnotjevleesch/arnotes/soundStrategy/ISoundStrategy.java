package com.arnotjevleesch.arnotes.soundStrategy;

import com.arnotjevleesch.arnotes.exception.TechnicalException;
import com.arnotjevleesch.arnotes.pojo.SoundNoteSet;


public interface ISoundStrategy {

	SoundNoteSet getSoundNoteListAndPlay(int numberOfSound) throws TechnicalException;

	boolean begin();

	boolean end();
}
