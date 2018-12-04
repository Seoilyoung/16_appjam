package org.androidtown.myko.component_server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.androidtown.myko.network.ServerInterface;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * Created by Administrator on 2015-07-08.
 */
public class ApplicationController extends android.app.Application{

    private static ApplicationController instance;
    public static ApplicationController getInstance() { return instance; }
    @Override
    public void onCreate() {
        super.onCreate();
        ApplicationController.instance = this;
    }
    private ServerInterface api;
    public ServerInterface getServerInterface() { return api; }
    private String endpoint;
    public void buildServerInterface() {
        synchronized (ApplicationController.class) {
            if (api == null) {
                endpoint = "http://52.25.221.90:3000";
                Gson gson = new GsonBuilder().create();
                RestAdapter.Builder builder = new RestAdapter.Builder();
                builder.setConverter(new GsonConverter(gson));
                builder.setEndpoint(endpoint);
                RestAdapter adapter = builder.build();
                api = adapter.create(ServerInterface.class);
            }
        }
    }

}
