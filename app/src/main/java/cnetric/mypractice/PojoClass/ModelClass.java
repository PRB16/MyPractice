
package cnetric.mypractice.PojoClass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ModelClass {

    @SerializedName("recordSetTotal")
    @Expose
    private String recordSetTotal;
    @SerializedName("resourceId")
    @Expose
    private String resourceId;
    @SerializedName("resourceName")
    @Expose
    private String resourceName;
    @SerializedName("recordSetStartNumber")
    @Expose
    private String recordSetStartNumber;
    @SerializedName("recordSetComplete")
    @Expose
    private String recordSetComplete;
    @SerializedName("CatalogGroupView")
    @Expose
    private List<CatalogGroupView> catalogGroupView = null;
    @SerializedName("recordSetCount")
    @Expose
    private String recordSetCount;
    @SerializedName("MetaData")
    @Expose
    private List<MetaDatum> metaData = null;

    public String getRecordSetTotal() {
        return recordSetTotal;
    }

    public void setRecordSetTotal(String recordSetTotal) {
        this.recordSetTotal = recordSetTotal;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getRecordSetStartNumber() {
        return recordSetStartNumber;
    }

    public void setRecordSetStartNumber(String recordSetStartNumber) {
        this.recordSetStartNumber = recordSetStartNumber;
    }

    public String getRecordSetComplete() {
        return recordSetComplete;
    }

    public void setRecordSetComplete(String recordSetComplete) {
        this.recordSetComplete = recordSetComplete;
    }

    public List<CatalogGroupView> getCatalogGroupView() {
        return catalogGroupView;
    }

    public void setCatalogGroupView(List<CatalogGroupView> catalogGroupView) {
        this.catalogGroupView = catalogGroupView;
    }

    public String getRecordSetCount() {
        return recordSetCount;
    }

    public void setRecordSetCount(String recordSetCount) {
        this.recordSetCount = recordSetCount;
    }

    public List<MetaDatum> getMetaData() {
        return metaData;
    }

    public void setMetaData(List<MetaDatum> metaData) {
        this.metaData = metaData;
    }

}
