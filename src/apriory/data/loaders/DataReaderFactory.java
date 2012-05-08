package apriory.data.loaders;

/**
 * Created with IntelliJ IDEA.
 * User: Michal
 * Date: 7.5.12
 * Time: 23:38
 * To change this template use File | Settings | File Templates.
 */
public class DataReaderFactory {

    public static DataReaderI createArffDataReader(String file){

        return new ArffDataReader(file);

    }

}
