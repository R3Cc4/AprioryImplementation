package apriory.controller.implementation;

import apriory.controller.items.ItemSet;
import apriory.controller.items.RuleSet;
import apriory.controller.items.RuleSetWrapper;
import apriory.data.loaders.DataReaderI;

import java.io.IOException;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Michal
 * Date: 8.5.12
 * Time: 10:03
 * To change this template use File | Settings | File Templates.
 *
 * This class implements Apriori algorithm.
 */
public class AprioryImpl implements AprioryImplI {

    private DataReaderI dataReader;
    private FrequentItemSetsGenerator frequentItemSetsGenerator;
    private RulesGenerator rulesGenerator = null;
    private Set<ItemSet> itemSets = null;

    public AprioryImpl(DataReaderI dataReader) {
        this.dataReader = dataReader;
        this.frequentItemSetsGenerator = new FrequentItemSetsGenerator(dataReader);
    }

    @Override
    public Set<ItemSet> getFrequentItemSets(double support) throws IOException {

        Set<ItemSet> frequentItems = frequentItemSetsGenerator.generate(support);
        this.rulesGenerator = new RulesGenerator(frequentItems);

        return frequentItems;

    }

    @Override
    public Set<RuleSetWrapper> getRules(double support, double confidence) throws IOException {

        if (rulesGenerator == null) this.rulesGenerator = new RulesGenerator(getFrequentItemSets(support));

        return rulesGenerator.generate(confidence);

    }
}
