import java.io.File;
import java.io.IOException;

import javax.sound.sampled.*;


public class Sound {
	
	boolean isOpen = false;
	Clip audioClip;
	AudioInputStream audioStream; 
	
	public Sound (String filePath) throws LineUnavailableException {
		File audioFile = new File(filePath);	
		try {
			audioStream = AudioSystem.getAudioInputStream(audioFile);
			AudioFormat format = audioStream.getFormat();
			DataLine.Info info = new DataLine.Info(Clip.class, format);
			audioClip = (Clip) AudioSystem.getLine(info);
			
		} catch (UnsupportedAudioFileException e) {
			System.out.println("unsupported audio file exception, only wav files allowed!");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("io exception");
			e.printStackTrace();
		}		
	}
	
	public void start() throws LineUnavailableException, IOException {
		
		if (!isOpen) {	
			System.out.println("opening clip");
			audioClip.open(audioStream);
			isOpen = true;
		}
		audioClip.setFramePosition(0);
		audioClip.start();
	}
	
	public void stop() throws IOException {
		audioClip.close();
		audioStream.close();
	}
	
}
