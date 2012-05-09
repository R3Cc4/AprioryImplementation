package test;

import apriory.controller.implementation.AprioryImplFactory;
import apriory.controller.implementation.AprioryImplI;
import apriory.controller.items.RuleSetWrapper;
import apriory.data.loaders.DataReaderFactory;
import apriory.data.loaders.DataReaderI;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Michal
 * Date: 7.5.12
 * Time: 22:12
 * To change this template use File | Settings | File Templates.
 *
 * Main test class, for testing implementation of algorithm
 */
public class Test {

    public static void main(String[] args) {

        DataReaderI dataReader = DataReaderFactory.createArffDataReader("e:/vyvoj/AprioryProject/data/weather.nominal.arff");
        AprioryImplI aprioryImpl = AprioryImplFactory.createAprioryImpl(dataReader);

        int counter = 1;
        try {
            for (RuleSetWrapper ruleSetWrapper : aprioryImpl.getRules(0.1, 0.4)) {
                System.out.println(counter + ": " + ruleSetWrapper.toString());
                counter++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
