package apriory.controller.implementation;

import apriory.controller.items.ItemSet;
import apriory.data.loaders.DataReaderI;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
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
        int maxValue = data.iterator().next().size();
        Set<ItemSet> freqItemsK = initializeSet(data);
        Set<ItemSet> generatedData = new HashSet<ItemSet>();
        generatedData.addAll(freqItemsK);

        for (int i = 0; i < maxValue; i++) {

            Set<ItemSet> candidates = generateCandidates(freqItemsK);
            for (Set<String> strings : data) {
                for (ItemSet candidate : candidates) {
                    if (strings.containsAll(candidate.getItems())) candidate.incrementSupport();
                }
            }
            Iterator<ItemSet> iterator = candidates.iterator();
            ItemSet candidateT = null;
            while(iterator.hasNext()) {
                candidateT = iterator.next();
                if (candidateT.getSupport() == 0) iterator.remove();
            }

            freqItemsK = candidates;
            generatedData.addAll(freqItemsK);

        }


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
                    if (setToBeAdded) addItem(itemSets, s);
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

    private Set<ItemSet> generateCandidates(Set<ItemSet> freqItems) {

        Set<ItemSet> candidates = new HashSet<ItemSet>();
        boolean toBeAdded = false;

        for (ItemSet freqItemJ : freqItems) {
            for (ItemSet freqItemK : freqItems) {
                if (freqItemJ.equals(freqItemK)) continue;
                if (freqItemJ.equalMinusOne(freqItemK)) {
                    ItemSet temp = freqItemJ.join(freqItemK);
                    if (candidates.size() == 0) candidates.add(temp);
                    else {
                        toBeAdded = false;
                        for (ItemSet candidate : candidates) {
                            if (candidate.equals(temp)){
                                toBeAdded = false;
                                break;
                            }
                            toBeAdded = true;
                        }
                    }

                    if (toBeAdded) candidates.add(temp);
                }
            }

        }
        return candidates;
    }
}
