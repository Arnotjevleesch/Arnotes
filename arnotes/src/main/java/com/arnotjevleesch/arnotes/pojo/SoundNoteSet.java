package com.arnotjevleesch.arnotes.pojo;

import java.util.Iterator;
import java.util.LinkedHashSet;

/**
 * Created by arno on 04/10/15.
 */
public class SoundNoteSet extends LinkedHashSet<SoundNote> {

    public SoundNote get(int i) {
        int x = 0;
        Iterator<SoundNote> it = iterator();
        while (it.hasNext() && x!=i){
            x++;
            it.next();
        }
        return it.next();
    }

}
