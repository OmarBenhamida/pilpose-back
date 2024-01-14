/**
 * 
 */
package com.benfat.pilpose.util;

import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;

@Component
public class CsvBuilder<T> {

	private static Logger logger = LoggerFactory.getLogger(CsvBuilder.class);

	/**
	 * transform CSV file to bean
	 * 
	 * @param reader
	 * @param clazz
	 * @param mapping
	 * @param separator
	 * @return List<T>
	 * @throws IOException
	 */
	public List<T> csvToBean(Reader reader, Class<T> clazz, Map<String, String> mapping, char separator) {
		List<T> beans = null;
		clazz.getDeclaredFields();
		try {
			HeaderColumnNameTranslateMappingStrategy<T> ms = new HeaderColumnNameTranslateMappingStrategy<>();
			ms.setType(clazz);
			ms.setColumnMapping(mapping);
			CsvToBean<T> cb = new CsvToBeanBuilder<T>(reader).withType(clazz).withSeparator(separator)
					.withMappingStrategy(ms).build();
			beans = cb.parse();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return beans;
	}
}
