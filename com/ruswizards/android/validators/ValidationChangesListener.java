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
 * Interface to receive notification when validator change it's state.
 * @author Dmitry Sitnikov
 */
public interface ValidationChangesListener {
	/**
	 * Fired when validator updates state.
	 * @param sender validator that change state.
	 * @param isValid is current state valid.
	 */
	public void onValidationStateUpdated(BaseValidator sender, boolean isValid);
}
