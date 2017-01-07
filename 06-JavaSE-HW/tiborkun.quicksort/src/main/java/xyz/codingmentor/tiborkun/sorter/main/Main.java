package xyz.codingmentor.tiborkun.sorter.main;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import xyz.codingmentor.tiborkun.sorter.Sorter;

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
        List<Integer> intList = new ArrayList<>();
        intList.add(9);
        intList.add(5);
        intList.add(1);
        intList.add(8);
        intList.add(3);
        intList.add(7);
        intList.add(2);
        intList.add(4);
        intList.add(6);
        intList.add(0);

        Sorter<Integer> sorter = new Sorter<>();
        LOGGER.log(Level.INFO, intList.toString());
        sorter.quicksort(intList, 0, intList.size() - 1);
        LOGGER.log(Level.INFO, intList.toString());
    }
}
