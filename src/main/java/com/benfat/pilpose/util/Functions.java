package com.benfat.pilpose.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.benfat.pilpose.exception.PilposeBusinessException;

public class Functions {

	private static Logger logger = LoggerFactory.getLogger(Functions.class);
	private static final String PDF_EXTENSION = ".pdf";

	private static final ThreadLocal<SoftReference<Map<String, SimpleDateFormat>>> THREADLOCAL_FORMATS = new ThreadLocal<>();

	/**
	 * Default Constructor
	 */
	private Functions() {
	}

	/**
	 * validate size of String
	 *
	 * @param pStr
	 * @param pSize
	 * @return
	 */
	public static boolean calcSizeString(String pStr, int pMaxSize) {
		boolean vSizeOk = false;
		if (pStr != null && (pStr.length() > Constants.STR_LENGTH_0 && pStr.length() <= pMaxSize)) {
			vSizeOk = true;
		}
		return vSizeOk;
	}

	/**
	 * validate the size of contract num
	 *
	 * @param pStr
	 * @param pSize
	 * @return boolean
	 */
	public static boolean calcSizeContractNum(String pStr) {
		boolean vSizeOk = false;
		if (pStr != null && (pStr.length() == Constants.STR_LENGTH_9)) {
			vSizeOk = true;
		}
		return vSizeOk;
	}

