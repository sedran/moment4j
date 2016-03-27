package com.asosyalbebe.moment4j;

import java.util.Comparator;

/**
 * 
 * @author Serdar Kuzucu
 *
 */
public class MomentComparator implements Comparator<Moment> {
    public int compare(Moment o1, Moment o2) {
	return Long.compare(o1.valueOf(), o2.valueOf());
    }
}
