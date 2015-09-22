package com.arnotjevleesch.arnotes.pojo;

import java.math.BigDecimal;


public class SoundNote {
	private BigDecimal high;

	public SoundNote(BigDecimal high){
        this.high = high;
    }

	public BigDecimal getHigh() {
		return high;
	}

    @Override
    public boolean equals(Object o) {
        return (o instanceof  SoundNote && high.equals(((SoundNote) o).getHigh()));
    }

    @Override
    public int hashCode() {
        return high.intValue() * 100;
    }

    @Override
    public String toString() {
        return high.toString();
    }
}