	/**
	 * Convert String to Date [dd/MM/yyyy]
	 *
	 * @param pDateStr
	 * @return
	 */
	public static Date stringToDate(String pDateStr) throws ParseException {
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE);
		Date vDate = null;
		if (pDateStr != null) {
			try {
				vDate = format.parse(pDateStr);
			} catch (ParseException e) {
				if (logger.isErrorEnabled()) {
					logger.error("Err parse string to date !", e);
				}
				throw e;
			}
		}
		return vDate;
	}

	/**
	 * get Date from string format
	 *
	 * @param pDateStr
	 * @param dateFormat
	 * @return
	 * @throws ParseException
	 */
	public static Date getDateFromStringFormat(String pDateStr, String dateFormat) throws ParseException {
		DateFormat format = new SimpleDateFormat(dateFormat, Locale.FRANCE);
		Date vDate = null;
		if (pDateStr != null) {
			try {
				vDate = format.parse(pDateStr);
			} catch (ParseException e) {
				if (logger.isErrorEnabled()) {
					logger.error("Err parse string to date !", e);
				}
				throw e;
			}
		}
		return vDate;
	}

	/**
	 * get String from Date
	 *
	 * @param date
	 * @param dateFormat
	 * @return
	 * @throws ParseException
	 */
	public static String convertDateToString(Date date, String dateFormat) throws ParseException {
		DateFormat format = new SimpleDateFormat(dateFormat);
		String strDate = null;
		if (date != null) {
			strDate = format.format(date);
		}
		return strDate;
	}

	/**
	 * This method will generate the name of a getter method for a given field
	 *
	 * @return the name of the getter method
	 */
	public static String getGetterName(String pFieldName) {
		StringBuilder vStrBuilder = new StringBuilder("get");
		vStrBuilder.append(pFieldName.substring(0, 1).toUpperCase());
		vStrBuilder.append(pFieldName.substring(1));
		return vStrBuilder.toString();
	}

	/**
	 * Increments the given date by one day.
	 *
	 * @param pDate
	 * @return Date
	 */
	public static Date getNextDay(Date pDate) {
		GregorianCalendar vDateCal = new GregorianCalendar();
		vDateCal.setTime(pDate);
		vDateCal.add(Calendar.DAY_OF_MONTH, 1);
		return vDateCal.getTime();
	}

	/**
	 * Compare Dates ignoring time.
	 *
	 *
	 * @param pDateFront
	 * @param vDateOfDay
	 * @return boolean
	 */
	public static boolean compareDateOnly(Date pDate1, Date pDate2) {
		SimpleDateFormat vSdf = new SimpleDateFormat("yyyyMMdd");
		return vSdf.format(pDate1).equals(vSdf.format(pDate2));
	}

	/**
	 * Set time to midi night
	 *
	 * @param date
	 * @return
	 */
	public static Date setTimeToMidnight(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	/**
	 * Convert from date to XMLGregorianCalendar
	 *
	 * @param date
	 * @return dateXMLGregC
	 * @throws DatatypeConfigurationException
	 */
	public static XMLGregorianCalendar dateToXMLGregCalendar(Date date) throws DatatypeConfigurationException {
		GregorianCalendar c = new GregorianCalendar();
		XMLGregorianCalendar vDateXMLGregC = null;
		if (date != null) {
			c.setTime(date);
			try {
				vDateXMLGregC = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
			} catch (DatatypeConfigurationException e) {
				if (logger.isErrorEnabled()) {
					logger.error("Err parse date to XMLGregorianCalendar !", e);
				}
				throw e;
			}
		}
		return vDateXMLGregC;
	}

	/**
	 *
	 * @param fileName
	 * @param key
	 * @return {String}
	 * @throws IOException
	 */
	public static String getMessage(String fileName, String key) {
		Properties prop = new Properties();
		String vMessage = "";
		try {
			// load a properties file
			prop.load(Functions.class.getClassLoader().getResourceAsStream(fileName));
			// get the property value and print it out
			vMessage = prop.getProperty(key) != null ? prop.getProperty(key) : "";
		} catch (IOException ex) {
			if (logger.isErrorEnabled()) {
				logger.error(new StringBuilder("Erreur get message :").append(ex.getMessage()).toString(), ex);
			}
		}
		return vMessage;
	}

	/**
	 * @category get error message for "Transverse" Rest API
	 * @param key
	 * @param params
	 * @return
	 */
	public static String getTransverseErrMessage(String key, Object... params) {
		/* get messages */
		String message = Functions.getMessage("errorMessageTransverse_fr_FR.properties", key);
		/* replace all ' by '' */
		message = message.replace("'", "''");
		return MessageFormat.format(message, params);
	}

	/**
	 * Convert list collection to map collection.
	 *
	 *
	 * @param list
	 * @param mapper
	 * @return Map
	 */
	public static <K, V, T extends V> Map<K, V> listToMap(List<T> list, Function<? super T, ? extends K> mapper) {
		return list.stream().collect(Collectors.toMap(mapper, Function.identity()));
	}

	/**
	 * Null-safe check if the specified collection is empty.
	 * <p>
	 * Null returns true.
	 *
	 * @param coll the collection to check, may be null
	 * @return true if empty or null
	 */
	public static boolean isEmpty(Collection<?> coll) {
		return (coll == null || coll.isEmpty());
	}

	/**
	 * Null-safe check if the specified collection is not empty.
	 * <p>
	 * Null returns false.
	 *
	 * @param coll the collection to check, may be null
	 * @return true if not empty and null
	 */
	public static boolean isNotEmpty(Collection<?> coll) {
		return !isEmpty(coll);
	}

	/**
	 * Convert data to csv format
	 *
	 * @param pData
	 * @param pHeader
	 * @param pDelimiter
	 * @return
	 */
	public static StringBuilder convertDataToCsvForm(List<List<String>> pData, String pHeader, String pDelimiter) {
		StringBuilder sb = new StringBuilder();
		sb.append(pHeader).append(Constants.NEW_LINE_SEPARATOR);
		for (List<String> vListData : pData) {
			boolean vFirst = true;
			for (String value : vListData) {
				if (!vFirst) {
					sb.append(pDelimiter);
				}
				sb.append(value);
				vFirst = false;
			}
			sb.append(Constants.NEW_LINE_SEPARATOR);
		}
		return sb;
	}

	/**
	 * get start of date
	 *
	 * @param pDay
	 * @param pCal
	 * @return
	 */
	public static Date getStartOfDay(Date pDay, Calendar pCal) {
		pCal.setTime(pDay == null ? new Date() : pDay);
		pCal.set(Calendar.HOUR_OF_DAY, pCal.getMinimum(Calendar.HOUR_OF_DAY));
		pCal.set(Calendar.MINUTE, pCal.getMinimum(Calendar.MINUTE));
		pCal.set(Calendar.SECOND, pCal.getMinimum(Calendar.SECOND));
		pCal.set(Calendar.MILLISECOND, pCal.getMinimum(Calendar.MILLISECOND));
		return pCal.getTime();
	}

	/**
	 * get start of date
	 *
	 * @param pDay
	 * @param pCal
	 * @return
	 */
	public static Date getEndOfDay(Date pDay, Calendar pCal) {
		pCal.setTime(pDay == null ? new Date() : pDay);
		pCal.set(Calendar.HOUR_OF_DAY, pCal.getMaximum(Calendar.HOUR_OF_DAY));
		pCal.set(Calendar.MINUTE, pCal.getMaximum(Calendar.MINUTE));
		pCal.set(Calendar.SECOND, pCal.getMaximum(Calendar.SECOND));
		pCal.set(Calendar.MILLISECOND, pCal.getMaximum(Calendar.MILLISECOND));
		return pCal.getTime();
	}

	/**
	 * Convert String builder to string
	 *
	 * @param pchaine
	 * @return {String}
	 */
	public static String convertStringBuilderToString(StringBuilder pchaine) {
		return (pchaine != null ? pchaine.toString() : null);
	}

	/**
	 * Convert LocalDateTime to Date
	 *
	 * @param pLocalDateTime
	 * @return
	 */
	public static Date convertLocalDateTimeToDate(LocalDateTime pLocalDateTime) {
		if (pLocalDateTime != null) {
			ZonedDateTime zdt = pLocalDateTime.atZone(ZoneId.systemDefault());
			return Date.from(zdt.toInstant());
		}
		return null;
	}

	/**
	 * Difference Between two dates -> to String
	 *
	 * @param pDate1
	 * @param pDate2
	 * @return
	 */
	public static String diffDate(Date pDate1, Date pDate2) {
		StringBuilder sb = null;

		if (pDate1 != null && pDate2 != null) {
			long date1Ms = pDate1.getTime();
			long date2MS = pDate2.getTime();
			long def = date2MS - date1Ms;
			int diffMins = (int) (((def % 86400000) % 3600000) / 60000);
			int diffScnd = (int) (def / 1000);
			double diffMScnd = def % 1000;

			sb = new StringBuilder();
			sb.append(diffMins);
			sb.append("min ");
			sb.append(diffScnd);
			sb.append("s ");
			sb.append(diffMScnd);
			sb.append("ms");
		}
		return (sb != null) ? sb.toString() : null;
	}

	/**
	 * Difference Between two dates -> to MILLISECONDS
	 *
	 * @param pDate1
	 * @param pDate2
	 * @return
	 */
	public static long diffDateMillseconds(Date pDate1, Date pDate2, TimeUnit timeUnit) {
		long diffInMillies = pDate2.getTime() - pDate1.getTime();
		return timeUnit.convert(diffInMillies, TimeUnit.MILLISECONDS);
	}

	/**
	 * convert date to Log date format -> to String
	 *
	 * @param date
	 * @return
	 */
	public static String getDateInLogDateFormat(Date date) {
		LocalDateTime mydate = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
		/* date */
		int year = mydate.getYear();
		int month = mydate.getMonthValue();
		int day = mydate.getDayOfMonth();
		int hour = mydate.getHour();
		int minute = mydate.getMinute();
		int second = mydate.getSecond();
		int millis = mydate.get(ChronoField.MILLI_OF_SECOND);
		/* Preparation data toString */
		StringBuilder sb = new StringBuilder();
		sb.append(year);
		sb.append("/");
		sb.append(month);
		sb.append("/");
		sb.append(day);
		sb.append(" ");
		sb.append(hour);
		sb.append(":");
		sb.append(minute);
		sb.append(":");
		sb.append(second);
		sb.append(" :");
		sb.append(millis);
		return sb.toString();
	}

	/**
	 * get number length
	 *
	 * @param number
	 * @return {Integer}
	 */
	public static int numberLength(int number) {
		int numberCounter = 10;
		boolean condition = true;
		int digitLength = 1;

		while (condition) {
			int numberRatio = number / numberCounter;
			if (numberRatio < 1) {
				condition = false;
			} else {
				digitLength++;
				numberCounter *= 10;
			}
		}
		return digitLength;
	}

	/**
	 * returns true if string is null or empty
	 *
	 * @param str
	 * @return
	 */
	public static boolean isNullOrEmpty(String str) {
		return !(str != null && !str.isEmpty());
	}

	/**
	 * Get differences between two objects of same type
	 *
	 * @param newItem
	 * @param oldItem
	 * @return
	 */
	public static String getDifferencesTwoObjects(List<String> oldObject, List<String> newObject) {
		StringBuilder differencesSb = new StringBuilder();
		for (int i = 0; i < oldObject.size(); i++) {
			String oldValue = oldObject.get(i).toLowerCase().trim();
			String newValue = newObject.get(i).toLowerCase().trim();
			if (!(oldValue.contains("non spécifié") && newValue.contains("null")) && !oldValue.equals(newValue)) {
				differencesSb.append("-[" + oldValue + "] => [" + newValue + "] \r\n");
			}
		}
		return differencesSb.toString();
	}

	/**
	 * convert miliSeconds to <b>mm:ss</b> format
	 *
	 * @param miliSeconds
	 * @author Zakarya EL BAZY
	 * @return
	 */
	public static String convertMilisecsToMinSec(Long miliSeconds) {
		long min = 0;
		long sec = 0;
		String result = "";
		if (miliSeconds != null) {
			min = (miliSeconds / 1000) / 60;
			sec = (miliSeconds / 1000) % 60;
			result = String.format("%02d:%02d", min, sec);
		}
		return result;
	}

	/**
	 * Formats the given date according to the specified pattern. The pattern must
	 * conform to that used by the {@link SimpleDateFormat simple date format}
	 * class.
	 *
	 * @param date    The date to format.
	 * @param pattern The pattern to use for formatting the date.
	 * @return A formatted date string.
	 *
	 * @throws IllegalArgumentException If the given date pattern is invalid.
	 *
	 * @see SimpleDateFormat
	 */
	public static String formatDate(final Date date, final String pattern) {
		final SimpleDateFormat formatter = formatFor(pattern);
		return formatter.format(date);
	}

	/**
	 * creates a {@link SimpleDateFormat} for the requested format string.
	 *
	 * @param pattern a non-{@code null} format String according to
	 *                {@link SimpleDateFormat}. The format is not checked against
	 *                {@code null} since all paths go through {@link DateUtils}.
	 * @return the requested format. This simple dateformat should not be used to
	 *         {@link SimpleDateFormat#applyPattern(String) apply} to a different
	 *         pattern.
	 */
	public static SimpleDateFormat formatFor(final String pattern) {
		final SoftReference<Map<String, SimpleDateFormat>> ref = THREADLOCAL_FORMATS.get();
		Map<String, SimpleDateFormat> formats = ref == null ? null : ref.get();
		if (formats == null) {
			formats = new HashMap<>();
			THREADLOCAL_FORMATS.set(new SoftReference<>(formats));
		}

		SimpleDateFormat format = formats.computeIfAbsent(pattern, key -> new SimpleDateFormat(pattern, Locale.US));
		if (format == null) {
			format = new SimpleDateFormat(pattern, Locale.US);
			format.setTimeZone(TimeZone.getTimeZone("GMT"));
			formats.put(pattern, format);
		}

		THREADLOCAL_FORMATS.remove();
		return format;
	}

	/**
	 * @param Date and number month
	 * @return method return Month +1
	 */
	public static Date addMonth(Date date, int i) {
		if (date != null) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.add(Calendar.MONTH, i);
			return cal.getTime();
		}
		return null;
	}

	/**
	 * @param Date
	 * @return first day in Month
	 */
	public static Date firstDayInMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		return cal.getTime();
	}

	/**
	 * @param Date
	 * @return last day in Month
	 */
	public static Date lastDayInMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, 1);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.add(Calendar.DATE, -1);
		return cal.getTime();
	}

	/**
	 * @param int month
	 * @return name month
	 */
	public static String theMonth(int month) {
		String[] monthNames = { "Janvier", "Février", "Mars", "Avril", "Mai", "Juin", "Juillet", "Août", "Septembre",
				"Octobre", "Novembre", "Décembre" };
		return monthNames[month];
	}

	/**
	 * method return index of month from 0 (January) to 11 (December)
	 *
	 * @param Date
	 * @return return month int
	 */
	public static int getMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.MONTH);
	}

	/**
	 * method return index of month from 0 (January) to 11 (December)
	 *
	 * @param Date
	 * @return return month int
	 */
	public static int getDay(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * convert objet to double
	 *
	 * @param object
	 * @return double
	 */
	public static double convertToDouble(Object object) {
		if (object == null) {
			return 0;
		}
		return Double.parseDouble(object.toString());
	}

	/**
	 * convert objet to integer
	 *
	 * @param object
	 * @return double
	 */
	public static Integer convertToInteger(Object object) {
		if (object == null) {
			return null;
		}
		return Integer.parseInt(object.toString());
	}

	/**
	 * convert object to Long
	 *
	 * @param object
	 * @return
	 */
	public static Long convertToLong(Object object) {
		if (object == null) {
			return null;
		}
		return Long.parseLong(object.toString());
	}

	/**
	 * convert object to Float
	 *
	 * @param object
	 * @return
	 */
	public static Float convertToFloat(Object object) {
		if (object == null) {
			return null;
		}
		return Float.parseFloat(object.toString());
	}

	/**
	 * Afficher un double avec 2 chiffres apres la virgule
	 *
	 * @param nbrDouble
	 * @return
	 */
	public static double convertToDoubleDeuxChiffres(double nbrDouble) {
		double pow = Math.pow(10, 2);
		return (Math.floor(nbrDouble * pow)) / pow;
	}

	/**
	 * @param Date
	 * @return return year int
	 */
	public static Integer getYear(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.YEAR);
	}

	/**
	 * capitalize string
	 *
	 * @param str
	 * @return
	 */
	public static String toCapitalize(String str) {
		return str.substring(0, 1).toUpperCase(Locale.FRANCE) + str.substring(1);
	}

	/**
	 * calcul 12 mois glissant
	 *
	 * @return debut du 12 mois glissant
	 * @param Date
	 */
	public static Date getDouzeMoisGlissant(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, -12);
		/* return month start 12MG */
		return cal.getTime();
	}

	/**
	 * get previous month
	 *
	 * @param date
	 * @return
	 */
	public static Date getPreviousMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, -1);
		return cal.getTime();
	}

	/**
	 * methode perme de return int month example : 2019-12-01 return 12
	 *
	 * @param Date
	 * @return return month int
	 * @author okourmou
	 */
	public static Integer getMonthV2(Date date) {
		LocalDate currentDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		Month m = currentDate.getMonth();
		return m.getValue();
	}

	/**
	 * Get string day/month from full String Date Jour ferie
	 *
	 * @param dateJourFerie
	 * @return String
	 * @author melbarhoumi
	 */
	public static String getDayMonthStringFromDate(String dateJourFerie) {
		String[] dateSplit = dateJourFerie.split("/");
		return new StringBuilder(dateSplit[0]).append("/").append(dateSplit[1]).toString();
	}

	/**
	 * Get string day/month from Date Jour ferie
	 *
	 * @param dateJourFerie
	 * @return String
	 * @author melbarhoumi
	 */
	public static String getDayMonthStringFromDate(Date dateJourFerie) throws ParseException {
		String[] dateSplit = Functions.convertDateToString(dateJourFerie, Constants.DATE_FORMAT_DD_MM_YYYY).split("/");
		return new StringBuilder(dateSplit[0]).append("/").append(dateSplit[1]).toString();
	}

	/**
	 * Get string day/month from Date Jour ferie
	 *
	 * @param dayMonthJourFerie
	 * @return String
	 * @author melbarhoumi
	 */
	public static Date getDateFromString(String dayMonthJourFerie) throws ParseException {
		Date date = new Date();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		return Functions.getDateFromStringFormat(
				new StringBuilder(dayMonthJourFerie).append("/").append(calendar.get(Calendar.YEAR)).toString(),
				Constants.DATE_FORMAT_DD_MM_YYYY);

	}

	/**
	 * convert object (that contains a String value) to string
	 *
	 * @param o
	 * @return
	 */
	public static String objectToString(Object o) {
		String str = null;
		if (o != null) {
			str = o.toString();
		}
		return str;
	}

	/**
	 * methode convert byte to image
	 *
	 * @param pImageData
	 * @param pWidth
	 * @param pHeight
	 * @return
	 * @throws IOException
	 */
	public static byte[] resizeImageAsJPG(byte[] pImageData, int pWidth, int pHeight) throws IOException {
		InputStream is = new ByteArrayInputStream(pImageData);
		BufferedImage newBi = ImageIO.read(is);
		if (newBi != null) {
			BufferedImage imageTreated = resizeImage(newBi, pWidth, pHeight);
			ByteArrayOutputStream encoderOutputStream = new ByteArrayOutputStream();
			ImageIO.write(imageTreated, "jpg", encoderOutputStream);
			return encoderOutputStream.toByteArray();
		}
		return pImageData;
	}

	/**
	 * methode to resize photos
	 *
	 * @param originalImage
	 * @param targetWidth
	 * @param targetHeight
	 * @return
	 */
	public static BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) {
		if (originalImage != null) {
			Image resultingImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_DEFAULT);
			BufferedImage outputImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
			outputImage.getGraphics().drawImage(resultingImage, 0, 0, null);
			return outputImage;
		}
		return null;
	}

	/**
	 * get exceptions line number
	 *
	 * @param e
	 * @return
	 */
	public static int getExceptionLineNumber(Exception e) {
		int lineNumber = 0;
		if (e != null && e.getStackTrace().length > 0 && e.getStackTrace()[0] != null) {
			lineNumber = e.getStackTrace()[0].getLineNumber();
		}
		return lineNumber;
	}

	/**
	 * convert {@link Date} to {@link LocalDate}
	 *
	 * @param dateToConvert
	 * @return
	 */
	public static LocalDate convertDateToLocalDate(Date dateToConvert) {
		return Instant.ofEpochMilli(dateToConvert.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
	}

	/**
	 * convert {@link LocalDate} to {@link Date}
	 *
	 * @param dateToConvert
	 * @return
	 */
	public static Date convertLocalDateToDate(LocalDate dateToConvert) {
		return Date.from(dateToConvert.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
	}

	/**
	 * convert {@link Date} to {@link LocalDateTime}
	 *
	 * @param dateToConvert
	 * @return
	 */
	public static LocalDateTime convertDateToLocalDateTime(Date dateToConvert) {
		if (dateToConvert != null) {
			return Instant.ofEpochMilli(dateToConvert.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
		}
		return null;
	}

	/**
	 *
	 * @param <T>
	 * @param clazz
	 * @param rawCollection
	 * @return
	 */
	public static <T> List<T> castList(Class<? extends T> clazz, Collection<?> rawCollection) {
		List<T> result = new ArrayList<>(rawCollection.size());
		for (Object o : rawCollection) {
			try {
				result.add(clazz.cast(o));
			} catch (ClassCastException e) {
				throw new PilposeBusinessException(e);
			}
		}
		return result;
	}

	/**
	 *
	 * @param date
	 * @return
	 */
	public static Date convertObjectToDate(Object date) {
		return (Date) date;
	}

	/**
	 *
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static Period diffrenceBetweenTwoDates(LocalDate date1, LocalDate date2) {
		return Period.between(date1, date2);
	}

	/**
	 * @param localDateTime
	 * @param formatPatter
	 * @return
	 */
	public static String convertLocalDateTimeToString(LocalDateTime localDateTime, String formatPatter) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatPatter);
		return localDateTime.format(formatter);
	}

	/**
	 *
	 * @param localDateTimeStr
	 * @param formatPattern
	 * @return
	 */
	public static LocalDateTime stringToLocalDateTime(String localDateTimeStr, String formatPattern) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatPattern);
		return LocalDateTime.parse(localDateTimeStr, formatter);
	}

	public static byte[] convertFileToByte(File file) throws IOException {

		// Creating an object of FileInputStream to

		// read from a file

		FileInputStream fl = new FileInputStream(file);

		// Now creating byte array of same length as file

		byte[] arr = new byte[(int) file.length()];

		// Reading file content to byte array

		// using standard read() method

		fl.read(arr);

		// lastly closing an instance of file input stream

		// to avoid memory leakage
		fl.close();
		// Returning above byte array
		return arr;

	}

	/**
	 *
	 * @param nom
	 * @return
	 */
	public static String constructTemplateFilePath(String nom) {
		if (nom == null) {
			return null;
		}
		return nom.concat(PDF_EXTENSION);
	}

	/**
	 *
	 * @param file
	 * @param data
	 */
	public static void writeData(File file, byte[] data) {
		try {
			FileOutputStream output = new FileOutputStream(file);
			output.write(data);
			output.close();
		} catch (IOException e) {
			throw new PilposeBusinessException(e);
		}
	}

	/**
	 *
	 * @param file
	 * @param data
	 */
	public static void writeDataToFile(File file, byte[] data) {
		try {
			writeData(file, data);
		} catch (Exception e) {
			throw new PilposeBusinessException(e);
		}
	}

	/**
	 *
	 * @param newPath
	 * @param data
	 */
	public static void addOrRenameFile(String newPath, byte[] data) {
		File oldFile = new File(newPath);
		writeDataToFile(oldFile, data);
	}

	/**
	 * convert string to Long
	 *
	 * @param string
	 * @return
	 */
	public static Long convertStringToLong(String string) {
		return Long.parseLong(string);
	}

}
