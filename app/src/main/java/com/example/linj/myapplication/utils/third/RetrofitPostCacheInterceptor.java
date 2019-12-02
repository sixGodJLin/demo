package com.example.linj.myapplication.utils.third;

import android.content.Context;
import android.support.annotation.NonNull;


import com.example.linj.myapplication.BuildConfig;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;

/**
 * Retrofit Post请求时缓存策略 （已过时，已用数据库方案替代）
 * Created by shenhua on 2018/8/3.
 *
 * @author shenhua
 * Email shenhuanet@126.com
 */
@Deprecated
class RetrofitPostCacheInterceptor implements Interceptor {

    private Context context;

    RetrofitPostCacheInterceptor(Context context) {
        this.context = context;
    }

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);
        final String url = request.url().toString();
        RequestBody requestBody = request.body();
        Charset charset = Charset.forName("UTF-8");
        StringBuilder sb = new StringBuilder();
        sb.append(url);
        if ("POST".equals(request.method()) && requestBody != null) {
            MediaType mediaType = requestBody.contentType();
            if (mediaType != null) {
                charset = mediaType.charset(Charset.forName("UTF-8"));
            }
            Buffer buffer = new Buffer();
            requestBody.writeTo(buffer);
            sb.append(buffer.readString(charset));
            buffer.close();
        }
        ResponseBody responseBody = response.body();
        if (responseBody != null) {
            MediaType mediaType = responseBody.contentType();
            BufferedSource source = responseBody.source();
            source.request(Long.MAX_VALUE);
            Buffer buffer = source.buffer();
            if (mediaType != null) {
                charset = mediaType.charset(Charset.forName("UTF-8"));
            }
            String key = sb.toString();
            String content = buffer.clone().readString(charset);
            CacheManager.get(context).putCache(key, content);
        }
        return response;
    }

    final static class CacheManager {
        /**
         * max cache size 10MB
         */
        private static final long DISK_CACHE_SIZE = 1024 * 1024 * 10;
        private static final int DISK_CACHE_INDEX = 0;
        private static final String CACHE_DIR = "retrofit-post";
        private DiskLruCache diskLruCache;
        private static volatile CacheManager sCacheManager;

        static CacheManager get(Context context) {
            if (sCacheManager == null) {
                synchronized (CacheManager.class) {
                    if (sCacheManager == null) {
                        sCacheManager = new CacheManager(context);
                    }
                }
            }
            return sCacheManager;
        }

        private CacheManager(Context context) {
            File diskCacheDir = new File(context.getCacheDir() + File.separator + CACHE_DIR);
            if (!diskCacheDir.exists()) {
                diskCacheDir.mkdirs();
            }
            if (diskCacheDir.getUsableSpace() > DISK_CACHE_SIZE) {
                try {
                    diskLruCache = DiskLruCache.open(
                            diskCacheDir,
                            BuildConfig.VERSION_CODE,
                            /*一个key对应多少个文件*/
                            1,
                            DISK_CACHE_SIZE);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        /**
         * 添加缓存
         *
         * @param key     key
         * @param content cache content
         */
        void putCache(String key, String content) {
            if (diskLruCache != null) {
                OutputStream outputStream = null;
                try {
                    DiskLruCache.Editor editor = diskLruCache.edit(encryptMD5(key));
                    outputStream = editor.newOutputStream(0);
                    outputStream.write(content.getBytes());
                    outputStream.flush();
                    editor.commit();
                    diskLruCache.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (outputStream != null) {
                        try {
                            outputStream.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

        /**
         * 获取缓存
         *
         * @param key key
         * @return cache content
         */
        String getCache(String key) {
            if (diskLruCache == null) {
                return null;
            }
            FileInputStream fileInputStream = null;
            ByteArrayOutputStream byteArrayOutputStream = null;
            try {
                DiskLruCache.Snapshot snapshot = diskLruCache.get(encryptMD5(key));
                if (snapshot != null) {
                    fileInputStream = (FileInputStream) snapshot.getInputStream(DISK_CACHE_INDEX);
                    byteArrayOutputStream = new ByteArrayOutputStream();
                    byte[] bytes = new byte[1024];
                    int len;
                    while ((len = fileInputStream.read(bytes)) != -1) {
                        byteArrayOutputStream.write(bytes, 0, len);
                    }
                    byte[] data = byteArrayOutputStream.toByteArray();
                    return new String(data);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (byteArrayOutputStream != null) {
                    try {
                        byteArrayOutputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return null;
        }

        /**
         * 清理缓存
         *
         * @param key key
         * @return true 则清理成功
         */
        boolean clear(String key) {
            if (diskLruCache != null) {
                try {
                    return diskLruCache.remove(encryptMD5(key));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return false;
        }

        private String encryptMD5(String key) {
            try {
                byte[] hash = MessageDigest.getInstance("MD5").digest(key.getBytes("UTF-8"));
                StringBuilder sb = new StringBuilder(hash.length * 2);
                for (byte b : hash) {
                    if ((b & 0xFF) < 0x10) {
                        sb.append("0");
                    }
                    sb.append(Integer.toHexString(b & 0xFF));
                }
                return sb.toString();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
                return key;
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                return key;
            }
        }
    }
}
