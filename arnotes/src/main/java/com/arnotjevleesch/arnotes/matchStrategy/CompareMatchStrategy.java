package com.arnotjevleesch.arnotes.matchStrategy;

import android.content.Context;
import com.arnotjevleesch.arnotes.exception.UserException;
import com.arnotjevleesch.arnotes.pojo.GraphicalNote;
import com.arnotjevleesch.arnotes.pojo.SoundNoteSet;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;


public class CompareMatchStrategy extends MatchSoundAndGraphical implements IMatchStrategy {


	public CompareMatchStrategy(Context applicationContext, SoundNoteSet soundNotes, List<GraphicalNote> graphicalNotes)
			throws UserException {
		super(applicationContext, soundNotes,graphicalNotes);
	}

	
	@Override
	public boolean isMatching() {
		
		final Integer[] soundIndexes = new Integer[getSoundNotes().size()];
		for(int i=0;i<getSoundNotes().size();i++) {
			soundIndexes[i] = i;
		}
		
		final Integer[] graphicalIndexes = new Integer[getGraphicalNotes().size()];
		for(int i=0;i<getGraphicalNotes().size();i++) {
			graphicalIndexes[i] = i;
		}
		
		Arrays.sort(soundIndexes, new Comparator<Integer>() {
		    @Override 
		    public int compare(final Integer o1, final Integer o2) {
				return getSoundNotes().get(o1).getHigh().compareTo(getSoundNotes().get(o2).getHigh());
		    }
		});
		
		Arrays.sort(graphicalIndexes, new Comparator<Integer>() {
		    @Override 
		    public int compare(final Integer o1, final Integer o2) {
		        return getGraphicalNotes().get(o1).getY().compareTo(getGraphicalNotes().get(o2).getY());
		    }
		});
		
		for(int i=0;i<getSoundNotes().size();i++){
			if(!soundIndexes[i].equals(graphicalIndexes[i])){
				return false;
			}
		}
		return true;
	}

}
