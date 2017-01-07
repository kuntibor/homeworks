package xyz.codingmentor.tiborkun.generictree;

import java.util.ArrayList;
import java.util.List;
import static xyz.codingmentor.tiborkun.generictree.GenericTree.GenericTreeOrderType.*;

/**
 *
 * @author teiep
 */
public class GenericTree<T> {

    private T data;
    private List<GenericTree<T>> children;
    private GenericTree<T> parent;

    public enum GenericTreeOrderType {
        PRE_ORDER,
        POST_ORDER;
    }

    public GenericTree() {
        children = new ArrayList<>();
    }

    public GenericTree(T data) {
        this.data = data;
        children = new ArrayList<>();
    }

    public void addChild(GenericTree<T> child) {
        child.parent = this;
        children.add(child);
    }

    public void addChildren(List<GenericTree<T>> children) {
        for (GenericTree<T> child : children) {
            child.parent = this;
        }
        this.children.addAll(children);
    }

    public GenericTree<T> getChild(int i) {
        return children.get(i);
    }

    public List<GenericTree<T>> getChildren() {
        return children;
    }

    public boolean removeChild(GenericTree<T> child) {
        return children.remove(child);
    }

    public boolean removeChildren(List<GenericTree<T>> children) {
        return this.children.removeAll(children);
    }

    public boolean isRoot() {
        return this.getParent() == null;
    }

    public GenericTree<T> getRoot() {
        GenericTree<T> genericTree = this;
        while (!genericTree.isRoot()) {
            genericTree = getParent();
        }
        return genericTree;
    }

    public boolean hasChild() {
        return getNumberOfChildren() > 0;
    }

    public int getNumberOfChildren() {
        return children.size();
    }

    public GenericTree<T> getParent() {
        return parent;
    }

    public void setParent(GenericTree<T> parent) {
        this.parent = parent;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public List<T> build(GenericTreeOrderType orderType) {
        List<T> dataList = new ArrayList<>();
        GenericTree<T> root = getRoot();
        if (null != root) {
            if (orderType.equals(PRE_ORDER)) {
                buildPreOrder(root, dataList);
            } else if (orderType.equals(POST_ORDER)) {
                buildPostOrder(root, dataList);
            }
        }
        return dataList;
    }

    public void buildPreOrder(GenericTree<T> node, List<T> dataList) {
        dataList.add(node.data);
        for (GenericTree<T> child : node.children) {
            buildPreOrder(child, dataList);
        }
    }

    public void buildPostOrder(GenericTree<T> node, List<T> dataList) {
        for (GenericTree<T> child : node.children) {
            buildPreOrder(child, dataList);
        }
        dataList.add(node.data);
    }

}
