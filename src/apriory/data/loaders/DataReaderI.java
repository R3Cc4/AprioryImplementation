package apriory.data.loaders;

import java.io.IOException;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Michal
 * Date: 7.5.12
 * Time: 23:36
 * To change this template use File | Settings | File Templates.
 */
public interface DataReaderI {

    public Set<Set<String>> readData() throws IOException;

}
