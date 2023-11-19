package com.benfat.pilpose.entities.common;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Parent class for all entity objects (beans)
 * 
 * @author : ROUAIZI OTHMANE SOFRECOM
 * @see : <othmane.rouaizi@sofrecom.com>
 * @creation : 09/03/2021
 * @version : 1.0
 * 
 * @param <T>
 *            java type of entity object's id
 */
@MappedSuperclass
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class AbstractCommonEntity<T extends Serializable> implements Serializable {

	private static final long serialVersionUID = 1L;

	public abstract T getId();

	/**
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public final int hashCode() {
		T id = getId();
		return (id == null ? super.hashCode() : id.hashCode());
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public final boolean equals(final Object obj) {
		boolean equals = false;
		if (obj != null && this.getClass().isInstance(obj)) {
			// same type
			T id = getId();
			T objId = (T) ((AbstractCommonEntity<?>) obj).getId();
			if (id == null) {
				// if both ids are null then compare objects
				equals = (objId == null && super.equals(obj));
			} else {
				// compare ids
				equals = id.equals(objId);
			}
		}
		return equals;
	}
}