package apriory.controller.items;

import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Michal
 * Date: 8.5.12
 * Time: 9:43
 * To change this template use File | Settings | File Templates.
 */
public class ItemSet {

    private Set<String> items;
    private double support;

    public ItemSet() {
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

    public void incrementSupport() {
        this.support++;
    }

    @Override
    public boolean equals(Object obj) {
        ItemSet itemSet2 = (ItemSet) obj;
        if (items.containsAll(itemSet2.getItems())) return true;
        else return false;
    }

    public boolean equalMinusOne(ItemSet itemSet2) {

        int sizeOfL = items.size();
        int counter = 0;

        for (String s : items) {
            if (itemSet2.getItems().contains(s)) counter++;
        }

        if (counter == sizeOfL - 1) return true;
        return false;
    }

    public ItemSet join(ItemSet itemSet2) {

        ItemSet joined = new ItemSet();
        joined.setSupport(0);
        joined.getItems().addAll(itemSet2.getItems());
        joined.getItems().addAll(items);

        return joined;
    }

}
