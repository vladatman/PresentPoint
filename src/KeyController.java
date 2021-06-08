import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

/** <p>This is the KeyController (KeyListener)</p>
 * @author  Atamanchuk, vladataman44@gmail.com
 * @version 1.0 2021/06/03 Vladyslav Atamanchuk
*/

public class KeyController extends KeyAdapter {
	private final Presentation presentation; //Commands are given to the presentation

	public KeyController(Presentation p) {
		presentation = p;
	}

	/**
	 * Runs a command, depending on key event
	 * @param keyEvent pressed key
	 */
	public void keyPressed(KeyEvent keyEvent) {
		switch(keyEvent.getKeyCode()) {
			case KeyEvent.VK_PAGE_DOWN:
			case KeyEvent.VK_DOWN:
			case KeyEvent.VK_ENTER:
			case '+':
				getPresentation().nextSlide();
				break;
			case KeyEvent.VK_PAGE_UP:
			case KeyEvent.VK_UP:
			case '-':
				getPresentation().prevSlide();
				break;
			case 'q':
			case 'Q':
				System.exit(0);
				break; //Should not be reached
			default:
				break;
		}
	}

	public Presentation getPresentation() {
		return presentation;
	}
}
