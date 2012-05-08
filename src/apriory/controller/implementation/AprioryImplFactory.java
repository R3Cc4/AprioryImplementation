package apriory.controller.implementation;

import apriory.data.loaders.DataReaderI;

/**
 * Created with IntelliJ IDEA.
 * User: Michal
 * Date: 8.5.12
 * Time: 11:30
 * To change this template use File | Settings | File Templates.
 */
public class AprioryImplFactory {

    public static AprioryImplI createAprioryImpl(DataReaderI dataReader){
        return new AprioryImpl(dataReader);
    }

}
