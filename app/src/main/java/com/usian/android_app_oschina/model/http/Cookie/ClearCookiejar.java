package com.usian.android_app_oschina.model.http.Cookie;

import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.usian.android_app_oschina.App;
import com.usian.android_app_oschina.utils.LogUtils;

import java.util.List;

import okhttp3.Cookie;
import okhttp3.HttpUrl;

/**
 * Created by 苏元庆 on 2017/5/22.
 */

public class ClearCookiejar implements ClearableCookieJar {

    private final PersistentCookieJar cookieStore =
            new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(App.getContext()));

    @Override
    public void clearSession() {
        cookieStore.clearSession();
    }

    @Override
    public void clear() {
        cookieStore.clear();
    }

    @Override
    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
        cookieStore.saveFromResponse(url, cookies);
    }

    @Override
    public List<Cookie> loadForRequest(HttpUrl url) {
        List<Cookie> cookies = cookieStore.loadForRequest(url);
        for (Cookie cookie : cookies){
            LogUtils.e("Cookie",cookie.toString());
        }
        return cookies;
    }
}
