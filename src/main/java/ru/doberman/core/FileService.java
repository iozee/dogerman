package ru.doberman.core;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author izerenev
 *         Date: 24.03.2014
 */
public interface FileService {
  File getFileById(String id);

  String uploadFile(InputStream inputStream, String path) throws IOException;
}
