package com.example.wangdonghai.didiactivity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.wangdonghai.Layout.CheckableLinearLayout;
import com.example.wangdonghai.adapter.DiDiListAdapter;
import com.example.wangdonghai.didimodel.DiDiOrder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
/**
 * Created by wangdonghai on 16/10/16.
 */
public class DiDiActivity extends AppCompatActivity {
    private Context context;
    private EditText LoginAccountText, LoginPasswordText;
    private Button LoginBtn;
    private String account, password;
    private String urlroot = "https://epassport.diditaxi.com.cn/passport/login/v3/password?cell=";
    private String url1 = "&role=1&password=";
    private String url2 = "&url=http%3A%2F%2Fcommon.diditaxi.com.cn%2Fgeneral%2FwebEntry%3Fopenid%3Dgeneral_app%26channel%3D%26source%3D%26datatype%3Dwebapp%26token%3DKrWjvVvrs8gbKnO1e6r9rNlJjryEQdfmTLIT8TChN5tUjTsOAjEMRO8ytQvHdoLj2yx_qkUbIYpV7o6hY5qR3pNmdiwIgHBEiEtzZtNm4s6EM0KVcEHseC5jvNctUbFDq71b7YSxvrZT-joJ1391y92i_Espkhf3L8l-IHh-AgAA__8%253D%26phone%3D13000000112&version=0.1.8&appid=100000001&lat=undefined&lng=undefined&maptype=undefined&city_id=undefined&area=undefined&channel=&phone=";
    private String url3 = "&openid=general_app&userType=0&flag=2";
    private String diditoken = null;
    private ListView mlistview;
    private RelativeLayout Loginlayout;
    private ArrayList<DiDiOrder> AllOrderList=new ArrayList<>();
    private ArrayList<DiDiOrder> ReimbursementOrderList=new ArrayList<>();
    private String urlorder = "http://common.diditaxi.com.cn/general/webEntry/history?openid=general_app&channel=&source=&datatype=webapp&phone=";
    private String urlorder1 = "&token=";
    private DiDiListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_di_di);
        context=this;
        Loginlayout = (RelativeLayout) findViewById(R.id.login_layout);
        LoginAccountText = (EditText) findViewById(R.id.login_account);
        LoginPasswordText = (EditText) findViewById(R.id.login_password);
        LoginBtn = (Button) findViewById(R.id.login_btn);
        mlistview = (ListView) findViewById(R.id.listview);
        adapter=new DiDiListAdapter(getLayoutInflater(),AllOrderList);
        LoginAccountText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (count == 1){
                    int length = s.toString().length();
                    if (length == 3 || length == 8){
                        LoginAccountText.setText(s + " ");
                        LoginAccountText.setSelection(LoginAccountText.getText().toString().length());
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mlistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ((CheckableLinearLayout)view).toggle();
            }
        });
    }
//模拟登陆
    public void LoginRequest(View v) {
        account = LoginAccountText.getText().toString();
        account=account.replaceAll(" ","");
        password = LoginPasswordText.getText().toString();
        if (!TextUtils.isEmpty(account) && !TextUtils.isEmpty(password)) {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, urlroot + account + url1 + password + url2 + account + url3, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Log.e("--->>", response.toString());
                    if ("OK".equals(response.optString("errmsg"))) {
                        diditoken = response.optString("ticket");
                        if (!TextUtils.isEmpty(diditoken))
                            getOrderList(diditoken);
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("-->>>>", error.getMessage());
                }
            });
            BaseApplication.getRequestQueue().add(jsonObjectRequest);
        }
    }
//获取所有行程订单
    private void getOrderList(String token) {
        JsonObjectRequest jsonObjectRequestOrderList = new JsonObjectRequest(Request.Method.GET, urlorder + account + urlorder1 + token, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response)  {
                Log.e("--->>", response.toString());
                JSONArray jsonArray=response.optJSONArray("order_done");
                for(int i=0;i<jsonArray.length();i++){
                    JSONObject jsonobject= null;
                    try {
                        jsonobject = (JSONObject) jsonArray.get(i);
                        DiDiOrder diorder=new DiDiOrder();
                        diorder.setOrderId(jsonobject.optString("orderId"));
                        diorder.setFromAdress(jsonobject.optString("fromAddress"));
                        diorder.setTime(jsonobject.optString("setuptime"));
                        diorder.setToAdress(jsonobject.optString("toAddress"));
                        AllOrderList.add(diorder);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
                mlistview.setAdapter(adapter);
                Loginlayout.setVisibility(View.GONE);
                mlistview.setVisibility(View.VISIBLE);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("-->>>>", error.getMessage());
            }
        });
        BaseApplication.getRequestQueue().add(jsonObjectRequestOrderList);
    }
}
