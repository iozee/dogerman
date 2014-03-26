package ru.doberman.core;

import org.apache.commons.io.FilenameUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * @author izerenev
 *         Date: 22.03.2014
 */
public class ImageResizer {
  public InputStream resize(InputStream image, String thumbRelativePath, int width, int height) throws IOException {
    BufferedImage originalImage = getBufferedImage(image);
    BufferedImage thumbImage = getCompatibleImage(width, height);
    Graphics2D g2d = thumbImage.createGraphics();
    double xScale = (double) width / originalImage.getWidth();
    double yScale = (double) height / originalImage.getHeight();
    AffineTransform at = AffineTransform.getScaleInstance(xScale, yScale);
    g2d.drawRenderedImage(originalImage, at);
    g2d.dispose();

    String extension = FilenameUtils.getExtension(thumbRelativePath);
    ByteArrayOutputStream os = new ByteArrayOutputStream();
    ImageIO.write(thumbImage, extension, os);
    return new ByteArrayInputStream(os.toByteArray());
  }

  private BufferedImage getCompatibleImage(int w, int h) {
    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
    GraphicsDevice gd = ge.getDefaultScreenDevice();
    GraphicsConfiguration gc = gd.getDefaultConfiguration();
    return gc.createCompatibleImage(w, h);
  }

  private BufferedImage getBufferedImage(InputStream image) throws IOException {
    BufferedImage in = ImageIO.read(image);
    BufferedImage newImage = new BufferedImage(in.getWidth(), in.getHeight(), BufferedImage.TYPE_INT_ARGB);
    Graphics2D g = newImage.createGraphics();
    g.drawImage(in, 0, 0, null);
    g.dispose();
    return newImage;
  }
}
