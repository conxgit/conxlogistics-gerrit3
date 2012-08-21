package com.conx.logistics.kernel.ui.editors.entity.vaadin.ext.upload;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.conx.logistics.kernel.documentlibrary.remote.services.IRemoteDocumentRepository;
import com.conx.logistics.mdm.domain.documentlibrary.Folder;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Form;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.ProgressIndicator;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.Upload;
import com.vaadin.ui.Upload.FinishedEvent;
import com.vaadin.ui.Upload.Receiver;
import com.vaadin.ui.Upload.StartedEvent;

public class AttachmentForm extends Form {
	private static final long serialVersionUID = -7906217975800435620L;
	
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	private GridLayout layout;

	private IRemoteDocumentRepository docRepo;

	private Folder docFolder;
	
	public AttachmentForm(IRemoteDocumentRepository docRepo) {
		this.docRepo = docRepo;
		
		layout = new GridLayout(4, 3);
		layout.setWidth("100%");
		layout.setMargin(true);
		layout.setSpacing(true);
		
		setLayout(layout);
		setSizeFull();
		initialize();
	}

	private void initialize() {
		final Label stateField = new Label();
		stateField.setCaption("Upload State");
		stateField.setValue("Idle");
		
//		final ComboBox fileType = new ComboBox();
//		fileType.setCaption("Attachment Type");
//		fileType.setInputPrompt("Select a file type");
//		fileType.setNullSelectionAllowed(false);
//		fileType.setContainerDataSource(JPAContainerFactory.make(DocType.class, em));
		
		final Label fileNameField = new Label();
		fileNameField.setCaption("File");
		fileNameField.setValue("No file uploaded");
		
		final ProgressIndicator progressBar = new ProgressIndicator();
		progressBar.setCaption("Progress");
        progressBar.setVisible(false);
		
		Upload upload = new Upload();
        upload.setImmediate(true);
        upload.setButtonCaption("Browse");
        upload.setReceiver(new Receiver() {
			private static final long serialVersionUID = 2055417259914716331L;

			@Override
			public OutputStream receiveUpload(String filename, String mimeType) {
				return new OutputStream() {
					
					@Override
					public void write(int b) throws IOException {
					}
				};
			}
		});
        upload.addListener(new Upload.StartedListener() {
			private static final long serialVersionUID = 6192550643127666044L;

			public void uploadStarted(StartedEvent event) {
                progressBar.setValue(0f);
                progressBar.setVisible(true);
                progressBar.setPollingInterval(500);
                stateField.setValue("Uploading");
                fileNameField.setValue(event.getFilename());
            }
        });
        upload.addListener(new Upload.ProgressListener() {
			private static final long serialVersionUID = 1830359180657976775L;

			public void updateProgress(long readBytes, long contentLength) {
                progressBar.setValue(new Float(readBytes / (float) contentLength));
            }

        });
        upload.addListener(new Upload.FinishedListener() {
			private static final long serialVersionUID = 982087759549669305L;

			public void uploadFinished(FinishedEvent event) {
				String sourceFileName = event.getFilename();
				String mimeType = event.getMIMEType();
				String title = (String)AttachmentForm.this.getItemDataSource().getItemProperty("").getValue();
				String description = (String)AttachmentForm.this.getItemDataSource().getItemProperty("").getValue();
				
				String folderId = Long.toString(AttachmentForm.this.docFolder.getFolderId());
				
				try {
					AttachmentForm.this.docRepo.addFileEntry(
							folderId, sourceFileName,
							mimeType, title, description);
				} catch (Exception e) {
					StringWriter sw = new StringWriter();
					e.printStackTrace(new PrintWriter(sw));
					String stacktrace = sw.toString();
					logger.error(stacktrace);
					throw new RuntimeException("Error uploading doc "+sourceFileName+" to server", e);
				}
				stateField.setValue("Idle");
                progressBar.setVisible(false);
            }
        });
		
		HorizontalLayout uploadLayout = new HorizontalLayout();
		uploadLayout.setSpacing(true);
		uploadLayout.addComponent(fileNameField);
		uploadLayout.addComponent(upload);
		uploadLayout.setComponentAlignment(upload, Alignment.BOTTOM_LEFT);
		
		TextArea description = new TextArea();
		description.setCaption("Description");
		description.setRows(3);
		description.setColumns(0);
		description.setWidth("100%");
		
		Button save = new Button("Save");
		save.addListener(new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				commit();
			}
		});
		
		HorizontalLayout toolStrip = new HorizontalLayout();
		toolStrip.setWidth("100%");
		toolStrip.setHeight("30px");
		toolStrip.addComponent(save);
        
        layout.addComponent(stateField);
//        layout.addComponent(fileType);
        layout.addComponent(uploadLayout);
        layout.addComponent(description, 0, 1, 1, 1);
        layout.addComponent(progressBar, 2, 0, 3, 0);
        layout.addComponent(toolStrip, 0, 2, 3, 2);
	}

	public IRemoteDocumentRepository getDocRepo() {
		return docRepo;
	}

	public Folder getDocFolder() {
		return docFolder;
	}

	public void setDocFolder(Folder docFolder) {
		this.docFolder = docFolder;
	}
	
}
