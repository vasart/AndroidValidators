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
 * Base class for all validator conditions.
 * @author Dmitry Sitnikov
 */
public abstract class BaseValidatorConditionChecker {
	
	/**
	 * Create checker with default error message.
	 */
	public BaseValidatorConditionChecker() {
		setErrorMessage("Error");
	}
	
	/**
	 * Create checker and set error message.
	 */
	public BaseValidatorConditionChecker(String errorMessage) {
		setErrorMessage(errorMessage);
	}
	
	/**
	 * Check whether text fulfill the condition.
	 */
	public abstract boolean checkCondition(String textToCheck);
	
	/* Error message */
	private String errorMessage_;
	/**
	 * Set error message.
	 */
	public void setErrorMessage(String message) {
		errorMessage_ = message;
	}

	/**
	 * Get error message.
	 */
	public String getErrorMessage() {
		return errorMessage_;
	}
}
