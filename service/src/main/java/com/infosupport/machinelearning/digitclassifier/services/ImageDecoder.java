package com.infosupport.machinelearning.digitclassifier.services;

import java.io.IOException;
import java.io.InputStream;

public interface ImageDecoder {
    byte[] decode(InputStream stream) throws IOException;
}
