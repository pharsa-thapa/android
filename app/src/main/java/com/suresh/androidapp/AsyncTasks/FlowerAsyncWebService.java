package com.suresh.androidapp.AsyncTasks;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Looper;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.suresh.androidapp.FlowerDetail;
import com.suresh.androidapp.R;
import com.suresh.androidapp.adapters.FlowerRVAdapter;
import com.suresh.androidapp.constants.WebServiceConstants;
import com.suresh.androidapp.entities.Flower;
import com.suresh.androidapp.helpers.WebHelper;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.ClientProtocolException;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;

/**
 * Created by suresh on 11/11/16.
 */

public class FlowerAsyncWebService extends AsyncTask<String, Void, String> {

    private View view;
    private Context context;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;

    public FlowerAsyncWebService(View vw, Context ctx, ProgressBar pgrBar) {
        view = vw;
        context = ctx;
        progressBar = pgrBar;
        recyclerView = (RecyclerView) view.findViewById(R.id.flower_recycler_view);
    }

    @Override
    protected void onPreExecute() {
        progressBar.setVisibility(View.VISIBLE);

    }

    @Override
    protected String doInBackground(String... urls) {
        Looper.prepare();

        String url = WebServiceConstants.FLOWER_SERVICE_URL + "feeds/flowers.json";
        String response = null;

        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(url);
        try {
            HttpResponse httpResponse = httpclient.execute(httppost);
            InputStream is = httpResponse.getEntity().getContent();
            response = WebHelper.convertStreamToString(is);

            Log.v(".doInBackground", "json response is:" + response);

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }

    @Override
    protected void onPostExecute(String s) {
        progressBar.setVisibility(View.INVISIBLE);

        JsonElement json = new JsonParser().parse(s);
        JsonArray array = json.getAsJsonArray();
        Iterator iterator = array.iterator();
        List<Flower> flowerList = new ArrayList<Flower>();

        while (iterator.hasNext()) {
            JsonElement flowerJson = (JsonElement) iterator.next();
            Gson gson = new Gson();
            Flower flower = gson.fromJson(flowerJson, Flower.class);
            //can set some values in flower, if required
            flowerList.add(flower);
        }
        recyclerView.setHasFixedSize(true);

        FlowerRVAdapter adapter = new FlowerRVAdapter(flowerList);
        recyclerView.setAdapter(adapter);
        // Set layout manager to position the items
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

    }
}