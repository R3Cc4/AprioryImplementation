package apriory.controller.implementation;

import apriory.controller.items.ItemSet;
import apriory.data.loaders.DataReaderI;

import java.io.IOException;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Michal
 * Date: 8.5.12
 * Time: 10:03
 * To change this template use File | Settings | File Templates.
 */
public class AprioryImpl implements AprioryImplI {

    private DataReaderI dataReader;
    private FrequentItemSetsGenerator frequentItemSetsGenerator;

    public AprioryImpl(DataReaderI dataReader) {
        this.dataReader = dataReader;
        this.frequentItemSetsGenerator = new FrequentItemSetsGenerator(dataReader);
    }

    @Override
    public Set<ItemSet> getFrequentItemSets(double support) throws IOException {

        frequentItemSetsGenerator.generate(support);


        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
