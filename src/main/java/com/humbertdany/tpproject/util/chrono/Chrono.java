/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.humbertdany.tpproject.util.chrono;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author dhumbert
 */
public class Chrono {
    
    private final Calendar cStart = new GregorianCalendar();
    private final Calendar cEnd = new GregorianCalendar();
    
    public Chrono(){
        
    }
    
    public void start(){
        cStart.setTime(new Date());
    }
    
    public void stop(){
        cEnd.setTime(new Date());
    }
    
    public long getMilliSec(){
        return cEnd.getTimeInMillis() - cStart.getTimeInMillis();
    }
    
}
