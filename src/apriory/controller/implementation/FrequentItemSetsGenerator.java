package apriory.controller.implementation;

import apriory.controller.items.ItemSet;
import apriory.data.loaders.DataReaderI;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Michal
 * Date: 8.5.12
 * Time: 10:42
 * To change this template use File | Settings | File Templates.
 */
public class FrequentItemSetsGenerator {

    private DataReaderI dataReader;

    public FrequentItemSetsGenerator(DataReaderI dataReader) {
        this.dataReader = dataReader;
    }

    public Set<ItemSet> generate() throws IOException {

        Set<Set<String>> data = dataReader.readData();
        initializeSet(data);

        return null;
    }

    private Set<ItemSet> initializeSet(Set<Set<String>> data) {

        Set<ItemSet> itemSets = new HashSet<ItemSet>();
        boolean setToBeAdded = false;

        for (Set<String> strings : data) {
            for (String s : strings) {
                if (itemSets.isEmpty()) {
                    addItem(itemSets, s);
                } else {
                    for (ItemSet itemSet : itemSets) {
                        if (itemSet.getItems().contains(s)) {
                            itemSet.incrementSupport();
                            setToBeAdded = false;
                            break;
                        } else {
                            setToBeAdded = true;
                        }
                    }
                    if(setToBeAdded)addItem(itemSets, s);
                    setToBeAdded = false;
                }
            }
        }

        return itemSets;
    }

    private void addItem(Set<ItemSet> itemSets, String s) {
        ItemSet temp = new ItemSet();
        temp.addItem(s);
        itemSets.add(temp);
    }
}
