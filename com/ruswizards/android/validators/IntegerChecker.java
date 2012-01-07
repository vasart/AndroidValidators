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
 * Check integer values.
 * @author Dmitry Sitnikov
 */
public class IntegerChecker extends BaseValidatorConditionChecker {
	//Bounds
	private int minValue_ = Integer.MIN_VALUE;
	private int maxValue_ = Integer.MAX_VALUE;
	
	/**
	 * Create checker that convert text to int and match bounds.
	 * Bounds are inclusive.
	 * If text is not a number validator will fails.
	 */
	public IntegerChecker(int minValue, int maxValue, String errorMessage) {
		super(errorMessage);
		
		minValue_ = minValue;
		maxValue_ = maxValue;
	}

	/**
	 * Overloaded constructor with default error message.
	 * Similar to {@link IntegerChecker#IntegerChecker(int, int, String)}
	 */
	public IntegerChecker(int minValue, int maxValue) {
		minValue_ = minValue;
		maxValue_ = maxValue;
	}
	
	/* (non-Javadoc)
	 * @see com.ruswizards.validators.BaseValidatorConditionChecker#checkCondition(java.lang.String)
	 */
	@Override
	public boolean checkCondition(String textToCheck) {
		try {
			int value = Integer.parseInt(textToCheck);
			return value >= minValue_ && value <= maxValue_;
		} catch (NumberFormatException e) {
			return false;
		}
	}
}
