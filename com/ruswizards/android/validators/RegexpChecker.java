/**
 * Android validators.
 * Copyright (c) 2011 Ruswizards LLC <dsitnikov@ruswizards.com>
 * http://ruswizards.com / https://github.com/ruswizards/AndroidValidators
 * 
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.ruswizards.android.validators;

import java.util.regex.Pattern;

/**
 * Check regexp match.
 * @author Dmitry Sitnikov
 */
public class RegexpChecker extends BaseValidatorConditionChecker {

	private String regexp_ = ".*";
	
	/**
	 * Create regexpchecker that matches emails.
	 * Do not support non-latin domains for now.
	 */
	public static RegexpChecker createEmailRegexpChecker(String errorMessage) {
		return new RegexpChecker("\\b[\\w.%-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}\\b", errorMessage);
	}
	
	/**
	 * Create checker that match regexp.
	 */
	public RegexpChecker(String regexp, String errorMessage) {
		super(errorMessage);
		regexp_ = regexp;
	}
	
	/**
	 * Overloaded constructor with default error message.
	 * Similar to {@link RegexpChecker#RegexpChecker(String, String)}
	 */
	public RegexpChecker(String regexp) {
		regexp_ = regexp;
	}
	
	/* (non-Javadoc)
	 * @see com.ruswizards.validators.BaseValidatorConditionChecker#checkCondition(java.lang.String)
	 */
	@Override
	public boolean checkCondition(String textToCheck) {
		return Pattern.matches(regexp_, textToCheck);
	}
}
