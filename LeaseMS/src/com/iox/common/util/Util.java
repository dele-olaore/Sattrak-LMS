package com.iox.common.util;

import com.iox.lms.model.common.BaseCustomerData;
import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfImportedPage;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.sql.Date;
import java.util.List;
import java.math.MathContext;
import java.util.Collection;
import java.util.Map;
import java.text.ParseException;
import org.apache.commons.io.IOUtils;
import java.math.RoundingMode;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import org.apache.commons.net.ntp.NTPUDPClient;
import org.apache.commons.net.ntp.NtpUtils;
import org.apache.commons.net.ntp.NtpV3Packet;
import org.apache.commons.net.ntp.TimeInfo;
import org.apache.commons.net.ntp.TimeStamp;


public class Util 
{

    // Suppress default constructor for noninstantiability
    private Util() {
//        Because the explicit constructor is private, it is inaccessible outside of the
//class. The AssertionError isn’t strictly required, but it provides insurance in
//case the constructor is accidentally invoked from within the class. It guarantees
//that the class will never be instantiated under any circumstances.
        throw new AssertionError();
    }
    /**
     * The time format to be used for creating timestamp objects
     */
    public static final String TIMESTAMP_FORMAT = "yyyy-MM-dd HH:mm:ss";
    /**
     * Represent a conversion factor for Hour
     */
    public static final Long HOUR_IN_DAY = 24L;
    /**
     * Represent a conversion factor for Minutes
     */
    public static final Long MINUTES_IN_HOUR = 60L;
    /**
     * Represent a conversion factor for Seconds
     */
    public static final Long SECONDS_IN_MINUTE = 60L;
    /**
     * MILI Constant
     */
    public static final Long MILLI = 1000L;
    /**
     * array containing strings that mark a getter method
     */
    private static final String[] GETTER_METHODS = {
        "get", "is"
    };
    public final static int dayInMillis = 24 * 60 * 60 * 1000;
    /**
     * No. of MILLISECONDS IN A DAY
     */
    public static final Long TIME_CONVERSION_FACTOR = HOUR_IN_DAY * MINUTES_IN_HOUR * SECONDS_IN_MINUTE * MILLI;
    public static final NumberFormat CURRENCY_FORMAT = NumberFormat.getNumberInstance();
    public static final int CURRENCY_SCALE = 2;
    public static final RoundingMode CURRENCY_ROUNDING_MODE = RoundingMode.HALF_EVEN;

    /**
     * @return true if the method name is a getter method to obtain an attribute
     * @param methodName the method name to be tested
     */
    public static boolean isGetter(final String methodName) {
        for (int i = 0; i < GETTER_METHODS.length; i++) {
            if (methodName.startsWith(GETTER_METHODS[i])) {
                return true;
            }
        }
        return false;
    }

    /**
     * Retrieves letters from a given String
     *
     * @param val - String from which letters will be retrieved from
     * @return - letters parts of the string
     */
    public static String getLetters(final String val) {
        if (!isNullOrBlank(val)) {
            StringBuilder builder = new StringBuilder();
            char[] arr = val.toCharArray();
            for (int i = 0; i < arr.length; i++) {
                char c = arr[i];
                if (Character.isLetter(c)) {
                    builder.append(c);
                }
            }
            return builder.toString();
        }
        return "";
    }

    /**
     * Retrieves digits from a given String
     *
     * @param val - String from which digits will be retrieved from
     * @return - digits parts of the string
     */
    public static String getDigits(final String val) {
        if (!isNullOrBlank(val)) {
            StringBuilder builder = new StringBuilder();
            char[] arr = val.toCharArray();
            for (int i = 0; i < arr.length; i++) {
                char c = arr[i];
                if (Character.isDigit(c)) {
                    builder.append(c);
                }
            }
            return builder.toString();
        }
        return "";
    }

    /**
     * @return the property name
     * @param methodName the method name
     */
    public static String getAttribute(final String methodName) {
        int index = 0;
        for (int i = 0; i < GETTER_METHODS.length; i++) {
            if (methodName.startsWith(GETTER_METHODS[i])) {
                index = GETTER_METHODS[i].length();
                break;
            }
        }
        return methodName.substring(index);
    }

