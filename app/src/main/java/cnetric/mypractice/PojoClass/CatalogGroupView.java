
package cnetric.mypractice.PojoClass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CatalogGroupView {

    @SerializedName("shortDescription")
    @Expose
    private String shortDescription;
    @SerializedName("identifier")
    @Expose
    private String identifier;
    @SerializedName("resourceId")
    @Expose
    private String resourceId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("productsURL")
    @Expose
    private String productsURL;
    @SerializedName("thumbnail")
    @Expose
    private String thumbnail;
    @SerializedName("uniqueID")
    @Expose
    private String uniqueID;

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProductsURL() {
        return productsURL;
    }

    public void setProductsURL(String productsURL) {
        this.productsURL = productsURL;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getUniqueID() {
        return uniqueID;
    }

    public void setUniqueID(String uniqueID) {
        this.uniqueID = uniqueID;
    }

}
