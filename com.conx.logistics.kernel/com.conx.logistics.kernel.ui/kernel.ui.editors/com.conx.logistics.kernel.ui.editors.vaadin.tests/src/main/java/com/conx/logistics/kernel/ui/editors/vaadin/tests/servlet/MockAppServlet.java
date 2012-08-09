package com.conx.logistics.kernel.ui.editors.vaadin.tests.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.conx.logistics.app.whse.rcv.rcv.dao.services.IReceiveDAOService;
import com.conx.logistics.kernel.ui.editors.vaadin.tests.MockApp;
import com.vaadin.Application;
import com.vaadin.terminal.gwt.server.AbstractApplicationServlet;

@Service
public class MockAppServlet extends AbstractApplicationServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7505600789309312501L;

	protected final Logger log = LoggerFactory.getLogger(getClass().getName());
	
	static private MockApp mockApp;

	@Autowired
	public void setMainApp(MockApp mockApp) {
		MockAppServlet.mockApp = mockApp;
	}

    @Override
    protected Class<? extends Application> getApplicationClass() {
        return MockApp.class;
    }

    @Override
    protected Application getNewApplication(HttpServletRequest request)	throws ServletException {
        return mockApp;
    }
}
