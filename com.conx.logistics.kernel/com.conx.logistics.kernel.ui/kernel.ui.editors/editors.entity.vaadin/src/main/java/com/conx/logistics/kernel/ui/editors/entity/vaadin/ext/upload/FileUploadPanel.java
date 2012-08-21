package com.conx.logistics.kernel.ui.editors.entity.vaadin.ext.upload;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.ProgressIndicator;
import com.vaadin.ui.Upload;
import com.vaadin.ui.Upload.FinishedEvent;
import com.vaadin.ui.Upload.StartedEvent;
import com.vaadin.ui.VerticalLayout;

public class FileUploadPanel extends VerticalLayout {
	private static final long serialVersionUID = -3741528815922598608L;
	
	private Label state;
    private Label fileName;
    private Label textualProgress;
    private ProgressIndicator progressBar;
    private Upload upload;

    public FileUploadPanel() {
    	state = new Label();
        fileName = new Label();
        textualProgress = new Label();
        progressBar = new ProgressIndicator();
        upload = new Upload();
        
        final Button cancelProcessing = new Button("Cancel");
        cancelProcessing.addListener(new Button.ClickListener() {
        	private static final long serialVersionUID = 2430978869457280905L;

			public void buttonClick(ClickEvent event) {
                upload.interruptUpload();
            }
        });
        cancelProcessing.setVisible(false);
        
        HorizontalLayout stateLayout = new HorizontalLayout();
        stateLayout.setSpacing(true);
        stateLayout.addComponent(state);
        stateLayout.addComponent(cancelProcessing);
        stateLayout.setCaption("Current state");
        
        state.setValue("Idle");
        fileName.setCaption("File name");
        progressBar.setCaption("Progress");
        progressBar.setVisible(false);
        textualProgress.setVisible(false);
        
        VerticalLayout formLayout = new VerticalLayout();
        formLayout.setWidth("100%");
        formLayout.addComponent(stateLayout);
        formLayout.addComponent(fileName);
        formLayout.addComponent(textualProgress);
        formLayout.addComponent(progressBar);

        VerticalLayout statusLayout = new VerticalLayout();
        statusLayout.setWidth("100%");
        statusLayout.addComponent(formLayout);
        statusLayout.setExpandRatio(formLayout, 1.0f);
        
        upload.setImmediate(true);
        upload.setButtonCaption("Upload Attachment");
        upload.addListener(new Upload.StartedListener() {
			private static final long serialVersionUID = 6192550643127666044L;

			public void uploadStarted(StartedEvent event) {
                progressBar.setValue(0f);
                progressBar.setVisible(true);
                progressBar.setPollingInterval(500);
                textualProgress.setVisible(true);
                state.setValue("Uploading");
                fileName.setValue(event.getFilename());
                cancelProcessing.setVisible(true);
            }
        });
        upload.addListener(new Upload.ProgressListener() {
			private static final long serialVersionUID = 1830359180657976775L;

			public void updateProgress(long readBytes, long contentLength) {
                progressBar.setValue(new Float(readBytes / (float) contentLength));
                textualProgress.setValue("Processed " + readBytes
                        + " bytes of " + contentLength);
            }

        });
        upload.addListener(new Upload.FinishedListener() {
			private static final long serialVersionUID = 982087759549669305L;

			public void uploadFinished(FinishedEvent event) {
				//Send to DocLib
                state.setValue("Idle");
                progressBar.setVisible(false);
                textualProgress.setVisible(false);
                cancelProcessing.setVisible(false);
            }
        });
        
        setWidth("100%");
        addComponent(statusLayout);
        addComponent(upload);
    }
}