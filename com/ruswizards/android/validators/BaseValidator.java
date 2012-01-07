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

import java.util.ArrayList;
import java.util.Iterator;

import android.view.View;
import android.widget.TextView;

/**
 * Base validator class allow to check some conditions and notify about checking results.
 * To use:
 * 1. Create one not abstract validator.
 * 2. Set View to validate.
 * 3. Add as many checkers as you want (note: first added checker has highest priority).
 * All checker are sublasses of {@link BaseValidatorConditionChecker}.
 * 
 * @author Dmitry Sitnikov
 */
public abstract class BaseValidator {
	/**
	 * Validation mode.
	 */
	public enum ValidationMode {
		/** Check conditions automatically. On data changes, focus loose etc. */
		Auto,
		/** Check manually. */
		Manual
	}
	/**
	 * Set view to validate their data.
	 * @param view - view to validate.
	 * @param mode - validation mode.
	 * @throws IllegalArgumentException when view could not be validated.
	 */
	public abstract void setViewToValidate(View view, ValidationMode mode) throws IllegalArgumentException;
	
	/**
	 * Set view to validate their data.
	 * Overloaded method, launches {@link #setViewToValidate(View, ValidationMode)} with mode {@link ValidationMode#Auto}
	 */
	public void setViewToValidate(View view) throws IllegalArgumentException {
		setViewToValidate(view, ValidationMode.Auto);
	}
	
	/**
	 * Get text from validated view.
	 */
	protected abstract String getTextToValidate();
	
	/**
	 * Display an error message in external error view if is was set.
	 * When external error view not set does nothing.
	 * @param error - message, if no error pass null.
	 */
	protected void displayError(String error) {
		if (externalErrorView_ != null) {
			if (error != null) {
				externalErrorView_.setVisibility(View.VISIBLE);
				externalErrorView_.setText(error);
			} else {
				externalErrorView_.setVisibility(View.GONE);
			}
		}
	}

	/**
	 * Perform checks manually.
	 */
	public void performCheck() {
		displayError(checkConditions());
	}
	
	//External error view ------------------------------------------------------
	protected TextView externalErrorView_ = null;
	/**
	 * Set text view as a place to display error.
	 * Set errorView visibility to GONE at call.
	 */
	public void setExternalErrorView(TextView errorView) {
		resetExternalErrorView();
		externalErrorView_ = errorView;
		//Hide error-view at the begignig 
		if (externalErrorView_ != null) {
			externalErrorView_.setVisibility(View.GONE);
		}
	}
	/**
	 * Remove external error view.
	 */
	public void resetExternalErrorView() {
		if (externalErrorView_ == null)
			return;
		externalErrorView_ = null;
	}
	
	//Checkers -----------------------------------------------------------------
	
	/* List of checkers */
	private ArrayList<BaseValidatorConditionChecker> checkers_ = new ArrayList<BaseValidatorConditionChecker>();
	
	/**
	 * Add new checker to validator.
	 */
	public void addConditionChecker(BaseValidatorConditionChecker checker) {
		checkers_.add(checker);
	}
	
	/**
	 * Remove checker from validator.
	 */
	public void removeConditionChecker(BaseValidatorConditionChecker checker) {
		checkers_.remove(checker);
	}
	
	/**
	 * Remove all checkers from validator.
	 */
	public void clearCheckers() {
		checkers_.clear();
	}
	
	//Logic    -----------------------------------------------------------------
	
	/**
	 * Check all conditions.
	 * @return null - if all conditions are satisfied, Error-text - if one of conditions fails (in that case other conditions won't be checked).
	 */
	public String checkConditions() {
		for (Iterator<BaseValidatorConditionChecker> checkerIterator = checkers_.iterator(); checkerIterator.hasNext();) {
			BaseValidatorConditionChecker checker = (BaseValidatorConditionChecker) checkerIterator.next();
			if (!checker.checkCondition(this.getTextToValidate())) {
				notifyCurrentCheckState(false);
				return checker.getErrorMessage();
			}
		}
		notifyCurrentCheckState(true);
		return null;
	}

	
	//Changes notification    --------------------------------------------------
	private ValidationChangesListener changesListener_ = null;
	/**
	 * Set changes listener.
	 * Pass null to remove listener.
	 */
	void setChangesListener(ValidationChangesListener listener) {
		changesListener_ = listener;
	}
	/** Notify listener about next check. Work only if listener is present. */
	private void notifyCurrentCheckState(boolean isValid) {
		if (changesListener_ != null)
			changesListener_.onValidationStateUpdated(this, isValid);
	}
}
