package be.hogent.tarsos.dsp;

/**
 * With the gain processor it is possible to adapt the volume of the sound. With
 * a gain of 1, nothing happens. A gain greater than one is a volume increase a
 * gain between zero and one, exclusive, is a decrease. If you need to flip the
 * sign of the audio samples, you can by providing a gain of -1.0. but I have no
 * idea what you could gain by doing that (pathetic pun, I know).
 * 
 * @author Joren Six
 */
public class NewGainProcessor implements NewAudioProcessor {
	private double gain;
	
	public NewGainProcessor(double newGain) {
		setGain(newGain);
	}

	public void setGain(double newGain) {
		this.gain = newGain;
	}

	@Override
	public boolean process(AudioEvent audioEvent) {
		float[] audioFloatBuffer = audioEvent.getFloatBuffer();
		for (int i = audioEvent.getOverlap(); i < audioFloatBuffer.length ; i++) {
			float newValue = (float) (audioFloatBuffer[i] * gain);
			if(newValue > 1.0f) {
				newValue = 1.0f;
			} else if(newValue < -1.0f) {
				newValue = -1.0f;
			}
			audioFloatBuffer[i] = newValue;
		}
		return true;
	}
	
	@Override
	public void processingFinished() {
		// NOOP
	}
}