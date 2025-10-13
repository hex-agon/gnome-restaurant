package io.github.mmagicala.gnomeRestaurant.overlay;


public class OverlayHeader
{
	public final String instruction;
	public final int stepNum, totalSteps;

	public OverlayHeader(String instruction, int stepNum, int totalSteps)
	{
		this.instruction = instruction;
		this.stepNum = stepNum;
		this.totalSteps = totalSteps;
	}
}
