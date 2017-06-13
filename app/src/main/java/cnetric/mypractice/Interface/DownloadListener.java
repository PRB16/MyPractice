package cnetric.mypractice.Interface;

import cnetric.mypractice.PojoClass.Message;

/**
 * Created by cnetric on 5/18/2017.
 */

public interface DownloadListener {
    /**
     * downloadFile progress value
     *
     * @param value
     */
    void progressUpdate(Message value);

}
