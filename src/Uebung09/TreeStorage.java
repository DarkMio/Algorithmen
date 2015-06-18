package Uebung09;

public class TreeStorage {
    
    private AbstractTreeNode root;


    public static void main(String[] args) {
        TreeStorage ts = new TreeStorage();
        ts.insert(new IntKeyable(10));
        ts.insert(new IntKeyable(5));
        ts.insert(new IntKeyable(15));
        System.out.println(ts.get(15).getKey());
    }
    /**
     * Fügt das gegebene Objekt in diese Sammlung ein.
     */
    public void insert(KeyAble o) {
        TreeNode newNode = new TreeNode(o);
        if (root == null) root = newNode;
        else {
            AbstractTreeNode nr = root.insert(newNode);
            root = nr;
        }
    }

    
    public String toString() {
        if (root == null) return "leerer Baum";
        else return root.toString();
    }
    
    /**
     * @return Das Objekt mit dem gegebenen Schlüssel (falls vorhanden).
     */
    public KeyAble get(int key) {
        if (root == null) return null;
        else return root.getObjectForKey(key);
    }

    /**
     * @return Das Objekt mit dem größten Schlüssel in der Sammlung.
     */
    public KeyAble getMaximum() {
        if (root == null) return null;
        else return root.getMaximumNodeInSubTree().getData();
    }

    /**
     * @return Das Objekt mit dem kleinsten Schlüssel in der Sammlung.
     */
    public KeyAble getMinimum() {
        if (root == null) return null;
        else return root.getMinimumNodeInSubTree().getData();
    }

    /**
     * Entfernt ein Objekt mit dem gegebenen Schlüssel aus
     * der Sammlung, falls es ein solches Objekt gibt.
     * @param key Der Schlüssel des Objekts, das entfernt werden soll.
     * @return Das Objekt, das entfernt wurde, falls es ein solches gibt
     *         und null, sonst.
     */
    public KeyAble remove(int key) {
        if (root == null) return null;
        else {
            AbstractTreeNode[] pair =  root.delete(key);
            root = pair[0];
            if (pair[1] != null) return pair[1].getData();
            else return null;
        }
    }


    
    /**
     * @return Genau dann true, wenn ein Objekt mit dem gegebenen 
     *         Schlüssel in dieser Sammlung enthalten ist.
     */
    public boolean contains(int key) {
        return (root != null) && (root.search(key) != null);
    }

    /**
     * @return Den Nachfolger (bezüglich der Schlüsselreihenfolge)
     *         des gegebenen Objekts o in der Sammlung.
     */
    public KeyAble getSuccessor(KeyAble o) {
        if (root == null) return null;
        else {
            AbstractTreeNode k = root.search(o.getKey());            
            if (k != null) {
                AbstractTreeNode s = k.getSuccessor(); 
                if (s != null) return s.getData();
            } 
            return null;
        }
    }

    /**
     * @return Den Vorgänger (bezüglich der Schlüsselreihenfolge)
     *         des gegebenen Objekts o in der Sammlung.
     */
    public KeyAble getPredecessor(KeyAble o) {
        if (root == null) return null;
        else {
            AbstractTreeNode k = root.search(o.getKey());            
            if (k != null) {
                AbstractTreeNode p = k.getPredecessor(); 
                if (p != null) return p.getData();
            } 
            return null;
        }
    }


    /**
     * Entfernt alle Objekte aus dieser Sammlung.
     */
    public void clear() {
        root = null;
    }

}
