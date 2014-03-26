package ru.doberman.core.fileupload;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.UploadedFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ru.doberman.core.SystemUtils;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.IOException;

/**
 * @author izerenev
 *         Date: 19.03.2014
 */
@ManagedBean
@RequestScoped
@Component
public class ImageUploadSingleMB implements ImageUpload {
  private static final Logger LOG = LoggerFactory.getLogger(ImageUploadSingleMB.class);

  private UploadedFile uploadedFile;

  private DefaultStreamedContent imageForView;

  public UploadedFile getUploadedFile() {
    return uploadedFile;
  }

  public void setUploadedFile(UploadedFile uploadedFile) {
    this.uploadedFile = uploadedFile;
  }

  public DefaultStreamedContent getImageForView() {
    return imageForView;
  }

  public void setImageForView(DefaultStreamedContent imageForView) {
    this.imageForView = imageForView;
  }

  public void handleUpload(FileUploadEvent event) {
    uploadedFile = event.getFile();
    try {
      imageForView = new DefaultStreamedContent(uploadedFile.getInputstream(), uploadedFile.getContentType());
    } catch (IOException e) {
      LOG.error(e.getMessage(), e);
      SystemUtils.showMessage("File upload failed");
      return;
    }
    SystemUtils.showMessage("File uploaded: " + uploadedFile.getFileName());
  }

  @Override
  public void resetUploadedFiles() {
    uploadedFile = null;
    imageForView = null;
  }
}
