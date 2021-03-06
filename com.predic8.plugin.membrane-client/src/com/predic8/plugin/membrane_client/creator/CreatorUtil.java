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

package com.predic8.plugin.membrane_client.creator;


import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;

import com.predic8.plugin.membrane_client.ImageKeys;
import com.predic8.plugin.membrane_client.ui.ControlUtil;
import com.predic8.plugin.membrane_client.ui.LayoutUtil;
import com.predic8.plugin.membrane_client.ui.PluginUtil;
import com.predic8.schema.Element;
import com.predic8.schema.restriction.BaseRestriction;

public class CreatorUtil {
	
	public static final Image REMOVE_IMAGE = PluginUtil.createImage("icons/cross.png", ImageKeys.IMAGE_CROSS_REMOVE);
	public static final Image ADD_IMAGE = PluginUtil.createImage("icons/add_element.png", ImageKeys.IMAGE_ADD_ELEMENT);
	public static final Image DELETE_IMAGE = PluginUtil.createImage("icons/delete.png", ImageKeys.IMAGE_DELETE);
	
	private static final Color COLOR_PARENT = new Color(Display.getCurrent(), 222, 220, 230);
	
	
	public static void createControls(Composite descendent, BaseRestriction restriction, CompositeCreatorContext ctx) {
		SimpleTypeCreatorFactory.getCreator(ctx).createControls(descendent, ctx, restriction);
	}

	public static void updateControl(Control control, boolean status, boolean visible) {
		control.setEnabled(status);
		if (visible) 
			control.setVisible(status);
	}

	public static void updateButtonControlEnable(final Control control, Button source, boolean visible) {
		if (source.getImage().equals(REMOVE_IMAGE)) {
			source.setImage(ADD_IMAGE);
			updateControl(control, false, visible);
		} else {
			source.setImage(REMOVE_IMAGE);
			updateControl(control, true, visible);
		}
	}

	public static void createAddRemoveButton(final Control control) {
		Button bt = ControlUtil.createButton(control.getParent(), REMOVE_IMAGE, 10, 10, 30);
		bt.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				updateButtonControlEnable(control, (Button) e.getSource(), false);
			}
		});
	}
	
	
	public static Button createAddButton(Composite parent) {
		return ControlUtil.createButton(parent, ADD_IMAGE, 10, 10, 30);
	}

	public static Button createDeleteButton(Composite parent) {
		return ControlUtil.createButton(parent, DELETE_IMAGE, 10, 10, 30);
	}
	
	public static ScrolledComposite createScrollComposite(Composite parent) {
		ScrolledComposite sC = new ScrolledComposite(parent, SWT.H_SCROLL | SWT.V_SCROLL | SWT.DOUBLE_BUFFERED | SWT.SMOOTH);
		sC.setExpandHorizontal(true);
		sC.setExpandVertical(true);
		sC.setLayout(new GridLayout());
		return sC;
	}

	public static Composite createRootComposite(Composite parent) {
		Composite root = new Composite(parent, SWT.NONE | SWT.DOUBLE_BUFFERED);
		root.setBackground(COLOR_PARENT);
		root.setLayout(LayoutUtil.createGridlayout(1, 5));
		root.setParent(parent);
		root.setLayoutData(LayoutUtil.createGridData(GridData.FILL_HORIZONTAL, GridData.FILL_VERTICAL, true, true));
		return root;
	}

	public static void layoutScrolledComposites(ScrolledComposite scrollComposite, Composite root) {
		root.layout();
		Point point = root.computeSize(SWT.DEFAULT, SWT.DEFAULT);
		scrollComposite.setMinSize(point);
		root.setSize(point);

		scrollComposite.setContent(root);

		scrollComposite.layout();
		root.layout();
	}
	
	public static String getComplexTypeCaption(CompositeCreatorContext ctx) {
		StringBuffer buf = new StringBuffer();
		buf.append(ctx.getDeclaration().getName().toString());
		buf.append(" (");
		buf.append(((Element)ctx.getDeclaration()).getMinOccurs());
		buf.append("..");
		buf.append(((Element)ctx.getDeclaration()).getMaxOccurs());
		buf.append(")");

		return buf.toString();
	}
}
