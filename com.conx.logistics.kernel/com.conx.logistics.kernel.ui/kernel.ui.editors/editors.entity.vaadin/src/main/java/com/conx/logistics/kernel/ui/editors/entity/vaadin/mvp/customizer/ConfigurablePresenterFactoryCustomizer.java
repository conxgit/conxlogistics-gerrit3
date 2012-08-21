package com.conx.logistics.kernel.ui.editors.entity.vaadin.mvp.customizer;

import java.util.Map;

import org.vaadin.mvp.presenter.IPresenter;
import org.vaadin.mvp.presenter.IPresenterFactoryCustomizer;

import com.conx.logistics.kernel.ui.editors.entity.vaadin.mvp.ConfigurableBasePresenter;

public class ConfigurablePresenterFactoryCustomizer implements
		IPresenterFactoryCustomizer {
	
	private Map<String,Object> config;

	public ConfigurablePresenterFactoryCustomizer(Map<String, Object> config) {
		super();
		this.config = config;
	}

	@Override
	public void customize(IPresenter presenter) {
		ConfigurableBasePresenter configPresenter = (ConfigurableBasePresenter)presenter;
		configPresenter.setConfig(this.config);
	}

	public Map<String, Object> getConfig() {
		return config;
	}

	public void setConfig(Map<String, Object> config) {
		this.config = config;
	}
}
