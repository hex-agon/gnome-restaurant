package io.github.mmagicala.gnomeRestaurant.overlay;

import lombok.Getter;

public class OverlayHeader
{
	public String instruction;
	public int stepNum, totalSteps;

	public OverlayHeader(String instruction, int stepNum, int totalSteps)
	{
		this.instruction = instruction;
		this.stepNum = stepNum;
		this.totalSteps = totalSteps;
	}
}
