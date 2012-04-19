package de.jutzig.jabylon.ui.forms;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.vaadin.data.Item;
import com.vaadin.data.Validator;
import com.vaadin.data.util.BeanItem;
import com.vaadin.terminal.ErrorMessage;
import com.vaadin.terminal.UserError;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.Form;
import com.vaadin.ui.FormFieldFactory;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

import de.jutzig.jabylon.cdo.connector.Modification;
import de.jutzig.jabylon.cdo.connector.TransactionUtil;
import de.jutzig.jabylon.properties.Project;
import de.jutzig.jabylon.properties.ProjectLocale;
import de.jutzig.jabylon.properties.ProjectVersion;
import de.jutzig.jabylon.properties.PropertiesFactory;
import de.jutzig.jabylon.ui.applications.MainDashboard;
import de.jutzig.jabylon.ui.beans.LocaleBean;
import de.jutzig.jabylon.ui.breadcrumb.CrumbTrail;

public class NewLocaleForm extends VerticalLayout implements CrumbTrail{

	private ProjectVersion project;
	
	public NewLocaleForm(ProjectVersion project) {
		this.project = project;
		setMargin(true);
		createContents();

	}
	
	private void createContents() {
		  // Create the Form
        final Form form = new Form();
        form.setCaption("New Locale");
        form.setWriteThrough(true);
        form.setImmediate(true);
//        form.setInvalidCommitted(false); // no invalid values in datamodel
        final BeanItem<LocaleBean> localeItem = new BeanItem<LocaleBean>(new LocaleBean());
        form.setItemDataSource(localeItem);
        form.setFormFieldFactory(new DefaultFieldFactory() {
			
        	@Override
        	public Field createField(Item item, Object propertyId,
        			Component uiContext) {

        		Field field = super.createField(item, propertyId, uiContext);
        		field.addValidator(new ProjectLocaleValidator());
        		if(propertyId.equals("language"))
        			field.setRequired(true);
        		return field;
        	}
		});
        // Determines which properties are shown, and in which order:
        List<String> properties = new ArrayList<String>(3);
        properties.add("language");
        properties.add("country");
        properties.add("variant");
        form.setVisibleItemProperties(properties);

        // Add form to layout
        addComponent(form);

        // The cancel / apply buttons
        HorizontalLayout buttons = new HorizontalLayout();
        buttons.setSpacing(true);
        Button discardChanges = new Button("Cancel",
                new Button.ClickListener() {
                    public void buttonClick(ClickEvent event) {
                        form.discard();
                        MainDashboard.getCurrent().getBreadcrumbs().goBack();
                    }
                });
        buttons.addComponent(discardChanges);
        buttons.setComponentAlignment(discardChanges, Alignment.MIDDLE_LEFT);

        Button apply = new Button("Create", new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
                try {
                	if(!form.isValid())
                		return;
                	Locale newLocale = localeItem.getBean().createLocale();
                	ProjectLocale existingProjectLocale = project.getProjectLocale(newLocale);
                	if(existingProjectLocale!=null)
                	{
                		form.setComponentError(new UserError("The locale "+newLocale.toString()+" exists already"));
                		return;
                		
                	}
                    final ProjectLocale locale = PropertiesFactory.eINSTANCE.createProjectLocale();
                    locale.setLocale(newLocale);
                	project = TransactionUtil.commit(project, new Modification<ProjectVersion, ProjectVersion>() {
                		
                		@Override
                		public ProjectVersion apply(ProjectVersion object) {
                			
                			object.getLocales().add(locale);                			
                			return object;
                		}
					});
                		
					MainDashboard.getCurrent().getBreadcrumbs().goBack();
                    
                } catch (Exception e) {
                    // Ignored, we'll let the Form handle the errors
                	e.printStackTrace();
                }
            }
        });
        buttons.addComponent(apply);
        form.getFooter().addComponent(buttons);
        form.getFooter().setMargin(false, false, true, true);
		
	}


	@Override
	public CrumbTrail walkTo(String path) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Component getComponent() {
		return this;
	}

	@Override
	public String getTrailCaption() {
		return "Create Locale";
	}

	@Override
	public boolean isDirty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object getDomainObject() {
		return project;
	}
	
}

class ProjectLocaleValidator implements Validator
{

	@Override
	public void validate(Object value) throws InvalidValueException {
		if(!isValid(value))
		{
			throw new InvalidValueException("Must be a two letter code");
		}
		
	}

	@Override
	public boolean isValid(Object value) {
		if(value==null)
			return false;
		String string = value.toString();
		if(string.length()!=2)
			return false;
		return (Character.isLetter(string.charAt(0)) && Character.isLetter(string.charAt(0)));
	}
	
}