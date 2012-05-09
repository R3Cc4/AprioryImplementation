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
 */
public interface AprioryImplI {

    public Set<ItemSet> getFrequentItemSets(double support) throws IOException;

    public Set<RuleSetWrapper> getRules(double support, double confidence) throws IOException;
}
