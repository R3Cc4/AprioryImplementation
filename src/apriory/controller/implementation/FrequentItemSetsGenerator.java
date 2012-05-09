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
 *
 * This class implements frequent itemsets generation algorithm
 */
public class FrequentItemSetsGenerator {

    private DataReaderI dataReader;

    public FrequentItemSetsGenerator(DataReaderI dataReader) {
        this.dataReader = dataReader;
    }

    /**
     * Main method for generating itemsets. Algorithm is implemented regarding to the materials supplied.
     *
     * @param support min support
     * @return set of itemsets
     * @throws IOException
     */
    public Set<ItemSet> generate(double support) throws IOException {

        Set<Set<String>> data = dataReader.readData();
        int maxValue = data.iterator().next().size(); //obtaining number of rows of input data
        Set<ItemSet> freqItemsK = recomputeSupport(initializeSet(data),data,support); //initial frequent items
        Set<ItemSet> generatedData = new HashSet<ItemSet>();
        generatedData.addAll(freqItemsK);

        for (int i = 0; i < maxValue; i++) {

            //generation of candidates
            Set<ItemSet> candidates = generateCandidates(freqItemsK);
            for (Set<String> strings : data) {
                for (ItemSet candidate : candidates) {
                    if (strings.containsAll(candidate.getItems())) candidate.incrementSupport();
                }
            }
            //candidates with less support than minimal are fired, support is normalized
            candidates = recomputeSupport(candidates,data,support);
            freqItemsK = candidates;
            generatedData.addAll(freqItemsK);

        }

        return generatedData;
    }

    /**
     * Method normalize support and also fire candidates with less than minimal support
     *
     * @param dataFreqSet set of frequent items
     * @param data input data model
     * @param support min support
     * @return
     */
    private Set<ItemSet> recomputeSupport(Set<ItemSet> dataFreqSet,Set<Set<String>> data, double support){

        double size = data.size();

        for (ItemSet itemSet : dataFreqSet) {
            itemSet.setSupport(itemSet.getSupport()/size); //normalizing support
        }

        Iterator<ItemSet> iterator = dataFreqSet.iterator();
        ItemSet candidateT = null;
        while (iterator.hasNext()) {
            candidateT = iterator.next();
            if (candidateT.getSupport() <= support) iterator.remove(); //firing candidates
        }

        return dataFreqSet;
    }

    /**
     * Method for initialisation of frequent item set.
     *
     * @param data model
     * @return Set of initialised frequent items
     */
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

    /**
     * Helper method for adding items
     *
     * @param itemSets
     * @param s
     */
    private void addItem(Set<ItemSet> itemSets, String s) {
        ItemSet temp = new ItemSet();
        temp.addItem(s);
        itemSets.add(temp);
    }

    /**
     * Method for generating candidates. Implemented same as in the supported materials.
     *
     * @param freqItems set of frequent items
     * @return Set of candidates
     */
    private Set<ItemSet> generateCandidates(Set<ItemSet> freqItems) {

        Set<ItemSet> candidates = new HashSet<ItemSet>();
        boolean toBeAdded = false;

        for (ItemSet freqItemJ : freqItems) {
            for (ItemSet freqItemK : freqItems) {
                if (freqItemJ.equals(freqItemK)) continue;
                if (freqItemJ.equalMinusOne(freqItemK)) {
                    addCandidate(candidates, toBeAdded, freqItemJ, freqItemK);
                }
            }

        }
        return candidates;
    }

    /**
     * Helper method for adding candidate.
     *
     * @param candidates
     * @param toBeAdded
     * @param freqItemJ
     * @param freqItemK
     */
    private void addCandidate(Set<ItemSet> candidates, boolean toBeAdded, ItemSet freqItemJ, ItemSet freqItemK) {

        ItemSet temp = freqItemJ.join(freqItemK);

        if (candidates.size() == 0) candidates.add(temp);
        else {
            toBeAdded = false;
            for (ItemSet candidate : candidates) {
                if (candidate.equals(temp)) {
                    toBeAdded = false;
                    break;
                }
                toBeAdded = true;
            }
        }

        if (toBeAdded) candidates.add(temp);

    }
}
