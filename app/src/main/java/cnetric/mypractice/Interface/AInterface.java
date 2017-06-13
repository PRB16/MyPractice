package cnetric.mypractice.Interface;

import cnetric.mypractice.PojoClass.RetroPost;
import cnetric.mypractice.PojoClass.RetroPostResponseModel;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by cnetric on 3/15/2017.
 */

public interface AInterface {

    @POST("loginidentity")
    Call<RetroPostResponseModel> postDetails(@Body RequestBody retroPost);

//        @POST("loginidentity")
//        Call<ResponseBody> postDetails(@Body RequestBody retroPost);



//    @POST("loginidentity")
//    RetroPostResponseModel postDetails(@Body RequestBody retroPost);


//    @POST("loginidentity")
//    Call<RetroPost> postDetails(@Field("logonId") String logonId,
//                                @Field("logonPassword") String logonPassword);


//    @POST("loginidentity")
//    Call<RetroPostResponseModel> postDetails(@Field("userId") String userId,
//                                             @Field("WCToken") String wCToken,
//                                             @Field("WCTrustedToken") String wCTrustedToken);
//

}
