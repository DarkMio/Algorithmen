package Uebung09;


public abstract class AbstractTreeNode {
    
    /**
     * In diesem Feld wird der Inhalt des Knotens gespeichert.
     */
    protected KeyAble data;
    /**
     * Referenz auf die Wurzel des linken Teilbaums unter diesem Knoten.
     */
    protected AbstractTreeNode left;
    /**
     * Referenz auf die Wurzel des rechten Teilbaums unter diesem Knoten.
     */
    protected AbstractTreeNode right;
    /**
     * Referenz auf den Vaterknoten von diesem Knoten.
     */
    protected AbstractTreeNode parent;
    
    
    protected AbstractTreeNode(KeyAble data) {
        this.data = data;
        this.left = null;
        this.right = null;
        this.parent = null;
    }

    
    
    /**
     * Erzwingt das Überschreiben der toString-Methode. 
     * 
     * Diese Methode gibt einen String der Form
     * (r:P,Q)
     * zurück, falls dieser Knoten mindestens ein Kind hat,
     * wobei r der Schlüssel des Inhalts dieses Knoten ist (data.getKey()), und
     * P und Q die Darstellung des linken und rechten Teilbaums unter diesem
     * Baum oder null, falls ein entsprechender Teilbaum nicht existiert.
     * 
     * Diese Methode gibt r (als String) zurück, wobei r der Schlüssel des Inhalts
     * dieses Knoten ist (data.getKey()), falls dieser Knoten keine Kinder hat.
     * 
     * Der Rückgabewert dieser Methode enthält keine Leerzeichen, Tabs oder Zeilenumbrüche.
     */ 
    public abstract String toString();
    

    /**
     * Gibt einen Knoten mit dem gegebenen Schlüssel zurück, falls ein
     * solcher im Baum existiert, sonst null.
     */
    public abstract AbstractTreeNode search(int key);
    

    
    /**
     * Gibt ein Element mit dem gegebenen Schlüssel zurück,
     * falls der Baum unter diesem Knoten ein solches enthält.
     * Ansonsten wird null zurückgegeben.
     */
    public abstract KeyAble getObjectForKey(int key);
    
    
    /**
     * Liefert den Wurzelknoten des Baumes zurück, zu dem dieser Knoten gehört.
     */ 
    public abstract AbstractTreeNode getRoot();
    

    /**
     * Liefert die Distanz dieses Knotens zum Wurzelknoten zurück.
     * Dabei ist die Distanz eines Wurzelknotens zu sich selbst gleich 0,
     * die der Kinder eines Wurzelknotens zum Wurzelknoten gleich 1 und so weiter.
     */
    public abstract int getRootDistance();
    
    
    /**
     * Liefert den Knoten mit dem kleinsten Schlüssel im Baum unter diesem 
     * Knoten zurück.
     */
    public abstract AbstractTreeNode getMinimumNodeInSubTree();
    
    /**
     * Liefert den Knoten mit dem größten Schlüssel im Baum unter diesem 
     * Knoten zurück.
     */
    public abstract AbstractTreeNode getMaximumNodeInSubTree();
    
    
    /**
     * Sortiert den gegebene AbstractTreeNode atn in den Baum unter diesen Knoten ein.
     * Ist atn==null, passiert nichts.
     * 
     * Diese Methode gibt den neuen Wurzelknoten nach der Einfügeoperation zurück.
     * (Der Wurzelknoten muss sich nicht ändern, könnte es aber bei
     * manchen Implementationen.)
     */ 
    public abstract AbstractTreeNode insert(AbstractTreeNode atn);
    
    
    /**
     * Liefert den Nachfolgerknoten dieses Knoten zurück, falls
     * ein solcher existiert, sonst null.
     */
    public abstract AbstractTreeNode getSuccessor();
    
