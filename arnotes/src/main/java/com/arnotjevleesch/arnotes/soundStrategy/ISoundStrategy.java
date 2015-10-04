package com.arnotjevleesch.arnotes.soundStrategy;

import com.arnotjevleesch.arnotes.pojo.SoundNoteSet;


public interface ISoundStrategy {

	SoundNoteSet getSoundNoteList(int numberOfSound);

	boolean begin();

	boolean end();
}