    /**
     * Method toString is used to obtain a string representation of an object
     * the method generates a string with each attribute of the object enclosed
     * within an XML like tag with the tag name set to the attribute name
     *
     * @param value the object whose string representation is required
     * @return String the string representation
     */
    public static String toString(final Serializable value) {
        Method[] methods = value.getClass().getMethods();
        String className = value.getClass().getName();
        StringBuilder buffer = new StringBuilder();
        // Check if the field modifier is static and final, then skip it
        String propertyName = null;
        String propertyValue = null;
        String methodName = null;

        buffer.append('<');
        buffer.append(className);
        buffer.append(">\n");
        for (int i = 0; i < methods.length; i++) {
            try {
                methodName = methods[i].getName();
                if (!isGetter(methodName)) {
                    continue;
                }
                // Check to see that the getter does not take any formal parameters
                if (methods[i].getParameterTypes().length == 0) {
                    //Added this on 13-01-2012 to avoid printing Object graph when printing a data Object
                    //The same reason is why i moved BaseData from common project to util project to avoid cyclic dependecies.
                    Object invObj = methods[i].invoke(value);
                    if (invObj instanceof BaseCustomerData || invObj instanceof Collection || invObj instanceof Map) {
                        continue;
                    }
                    propertyName = getAttribute(methodName);
                    propertyValue = "" + methods[i].invoke(value);
                    // Append all the other types fields
                    buffer.append('<');
                    buffer.append(propertyName);
                    buffer.append('>');
                    buffer.append(propertyValue);
                    buffer.append("</");
                    buffer.append(propertyName);
                    buffer.append(">\n");
                }
            } catch (Exception ex) {
                String[] params = {
                    "Class Name = [" + className + "], Method Name = [" + methodName + "]"
                };
              }
        }
        buffer.append("</");
        buffer.append(className);
        buffer.append(">\n");
        return buffer.toString();
    }

