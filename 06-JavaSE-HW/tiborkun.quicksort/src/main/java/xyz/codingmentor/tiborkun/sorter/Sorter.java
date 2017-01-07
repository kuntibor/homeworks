package xyz.codingmentor.tiborkun.sorter;

import java.util.List;

/**
 *
 * @author teiep
 * @param <T>
 */
public class Sorter<T extends Comparable<T>> {

    public void quicksort(List<T> list, int start, int end) {
        if (null == list || list.isEmpty()) {
            return;
        }

        int i = start;
        int j = end;
        T pivot = list.get(start);

        while (i <= j) {
            while (list.get(i).compareTo(pivot) < 0) {
                i++;
            }
            while (list.get(j).compareTo(pivot) > 0) {
                j--;
            }
            if (i <= j) {
                switchItems(list, i, j);
                i++;
                j--;
            }
        }

        if (start < j) {
            quicksort(list, start, j);
        }
        if (i < end) {
            quicksort(list, i, end);
        }
    }

    public List<T> switchItems(List<T> list, int first, int second) {
        T temp;
        temp = list.get(first);
        list.add(first, list.get(second));
        list.remove(first + 1);
        list.add(second, temp);
        list.remove(second + 1);
        return list;
    }

}
