package test;

import apriory.data.loaders.ArffDataReader;
import apriory.data.loaders.DataReaderFactory;

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

        try {
            DataReaderFactory.createArffDataReader("e:/vyvoj/AprioryProject/data/weather.nominal.arff").readData();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        System.out.println("end");

    }

}
