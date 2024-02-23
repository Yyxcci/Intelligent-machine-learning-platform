package com.yyx.aidemo1.utils;

import java.io.IOException;
import java.util.HashMap;

import com.baidu.aip.imageclassify.AipImageClassify;
import com.baidu.aip.nlp.AipNlp;
import org.json.JSONArray;
import org.json.JSONObject;
import com.baidu.aip.ocr.AipOcr;
import org.springframework.web.multipart.MultipartFile;


public class AiUtils {
    //设置APPID/AK/SK
    public static final String APP_ID_PIC = "46476064";
    public static final String API_KEY_PIC = "Mcw26347dZ0O1sp3U5IBG0yz";
    public static final String SECRET_KEY_PIC = "Q6tOF5Kk0ukuK7qqri50O99tGNktowM2";

    public static final String APP_ID_IMG = "46594143";
    public static final String API_KEY_IMG = "Vr7rA9u5RX8mk9GLrV2rNEdA";
    public static final String SECRET_KEY_IMG = "fCEtNY7jMMusbLDWKl0mZI92pdcbGSo4";

    public static final String APP_ID_NLP = "46670266";
    public static final String API_KEY_NLP = "VtXdKWUNtoFs4NtchxnVc81K";
    public static final String SECRET_KEY_NLP = "erl6eFYp3oifaMl3grvhSsQO6FgoLD7l";

    public static String picToWords(MultipartFile file) throws IOException {
        AipOcr client = new AipOcr(APP_ID_PIC, API_KEY_PIC, SECRET_KEY_PIC);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
//        client.setHttpProxy("proxy_host", proxy_port);  // 设置http代理
//        client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理

        // 可选：设置log4j日志输出格式，若不设置，则使用默认配置
        // 也可以直接通过jvm启动参数设置此环境变量
//        System.setProperty("aip.log4j.conf", "path/to/your/log4j.properties");

        // 调用接口
        JSONObject res = client.basicGeneral(file.getBytes(), new HashMap<String, String>());
        JSONArray words_result = res.getJSONArray("words_result");
        String result = "";
        for (int i = 0; i < words_result.length(); i++) {
            JSONObject jsonObject = words_result.getJSONObject(i);
            Object words = jsonObject.get("words");
            String s = (String) words;
            result += s + " ";
        }
        return result;
    }

    public static String imgRecognition(MultipartFile file) throws IOException {
        // 初始化一个AipImageClassify
        AipImageClassify client = new AipImageClassify(APP_ID_IMG, API_KEY_IMG, SECRET_KEY_IMG);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
//        client.setHttpProxy("proxy_host", proxy_port);  // 设置http代理
//        client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理

        // 调用接口
        JSONObject res = client.advancedGeneral(file.getBytes(), new HashMap<String, String>());
        JSONArray res_Array = res.getJSONArray("result");
        String result = "";
        for (int i = 0; i < res_Array.length(); i++) {
            JSONObject jsonObject = res_Array.getJSONObject(i);
            Object words = jsonObject.get("keyword");
            String s = (String) words;
            result += s + " ";
        }
        return result;
    }


    public static String nlp_correction(String text) {
        // 初始化一个AipNlp
        AipNlp client = new AipNlp(APP_ID_NLP, API_KEY_NLP, SECRET_KEY_NLP);

        // 传入可选参数调用接口
        HashMap<String, Object> options = new HashMap<String, Object>();

        // 文本纠错
        JSONObject res = client.ecnet(text, options);
        Object item = res.getJSONObject("item");
        Object correct_query = ((JSONObject) item).get("correct_query");
        String result = (String) correct_query;
        return result;
    }
}
