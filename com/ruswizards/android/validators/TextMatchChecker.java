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

import android.widget.TextView;

/**
 * Match text with text in another TextView.
 * @author Dmitry Sitnikov
 */
public class TextMatchChecker extends BaseValidatorConditionChecker {

	private TextView viewToMatch_;
	
	/**
	 * Create checker and set view to match text with text in given view.
	 * @param errorMessage error message.
	 * @param viewToMatch view to check text.
	 */
	public TextMatchChecker(String errorMessage, TextView viewToMatch) {
		super(errorMessage);
		viewToMatch_ = viewToMatch;
	}
	
	@Override
	public boolean checkCondition(String textToCheck) {
		return textToCheck.equals(viewToMatch_.getText().toString());
	}
}
