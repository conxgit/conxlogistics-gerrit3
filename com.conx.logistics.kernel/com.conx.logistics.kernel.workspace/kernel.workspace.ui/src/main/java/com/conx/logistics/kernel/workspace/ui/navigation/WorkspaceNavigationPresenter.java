package com.conx.logistics.kernel.workspace.ui.navigation;

import org.vaadin.mvp.presenter.BasePresenter;
import org.vaadin.mvp.presenter.annotation.Presenter;

import com.conx.logistics.kernel.ui.common.gwt.client.ui.ConXNavigationAccordion;
import com.conx.logistics.kernel.ui.common.gwt.client.ui.ConXNavigationTree;
import com.conx.logistics.kernel.ui.common.ui.feature.Feature;
import com.conx.logistics.kernel.ui.common.ui.feature.FeatureSet;
import com.conx.logistics.kernel.workspace.ui.WorkspaceEventBus;
import com.conx.logistics.kernel.workspace.ui.navigation.view.IWorkspaceNavigationView;
import com.conx.logistics.kernel.workspace.ui.navigation.view.WorkspaceNavigationView;
import com.vaadin.data.Property;
import com.vaadin.data.util.HierarchicalContainer;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.ui.Field.ValueChangeEvent;
import com.vaadin.ui.Tree;

@Presenter(view = WorkspaceNavigationView.class)
public class WorkspaceNavigationPresenter extends
		BasePresenter<IWorkspaceNavigationView, WorkspaceEventBus> {

	private FeatureSet allFeatures;
	private final ObjectProperty<Feature> currentFeature = new ObjectProperty<Feature>(
			null, Feature.class);
	private ConXNavigationAccordion navigationTree;

	@Override
	public void bind() {
		// Menu tree, initially shown
		this.navigationTree = createMenuTree();
		this.navigationTree.setSizeFull();
		this.view.getMainLayout().addComponent(navigationTree);
	}

	private ConXNavigationAccordion createMenuTree() {
		final ConXNavigationAccordion tree = new ConXNavigationAccordion();
		try {
			// -- Create datasource
			Feature userInfo = new Feature("KERNEL.WORKSPACE.PROFILE.BASICVIEW","Basic", "Basic User Details", null,Feature.Version.V67);
			Feature userContactInfo = new Feature("KERNEL.WORKSPACE.PROFILE.CONTACTSVIEW","Contacts", "User Contacts",null, Feature.Version.V67);
			FeatureSet profileFS = new FeatureSet("Profile", "Profile",
					new Feature[] { userInfo, userContactInfo });
			this.allFeatures = new FeatureSet("Home", "Home",
					new Feature[] { profileFS });

			// -- Populate tree
			tree.setImmediate(true);
			tree.setNamePropertyId("Name");
			tree.setContainer(allFeatures.getContainer(true));
			currentFeature.addListener(new Property.ValueChangeListener() {
				@Override
				public void valueChange(
						com.vaadin.data.Property.ValueChangeEvent event) {
					Feature f = (Feature) event.getProperty().getValue();
					Feature v = (Feature) tree.getValue();
					if ((f != null && !f.equals(v)) || (f == null && v != null)) {
						tree.setValue(f);
					}
				}
			});
			tree.addNavigationListener(new Property.ValueChangeListener() {
				@Override
				public void valueChange(
						com.vaadin.data.Property.ValueChangeEvent event) {
					Feature f = (Feature) event.getProperty().getValue();
					setFeature(f);
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tree;
	}

	private HierarchicalContainer getAllFeatures() {
		return this.allFeatures.getContainer(true);
	}

	/**
	 * Displays a Feature(Set)
	 * 
	 * @param f
	 *            the Feature(Set) to show
	 */
	public void setFeature(Feature f) {
		if (f == this.allFeatures) {
			// "All" is no longer in the tree, use null instead
			f = null;
		}
		currentFeature.setValue(f);
		String fragment = (f != null ? f.getFragmentName() : "");
		
		this.getEventBus().openFeatureView(f);
		//updateFeatureList(currentList);
	}

	public void onSelectMenu(ValueChangeEvent event) {
		// get the selected menu entry and initiate another event
		// MenuEntry menuEntry = (MenuEntry) this.view.getTree().getValue();
		// this.eventBus.openModule(menuEntry.getPresenterType());
	}

}
