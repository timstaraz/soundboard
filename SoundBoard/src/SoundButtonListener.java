import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class SoundButtonListener extends SelectionAdapter {
		Sound sound;
	
		public SoundButtonListener (Sound s) {
			sound = s;
		}
	 
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("pushed");
				try {				
					sound.start();
				} catch (LineUnavailableException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		
}
