# YMD
Simple Year-Month-Day class in Java

There are a myriad of classes in Java for storing and comparing dates and times.  Some of these are:
- Date
- LocalDate
- LocalDateTime
- Calendar


The "YMD" class in repository is an attempt to provide a simple class for storing, comparing, and modifing (with rolling) a representation of a combination of "Year + Month + Date".

This class properly handles rolling with leap years.  It does not consider time-zones, however.

I wrote this class because I was tired of fighting with the nuances between the various date/time/calendar/time-zone classes in the Java library -- when all I wanted was a simple representation of a particular YMD date, with easy comparisons.  I'm sure what this class does could easily be handled by the Calendar class instead, though.
