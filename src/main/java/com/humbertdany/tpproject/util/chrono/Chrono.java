package com.humbertdany.tpproject.util.chrono;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author dhumbert
 */
public class Chrono {

	/**
	 * Store the start moment
     */
    private final Calendar cStart = new GregorianCalendar();

	/**
	 * Store the end moment
	 */
    private final Calendar cEnd = new GregorianCalendar();
    
    public Chrono(){
        
    }

	/**
	 * Start the record
	 */
	public void start(){
        cStart.setTime(new Date());
    }

	/**
	 * End the record
	 */
	public void stop(){
        cEnd.setTime(new Date());
    }

	/**
	 * calculate the time between start and end moments
	 * @return the time
	 */
	public long getMilliSec(){
        return cEnd.getTimeInMillis() - cStart.getTimeInMillis();
    }
    
}
