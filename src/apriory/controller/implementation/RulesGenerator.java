package apriory.controller.implementation;

import apriory.controller.items.ItemSet;
import apriory.controller.items.RuleSet;
import apriory.controller.items.RuleSetWrapper;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Michal
 * Date: 9.5.12
 * Time: 18:11
 * To change this template use File | Settings | File Templates.
 *
 * This class implements rules generation algorithm.
 */
public class RulesGenerator {

    private Set<ItemSet> itemSets;

    public RulesGenerator(Set<ItemSet> itemSets) {

        this.itemSets = itemSets;

    }

    /**
     * Main method for generating rule sets. Implemented as in the supplied material.
     *
     * @param confidence min confidence
     * @return Set of rules
     */
    public Set<RuleSetWrapper> generate(double confidence) {

        Set<RuleSetWrapper> ruleSets = new HashSet<RuleSetWrapper>();

        for (ItemSet itemSet : itemSets) {

            Set<ItemSet> subSets = new HashSet<ItemSet>();
            subSets.addAll(getSubsets(itemSets, itemSet));

            for (ItemSet subSet : subSets) {
                ItemSet complement = getComplement(itemSets, itemSet, subSet);
                if (itemSet.getSupport() / complement.getSupport() >= confidence) {

                    RuleSetWrapper ruleSetWrapper = new RuleSetWrapper(itemSet.getSupport(), itemSet.getSupport() / complement.getSupport());

                    ruleSetWrapper.getLeftSet().getItems().addAll(complement.getItems());
                    ruleSetWrapper.getLeftSet().setSupport(complement.getSupport());
                    ruleSetWrapper.getRightSet().getItems().addAll(subSet.getItems());
                    ruleSetWrapper.getRightSet().setSupport(subSet.getSupport());

                    ruleSets.add(ruleSetWrapper);
                }
            }
        }
        return ruleSets;
    }

    /**
     * This method implements generation of subsets for given set.
     * @param itemSets Set of all frequent items
     * @param itemSetSup Item which subsets are generated
     * @return Set of subsets
     */
    private Set<ItemSet> getSubsets(Set<ItemSet> itemSets, ItemSet itemSetSup) {

        int size = itemSetSup.getItems().size();
        Set<ItemSet> subItems = new HashSet<ItemSet>();

        for (ItemSet itemSet : itemSets) {
            if (itemSet.getItems().size() < size)
                if (itemSetSup.getItems().containsAll(itemSet.getItems())) subItems.add(itemSet);
        }

        return subItems;

    }

    /**
     * Method for complement generation.
     *
     * @param itemSets Set of frequent items
     * @param itemSetOrig Whole Itemset
     * @param subset Itemset for which the complement will be generated
     * @return
     */
    private ItemSet getComplement(Set<ItemSet> itemSets, ItemSet itemSetOrig, ItemSet subset) {

        ItemSet complement = new ItemSet();
        complement.getItems().addAll(itemSetOrig.getItems());

        for (String s : subset.getItems()) {
            complement.getItems().remove(s);
        }

        for (ItemSet itemSet : itemSets) {
            if (complement.equalsExactly(itemSet)) {
                complement.setSupport(itemSet.getSupport());
                break;
            }
        }
        return complement;

    }


}


