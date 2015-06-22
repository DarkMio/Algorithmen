package Uebung09;

public class TreeNode extends AbstractTreeNode {

    protected TreeNode(KeyAble data) {
        super(data);
    }

    @Override
    public AbstractTreeNode insert(AbstractTreeNode atn) {
        if(super.data.getKey() == atn.getKey()) {
            return null;
        }
        if(data.getKey() > atn.getKey()) {
            if (left == null) {
                left = atn;
                atn.parent = this;
                return getRoot();
            }
            return left.insert(atn);
        } else {
            if (right == null) {
                right = atn;
                atn.parent = this;
                return getRoot();
            }
            return right.insert(atn);
        }
    }

    @Override
    public String toString() {
        String s = "";
        s += "(" + data.getKey();

        if (left == right && left == null) {
            s += ")";
        } else if (left == null) {
            s += ": null, " + right.toString() + ")";
        } else if (right == null) {
            s += ": " + left.toString() + ", null)";
        } else {
            s += ": " + left.toString() + ", " + right.toString() + ")";
        }
        return s;
    }

    @Override
    public AbstractTreeNode search(int key) {
        int d = data.getKey();
        if (key == d) {
            return this;
        } else if (key < d) {
            return left == null ? null : left.search(key);
        } else {
            return right == null ? null : right.search(key);
        }
    }

    @Override
    public KeyAble getObjectForKey(int key) {
        return search(key).getData();
    }

    @Override
    public AbstractTreeNode getRoot() {
        return parent == null ? this : parent.getRoot();
    }

    @Override
    public int getRootDistance() {
        return parent == null ? 0 : parent.getRootDistance() + 1;
    }

    @Override
    public AbstractTreeNode getMinimumNodeInSubTree() {
        return left == null ? this : left.getMinimumNodeInSubTree();
    }

    @Override
    public AbstractTreeNode getMaximumNodeInSubTree() {
        return right == null ? this : right.getMinimumNodeInSubTree();
    }


    @Override
    public AbstractTreeNode getSuccessor() {
        return right;
    }

    @Override
    public AbstractTreeNode getPredecessor() {
        return left;
    }

    @Override
    public AbstractTreeNode[] delete(int key) {
        AbstractTreeNode deletable = search(key);
        if(deletable != null) deletable = deletable.getMinimumNodeInSubTree();
        return new AbstractTreeNode[]{this.getRoot(), deletable};
    }

    @Override
    public AbstractTreeNode remove() {
        if (parent.left == this) parent.left = null;
        else parent = right = null;
        return this;
    }
}
