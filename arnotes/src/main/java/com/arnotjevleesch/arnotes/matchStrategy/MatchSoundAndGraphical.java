package com.arnotjevleesch.arnotes.matchStrategy;

import com.arnotjevleesch.arnotes.exception.SizeGraphicalSoundCoherenceException;
import com.arnotjevleesch.arnotes.pojo.GraphicalNote;
import com.arnotjevleesch.arnotes.pojo.SoundNoteSet;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class MatchSoundAndGraphical {

	private SoundNoteSet soundNotes;
	private List<GraphicalNote> graphicalNotes;

	public MatchSoundAndGraphical(SoundNoteSet soundNotes, List<GraphicalNote> graphicalNotes)
			throws SizeGraphicalSoundCoherenceException {
		this.soundNotes = soundNotes;
		this.graphicalNotes = graphicalNotes;

		controlSoundAndGraphical();

        sortByX(this.graphicalNotes);
	}

	private void controlSoundAndGraphical() throws SizeGraphicalSoundCoherenceException {
		if(soundNotes.size() != graphicalNotes.size()) {
			throw new SizeGraphicalSoundCoherenceException();
		}
	}

    private void sortByX(List<GraphicalNote> graphicalNotes) {
        Collections.sort(graphicalNotes, new Comparator<GraphicalNote>() {
            @Override
            public int compare(final GraphicalNote o1, final GraphicalNote o2) {
                return o1.getX().compareTo(o2.getX());
            }
        });
    }

	public List<GraphicalNote> getGraphicalNotes() {
		return graphicalNotes;
	}
	
	public SoundNoteSet getSoundNotes() {
		return soundNotes;
	}
}
