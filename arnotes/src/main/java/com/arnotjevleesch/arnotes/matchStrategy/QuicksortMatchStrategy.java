package com.arnotjevleesch.arnotes.matchStrategy;

import com.arnotjevleesch.arnotes.exception.SizeGraphicalSoundCoherenceException;
import com.arnotjevleesch.arnotes.pojo.GraphicalNote;
import com.arnotjevleesch.arnotes.pojo.SoundNoteSet;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;



public class QuicksortMatchStrategy extends MatchSoundAndGraphical implements IMatchStrategy {

	public QuicksortMatchStrategy(List<GraphicalNote> graphicalNotes, SoundNoteSet soundNotes)
			throws SizeGraphicalSoundCoherenceException {
		super(soundNotes, graphicalNotes);
	}
	
	@Override
	public boolean isMatching() {
		
		final int[] soundIndexes = new int[getSoundNotes().size()];
		BigDecimal[] highs = new BigDecimal[getSoundNotes().size()];

		for(int i=0;i<getSoundNotes().size();i++) {
			soundIndexes[i] = i;
			highs[i] = getSoundNotes().get(i).getHigh();
		}
		
		final int[] graphicalIndexes = new int[getGraphicalNotes().size()];
		BigDecimal[] ys = new BigDecimal[getGraphicalNotes().size()];
		
		for(int i=0;i<getGraphicalNotes().size();i++) {
			graphicalIndexes[i] = i;
			ys[i] = getGraphicalNotes().get(i).getY();
		}
		
		triRapide(highs,0,highs.length-1,soundIndexes);
		triRapide(ys,0,ys.length-1,graphicalIndexes);
		
		for(int i=0;i<getSoundNotes().size();i++){
			if(soundIndexes[i] != graphicalIndexes[i]){
				return false;
			}
		}
		return true;
	}

	private static void triRapide(BigDecimal[] t, int premier, int dernier,
			int[] indices) {
		if (premier < dernier) {
			int pivot = choixPivot(premier, dernier);
			pivot = partitionner(t, premier, dernier, pivot, indices);
			triRapide(t, premier, pivot - 1, indices);
			triRapide(t, pivot + 1, dernier, indices);
		}
	}

	private static int choixPivot(int premier, int dernier) {
		Random rand = new Random();
		return rand.nextInt(dernier - premier + 1) + premier;
	}

	private static void echanger(BigDecimal[] t, int a, int b, int[] indices) {
		BigDecimal tmp = t[a];
		t[a] = t[b];
		t[b] = tmp;

		int tmpi = indices[a];
		indices[a] = indices[b];
		indices[b] = tmpi;
	}

	private static int partitionner(BigDecimal[] t, int premier, int dernier,
			int pivot, int[] indices) {
		echanger(t, pivot, dernier, indices);
		int j = premier;
		for (int i = premier; i <= dernier - 1; i++) {
			if (t[i].doubleValue() <= t[dernier].doubleValue()) {
				echanger(t, i, j, indices);
				j++;
			}
		}
		echanger(t, dernier, j, indices);
		return j;
	}

}
