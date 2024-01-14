package com.benfat.pilpose.util;

import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.Date;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.benfat.pilpose.enums.CtxtEnum;
import com.benfat.pilpose.enums.OrigineEnum;
import com.benfat.pilpose.exception.PilposeBusinessException.Severity;
import com.benfat.pilpose.logging.FactoryLog;

public class MessageProperties {

	private static final Logger logger = LoggerFactory.getLogger(MessageProperties.class);

	private Properties prop;
	private InputStream input;

	private MessageProperties(String fileName) {
		this.prop = new Properties();
		this.input = MessageProperties.class.getClassLoader().getResourceAsStream(fileName);
		try {
			prop.load(input);
		} catch (IOException e) {
			logger.error(FactoryLog.getErrorLog(CtxtEnum.SERVICE.toString(), OrigineEnum.PILPOSE_AUTH.getValue(),
					new Date(), e.getMessage(), Severity.ERROR.toString(), null));
		}
	}

	/**
	 * 
	 * @param fileName
	 * @return MessageProperties
	 */
	public static MessageProperties getData(String fileName) {
		return new MessageProperties(fileName);
	}

	/**
	 * 
	 * @param key
	 * @param params
	 * @return
	 */
	public String getMessage(String key, Object... params) {
		return MessageFormat.format(prop.getProperty(key), params);
	}
}