    /**
     * Liefert den Vorgängerknoten dieses Knoten zurück, falls
     * ein solcher existiert, sonst null.
     */
    public abstract AbstractTreeNode getPredecessor();
    

    
    /**
     * Löscht einen Knoten mit dem gegebenen Schlüssel aus dem Baum, 
     * falls ein solcher Knoten existiert. Sonst hat ein Aufruf
     * der Methode keine Auswirkung.
     * Beim Löschen kann sich der Wurzelknoten ändern. 
     * 
     * Die Methode gibt ein zweielementiges Array zurück.
     * Das erste Element ist der aktuelle Wurzelknoten des 
     * Baumes nach dem Löschvorgang, der zweite enthält den
     * gelöschten Knoten (oder null, falls es keinen solchen 
     * Knoten gab).
     */
    public abstract AbstractTreeNode[] delete(int key);
    
    
    
    /**
     * Entfernt diesen Knoten aus dem Baum. Möglicherweise
     * ändert sich dadurch der Wurzelknoten des Baums.
     * Die Rückgabe dieser Methode ist daher der neue Wurzelknoten.
     */
    public abstract AbstractTreeNode remove();
    
    
    
    /**
     * Gibt den Inhalt dieses Knoten zurück.
     */
    public KeyAble getData() {
        return data;
    }
    
    /**
     * Gibt den Schlüssel des Inhalts dieses Knoten zurück.
     */
    public int getKey() {
        return data.getKey();
    }
    
    /**
     * Gibt das linke Kind dieses knoten zurück und null, falls es kein linkes Kind gibt.
     */
    public AbstractTreeNode getLeftChild() {
        return left;
    }
    
    /**
     * Gibt das linke Kind dieses knoten zurück und null, falls es kein linkes Kind gibt.
     */
    public AbstractTreeNode getRightChild() {
        return right;
    }
    
    
    /**
     * Gibt den Vaterknoten dieses knoten zurück, falls ein solcher existiert.
     * Sonst wird null zurückgegeben.
     */
    public AbstractTreeNode getParent() {
        return parent;
    }
    
    
    public boolean isAncestor(AbstractTreeNode other) {
        if (this == other) return true;
        if (parent != null) return parent.isAncestor(other);
        return false;
    }
    
    
    /**
     * Fügt den gegebenen Knoten c als rechtes Kind dieses Knoten ein.
     * Die Methode überschreibt eventuell bereits vorhandene rechte Kindknoten!
     */
    public void setRightChild(AbstractTreeNode c) {
        if (isAncestor(c)) throw new IllegalArgumentException("Kann keinen Ahnen als Kind einfügen.");
        right = c;
        c.parent = this;
    }
    
    /**
     * Fügt den gegebenen Knoten c als linkes Kind dieses Knoten ein.
     * Die Methode überschreibt eventuell bereits vorhandene linke Kindknoten!
     */
    public void setLeftChild(AbstractTreeNode c) {
        if (isAncestor(c)) throw new IllegalArgumentException("Kann keinen Ahnen als Kind einfügen.");
        left = c;
        c.parent = this;
    }
    
    
    
    
    
    
    /**
     * Tauscht das gegebene Kind oldCild dieses Knoten mit dem
     * neuen Kind newChild aus. Falls dieser Knoten kein Kind
     * oldChild besitzt, passiert nichts.
     */
    public void replaceChild(AbstractTreeNode oldChild, AbstractTreeNode newChild) {
        if (isAncestor(newChild)) throw new IllegalArgumentException("Kann keinen Ahnen als Kind einfügen.");
        if (left == oldChild) left = newChild;
        if (right == oldChild) right = newChild;
        oldChild.parent = null;
    }
    
    
    /**
     * Gibt genau dann true zurück, wenn dieser Knoten das linke
     * Kind seines Vaterknotens ist.
     */
    public boolean isLeftChild() {
        return (parent != null) && (parent.left == this);
    }
    
    /**
     * Gibt genau dann true zurück, wenn dieser Knoten das linke
     * Kind seines Vaterknotens ist.
     */
    public boolean isRightChild() {
        return (parent != null) && (parent.right == this);
    }
    
    
    

}