    /**
     * Method isLeapYear. Check whether Leap Year or Not
     *
     * @param value - Input Value
     * @return boolean - true if leap year
     */
    public static boolean isLeapYear(final java.sql.Date value) {
        if (value == null) {
            throw new IllegalArgumentException("The parameter First Calendar is null");
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(value);
        GregorianCalendar gcal = new GregorianCalendar();

        return gcal.isLeapYear(cal.get(Calendar.YEAR));
    }

    /**
     * Retrieves the number of days in a month
     *
     * @param date the date
     * @return int the number of days in the month of given date
     */
    public static int getDaysInMonth(final java.sql.Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(date.getTime());
        int days = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        return days;
    }

    /**
     * Retrieves the number of days in a month
     *
     * @param cal date in calendar
     * @return int the number of days in the month of given date
     */
    public static int getDaysInMonth(final Calendar cal) {
        int days = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        return days;
    }

    /**
     * Used to obtain the timestamp using the calendar passed as an input
     * argument. It uses Util.TIMESTAMP_FORMAT to format the timestamp.
     *
     * @param cal - the calendar containing the time component
     * @return a Timestamp object representing the UTC time
     */
    public static java.sql.Timestamp getTimestamp(final Calendar cal) {
        SimpleDateFormat formatter = new SimpleDateFormat(TIMESTAMP_FORMAT);
        cal.setTimeZone(TimeZone.getTimeZone("Africa/Lagos"));
        java.util.Date date = cal.getTime();
        String strTimestamp = formatter.format(date);
        return java.sql.Timestamp.valueOf(strTimestamp);
    }

    /**
     * Returns the timestamp as a String according to a format supplied by the
     * user
     *
     * @param ts - the timestamp passed by the client
     * @return A String representing the current UTC time
     */
    public static String getTimestampAsString(final Timestamp ts) {
        return getTimestampAsString(ts, TIMESTAMP_FORMAT);
    }

    /**
     * Returns the timestamp as a String according to a format supplied by the
     * user
     *
     * @param ts - the timestamp passed by the client
     * @param strFormat The format required by the user
     * @return A String representing the current UTC time
     */
    private static String getTimestampAsString(final Timestamp ts, final String strFormat) {
        SimpleDateFormat formatter = new SimpleDateFormat(strFormat);
        Date gmtTime = new Date(ts.getTime());
        String strUTCTimestamp = formatter.format(gmtTime);
        return (strUTCTimestamp);
    }

    /**
     * Finds the difference between two dates in terms in milliseconds It
     * returns a -ve number if the firstDate is before the secondDate. It
     * returns a +ve number if the firstDate is after the secondDate. It returns
     * zero if the firstDate is same as the secondDate. It doesn't take into
     * account the time component of dates.
     *
     * @param firstDate firstDate
     * @param secondDate secondDate
     * @return long the date difference in milliseconds
     * @throws IllegalArgumentException if either the first date or second date
     * parameters are null
     */
    public static long getDateDifference(final java.sql.Date firstDate,
            final java.sql.Date secondDate) throws IllegalArgumentException {
        if (firstDate == null) {
            throw new IllegalArgumentException("The parameter First Date is null");
        }
        if (secondDate == null) {
            throw new IllegalArgumentException("The parameter Second Date is null");
        }
        long numberOfDays = Long.MIN_VALUE;
        Calendar firstCal = Calendar.getInstance();
        firstCal.setTime(firstDate);
        Calendar secondCal = Calendar.getInstance();
        secondCal.setTime(secondDate);
        numberOfDays = getDateDifference(firstCal, secondCal);
        return numberOfDays;
    }

    /**
     * Method getDateDifference.finds the difference between two calendar dates
     * in terms of number of days
     *
     * @param firstCal the first calendar
     * @param secondCal the second calendar
     * @return long
     * @throws IllegalArgumentException if either the first calendar or second
     * calendar parameters are null
     */
    public static long getDateDifference(final Calendar firstCal, final Calendar secondCal)
            throws IllegalArgumentException {
        if (firstCal == null) {
            throw new IllegalArgumentException("The parameter First Calendar is null");
        }
        if (secondCal == null) {
            throw new IllegalArgumentException("The parameter Second Calendar is null");
        }

        long numberOfDays = Long.MIN_VALUE;
        resetTime(firstCal);
        resetTime(secondCal);
        long diffInMilliSecond = firstCal.getTimeInMillis() - secondCal.getTimeInMillis();
        numberOfDays = diffInMilliSecond / TIME_CONVERSION_FACTOR;

        return numberOfDays;
    }

    /**
     * calculates the difference between two dates
     *
     * @param start - the start date
     * @param end - the end date
     * @return Integer - the difference in age
     */
    public static Integer differenceInYears(java.sql.Date start, java.sql.Date end) {
        int age = 0;
        Calendar startCal = Calendar.getInstance();
        startCal.setTime(start);
        Calendar endCal = Calendar.getInstance();
        endCal.setTime(end);
        boolean birthdayPassed = false;
        int startYear = startCal.get(Calendar.YEAR);
        int endYear = endCal.get(Calendar.YEAR);
        int startMonth = startCal.get(Calendar.MONTH);
        int endMonth = endCal.get(Calendar.MONTH);
        if (startMonth < endMonth) {
            birthdayPassed = true;
        } else if (startMonth == endMonth) {
            int startDay = startCal.get(Calendar.DAY_OF_MONTH);
            int endDay = endCal.get(Calendar.DAY_OF_MONTH);
            if (startDay <= endDay) {
                birthdayPassed = true;
            }
        }
        if (birthdayPassed) {
            age = endYear - startYear;
        } else {
            age = endYear - startYear - 1;
        }
        return age;
    }

    /**
     * Converts an SQL Date to java.util.Date.
     *
     * @param sqlDate an SQL Date
     * @return Date Java Date
     */
    public static java.util.Date convertSQLDateToJavaUtilDate(final java.sql.Date sqlDate) {
        return null == sqlDate ? null : new java.util.Date(sqlDate.getTime());
    }

    /**
     * Converts a java.util.Date to java.sql.Date
     *
     * @param date date
     * @return sqlDate an SQL Date
     */
    public static java.sql.Date convertJavaUtilDateToSQLDate(final java.util.Date date) {
        return null == date ? null : new java.sql.Date(date.getTime());
    }

    /**
     * Method resetTime. It sets the time component of a date to zero.
     *
     * @param cal Calendar
     */
    public static void resetTime(final Calendar cal) {
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal.set(Calendar.DST_OFFSET, 0);
    }

    /**
     * Checks if the string is null or blank
     *
     * @param value the string
     * @return true if the string is null/blank
     */
    public static boolean isNullOrBlank(final String value) {
        if (value == null) {
            return true;
        }
        if (value.trim().length() == 0) {
            return true;
        }
        return false;
    }

    /**
     * Checks if the Long is null or less than or equal to zero
     *
     * @param value the Long
     * @return true if the Long is null or zero
     */
    public static boolean isNullOrZero(final Long value) {
        if (value == null) {
            return true;
        }
        if (value <= 0) {
            return true;
        }
        return false;
    }

    /**
     * Checks if the Double is null or less than or equal to zero
     *
     * @param value the Double
     * @return true if the Double is null or zero
     */
    public static boolean isNullOrZero(final Double value) {
        if (value == null) {
            return true;
        }
        if (value.intValue() <= 0) {
            return true;
        }
        return false;
    }

    /**
     * Checks if the Integer is null or less than or equal to zero
     *
     * @param value the Long
     * @return true if the integer is null or zero
     */
    public static boolean isNullOrZero(final Integer value) {
        if (value == null) {
            return true;
        }
        if (value <= 0) {
            return true;
        }
        return false;
    }

    /**
     * Checks if a collection is null or length equal to zero
     *
     * @param value the List
     * @return true if the list is empty or null
     */
    public static boolean isNullOrEmpty(List<? extends BaseCustomerData> list) {
        if (list != null && !list.isEmpty()) {
            return false;
        }
        return true;
    }

    /**
     * This method returns an Array of Long from a comma separated numbers.
     *
     * @param val - Comma separated numbers
     * @return - Array of Long
     */
    public static Long[] convertCommaSeparatedNoToLongArray(final String val) {
        if (!isNullOrBlank(val)) {
            String[] arr = val.split(",");
            Long[] retArr = new Long[arr.length];
            for (int i = 0; i < arr.length; i++) {
                String string = arr[i];
                //What if the value of string is "" or null, let it throw a NumberFormatException
                retArr[i] = Long.valueOf(string);
            }
            return retArr;
        }
        return new Long[]{};
    }

    /**
     * This method returns a List of Long from a comma separated numbers.
     *
     * @param val - Comma separated numbers
     * @return - Array of Long
     */
    public static List<Long> convertCommaSeparatedNoToLongList(final String val) {
        if (!isNullOrBlank(val)) {
            String[] arr = val.split(",");
            List<Long> retList = new ArrayList<Long>();
            for (int i = 0; i < arr.length; i++) {
                String string = arr[i];
                //What if the value of string is "" or null, let it throw a NumberFormatException
                retList.add(Long.valueOf(string));
            }
            return retList;
        }
        return null;
    }

    public static boolean isEntityTypeLogged(final Long entityTypeId) {
        //283 == payroll nominal roll
        //247 == payroll export
        //250 == student result
        //248 == payroll history
        //273 == student result status
        //243 == student score
        //124 == activity
        //322 == student_history
        //323 == student_course_exception_hist
        //324 == student_course_extra_history
        //205 == student_score
        if (entityTypeId != null) {
            if (entityTypeId.longValue() == 283 || entityTypeId.longValue() == 247
                    || entityTypeId.longValue() == 250 || entityTypeId.longValue() == 248
                    || entityTypeId.longValue() == 273 || entityTypeId.longValue() == 243
                    || entityTypeId.longValue() == 124 || entityTypeId.longValue() == 322
                    || entityTypeId.longValue() == 323 || entityTypeId.longValue() == 324
                    || entityTypeId.longValue() == 205) {
                return false;
            }
        }
        return true;
    }

    /**
     * Converts displayed time string to java.sql.Time
     *
     * @param val - displayed time string
     * @return - java.sql.Time
     */
    public static java.sql.Time convertDisplayedTimeToTimeObject(final String value) {
        if (!Util.isNullOrBlank(value)) {
            try {
                SimpleDateFormat format = new SimpleDateFormat("h:mm a");
                return new java.sql.Time(format.parse(value).getTime());
            } catch (ParseException ex) {
                String[] params = {
                    "The time value [" + value + "] cannot be parsed.", value
                };
                
            }
        }
        return null;
    }

    /**
     * This method returns an Array of BigDecimal from a comma separated string.
     *
     * @param val - Comma separated string
     * @return - Array of BigDecimal
     */
    public static BigDecimal[] convertCommaSeparatedStringToBigDecimalArray(final String val) {
        if (!isNullOrBlank(val)) {
            String[] arr = val.split(",");
            BigDecimal[] retArr = new BigDecimal[arr.length];
            for (int i = 0; i < arr.length; i++) {
                String string = arr[i];
                if (Util.isNullOrBlank(string)) {
                    retArr[i] = new BigDecimal(0);
                } else {
                    retArr[i] = new BigDecimal(string);
                }
            }
            return retArr;
        }
        return new BigDecimal[]{};
    }

    /**
     * This method returns an Array of Integer from a comma separated string.
     *
     * @param val - Comma separated string
     * @return - Array of Double
     */
    public static Double[] convertCommaSeparatedStringToDoubleArray(final String val) {
        if (!isNullOrBlank(val)) {
            String[] arr = val.split(",");
            Double[] retArr = new Double[arr.length];
            for (int i = 0; i < arr.length; i++) {
                String string = arr[i];
                if (Util.isNullOrBlank(string)) {
                    retArr[i] = new Double(0);
                } else {
                    retArr[i] = new Double(string);
                }
            }
            return retArr;
        }
        return new Double[]{};
    }

    /**
     * Change a method name to a descriptive text. For example, it should change
     * [convertCommaSeparatedNoToLongArray] to [Convert Comma Separated No To
     * Long Array]
     *
     * @param methodName
     * @return the Description of the Field
     */
    public static String changeMethodNameToDescription(final String methodName) {
        if (methodName != null) {
            char[] arr = methodName.toCharArray();
            arr[0] = Character.toUpperCase(arr[0]);
            if (arr.length > 1) {
                arr[1] = Character.toLowerCase(arr[1]);
            }
            StringBuilder buffer = new StringBuilder();
            for (int i = 0; i < arr.length; i++) {
                if (i > 1 && Character.isUpperCase(arr[i])) {
                    buffer.append(" ");
                }
                buffer.append(arr[i]);
            }
            return buffer.toString();
        }
        return "";
    }

    /**
     * checks if the number string has a string character in it
     *
     * @param value the string
     * @return true if the string is null/blank
     */
    public static boolean hasCharacter(final String value, final boolean acceptDot) {
        boolean retValue = false;

        if (!isNullOrBlank(value)) {
            char[] arr = value.toCharArray();
            for (int i = 0; i < arr.length; i++) {
                if (!Character.isDigit(arr[i]) && arr[i] != '.') {
                    retValue = true;
                }
                if (!acceptDot && arr[i] == '.') {
                    return true;
                }
            }
        } else {
            return true;
        }
        return retValue;
    }

    /**
     * @see org.apache.commons.io.IOUtils#toByteArray(java.io.InputStream)
     */
    public static byte[] toByteArray(final InputStream stream) {
        try {
            return IOUtils.toByteArray(stream);
        } catch (IOException ioe) {
            String[] params = {
                "Could not convert java.io.InputStream to byte[]"
            };
        }
        return null;
    }

    /**
     * Retrieves application full path
     *
     * @return - String application full path
     */
    public static String getApplicationFullPath() {
        return System.getProperty("");
    }

    /**
     * Formats the position of students to the corresponding string
     * representation
     *
     * @param position - Student position integer
     * @return - String representation of position e.g. 1st, 2nd, 3rd etc
     */
    public static String positionFormatter(final int position) {
        String value = "" + position;
        int iTemp = value.length() - 1;

        if (value.length() == 2) {
            if (value.charAt(0) == '1') {
                return value + "th";
            }
        }

        if (value.charAt(iTemp) == '1') {
            return value + "st";
        } else if (value.charAt(iTemp) == '2') {
            return value + "nd";
        } else if (value.charAt(iTemp) == '3') {
            return value + "rd";
        }

        return value + "th";
    }

    /**
     * Rounds or approximates a double value to the specified decimal places
     *
     * @param val - double value to be rounded
     * @param decimalPlaces - required decimal places of the number
     * @return - rounded number
     */
    public static double roundToDecimalPlaces(final double val, final int decimalPlaces) {
        String zeros = "1";
        for (int i = 0; i < decimalPlaces; i++) {
            zeros += "0";
        }
        int decimal = Integer.valueOf(zeros).intValue();
        if (!isNullOrZero(val)) {
            return (double) Math.round(val * decimal) / decimal;
        }
        return val;
    }

    /**
     * Rounds or approximates a double value to the specified decimal places
     *
     * @param val - double value to be rounded
     * @param decimalPlaces - required decimal places of the number
     * @return - rounded number
     */
    public static Double roundToDecimalPlaces(final Double val, final int decimalPlaces) {
        //double formattedNumber = Double.parseDouble(new DecimalFormat("#.##").format(unformattedNumber));
        if (!isNullOrZero(val)) {
            return Double.valueOf(roundToDecimalPlaces(val.doubleValue(), decimalPlaces));
        }
        return val;
    }

    /**
     * Rounds or approximates a BigDecimal value to the specified decimal places
     *
     * @param val - double value to be rounded
     * @param decimalPlaces - required decimal places of the number
     * @return - rounded number
     */
    public static BigDecimal roundToDecimalPlaces(final BigDecimal unrounded, final int precision) {
        if (unrounded == null) {
            return unrounded;
        }
        BigDecimal rounded = unrounded.setScale(precision, RoundingMode.HALF_UP);
        return rounded;
    }

    /**
     * Divides a BigDecimal Value with another Value
     *
     * @param value - Value to be divided
     * @param divisor - Divisor
     * @return - Value of the operation
     */
    public static BigDecimal divideBigDecimal(final BigDecimal value, final BigDecimal divisor) {
        if (value == null || divisor == null) {
            return null;
        }
        return value.divide(divisor, MathContext.DECIMAL32);
    }

    /**
     * Calculates date difference in hours
     *
     * @param startDate - Start date of reference
     * @param endDate - End date of reference
     * @return - hours difference between the supplied dates
     * @throws BusinessServiceException
     */
    public long getDateDifferenceInHours(final java.sql.Date startDate, final java.sql.Date endDate) {
        long difference = endDate.getTime() - startDate.getTime();
        long hours = difference / (MILLI.longValue() * MINUTES_IN_HOUR.longValue() * SECONDS_IN_MINUTE.longValue());

        return hours;
    }

    public static void main2(String[] args) throws Exception {
        InputStream stream = new FileInputStream("C:\\Users\\nuche\\Documents\\tentacle product document_compressed.pdf");
        InputStream stream1 = new FileInputStream("C:\\Users\\nuche\\Downloads\\Doreen Gomez - 2012_2013 Academic Session - 1st Term.pdf");
        InputStream stream2 = new FileInputStream("C:\\Users\\nuche\\Downloads\\Doreen Gomez - 2012_2013 Academic Session - 2nd Term.pdf");
        InputStream stream3 = new FileInputStream("C:\\Users\\nuche\\Downloads\\Doreen Gomez - 2012_2013 Academic Session - 3rd Term(1).pdf");
        InputStream stream4 = new FileInputStream("C:\\Users\\nuche\\Downloads\\Doreen Gomez - 2012_2013 Academic Session - 3rd Term.pdf");
        InputStream stream5 = new FileInputStream("C:\\Users\\nuche\\Downloads\\Student Class Nominal Roll Report.pdf");
        List<InputStream> list = new ArrayList<InputStream>();
        list.add(stream);
        list.add(stream1);
        list.add(stream2);
        list.add(stream3);
        list.add(stream4);
        //list.add(stream5);
        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream("G:/mergeds.pdf"));
        ByteArrayInputStream in = (ByteArrayInputStream) mergePDFs(list);
        IOUtils.write(IOUtils.toByteArray(in), out);
    }

