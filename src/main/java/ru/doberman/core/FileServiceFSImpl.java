package ru.doberman.core;

import org.apache.commons.io.FileUtils;

import javax.faces.context.FacesContext;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author izerenev
 *         Date: 24.03.2014
 */
public class FileServiceFSImpl implements FileService {
  @Override
  public File getFileById(String id) {
    return new File(id);
  }

  @Override
  public String uploadFile(InputStream inputStream, String path) throws IOException {
    String fullPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("") + path;
    File bigImage = new File(fullPath);
    FileUtils.copyInputStreamToFile(inputStream, bigImage);
    return path;
  }
}
