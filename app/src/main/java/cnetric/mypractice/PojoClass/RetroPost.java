
package cnetric.mypractice.PojoClass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RetroPost {

    @SerializedName("logonId")
    @Expose
    private String logonId;
    @SerializedName("logonPassword")
    @Expose
    private String logonPassword;

    public String getLogonId() {
        return logonId;
    }

    public void setLogonId(String logonId) {
        this.logonId = logonId;
    }

    public String getLogonPassword() {
        return logonPassword;
    }

    public void setLogonPassword(String logonPassword) {
        this.logonPassword = logonPassword;
    }

}
