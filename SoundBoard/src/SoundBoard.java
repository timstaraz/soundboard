

import javax.sound.sampled.LineUnavailableException;

import org.eclipse.swt.*;
import org.eclipse.swt.events.*;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.*;

public class SoundBoard {
	 
	public static Shell shell;
	
	private static final class AddFileListener implements Listener {
		static Sound sound = null;
		static Text t;
		static Shell s;
		
		private static final class OkayButtonListener extends SelectionAdapter {
			public void widgetSelected(SelectionEvent event) {
				AddSound(t.getText(), sound);
				s.dispose();
			}
		}

		@Override
		public void handleEvent (Event e) {
			FileDialog fileDialog = new FileDialog(shell, SWT.NULL);
			String filePath = fileDialog.open();
			
			try {
				sound = new Sound(filePath);
			} catch (LineUnavailableException e1) {
				e1.printStackTrace();
			}
			
			
			s = new Shell(shell);
			s.setSize(230, 110);
			Label label = new Label(s, 0);
			t = new Text(s, 0);
			label.setText("Choose a name for your sound clip");
			Button okay = new Button(s, SWT.PUSH);
			okay.setText("Ok");
			okay.addSelectionListener(new OkayButtonListener());
			
			FillLayout layout = new FillLayout();
			layout.type = SWT.VERTICAL;
			s.setLayout(layout);
			s.open();
			
			
			
			
			
			
			
		}
	}

	public static void main(String[] args) {
		
//		//test set up a sound file
//		Sound testSound = null;
//		Sound testSound2 = null;
//		try {
//			testSound = new Sound("audio\\Mario.wav");
//			testSound2 = new Sound("audio\\Heyyy.wav");
//		} catch (LineUnavailableException e1) {
//			System.out.println("nope");
//			e1.printStackTrace();
//		}
//		//mario
//		Button mario = new Button(shell, SWT.PUSH);
//		mario.setText("mario");
//		SelectionAdapter listener = new SoundButtonListener(testSound);
//		mario.addSelectionListener(listener);
//		
//		//claptrap heyyy
//		Button claptrap = new Button(shell, SWT.PUSH);
//		claptrap.setText("claptrap");
//		SelectionAdapter listener2 = new SoundButtonListener(testSound2);
//		claptrap.addSelectionListener(listener2);
		
		
		//set up the gui
		Display display = new Display();
		shell = new Shell(display);
		shell.setText("Soundboard");
		Menu menubar = new Menu(shell, SWT.BAR);
		shell.setMenuBar(menubar);
		MenuItem menuitem = new MenuItem(menubar, SWT.CASCADE);
		menuitem.setText ("File");
		
		Menu submenu = new Menu (shell, SWT.DROP_DOWN);
		menuitem.setMenu(submenu);
		MenuItem item = new MenuItem (submenu, SWT.PUSH);
		item.addListener (SWT.Selection, new AddFileListener());
		
		item.setText ("Add File");
		item.setAccelerator (SWT.MOD1 + 'A');
		shell.setSize (600, 200);
		


		shell.setLayout(new RowLayout());
		shell.open();
		

		
		//idle
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) display.sleep();
		}
		display.dispose();

	}
	
	public static void AddSound(String name, Sound sound) {
		Button b = new Button(shell, SWT.PUSH);			
		b.setText(name);
		SelectionAdapter listener = new SoundButtonListener(sound);
		b.addSelectionListener(listener);
		shell.layout();
	}
	


}
