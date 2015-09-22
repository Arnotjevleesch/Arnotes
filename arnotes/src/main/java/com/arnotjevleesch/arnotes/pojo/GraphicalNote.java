package com.arnotjevleesch.arnotes.pojo;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;


public class GraphicalNote {

	private BigDecimal x;
	private BigDecimal y;

	public GraphicalNote(BigDecimal x, BigDecimal y) {
		this.x = x;
        this.y = y;
	}

	public BigDecimal getX() {
		return x;
	}
	
	public BigDecimal getY() {
		return y;
	}

	@Override
	public String toString() {
        MathContext mc = new MathContext(3, RoundingMode.CEILING);
		return x.round(mc).toString()+","+y.round(mc).toString();
	}
}
