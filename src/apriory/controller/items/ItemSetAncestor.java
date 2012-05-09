package apriory.controller.items;

import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Michal
 * Date: 9.5.12
 * Time: 18:22
 * To change this template use File | Settings | File Templates.
 *
 * Parent class of all Item sets and Rule sets
 */
public class ItemSetAncestor {
    protected Set<String> items;
    protected double support;

    public ItemSetAncestor() {
        this.items = new HashSet<String>();
        this.support = 1;
    }

    public void addItem(String item) {
        items.add(item);
    }

    public Set<String> getItems() {
        return items;
    }

    public double getSupport() {
        return support;
    }

    public void setSupport(double support) {
        this.support = support;
    }

    @Override
    public boolean equals(Object obj) {
        ItemSet itemSet2 = (ItemSet) obj;
        if (items.containsAll(itemSet2.getItems())) return true;
        else return false;
    }
}
