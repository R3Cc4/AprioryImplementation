package test;

import apriory.controller.implementation.AprioryImplFactory;
import apriory.controller.implementation.AprioryImplI;
import apriory.data.loaders.DataReaderFactory;
import apriory.data.loaders.DataReaderI;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Michal
 * Date: 7.5.12
 * Time: 22:12
 * To change this template use File | Settings | File Templates.
 */
public class Test {

    public static void main(String[] args) {

        DataReaderI dataReader = DataReaderFactory.createArffDataReader("e:/vyvoj/AprioryProject/data/weather.nominal.arff");
        AprioryImplI aprioryImpl = AprioryImplFactory.createAprioryImpl(dataReader);

        try {
            aprioryImpl.getFrequentItemSets();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }


    }

}
