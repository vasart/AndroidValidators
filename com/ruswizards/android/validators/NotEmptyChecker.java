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

/**
 * Check that text is not empty.
 * @author Dmitry Sitnikov
 */
public class NotEmptyChecker extends BaseValidatorConditionChecker {

	/**
	 * Create checker that verify text is not null and not empty.
	 */
	public NotEmptyChecker(String errorMessage) {
		super(errorMessage);
	}

	/**
	 * Overloaded constructor with default error message.
	 * Similar to {@link NotEmptyChecker#NotEmptyChecker(String)}
	 */
	public NotEmptyChecker() {
	}
	
	/* (non-Javadoc)
	 * @see com.ruswizards.validators.BaseValidatorConditionChecker#checkCondition(java.lang.String)
	 */
	@Override
	public boolean checkCondition(String textToCheck) {
		return textToCheck != null && textToCheck.length() != 0;
	}
}
