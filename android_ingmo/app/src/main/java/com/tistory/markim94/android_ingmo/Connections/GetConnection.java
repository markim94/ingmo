package com.tistory.markim94.android_ingmo.Connections;

import android.os.AsyncTask;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

// clone code that Connection form by brother jun

public class GetConnection extends AsyncTask {
// 1. 객체 생성 (url)
// 2. addParam 함수로 매개변수 지정
// 3. Task 실행
// 4. 결과는 resultObj에 저장됨

    URL url;
    HttpURLConnection urlConn = null;
    StringBuffer param = null;
    public JSONObject resultObj = new JSONObject();

    public void addParam(String key, String value) {
        if (param == null) {
            param = new StringBuffer("?" + key + "=" + value);
        } else {
            param.append("&" + key + "=" + value);
        }
    }

    public GetConnection(String url) {
        super();
        String result;
        try {
            this.url = new URL(url);
            urlConn = (HttpURLConnection) this.url.openConnection();
            InputStream inputStream = urlConn.getInputStream();
            InputStreamReader isw = new InputStreamReader(inputStream);
            int responseCode = urlConn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK){
                result = readStream(urlConn.getInputStream());
                resultObj =  new JSONObject(result);
            }
        }catch (Exception exc){
            exc.printStackTrace();
        }

    }
    private String readStream(InputStream in) {
        BufferedReader reader = null;
        StringBuffer response = new StringBuffer();
        try {
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return response.toString();
    }

    @Override
    protected Object doInBackground(Object[] objects) {


        return null;
    }
}