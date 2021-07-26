package one.enix.smsforward;

import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiInterface {
    @Headers("Content-Type: application/json")
    @POST("v1")
    Call<ResponseBody> postMessage(@Body HashMap<String, Object> body);
}
