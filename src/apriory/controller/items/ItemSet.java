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
    private int support;

    public ItemSet() {
        this.items = new HashSet<String>();
        this.support = 1;
    }

    public void addItem (String item){
        items.add(item);
    }

    public Set<String> getItems(){
        return items;
    }

    public int getSupport() {
        return support;
    }

    public void setSupport(int support) {
        this.support = support;
    }

    public void incrementSupport(){
        this.support++;
    }


}
