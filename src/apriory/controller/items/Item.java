package apriory.controller.items;

import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Michal
 * Date: 8.5.12
 * Time: 9:44
 * To change this template use File | Settings | File Templates.
 */
public class Item {

    private Set<String> values;
    private int support;

    public Item(int support) {
        this.support = support;
        values = new HashSet<String>();
    }

    public Set<String> getValues() {
        return values;
    }

    public int getSupport() {
        return support;
    }

    public void addValue (String value) {
        this.values.add(value);
    }

    public void setSupport(int support) {
        this.support = support;
    }
}
