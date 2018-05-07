package com.infosupport.machinelearning.digitclassifier.controllers;

import com.infosupport.machinelearning.digitclassifier.models.ClassificationResult;
import com.infosupport.machinelearning.digitclassifier.models.GenericError;
import com.infosupport.machinelearning.digitclassifier.services.DigitClassifier;
import com.infosupport.machinelearning.digitclassifier.services.ImageDecoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class ClassifyImageController {
    private final ImageDecoder decoder;
    private final DigitClassifier classifier;

    @Autowired
    public ClassifyImageController(ImageDecoder decoder, DigitClassifier classifier) {
        this.decoder = decoder;
        this.classifier = classifier;
    }

    @RequestMapping(value = "/api/predict", method = RequestMethod.POST)
    public ResponseEntity<Object> classifyImage(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body(new GenericError("Please upload a valid image."));
        }

        try {
            byte[] pixels = decoder.decode(file.getInputStream());
            int digit = classifier.predict(pixels);

            return ResponseEntity.ok(new ClassificationResult(digit));
        } catch (IOException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new GenericError("Failed to process image."));
        }
    }
}
