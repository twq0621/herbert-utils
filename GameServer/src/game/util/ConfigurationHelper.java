package game.util;


import lion.common.Utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.support.ResourceBundleMessageSource;


public class ConfigurationHelper {

    private static final Log log = LogFactory.getLog(ConfigurationHelper.class);

    private ResourceBundleMessageSource messageSource;

	public String getProperty(String key) {
        try {
            String msg = messageSource.getMessage(key, null, Utils.DEFAULT_LOCALE);
            return msg != null ? msg.trim() : msg;
        }
        catch (NoSuchMessageException e) {
            log.warn("Message of key " + key + " NOT found!");
            return key;
        }
    }

    public String getProperty(String key, Object[] arg) {
        try {
            String msg = messageSource.getMessage(key, arg, Utils.DEFAULT_LOCALE);
            return msg != null ? msg.trim() : msg;
        }
        catch (NoSuchMessageException e) {
            log.warn("Message of key " + key + " NOT found!");
            return key;
        }
    }

    public String getProperty(String key, String defaultValue) {
        try {
            String msg = messageSource.getMessage(key, null, Utils.DEFAULT_LOCALE);
            return msg != null ? msg.trim() : msg;
        }
        catch (NoSuchMessageException e) {
            return defaultValue;
        }
    }

    public String getProperty(String key, Object[] arg, String defaultValue) {
        try {
            String msg = messageSource.getMessage(key, arg, Utils.DEFAULT_LOCALE);
            return msg != null ? msg.trim() : msg;
        }
        catch (NoSuchMessageException e) {
            return defaultValue;
        }
    }

    public int getIntProperty(String key, int defaultValue) {
        try {
            String s = messageSource.getMessage(key, null, Utils.DEFAULT_LOCALE);
            s = (s != null ? s.trim() : s);
            return Integer.parseInt(s);
        }
        catch (NoSuchMessageException e) {
            return defaultValue;
        }
        catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public int getIntProperty(String key, Object[] arg, int defaultValue) {
        try {
            String s = messageSource.getMessage(key, arg, Utils.DEFAULT_LOCALE);
            s = (s != null ? s.trim() : s);
            return Integer.parseInt(s);
        }
        catch (NoSuchMessageException e) {
            return defaultValue;
        }
        catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public long getLongProperty(String key, long defaultValue) {
        try {
            String s = messageSource.getMessage(key, null, Utils.DEFAULT_LOCALE);
            s = (s != null ? s.trim() : s);
            return Long.parseLong(s);
        }
        catch (NoSuchMessageException e) {
            return defaultValue;
        }
        catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public long getLongProperty(String key, Object[] arg, long defaultValue) {
        try {
            String s = messageSource.getMessage(key, arg, Utils.DEFAULT_LOCALE);
            s = (s != null ? s.trim() : s);
            return Long.parseLong(s);
        }
        catch (NoSuchMessageException e) {
            return defaultValue;
        }
        catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    // -------------------------------------- properties setter
    public void setMessageSource(ResourceBundleMessageSource messageSource) {
        this.messageSource = messageSource;
    }

}
