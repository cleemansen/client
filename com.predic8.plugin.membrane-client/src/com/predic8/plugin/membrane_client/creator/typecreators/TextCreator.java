package com.predic8.plugin.membrane_client.creator.typecreators;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;

import com.predic8.plugin.membrane_client.ui.PluginUtil;
import com.predic8.plugin.membrane_client.ui.RegexVerifierListener;
import com.predic8.schema.restriction.BaseRestriction;

public abstract class TextCreator extends SimpleTypeControlCreator {

	@Override
	public Control getActiveControl(Composite parent, BaseRestriction restriction) {
		Text text = PluginUtil.createText(parent, WIDGET_WIDTH, WIDGET_HEIGHT);
		text.addVerifyListener(new RegexVerifierListener(getRegEx()));
		return text;
		
	}
}
