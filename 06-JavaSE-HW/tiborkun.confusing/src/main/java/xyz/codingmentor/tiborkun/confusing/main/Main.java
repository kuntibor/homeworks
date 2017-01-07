package xyz.codingmentor.tiborkun.confusing.main;

import java.util.logging.Level;
import java.util.logging.Logger;
import xyz.codingmentor.tiborkun.confusing.annotation.Confusing;
import xyz.codingmentor.tiborkun.confusing.reflection.Reflection;

/**
 *
 * @author teiep
 */
@Confusing(author = "Big Troll")
public class Main {

    private static final Logger LOGGER = Logger.getAnonymousLogger();

    private Main() {
        //hide the implicit public constructor
    }

    public static void main(String[] args) {
        Reflection.analyze(Main.class);
    }

    @Confusing
    public static int foo(int count, String name, double price, Object object) {
        LOGGER.log(Level.INFO, "{0}{1}{2}{3}", new Object[]{count, name, price, object});
        return 1;
    }

    @Confusing()
    public static void fooFoo() {
        //no idea
    }

}
