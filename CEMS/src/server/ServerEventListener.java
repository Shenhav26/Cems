package server;

/**
 * Interface that demonstrate Server event listener in order to handle events
 * that occurred by server.
 * 
 * @author Arikz ,Dvir ben simon
 */
public interface ServerEventListener {
	void printToLog(String logs);

	void changeButtonStatus(boolean status);
}
