package com.arnotjevleesch.arnotes.matchStrategy;

import android.content.Context;
import com.arnotjevleesch.arnotes.R;
import com.arnotjevleesch.arnotes.exception.UserException;
import com.arnotjevleesch.arnotes.pojo.GraphicalNote;
import com.arnotjevleesch.arnotes.pojo.SoundNoteSet;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class MatchSoundAndGraphical {

	private SoundNoteSet soundNotes;
	private List<GraphicalNote> graphicalNotes;
	private Context context;

	public MatchSoundAndGraphical(Context applicationContext, SoundNoteSet soundNotes, List<GraphicalNote> graphicalNotes) throws UserException {
		this.context = applicationContext;
		this.soundNotes = soundNotes;
		this.graphicalNotes = graphicalNotes;

        sortByX(this.graphicalNotes);

		control();
	}

    private void sortByX(List<GraphicalNote> graphicalNotes) {
        Collections.sort(graphicalNotes, new Comparator<GraphicalNote>() {
            @Override
            public int compare(final GraphicalNote o1, final GraphicalNote o2) {
                return o1.getX().compareTo(o2.getX());
            }
        });
    }

    public boolean control() throws UserException{
		if(soundNotes.size() != graphicalNotes.size()) {
			throw new UserException((context!=null)?context.getString(R.string.notes_number_message):"");
		}
		return true;
	}
	
	public List<GraphicalNote> getGraphicalNotes() {
		return graphicalNotes;
	}
	
	public SoundNoteSet getSoundNotes() {
		return soundNotes;
	}
}
