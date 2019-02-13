package ch.so.agi.ilivalidator.webservice.jsf;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.Part;

import org.apache.commons.io.FilenameUtils;

@Named("ilivalidator")
@ViewScoped
public class IlivalidatorModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private Part iliTransferFile;

    public Part getIliTransferFile() {
        return iliTransferFile;
    }

    public void setIliTransferFile(Part iliTransferFile) {
        this.iliTransferFile = iliTransferFile;
    }

    public String processFileUpload() {
        try {
            Path tmpDir = Files.createTempDirectory(Paths.get(System.getProperty("java.io.tmpdir")), "ilivalidator_");
            Part uploadedFile = getIliTransferFile();
            final Path destination = Paths.get(tmpDir.toString(), FilenameUtils.getName(uploadedFile.getSubmittedFileName()));
        
            System.out.println(destination);
        
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
