package com.arnotjevleesch.arnotes.soundStrategy;

import com.arnotjevleesch.arnotes.pojo.SoundNote;

import java.util.List;


public interface ISoundStrategy {

	List<SoundNote> getSoundNoteList(int numberOfSound);

	boolean begin();

	boolean end();
}
