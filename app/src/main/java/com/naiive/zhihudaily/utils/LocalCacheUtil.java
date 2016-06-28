package com.naiive.zhihudaily.utils;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.jakewharton.disklrucache.DiskLruCache;
import com.naiive.zhihudaily.AppContext;
import com.naiive.zhihudaily.common.base.BaseModel;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangzhe on 16/5/22.
 */
public class LocalCacheUtil {
    private static final String FLAG_DEFAULT = "_DEFAULT";

    private static final Map<String, DiskLruCache> mCacheManager = new HashMap<>();

    private static DiskLruCache mCache;

    private volatile static LocalCacheUtil INSTANCE;

    private Gson mGson;

    private LocalCacheUtil() {
        mGson = new Gson();
    }

    public static LocalCacheUtil getDefault() {
        getInstance();
        if (mCacheManager.get(FLAG_DEFAULT) == null) {
            mCacheManager.put(FLAG_DEFAULT, INSTANCE.createDiskLruCache(INSTANCE.getCacheDir(FLAG_DEFAULT)));
        }
        mCache = mCacheManager.get(FLAG_DEFAULT);
        return INSTANCE;
    }

    private File getCacheDir(String uniqueName) {
        File cacheDir = FileUtil.getDiskCacheDir(AppContext.getContext(), uniqueName);
        if (!cacheDir.exists()) {
            cacheDir.mkdirs();
        }
        return cacheDir;
    }

    private DiskLruCache createDiskLruCache(File cacheDir) {
        try {
            return DiskLruCache.open(cacheDir, PackageUtil.getAppVersion(AppContext.getContext()), 1, 10 * 1024 * 1024);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    private static LocalCacheUtil getInstance() {
        if (INSTANCE == null) {
            synchronized (LocalCacheUtil.class) {
                if (INSTANCE == null) {
                    INSTANCE = new LocalCacheUtil();
                }
            }
        }
        return INSTANCE;
    }

    public boolean putString(String key, String string) {
        return write(key, string.getBytes());
    }

    public String getString(String key) {
        return new String(read(key));
    }

    public void removeString(String key) {
        remove(key);
    }

    public <T extends BaseModel> boolean putData(String key, T data) {
        if (data == null) return false;
        return write(key, mGson.toJson(data).getBytes());
    }

    public <T extends BaseModel> T fetchData(String key, Class clazz) {
        byte[] bytes = read(key);
        if (bytes == null) return null;
        String json = new String(bytes);
        return (T) mGson.fromJson(json, clazz);
    }


    private boolean write(String key, final byte[] data) {
        if (TextUtils.isEmpty(key) || data == null || mCache == null) return false;
        String hashKey = hashKeyForDisk(key);
        boolean flag = false;

        try {
            final DiskLruCache.Editor editor = mCache.edit(hashKey);
            if (editor == null) return false;
            OutputStream os = editor.newOutputStream(0);
            os.write(data);
            os.flush();
            os.close();
            editor.commit();
            mCache.flush();
            flag = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return flag;

    }

    private byte[] read(String key) {
        if (TextUtils.isEmpty(key) || mCache == null) return null;
        String hashKey = hashKeyForDisk(key);

        byte[] data = null;
        try {
            DiskLruCache.Snapshot snapshot = mCache.get(hashKey);
            String cacheValue = snapshot != null ? snapshot.getString(0) : null;
            data = TextUtils.isEmpty(cacheValue) ? null : cacheValue.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    private boolean remove(String key) {
        if (TextUtils.isEmpty(key) || mCache == null) return false;

        boolean flag = false;
        try {
            mCache.remove(hashKeyForDisk(key));
            flag = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return flag;
    }


    /**
     * key to hash key
     *
     * @param key
     * @return
     */
    private String hashKeyForDisk(String key) {
        String cacheKey;
        try {
            final MessageDigest mDigest = MessageDigest.getInstance("MD5");
            mDigest.update(key.getBytes());
            cacheKey = bytesToHexString(mDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            cacheKey = String.valueOf(key.hashCode());
        }
        return cacheKey;
    }

    private String bytesToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(0xFF & bytes[i]);
            if (hex.length() == 1) {
                sb.append('0');
            }
            sb.append(hex);
        }
        return sb.toString();
    }

    private byte[] objectToBytes(Object object) {
        return FileUtil.objectToByte(object);
    }

    private Object bytesToObject(byte[] data) {
        return FileUtil.byteToObject(data);
    }

    public static class KeyHelper {
        public static String moreDataKey(String id) {
            return "dateBefore---" + id;
        }
    }
}
