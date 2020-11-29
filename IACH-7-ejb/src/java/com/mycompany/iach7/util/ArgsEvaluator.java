package com.mycompany.iach7.util;

import java.util.Arrays;
import java.util.Locale;
import org.apache.log4j.Logger;

/**
 * Helper to evaluate the arguments in String[] args
 */
public class ArgsEvaluator {
    private static final Logger LOG = Logger.getLogger(ArgsEvaluator.class);

    /**
     * An invisible constructor to keep PMD happy. You can not create in instance of this class
     */
    private ArgsEvaluator() {
    }

    /**
     * Check if args contains the value val no matter of upper-/lowercase
     * <p>
     * @param val  the value to check for
     * @param args the String array to check
     * <p>
     * @return true if args contains val else false
     */
    public static boolean contains(String val, String[] args) {
        boolean ret = false;

        LOG.debug("Check: " + Arrays.toString(args) + " contains [" + val + ']');

        if (val == null || args == null) {
            throw new IllegalArgumentException();
        }

        for (int idx = 0; idx < args.length; ++idx) {
            if (args[idx].trim().toUpperCase(Locale.getDefault()).equals(val.toUpperCase(Locale.getDefault()))) {
                LOG.debug("true");
                return true;
            }
        }
        LOG.debug(ret ? "true" : "false");
        return ret;
    }
}
