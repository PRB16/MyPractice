package cnetric.mypractice.PojoClass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by cnetric on 4/3/2017.
 */

public class RetroPostResponseModel {
    @SerializedName("userId")
    @Expose
    private String userId;
    @SerializedName("WCToken")
    @Expose
    private String wCToken;
    @SerializedName("WCTrustedToken")
    @Expose
    private String wCTrustedToken;
    @SerializedName("personalizationID")
    @Expose
    private String personalizationID;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getWCToken() {
        return wCToken;
    }

    public void setWCToken(String wCToken) {
        this.wCToken = wCToken;
    }

    public String getWCTrustedToken() {
        return wCTrustedToken;
    }

    public void setWCTrustedToken(String wCTrustedToken) {
        this.wCTrustedToken = wCTrustedToken;
    }

    public String getPersonalizationID() {
        return personalizationID;
    }

    public void setPersonalizationID(String personalizationID) {
        this.personalizationID = personalizationID;
    }

}
