package io.github.mmagicala.gnomeRestaurant.overlay;

public class OverlayHeader {

    public final String instruction;
    public final int stepNum;
    public final int totalSteps;

    public OverlayHeader(String instruction, int stepNum, int totalSteps) {
        this.instruction = instruction;
        this.stepNum = stepNum;
        this.totalSteps = totalSteps;
    }
}
