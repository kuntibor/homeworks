package xyz.codingmentor.tiborkun.database;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import xyz.codingmentor.tiborkun.database.exception.RepositoryException;

/**
 *
 * @author Tibor Kun
 */
public class Main {

    private Main() {
        // nothing to initialize
    }

    public static void main(String[] args) throws RepositoryException {
        Weld weld = new Weld();
        WeldContainer container = weld.initialize();

        Application application = container.instance().select(Application.class).get();
        application.execute();

        weld.shutdown();

    }

}
