package com.ryanantkowiak;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

/**
 * Class definition of YMD - Store and compare simple dates consisting of
 * year, month, and day-of-month.
 * 
 * @author antkowiak@gmail.com
 *
 */
public class YMD
{
    /**
     * The year
     */
    private int year;
    
    /**
     * The month
     */
    private int month;
    
    /**
     * The day of the month
     */
    private int day;

    /**
     * Default constructor. Sets fields to the current local date.
     */
    public YMD()
    {
        LocalDateTime now = LocalDateTime.now();
        
        year = now.getYear();
        month = now.getMonthValue();
        day = now.getDayOfMonth();
    }

    /**
     * Construct a YMD from a Date object
     * 
     * @param dt
     *      - the Date object to construct YMD from
     */
    @SuppressWarnings("deprecation")
    public YMD(Date dt)
    {
        if (dt != null)
        {
            year = dt.getYear();
            month = dt.getMonth();
            day = dt.getDate();
        }
    }
    
    /**
     * Construct a YMD from a LocalDate object
     * 
     * @param ld
     *      - the LocalDate object to construct YMD from
     */
    public YMD (LocalDate ld)
    {
        if (ld != null)
        {
            year = ld.getYear();
            month = ld.getMonthValue();
            day = ld.getDayOfMonth();
        }
    }
    
    /**
     * Construct a YMD from a LocalDateTime object
     * 
     * @param ldt
     *      - the LocalDateTime object to construct YMD from
     */
    public YMD(LocalDateTime ldt)
    {
        if (ldt != null)
        {
            year = ldt.getYear();
            month = ldt.getMonthValue();
            day = ldt.getDayOfMonth();
        }
    }
    
    /**
     * Construct a YMD from a Calendar object
     * 
     * @param cal
     *      - the Calendar object to construct YMD from
     */
    public YMD(Calendar cal)
    {
        if (cal != null)
        {
            year = cal.get(Calendar.YEAR);
            month = cal.get(Calendar.MONTH) + 1;
            day = cal.get(Calendar.DAY_OF_MONTH);
        }
    }
    
    /**
     * Construct a YMD from a given year, month, and day of month
     * 
     * @param y
     *      - the year
     *      
     * @param m
     *      - the month
     *      
     * @param d
     *      - the day of month
     */
    public YMD(int y, int m, int d)
    {
        year = y;
        month = m;
        day = d;
    }
    
    /**
     * Construct a YMD from the local time in ms from Epoch
     * 
     * @param localTimeInMs
     *      - the local time in ms since Epoch
     */
    public YMD(long localTimeInMs)
    {
        LocalDate ld = Instant.ofEpochMilli(localTimeInMs).atZone(ZoneId.systemDefault()).toLocalDate();
        
        if (ld != null)
        {
            year = ld.getYear();
            month = ld.getMonthValue();
            day = ld.getDayOfMonth();
        }
    }
    
    /**
     * Construct a YMD from a string "YYYYMMdd"
     * 
     * @param ymd
     *      - The string representing the year, month, and day
     */
    public YMD(String ymd)
    {
        if (ymd != null && ymd.length() >= 8)
        {
            try
            {
                year = Integer.parseInt(ymd.substring(0, 4));
                month = Integer.parseInt(ymd.substring(4, 6));
                day = Integer.parseInt(ymd.substring(6, 8));
            }
            catch (Exception e)
            {
                year = month = day = 0;
            }
        }
    }
    
    /**
     * Returns the year
     * 
     * @return  the year
     */
    public int getYear()
    {
        return year;
    }
    
    /**
     * Returns the month
     * 
     * @return  the month
     */
    public int getMonth()
    {
        return month;
    }
    
    /**
     * Returns the day of month
     * 
     * @return - the day of month
     */
    public int getDay()
    {
        return day;
    }
    
    /**
     * Returns a clone of the current YMD object
     * 
     * @return - the cloned YMD object
     */
    public YMD clone()
    {
        return new YMD(year, month, day);
    }
    
    /**
     * Returns a clone of the provided YMD object
     * 
     * @param rhs
     *      - the object to clone
     *      
     * @return - the cloned YMD object
     */
    static YMD clone(YMD rhs)
    {
        if (rhs == null)
            return null;
        
        return new YMD(rhs.year, rhs.month, rhs.day);
    }
    
    /**
     * Returns a YMD object using today's date
     * 
     * @return - a new YMD object initialized to today's date
     */
    static YMD now()
    {
        return new YMD();
    }
    
    /**
     * Returns true if the given year is a leap year
     * 
     * @param y
     *      - the year to check if a leap year
     *      
     * @return - true if y is a leap year
     */
    public static boolean isLeapYear(int y)
    {
        boolean leap = false;
        
        if (y % 400 == 0)
            leap = true;
        
        else if (y % 100 == 0)
            leap = false;
        
        else if (y % 4 == 0)
            leap = true;
        
        return leap;
    }
    
