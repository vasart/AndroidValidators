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

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;

/**
 * Validator for editText.
 * Could display error using standard {@link EditText#setError(CharSequence)}
 * when there is no external error view.
 * @author Dmitry Sitnikov
 */
public class EditTextValidator extends BaseValidator implements TextWatcher, OnFocusChangeListener {
	//Validated view.
	protected EditText viewToValidate_ = null;

	/* (non-Javadoc)
	 * @see com.ruswizards.validators.BaseValidator#setViewToValidate(android.view.View)
	 */
	@Override
	public void setViewToValidate(View view, ValidationMode mode) {
		if (!(view instanceof EditText))
			throw new IllegalArgumentException("Given view is not a EditText");
		
		//If we have previous view, remove listener 
		if (viewToValidate_ != null) {
			viewToValidate_.removeTextChangedListener(this);
			viewToValidate_.setOnFocusChangeListener(null);
		}
		
		//Store new view
		viewToValidate_ = (EditText)view;
		
		switch (mode) {
		case Auto:
			//and set listener if necessary
			viewToValidate_.addTextChangedListener(this);
			viewToValidate_.setOnFocusChangeListener(this);	
			break;
		case Manual:
			//Nothing to do for manual
			break;
		}
	}
	
	/* (non-Javadoc)
	 * @see com.ruswizards.validators.BaseValidator#getTextToValidate()
	 */
	@Override
	protected String getTextToValidate() {
		if (viewToValidate_ == null)
			return null;
		return viewToValidate_.getText().toString();
	}

	/* (non-Javadoc)
	 * @see com.ruswizards.validators.BaseValidator#displayError(java.lang.String)
	 */
	@Override
	protected void displayError(String error) {
		if (externalErrorView_ != null)
			super.displayError(error);
		else
			viewToValidate_.setError(error);
	}
	
	/* (non-Javadoc)
	 * @see android.text.TextWatcher#afterTextChanged(android.text.Editable)
	 */
	@Override
	public void afterTextChanged(Editable s) {
		performCheck();
	}

	/* (non-Javadoc)
	 * @see android.text.TextWatcher#beforeTextChanged(java.lang.CharSequence, int, int, int)
	 */
	@Override
	public void beforeTextChanged(CharSequence s, int start, int count, int after) {
	}

	/* (non-Javadoc)
	 * @see android.text.TextWatcher#onTextChanged(java.lang.CharSequence, int, int, int)
	 */
	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
	}

	/* (non-Javadoc)
	 * @see android.view.View.OnFocusChangeListener#onFocusChange(android.view.View, boolean)
	 */
	@Override
	public void onFocusChange(View v, boolean hasFocus) {
		//Check conditions on loosing focus.
		if (!hasFocus)
			performCheck();
	}
}
