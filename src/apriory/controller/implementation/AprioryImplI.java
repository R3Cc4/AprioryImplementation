package apriory.controller.implementation;

import apriory.controller.items.ItemSet;
import apriory.controller.items.RuleSet;
import apriory.controller.items.RuleSetWrapper;

import java.io.IOException;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Michal
 * Date: 8.5.12
 * Time: 9:30
 * To change this template use File | Settings | File Templates.
 * <p/>
 * This interface should implement every class, which will implement Apriori algorithm computation.
 */
public interface AprioryImplI {

    /**
     * This method is used for obtaining frequent itemsets from given data model
     *
     * @param support min support
     * @return Set of itemsets
     * @throws IOException
     */
    public Set<ItemSet> getFrequentItemSets(double support) throws IOException;

    /**
     * This method is used for obtaining association rules from given data model
     *
     * @param support min support
     * @param confidence min confidence
     * @return Set of rules
     * @throws IOException
     */
    public Set<RuleSetWrapper> getRules(double support, double confidence) throws IOException;
}