    public static void main3(String[] args) throws Exception {
        InputStream stream = new FileInputStream("C:\\Users\\nuche\\Documents\\tentacle product document_compressed.pdf");
        PdfReader reader = new PdfReader(IOUtils.toByteArray(stream));
        Map<String, String> map = reader.getInfo();
        map.put("Producer", "Uchmannuz");
        for (Map.Entry<String, String> en : map.entrySet()) {
            String key = en.getKey();
            String val = en.getValue();
            System.out.println("key=" + key + "       " + "val=" + val);
        }
    }

    public static void maine(String[] args) throws Exception {
        InputStream stream = new FileInputStream("C:\\Users\\nuche\\Desktop\\word docs\\ACCION LIDER MICROSYSTEMS_PRICING MODEL.docx");
        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream("G:/pricing_model.pdf"));
        List<InputStream> list = new ArrayList<InputStream>();
        list.add(stream);
        InputStream in = mergePDFs(list);
        IOUtils.write(IOUtils.toByteArray(in), out);
        out.flush();
    }

    public static InputStream mergePDFs(final List<InputStream> streamOfPDFFiles) {
        if (streamOfPDFFiles != null && streamOfPDFFiles.size() == 1) {
            return streamOfPDFFiles.get(0);
        }
        Document document = new Document();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            PdfWriter writer = PdfWriter.getInstance(document, outputStream);
            document.open();
            PdfContentByte cb = writer.getDirectContent();
            for (InputStream stream : streamOfPDFFiles) {
                PdfReader reader = new PdfReader(IOUtils.toByteArray(stream));
                for (int i = 1; i <= reader.getNumberOfPages(); i++) {
                    document.newPage();
                    //import the page from source pdf
                    PdfImportedPage page = writer.getImportedPage(reader, i);
                    //add the page to the destination pdf
                    cb.addTemplate(page, 0, 0);
                }
            }
            outputStream.flush();
            document.close();
            outputStream.close();

            return new ByteArrayInputStream(outputStream.toByteArray());
        } catch (Exception e) {
            String[] params = {};
           return null;
        }
    }

    private static Date incrementDate(Date pDate, int pField, int pIncrement) {
//        Calendar cal = Calendar.getInstance(TimeZone.getDefault());

        //Set the first date to be the beginning of the week
        Calendar calRoll = Calendar.getInstance();
        if (pDate != null) {
            calRoll.setTime(pDate);
        }

        calRoll.add(pField, pIncrement);
        return new java.sql.Date(calRoll.getTimeInMillis());
    }

    public static Date addYearsToDate(Date pDate, int pIncrement) {
        return incrementDate(pDate, Calendar.YEAR, pIncrement);
    }

    public static Date addMonthsToDate(Date pDate, int pIncrement) {
        return incrementDate(pDate, Calendar.MONTH, pIncrement);
    }

    public static Date addDaysToDate(Date pDate, int pIncrement) {
        return incrementDate(pDate, Calendar.DAY_OF_YEAR, pIncrement);
    }

    public static String convertBigDecimalAmountToMoney(BigDecimal amount, String currency) {
        if (amount != null && currency != null) {
            //return new StringBuilder().append(CURRENCY_FORMAT.format(amount.doubleValue())).append(" ").append(currency).toString();
            return new StringBuilder().append(currency).append(" ").append(CURRENCY_FORMAT.format(amount.doubleValue())).toString();
        } else {
            return "N/A";
        }
    }

    public static String getPaddedString(String num, Integer width, char pad) {
        char cc[] = new char[width];
        char numa[] = num.toCharArray();
        int pc = width - numa.length;
        for (int i = 0; i < pc; i++) {
            cc[i] = pad;
        }

        System.arraycopy(numa, 0, cc, width - numa.length, numa.length);

        return new String(cc);
    }

    public static Timestamp getCurrentTimeStamp() {
        NTPUDPClient client = null;
        try {
            client = new NTPUDPClient();
            client.setDefaultTimeout(10000);
            client.open();
            InetAddress hostAddr = InetAddress.getByName("time-a.nist.gov");
            TimeInfo info = client.getTime(hostAddr);
            client.close();
            return new Timestamp(processResponse(info).getTime());
        } catch (Exception ex) {
            client.close();
        }

        return null;
    }

    public static java.sql.Date getCurrentDate() {
        return convertJavaUtilDateToSQLDate(getTodaysDate());
        //return new java.sql.Date(getCurrentTimeStamp().getTime());
    }

    public static void main(String[] args) {
        getCurrentTimeStamp();
    }

    private static TimeStamp processResponse(TimeInfo info) {
        NtpV3Packet message = info.getMessage();
        int stratum = message.getStratum();
        int version = message.getVersion();

        int refId = message.getReferenceId();
        String refAddr = NtpUtils.getHostAddress(refId);
        String refName = null;
        if (refId != 0) {
            if (refAddr.equals("127.127.1.0")) {
                refName = "LOCAL";
            } else if (stratum >= 2) {
                if (!refAddr.startsWith("127.127")) {
                    try {
                        InetAddress addr = InetAddress.getByName(refAddr);
                        String name = addr.getHostName();
                        if ((name != null) && (!name.equals(refAddr))) {
                            refName = name;
                        }
                    } catch (UnknownHostException e) {
                        refName = NtpUtils.getReferenceClock(message);
                    }
                }
            } else if ((version >= 3) && ((stratum == 0) || (stratum == 1))) {
                refName = NtpUtils.getReferenceClock(message);
            }
        }
        if ((refName != null) && (refName.length() > 1)) {
            refAddr = new StringBuilder().append(refAddr).append(" (").append(refName).append(")").toString();
        }
        return message.getTransmitTimeStamp();
    }

    public static java.util.Date getTodaysDate() {

        //Set the first date to be the beginning of the week
        Calendar calRoll = Calendar.getInstance();

        calRoll.set(Calendar.HOUR, calRoll.getActualMinimum(Calendar.HOUR));
        calRoll.set(Calendar.AM_PM, Calendar.AM);
        calRoll.set(Calendar.MINUTE, calRoll.getActualMinimum(Calendar.MINUTE));
        calRoll.set(Calendar.SECOND, calRoll.getActualMinimum(Calendar.SECOND));
        calRoll.set(Calendar.MILLISECOND, calRoll.getActualMinimum(Calendar.MILLISECOND));

        return calRoll.getTime();
    }

    public static String formatePaymentDate(String payDate) {

        SimpleDateFormat oracleLiteralDateFormat = new SimpleDateFormat("dd-MMM-yyyy");
        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");

        String paymentDtt = payDate;

        if (payDate.length() > 0 && payDate.contains("/")) {
            try {
                paymentDtt = oracleLiteralDateFormat.format(df.parse(payDate));
            } catch (Exception e) {
                //do nothing
            }
        }
        return paymentDtt;
    }

    /**
     * Retrieves system current time
     *
     * @param date date
     * @return java.sql.Timestamp
     */
    public static java.sql.Timestamp getCurrentTime() {
        Calendar cal = Calendar.getInstance();
        return getTimestamp(cal);
    }

    /**
     * Generates Random Password
     *
     * @param int i1
     * @param int i2
     * @return java.lang.String
     */
    public static String getRandomString(int i1, int i2) {
        int add = (int) (Math.random() * Math.abs(i2 - i1));
        int count = i1 + add;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < count; i++) {
            builder.append((char) ('A' + (char) (Math.random() * 26)));
        }
        return builder.toString();
    }

    public static DataSource getDataSource(String dataSourceName) throws Exception {
        DataSource dataSource = null;
        try {
            // Obtain our environment naming context
            InitialContext initCtx = new InitialContext();
            Context envCtx = (Context) initCtx.lookup("java:comp/env");
            // Build the datasource
            dataSource = (DataSource) envCtx.lookup(dataSourceName);
        } catch (NamingException ne) {
            throw new Exception(ne);
        } catch (Exception e) {
            throw new Exception(e);
        }
        return dataSource;
    }

    public static int getDatePart(Date pDate, int pDatePart) {
        Calendar calRoll = Calendar.getInstance();

        calRoll.setTime(pDate);

        int lPart;

        switch (pDatePart) {
            case Calendar.MONTH:
                lPart = calRoll.get(Calendar.MONTH) + 1; //because it's zero based
                break;
            default:
                lPart = calRoll.get(pDatePart);
                break;
        }

        return lPart;
    }

    public enum Month {

        JANUARY(1),
        FEBRUARY(2),
        MARCH(3),
        APRIL(4),
        MAY(5),
        JUNE(6),
        JULY(7),
        AUGUST(8),
        SEPTEMBER(9),
        OCTOBER(10),
        NOVEMBER(11),
        DECEMBER(12);

        Month(int val) {
            this.value = val;
        }
        private final int value;

        public int value() {
            return value;
        }
    }

    public static String getStringMonth(int j) {
        String str = null;
        for (Month m : Month.values()) {
            if (m.value() == j) {
                str = m.toString();
            }
        }
        return str;
    }

    public static boolean isOsWindows() {
        boolean os = false;
        if (System.getProperty("os.name").startsWith("Windows")) {
            os = true;
        }
        return os;
    }

    public static Calendar getCalendarWithOutTime(java.util.Date effDate) {
        Calendar calRoll = Calendar.getInstance();

        if (effDate != null) {
            calRoll.setTime(effDate);
        }

        calRoll.set(Calendar.HOUR, calRoll.getActualMinimum(Calendar.HOUR));
        calRoll.set(Calendar.AM_PM, Calendar.AM);
        calRoll.set(Calendar.MINUTE, calRoll.getActualMinimum(Calendar.MINUTE));
        calRoll.set(Calendar.SECOND, calRoll.getActualMinimum(Calendar.SECOND));
        calRoll.set(Calendar.MILLISECOND, calRoll.getActualMinimum(Calendar.MILLISECOND));

        return calRoll;
    }

    public static int getTimeDifference(Date start, Date end, int diffType) {
        //(int)DateAndTime.DateDiff(DateInterval.Month, lLastFlowDate, iterFlow.FlowDate, FirstDayOfWeek.Sunday, FirstWeekOfYear.Jan1);

        int result = 0;
        Calendar c1 = getCalendarWithOutTime(start);
        Calendar c2 = getCalendarWithOutTime(end);

        if (diffType == Calendar.DATE) {
            Long l = (c2.getTimeInMillis() - c1.getTimeInMillis()) / dayInMillis;
            result = l.intValue();
        } else if (diffType == Calendar.MONTH) {
            //result = c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH); todo
        } else if (diffType == Calendar.WEEK_OF_YEAR) {
            //result = c2.get(Calendar.WEEK_OF_YEAR) - c1.get(Calendar.WEEK_OF_YEAR); //todo
        } else if (diffType == Calendar.YEAR) {

            result = c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR); //todo
        }

        return result;
    }
   
    public static int getDaysBetween(Date startDate, Date endDate) {
        Calendar start = Calendar.getInstance();
        Calendar end = Calendar.getInstance();
        start.set(getDatePart(startDate, Calendar.YEAR), getDatePart(startDate, Calendar.DATE), getDatePart(startDate, Calendar.MONTH));
        end.set(getDatePart(endDate, Calendar.YEAR), getDatePart(endDate, Calendar.DATE), getDatePart(endDate, Calendar.MONTH));

        long startTime = startDate.getTime();
        long endTime = endDate.getTime();

        long diffDays = (endTime - startTime) / (1000 * 60 * 60 * 24);
        return (int) diffDays;
    }


    public static java.util.Date getDatePortion(java.util.Date effDate) {
        return getCalendarWithOutTime(effDate).getTime();
    }

}
