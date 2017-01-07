package xyz.codingmentor.tiborkun.generictree.main;

import java.util.logging.Level;
import java.util.logging.Logger;
import xyz.codingmentor.tiborkun.generictree.GenericTree;
import static xyz.codingmentor.tiborkun.generictree.GenericTree.GenericTreeOrderType.*;

/**
 *
 * @author teiep
 */
public class Main {

    private static final Logger LOGGER = Logger.getAnonymousLogger();

    private Main() {
        //hide the implicit public constructor
    }

    public static void main(String[] args) {
        GenericTree<Integer> root = new GenericTree<>(0);
        GenericTree<Integer> node1 = new GenericTree<>(1);
        root.addChild(node1);
        GenericTree<Integer> node2 = new GenericTree<>(2);
        root.addChild(node2);
        GenericTree<Integer> node3 = new GenericTree<>(3);
        node2.addChild(node3);
        GenericTree<Integer> node4 = new GenericTree<>(4);
        node2.addChild(node4);
        GenericTree<Integer> node5 = new GenericTree<>(5);
        node3.addChild(node5);
        GenericTree<Integer> node6 = new GenericTree<>(6);
        node3.addChild(node6);
        GenericTree<Integer> node7 = new GenericTree<>(7);
        node6.addChild(node7);
        LOGGER.log(Level.INFO, root.build(PRE_ORDER).toString());
        LOGGER.log(Level.INFO, root.build(POST_ORDER).toString());
    }

}
