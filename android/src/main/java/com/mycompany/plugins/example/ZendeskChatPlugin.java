package com.mycompany.plugins.example;

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;
import com.getcapacitor.JSArray;
import com.getcapacitor.NativePlugin;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.zopim.android.sdk.api.ZopimChat;
import com.zopim.android.sdk.model.VisitorInfo;
import com.zopim.android.sdk.prechat.ZopimChatActivity;

import org.json.JSONException;

@CapacitorPlugin(name = "ZendeskChat")
public class ZendeskChatPlugin extends Plugin {

    private ZendeskChat implementation = new ZendeskChat();

    @PluginMethod
    public void echo(PluginCall call) {
        String value = call.getString("value");

        JSObject ret = new JSObject();
        ret.put("value", implementation.echo(value));
        call.resolve(ret);
    }

  @PluginMethod()
  public void initialize(PluginCall call) {
    String accountKey = String.valueOf(this.getConfigValue("accountKey"));
    ZopimChat.init(accountKey);
  }

  @PluginMethod()
  public void setVisitorInfo(PluginCall call) {
    String name = call.getString("name");
    String email = call.getString("email");
    String phoneNumber = call.getString("phoneNumber");

    VisitorInfo visitorData = new VisitorInfo.Builder()
      .name(name)
      .email(email)
      .phoneNumber(phoneNumber)
      .build();

    ZopimChat.setVisitorInfo(visitorData);
  }

  @PluginMethod()
  public void open(PluginCall call) {
    String department = call.getString("department");
    JSArray tags = call.getArray("tags", new JSArray());

    String[] tagsParameters = null;
    try {
      tagsParameters = tags.<String>toList().toArray(new String[0]);
    } catch (JSONException e) {
      e.printStackTrace();
    }

    AppCompatActivity activity = getActivity();

    ZopimChat.SessionConfig config = new ZopimChat.SessionConfig()
      .department(department)
      .tags(tagsParameters);

    ZopimChatActivity.startActivity(activity, config);
  }
}
