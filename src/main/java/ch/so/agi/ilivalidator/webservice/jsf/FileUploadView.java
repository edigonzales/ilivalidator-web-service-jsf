package ch.so.agi.ilivalidator.webservice.jsf;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.primefaces.model.UploadedFile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Named
@ViewScoped
public class FileUploadView {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private UploadedFile iliTransferFile;
    
    private boolean buttonDisabled = false;
    
    public UploadedFile getIliTransferFile() {
        return iliTransferFile;
    }
 
    public void setIliTransferFile(UploadedFile iliTransferFile) {
        this.iliTransferFile = iliTransferFile;
    }
     
    public boolean isButtonDisabled() {
        return buttonDisabled;
    }

    public void setButtonDisabled(boolean buttonDisabled) {
        this.buttonDisabled = buttonDisabled;
    }

    public void upload() {
        // disable button in browser
        buttonDisabled = true;
        
        if (iliTransferFile == null || iliTransferFile.getFileName().equalsIgnoreCase("")) {
            FacesContext saveContext = FacesContext.getCurrentInstance();
            saveContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Upload file required"));
            
            // enable button in browser
            buttonDisabled = false;

            return;
        }
        
        FacesContext saveContext = FacesContext.getCurrentInstance();
        saveContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Information", iliTransferFile.getFileName() + " is uploaded and being processed."));
  
        // Save uploaded file.
        String filename = FilenameUtils.getName(iliTransferFile.getFileName());
        File uploadedFile = null;
        try {
            InputStream input = iliTransferFile.getInputstream();
            
            Path tmpDir = Files.createTempDirectory(Paths.get(System.getProperty("java.io.tmpdir")), "ilivalidator_");
            uploadedFile = new File(tmpDir.toFile().getAbsolutePath(), filename);
            OutputStream output = new FileOutputStream(uploadedFile);

            try {
                IOUtils.copy(input, output);  
            } finally {
                IOUtils.closeQuietly(input);
                IOUtils.closeQuietly(output);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } 
    
        log.info(uploadedFile.getAbsolutePath());
        
        // enable button in browser
        buttonDisabled = false;
    }
}
