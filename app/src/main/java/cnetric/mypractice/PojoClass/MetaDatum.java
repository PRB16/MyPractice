
package cnetric.mypractice.PojoClass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MetaDatum {

    @SerializedName("metaKey")
    @Expose
    private String metaKey;
    @SerializedName("metaData")
    @Expose
    private String metaData;

    public String getMetaKey() {
        return metaKey;
    }

    public void setMetaKey(String metaKey) {
        this.metaKey = metaKey;
    }

    public String getMetaData() {
        return metaData;
    }

    public void setMetaData(String metaData) {
        this.metaData = metaData;
    }

}
