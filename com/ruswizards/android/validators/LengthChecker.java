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
 * Checks length of text.
 * @author Dmitry Sitnikov
 */
public class LengthChecker extends BaseValidatorConditionChecker {
	private int minLength_;
	private int maxLength_;
	/**
	 * Create checker that match text length.
	 * Bounds are inclusive, so text can have length equals to minLength or maxLength.
	 * @param minLength - minimal length of text (set negative value to have only max length check).
	 * @param maxLength - maximal length of text.
	 */
	public LengthChecker(int minLength, int maxLength, String errorMessage) {
		super(errorMessage);

		minLength_ = minLength;
		maxLength_ = maxLength;
	}
	
	/**
	 * Overloaded constructor with default error message.
	 * Similar to {@link LengthChecker#LengthChecker(int, int, String)}
	 */
	public LengthChecker(int minLength, int maxLength) {
		minLength_ = minLength;
		maxLength_ = maxLength;
	}
	
	/* (non-Javadoc)
	 * @see com.ruswizards.validators.BaseValidatorConditionChecker#checkCondition(java.lang.String)
	 */
	@Override
	public boolean checkCondition(String textToCheck) {
		int length = textToCheck.length();
		return length >= minLength_ && length <= maxLength_;
	}
}
