package com.infosupport.machinelearning.digitclassifier.services;

import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.IndexColorModel;
import java.io.IOException;
import java.io.InputStream;

@Service
public class ImageDecoderImpl implements ImageDecoder {
    @Override
    public byte[] decode(InputStream stream) throws IOException {
        BufferedImage uploadedImage = ImageIO.read(stream);
        BufferedImage scaledImage = scale(uploadedImage);

        byte[] pixels = new byte[scaledImage.getWidth() * scaledImage.getHeight()];

        for (int row = 0; row < scaledImage.getHeight(); row++) {
            for (int col = 0; col < scaledImage.getWidth(); col++) {
                int offset = row * scaledImage.getWidth() + col;

                // We're using the intensity of the red channel.
                // The assumption is that the scaled image is binary black/white, so all channels are equal.
                pixels[offset] = (byte) new Color(scaledImage.getRGB(col, row)).getRed();
            }
        }

        return pixels;
    }

    private BufferedImage scale(BufferedImage image) {
        // To keep things simple we ask the user to upload a square image.
        // With that assumption in mind, we can just grab a single side and use it to scale.
        double scale = 8.0 / (double) image.getWidth();
        int newWidth = (int) Math.round(scale * image.getWidth());
        int newHeight = (int) Math.round(scale * image.getHeight());

        // In addition to scaling the image, we're also converting it to pure black and white.
        // Our model isn't trained for colors, so this is a necessary step to make the model work correctly.
        BufferedImage outputImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_BYTE_BINARY);
        AffineTransform transform = AffineTransform.getScaleInstance(scale, scale);
        Graphics2D graphics = outputImage.createGraphics();

        graphics.drawRenderedImage(image, transform);

        return outputImage;
    }
}
