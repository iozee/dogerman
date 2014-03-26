package ru.doberman.core.fileupload;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.springframework.stereotype.Component;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.ArrayList;
import java.util.List;

/**
 * @author izerenev
 *         Date: 19.03.2014
 */
@ManagedBean
@RequestScoped
@Component
public class ImageUploadMultipleMB implements ImageUpload {
  private List<UploadedFile> uploadedImages = new ArrayList<UploadedFile>();

  public List<UploadedFile> getUploadedImages() {
    return uploadedImages;
  }

  public void setUploadedImages(List<UploadedFile> uploadedImages) {
    this.uploadedImages = uploadedImages;
  }

  public void handleUpload(FileUploadEvent event) {
    uploadedImages.add(event.getFile());
  }

  @Override
  public void resetUploadedFiles() {
    uploadedImages.clear();
  }
}
