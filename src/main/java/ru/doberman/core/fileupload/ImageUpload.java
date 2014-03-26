package ru.doberman.core.fileupload;

import org.primefaces.event.FileUploadEvent;

/**
 * @author izerenev
 *         Date: 20.03.2014
 */
public interface ImageUpload {
  void handleUpload(FileUploadEvent event);

  void resetUploadedFiles();
}
