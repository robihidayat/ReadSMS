package one.enix.smsforward;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SMSReceiver extends BroadcastReceiver{

    ApiInterface mApiInterface;

    static final String SMS_RECEIVED_ACTION = android.provider.Telephony.Sms.Intents.SMS_RECEIVED_ACTION;
    @Override
    public void onReceive(Context context, Intent intent) {
        if (! intent.getAction().equals(SMS_RECEIVED_ACTION)) return;

        final Bundle bundle = intent.getExtras();
        final Object[] pduObjects = (Object[]) bundle.get("pdus");
        if (pduObjects == null) return;

        for (Object messageObj : pduObjects) {
            SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) messageObj, (String) bundle.get("format"));
            String senderNumber = currentMessage.getDisplayOriginatingAddress();
            String rawMessageContent = currentMessage.getDisplayMessageBody();
            MessageForm form = new MessageForm("umi", "type", senderNumber, rawMessageContent, senderNumber, "encode", "at", 1);
            sendMessage(form);
        }
    }

    public void sendMessage(MessageForm form)  {
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        HashMap<String, Object> body = new LinkedHashMap<>();
        body.put("umid", form.body);
        body.put("type", form.body);
        body.put("source", form.source);
        body.put("body", form.body);
        body.put("destination", form.source);
        body.put("encoding", form.body);
        body.put("receivedAt", form.body);
        body.put("version", 1);
        System.out.println("isi jsonnys "+ body);
        Call<ResponseBody> message = mApiInterface.postMessage(body);
        message.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                System.out.println("response code "+response.code());
                System.out.println(" response message "+response.body());
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                System.out.println(t);
            }
        });

    }
}
