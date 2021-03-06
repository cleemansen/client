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

package com.predic8.plugin.membrane_client.providers;

import java.util.List;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import com.predic8.membrane.client.core.controller.ExchangeNode;
import com.predic8.membrane.client.core.controller.ServiceParamsManager;
import com.predic8.membrane.client.core.model.ServiceParams;
import com.predic8.wsdl.BindingOperation;
import com.predic8.wsdl.Definitions;
import com.predic8.wsdl.Port;
import com.predic8.wsdl.Service;

public class ServiceTreeContentProvider implements ITreeContentProvider {

	@SuppressWarnings("rawtypes")
	
	public Object[] getChildren(Object parent) {
		
		if (parent instanceof List) {
			return ((List)parent).toArray();
		}
		
		if (parent instanceof ServiceParams) {
			Definitions definitions = ((ServiceParams)parent).getDefinitions();
			if (definitions == null)
				return new Object[0];
			
			return definitions.getServices().toArray();
		}
	
		if (parent instanceof Service) {
			return ((Service)parent).getPorts().toArray();
		}
		
		if (parent instanceof Port) {
			return ((Port)parent).getBinding().getOperations().toArray();	
		}
		
		if (parent instanceof BindingOperation) {
			List<ExchangeNode> excs = ServiceParamsManager.getInstance().getExchangesFor((BindingOperation)parent);
			if (excs == null)
				return new Object[0];
			
			return excs.toArray();
		}
		
		if (parent instanceof ExchangeNode) {
			ExchangeNode exc = (ExchangeNode)parent;
			return new Object[] {exc.getParamsMap(), exc.getResponse()};
		}
		
		return null;
	}
	
	public Object getParent(Object element) {
		
		return null;
	}

	public boolean hasChildren(Object element) {
		if (element instanceof List)
			return true;
		
		if (element instanceof Port)
			return true;
		
		if (element instanceof ServiceParams)
			return true;
		
		if (element instanceof Service)
			return true;
		
		if (element instanceof BindingOperation)
			return true;
		
		if (element instanceof ExchangeNode)
			return true;
		
		return false;
	}
	
	public Object[] getElements(Object inputElement) {
		return getChildren(inputElement);
	}

	public void dispose() {
		
	}

	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		
	}

}
