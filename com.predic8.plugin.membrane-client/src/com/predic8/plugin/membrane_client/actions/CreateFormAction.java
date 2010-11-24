/* Copyright 2010 predic8 GmbH, www.predic8.com

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License. */

package com.predic8.plugin.membrane_client.actions;

import org.eclipse.jface.action.Action;

import com.predic8.plugin.membrane_client.ui.PluginUtil;
import com.predic8.plugin.membrane_client.views.RequestView;
import com.predic8.wsdl.BindingOperation;

public class CreateFormAction extends Action {

	public static final String ID = "create form action";
	
	BindingOperation operation;
	
	public CreateFormAction() {
		setText("create form");
		setId(ID);
	}
	
	@Override
	public void run() {
		RequestView view = (RequestView)PluginUtil.showView(RequestView.VIEW_ID);
		view.updateView(operation, null);
	}
	
	public void setOperation(BindingOperation selection) {
		this.operation = selection;
	}
	
}
