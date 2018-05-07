package com.infosupport.machinelearning.digitclassifier.services;

import com.microsoft.CNTK.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class DigitClassifierImpl implements DigitClassifier {
    private final Function modelFunction;
    private final DeviceDescriptor device;

    private DigitClassifierImpl(Function modelFunction, DeviceDescriptor device) {
        this.modelFunction = modelFunction;
        this.device = device;
    }

    @Override
    public int predict(byte[] pixels) {
        Variable features = modelFunction.getArguments().get(0);
        Variable predictedDigit = modelFunction.getOutputs().get(0);

        FloatVectorVector batch = new FloatVectorVector();

        batch.add(translateInput(pixels));

        Value inputValue = Value.createDenseFloat(features.getShape(), batch, device);

        UnorderedMapVariableValuePtr inputMap = new UnorderedMapVariableValuePtr();
        UnorderedMapVariableValuePtr outputMap = new UnorderedMapVariableValuePtr();

        inputMap.add(features, inputValue);
        outputMap.add(predictedDigit, null);

        modelFunction.evaluate(inputMap, outputMap, device);

        FloatVectorVector outputBuffer = new FloatVectorVector();
        outputMap.getitem(predictedDigit).copyVariableValueToFloat(predictedDigit, outputBuffer);

        FloatVector outputRecord = outputBuffer.get(0);
        float[] scores = getValuesFromVector(outputRecord);

        return argMax(scores);
    }

    private int argMax(float[] vectorValues) {
        int maxIndex = -1;
        float currentMax = 0.0f;

        for (int i = 0; i < vectorValues.length; i++) {
            if (currentMax < vectorValues[i]) {
                currentMax = vectorValues[i];
                maxIndex = i;
            }
        }

        return maxIndex;
    }

    private float[] getValuesFromVector(FloatVector outputRecord) {
        float[] vectorValues = new float[(int) outputRecord.size()];

        for (int index = 0; index < outputRecord.size(); index++) {
            float currentValue = outputRecord.get(index);

            vectorValues[index] = currentValue;
        }

        return vectorValues;
    }

    public static DigitClassifierImpl create() {
        DeviceDescriptor device = DeviceDescriptor.getCPUDevice();
        Function modelFunction = Function.load("model.onnx", device, ModelFormat.ONNX);

        return new DigitClassifierImpl(modelFunction, device);
    }

    private FloatVector translateInput(byte[] pixels) {
        FloatVector vector = new FloatVector();

        for (int index = 0; index < pixels.length; index++) {
            vector.add((float) pixels[index]);
        }

        return vector;
    }
}
