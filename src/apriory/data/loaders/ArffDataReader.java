package apriory.data.loaders;


import weka.core.Instance;
import weka.core.Instances;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Michal
 * Date: 7.5.12
 * Time: 22:22
 * To change this template use File | Settings | File Templates.
 */
public class ArffDataReader implements DataReaderI {

    private String file;

    public ArffDataReader(String file) {
        this.file = file;
    }

    @Override
    public Set<Set<String>> readData() throws IOException {

        Set<Set<String>> data = new HashSet<Set<String>>();
        BufferedReader reader = new BufferedReader(
                new FileReader(file));
        Instances dataArff = new Instances(reader);

        for (Instance instance : dataArff) {

            Set<String> tData = new HashSet<String>();
            for (int i = 0; i < instance.numValues(); i++) {
                tData.add(instance.attribute(i).name() + " = " + instance.toString(i));
            }

            data.add(tData);
        }

        return data;
    }


}
