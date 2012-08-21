package com.conx.logistics.kernel.ui.editors.entity.vaadin.mvp.lineeditor;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.mvp.eventbus.EventBus;
import org.vaadin.mvp.presenter.IPresenter;
import org.vaadin.mvp.presenter.annotation.Presenter;

import com.conx.logistics.kernel.ui.components.domain.AbstractConXComponent;
import com.conx.logistics.kernel.ui.components.domain.attachment.AttachmentEditorComponent;
import com.conx.logistics.kernel.ui.components.domain.masterdetail.LineEditorComponent;
import com.conx.logistics.kernel.ui.components.domain.masterdetail.MasterDetailComponent;
import com.conx.logistics.kernel.ui.components.domain.note.NoteEditorComponent;
import com.conx.logistics.kernel.ui.editors.builder.vaadin.VaadinEntityEditorFactoryImpl;
import com.conx.logistics.kernel.ui.editors.entity.vaadin.mvp.AbstractEntityEditorEventBus;
import com.conx.logistics.kernel.ui.editors.entity.vaadin.mvp.ConfigurableBasePresenter;
import com.conx.logistics.kernel.ui.editors.entity.vaadin.mvp.ConfigurablePresenterFactory;
import com.conx.logistics.kernel.ui.editors.entity.vaadin.mvp.MultiLevelEntityEditorPresenter;
import com.conx.logistics.kernel.ui.editors.entity.vaadin.mvp.lineeditor.view.EntityLineEditorView;
import com.conx.logistics.kernel.ui.editors.entity.vaadin.mvp.lineeditor.view.IEntityLineEditorView;
import com.vaadin.addon.jpacontainer.EntityItem;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.ui.Component;

@Presenter(view = EntityLineEditorView.class)
public class EntityLineEditorPresenter extends ConfigurableBasePresenter<IEntityLineEditorView, EntityLineEditorEventBus>
implements Property.ValueChangeListener {
	private static final long serialVersionUID = 1L;

	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	private boolean initialized = false;
	private Map<IPresenter<?, ? extends EventBus>,EventBus> mvpCache = new HashMap<IPresenter<?,? extends EventBus>, EventBus>();

	private IPresenter<?, ? extends EventBus> attachmentsPresenter;

	private IPresenter<?, ? extends EventBus> notesPresenter;


	public EntityLineEditorPresenter() {
		super();
	}



	/**
	 * EventBus callbacks
	 */
	public void onStart(MultiLevelEntityEditorPresenter parentPresenter, AbstractEntityEditorEventBus entityEditorEventListener,  AbstractConXComponent aec, EntityManager em, HashMap<String,Object> extraParams) {
		try {
			this.getView().init();


		} catch (Exception e) {
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			String stacktrace = sw.toString();
			logger.error(stacktrace);
		}
	}

	//MultiLevelEntityEditorEventBus implementation
	public void onEntityItemEdit(EntityItem item) {
		for (EventBus  elesb : mvpCache.values())
		{
			((AbstractEntityEditorEventBus)elesb).entityItemEdit(item);
		}
	}
	
	@Override
	public void bind() {
		for (IPresenter<?, ? extends EventBus> presenter : mvpCache.keySet())
		{
			((IEntityLineEditorView)getView()).getMainLayout().addTab((Component)presenter.getView(),"!!!!");
		}
		
		//- Attachments
		if (attachmentsPresenter != null)
			((IEntityLineEditorView)getView()).getMainLayout().addTab((Component)attachmentsPresenter.getView(),"Attachements");

		//- Notes
		if (notesPresenter != null)
			((IEntityLineEditorView)getView()).getMainLayout().addTab((Component)attachmentsPresenter.getView(),"Notes");


		this.setInitialized(true);		
	}

	@Override
	public void valueChange(ValueChangeEvent event) {

	}

	public boolean isInitialized() {
		return initialized;
	}

	public void setInitialized(boolean initialized) {
		this.initialized = initialized;
	}

	@Override
	public void configure() {
		try {
			Map<String, Object> config = super.getConfig();
			EntityManager entityManager = (EntityManager)config.get("em");
			MasterDetailComponent metaData = (MasterDetailComponent)config.get("md");
			ConfigurablePresenterFactory presenterFactory = (ConfigurablePresenterFactory)config.get("presenterFactory");

			/**
			 * 1. Get LineEditor models
			 * 
			 * 2. For each, create LineEditorSection presenters
			 */

			//1. 
			Set<LineEditorComponent> lecs = metaData.getLineEditorPanel().getLineEditors();

			//2. 
			VaadinEntityEditorFactoryImpl entityFactory = new VaadinEntityEditorFactoryImpl(presenterFactory);
			IPresenter<?, ? extends EventBus>  elesp = null;
			AbstractEntityEditorEventBus elesb = null;
			Map<IPresenter<?, ? extends EventBus>, EventBus> entityMVP = null;
			for (LineEditorComponent lec : lecs)
			{
				presenterFactory.getCustomizer().getConfig().put("componentModel", lec.getContent());
				entityMVP = entityFactory.create(lec.getContent());
				if (entityMVP != null)
				{
					IPresenter<?, ? extends EventBus> presenter = entityMVP.keySet().iterator().next();
					AbstractEntityEditorEventBus bus = (AbstractEntityEditorEventBus)entityMVP.get(presenter);

					if (lec.getContent() instanceof AttachmentEditorComponent)
					{
						this.attachmentsPresenter = presenter;
					}
					else if (lec.getContent() instanceof NoteEditorComponent)
						this.notesPresenter = presenter;
					else //Other: simply add lec
					{
						mvpCache.putAll(entityMVP);
					}					
				}
			}
		} catch (Exception e) {
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			String stacktrace = sw.toString();
			logger.error(stacktrace);
		}
	}
}
