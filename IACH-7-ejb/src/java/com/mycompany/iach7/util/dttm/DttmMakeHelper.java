package com.mycompany.iach7.util.dttm;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.log4j.Logger;

/**
 * Helper to create DTTM format field content.<br>
 * - DTTM format length 14 is: yyyyMMddHHmmss<br>
 * - DTTM format length 16 is: yyyyMMddHHmmssSS<br>
 * - DTTM format length 17 is: yyyyMMddHHmmssSSS<br>
 */
public class DttmMakeHelper {
    private static final Logger LOG = Logger.getLogger(DttmMakeHelper.class);

    /**
     * Take the date and make a DTTM formated string length 14 of out of it.
     *
     * @param date the date
     *
     * @return the corresponding DTTM string
     */
    public static String makeDttm(Date date) {
        String ret;

        LOG.info("date=[" + date + ']');
        ret = new SimpleDateFormat("yyyyMMddHHmmss").format(date);

        LOG.info("ret=[" + ret + ']');
        return ret;
    }

    /**
     * Make a DTTM formated string length 14 of out of the current date and time.
     *
     * @return the corresponding DTTM string
     */
    public static String makeDttm() {
        return makeDttm(new Date());
    }

    /**
     * Take the date and make a DTTM formated string length 16 of out of it.
     *
     * @param date the date
     *
     * @return the corresponding DTTM string
     */
    public static String makeDttm16(Date date) {
        String ret;

        LOG.info("date=[" + date + ']');
        ret = new SimpleDateFormat("yyyyMMddHHmmssSS").format(date).substring(0, 16);

        LOG.info("ret=[" + ret + ']');
        return ret;
    }

    /**
     * Make a DTTM formated string length 16 of out of the current date and time.
     *
     * @return the corresponding DTTM string
     */
    public static String makeDttm16() {
        return makeDttm16(new Date());
    }

    /**
     * Take the date and make a DTTM formated string length 17 of out of it.
     *
     * @param date the date
     *
     * @return the corresponding DTTM string
     */
    public static String makeDttm17(Date date) {
        String ret;

        LOG.info("date=[" + date + ']');
        ret = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(date);

        LOG.info("ret=[" + ret + ']');
        return ret;
    }

    /**
     * Make a DTTM formated string length 17 of out of the current date and time.
     *
     * @return the corresponding DTTM string
     */
    public static String makeDttm17() {
        return makeDttm17(new Date());
    }
}