    /**
     * Increments the current day of the YMD object, rolling month and year
     * if appropriate.
     */
    public void incrementDay()
    {
        if (month == 12)
        {
            if (day == 31)
            {
                ++year;
                month = day = 1;
                return;
            }
            else
            {
                ++day;
                return;
            }
        }

        if (month == 2)
        {
            if (isLeapYear(year))
            {
                if (day == 29)
                {
                    ++month;
                    day = 1;
                    return;
                }
                else
                {
                    ++day;
                    return;
                }
            }
            else
            {
                if (day == 28)
                {
                    ++month;
                    day = 1;
                    return;
                }
                else
                {
                    ++day;
                    return;
                }
            }
        }
        
        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10)
        {
            if (day == 31)
            {
                ++month;
                day = 1;
                return;
            }
            else
            {
                ++day;
                return;
            }
        }
        else
        {
            if (day == 30)
            {
                ++month;
                day = 1;
                return;
            }
            else
            {
                ++day;
                return;
            }
        }
    }
    
    /**
     * Decrements the current day of the YMD object, rolling month and year
     * if appropriate.
     */
    public void decrementDay()
    {
        if (month == 1)
        {
            if (day == 1)
            {
                --year;
                month = 12;
                day = 31;
                return;
            }
            else
            {
                --day;
                return;
            }
        }
        
        if (month == 3)
        {
            if (day == 1)
            {
                if (isLeapYear(year))
                {
                    month = 2;
                    day = 29;
                    return;
                }
                else
                {
                    month = 2;
                    day = 28;
                    return;
                }
            }
            else
            {
                --day;
                return;
            }
        }
        
        
        if (month == 2 || month == 4 || month == 6 || month == 8 ||  month == 9 || month == 11)
        {
            if (day == 1)
            {
                --month;
                day = 31;
                return;
            }
            else
            {
                --day;
                return;
            }
        }
        else
        {
            if (day == 1)
            {
                --month;
                day = 30;
                return;
            }
            else
            {
                --day;
                return;
            }
        }
    }
    
    /**
     * Adds the given number of days to the YMD object. Use a negative
     * parameter to subtract days.
     * 
     * @param numDays
     *      - the number of days to add to the YMD object
     */
    public void addDays(int numDays)
    {
        if (numDays > 0)
            for (int i = 0 ; i < numDays ; ++i)
                incrementDay();
        
        if (numDays < 0)
        {
            for (int i = 0 ; i > numDays ; --i)
                decrementDay();
        }
    }
    
    /**
     * Increments the year of the YMD object.
     * Checks to ensure a valid date around leap years.
     */
    public void incrementYear()
    {
        ++year;
        
        if (month == 2 && day == 29 && !isLeapYear(year))
            day = 28;
    }
    
    /**
     * Decrements the year of the YMD object.
     * Checks to ensure a valid date around leap years.
     */
    public void decrementYear()
    {
        --year;

        if (month == 2 && day == 29 && !isLeapYear(year))
            day = 28;
    }
    
    /**
     * Adds a number of years to the YMD object.
     * Checks to ensure a valid date around leap years.
     * 
     * @param y
     *      - the number of years to add
     */
    public void addYears(int y)
    {
        year += y;
        
        if (month == 2 && day == 29 && !isLeapYear(year))
            day = 28;
    }
    
    /**
     * Convert YMD object to a string of format "YYYYMMdd"
     * 
     * @returns - the string representation of the YMD object
     */
    public String toString()
    {
        String s = "" + year;
        
        if (month < 10)
            s += "0";
            
        s += month;
        
        if (day < 10)
            s += "0";
        
        s += day;
                
        return s;
    }
    
    /**
     * Checks if two YMD objects represent the same date
     * 
     * @param rhs
     *      - the object to check for the same date
     *      
     * @return - true if this YMD date is the same as the given date
     */
    public boolean equals(YMD rhs)
    {
        if (rhs == null)
            return false;
        
        return (year == rhs.year && month == rhs.month && day == rhs.day);
    }
    
    /**
     * Checks if the provided object is equal
     * 
     * @param rhs
     *      - the object to check for equivalence
     *      
     * @return - true if this YMD date is the same as the provided object
     */
    public boolean equals(Object o)
    {
        if (o == null)
            return false;
        
        if (! (o instanceof YMD))
            return false;
        
        return (equals((YMD)o));
    }
    
    /**
     * Return true if this YMD date is before rhs date
     * 
     * @param rhs
     *      - the date to check if this is before
     *      
     * @return - true if this YMD is before rhs date
     */
    public boolean before(YMD rhs)
    {
        if (rhs == null)
            return false;
        
        if (year < rhs.year)
            return true;
        if (year > rhs.year)
            return false;

        if (month < rhs.month)
            return true;
        if (month > rhs.month)
            return false;

        if (day < rhs.day)
            return true;
        if (day > rhs.day)
            return false;

        return false;        
    }

    /**
     * Return true if this YMD date is after rhs date
     * 
     * @param rhs
     *      - the date to check if this is after
     *      
     * @return - true if this YMD is after rhs date
     */

    public boolean after(YMD rhs)
    {
        if (rhs == null)
            return false;
        
        if (year > rhs.year)
            return true;
        if (year < rhs.year)
            return false;

        if (month > rhs.month)
            return true;
        if (month < rhs.month)
            return false;

        if (day > rhs.day)
            return true;
        if (day < rhs.day)
            return false;

        return false;        
    }
}
