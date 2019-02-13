package ch.so.agi.ilivalidator.webservice.jsf;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

@Named
@ViewScoped
public class FileUploadView {
    private UploadedFile file;
    
    private boolean buttonDisabled = false;
    
    public UploadedFile getFile() {
        return file;
    }
 
    public void setFile(UploadedFile file) {
        this.file = file;
    }
     
    public boolean isButtonDisabled() {
        return buttonDisabled;
    }

    public void setButtonDisabled(boolean buttonDisabled) {
        this.buttonDisabled = buttonDisabled;
    }

    public void upload() {
        System.out.println("****"+file.getFileName());
        
        buttonDisabled = true;
        
        if (file == null || file.getFileName().equalsIgnoreCase("")) {
            FacesContext saveContext = FacesContext.getCurrentInstance();
            saveContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Upload file required"));
        }
        
        
        if(file != null && !file.getFileName().equalsIgnoreCase("")) {
            FacesContext saveContext = FacesContext.getCurrentInstance();
            saveContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Succesful", file.getFileName() + " is uploaded."));
        }
    }
     
    public void handleFileUpload(FileUploadEvent event) {
        FacesMessage msg = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}
