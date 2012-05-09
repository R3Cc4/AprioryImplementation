package apriory.controller.items;

import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Michal
 * Date: 8.5.12
 * Time: 9:43
 * To change this template use File | Settings | File Templates.
 *
 * Basic class representing single frequent item set.
 */
public class ItemSet extends ItemSetAncestor {

    public ItemSet() {
        super();
    }

    public void incrementSupport() {
        this.support++;
    }

    /**
     * Test if this subset with itemSet2.size-1 is the subset of itemSet2.
     *
     * @param itemSet2
     * @return
     */
    public boolean equalMinusOne(ItemSet itemSet2) {

        int sizeOfL = items.size();
        int counter = 0;

        for (String s : items) {
            if (itemSet2.getItems().contains(s)) counter++;
        }

        if (counter == sizeOfL - 1) return true;
        return false;
    }

    /**
     * Join of two frequent item sets.
     *
     * @param itemSet2
     * @return
     */
    public ItemSet join(ItemSet itemSet2) {

        ItemSet joined = new ItemSet();
        joined.setSupport(0);
        joined.getItems().addAll(itemSet2.getItems());
        joined.getItems().addAll(items);

        return joined;
    }

    /**
     * This method check whether two items contains exactly same values.
     *
     * @param itemSet
     * @return
     */
    public boolean equalsExactly(ItemSet itemSet) {

        if (items.containsAll(itemSet.getItems()) && itemSet.getItems().containsAll(items)) return true;
        return false;

    }

}
