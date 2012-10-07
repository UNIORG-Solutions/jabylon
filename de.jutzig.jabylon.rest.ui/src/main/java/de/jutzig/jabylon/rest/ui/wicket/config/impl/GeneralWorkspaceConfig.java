package de.jutzig.jabylon.rest.ui.wicket.config.impl;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.model.IModel;
import org.osgi.service.prefs.Preferences;

import de.jutzig.jabylon.properties.Workspace;
import de.jutzig.jabylon.rest.ui.wicket.config.AbstractConfigSection;

public class GeneralWorkspaceConfig extends AbstractConfigSection<Workspace>{

	private static final long serialVersionUID = 1L;

	@Override
	public WebMarkupContainer createContents(String id, IModel<Workspace> input, Preferences prefs) {
		return new WorkspaceConfigSection(id, input, prefs);
	}

	@Override
	public void commit(IModel<Workspace> input, Preferences config) {
		// TODO Auto-generated method stub
		
	}


}
