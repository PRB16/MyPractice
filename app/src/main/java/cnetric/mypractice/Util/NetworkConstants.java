package cnetric.mypractice.Util;

/**
 * Created by cnetric on 3/28/2017.
 */

public class NetworkConstants {
    //    Base Url
    public static final String DOMAIN_NAME = "http://35.154.52.107/wcs/resources/store/10701/";
    public static final String DOMAIN_NAME_LIVE = "http://35.154.52.107/wcs/resources/store/10701/";

    //  Data
    public static final String GET_ALL_CHAT = "categoryview/@top";
//    public static final String ONE_TO_ONE_CHAT = "one_to_one_chat";
//    public static final String GET_USER_LIST = "user_list";
//    public static final String SEND_MESSAGE = "send_message";


    //  Url Calling Method
    public static String getConstants(String requestType) {
        return DOMAIN_NAME_LIVE + requestType;
    }
}
