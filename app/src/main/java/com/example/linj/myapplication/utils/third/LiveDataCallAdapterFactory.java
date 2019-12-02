package com.example.linj.myapplication.utils.third;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;


import com.bumptech.glide.load.engine.Resource;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.concurrent.atomic.AtomicBoolean;

import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by shenhua on 2018/8/6.
 *
 * @author shenhua
 * Email shenhuanet@126.com
 */
public class LiveDataCallAdapterFactory extends CallAdapter.Factory {

    @Nullable
    @Override
    public CallAdapter<?, ?> get(@NonNull Type returnType, @NonNull Annotation[] annotations, @NonNull Retrofit retrofit) {
        if (getRawType(returnType) != LiveData.class) {
            return null;
        }
        Type observableType = getParameterUpperBound(0, (ParameterizedType) returnType);
        Class<?> rawObservableType = getRawType(observableType);
        if (rawObservableType != Resource.class) {
            throw new IllegalArgumentException("type must be a resource");
        }
        if (!(observableType instanceof ParameterizedType)) {
            throw new IllegalArgumentException("resource must be parameterized");
        }
        Type bodyType = getParameterUpperBound(0, (ParameterizedType) observableType);
        return new LiveDataCallAdapter<>(bodyType);
    }

    class LiveDataCallAdapter<R> implements CallAdapter<R, LiveData<Resource<R>>> {

        private Type responseType;

        LiveDataCallAdapter(Type responseType) {
            this.responseType = responseType;
        }

        @Override
        public Type responseType() {
            return responseType;
        }

        @Override
        public LiveData<Resource<R>> adapt(@NonNull final Call<R> call) {
            return new LiveData<Resource<R>>() {

                AtomicBoolean started = new AtomicBoolean(false);

                @Override
                protected void onActive() {
                    super.onActive();
                    if (started.compareAndSet(false, true)) {
                        call.enqueue(new Callback<R>() {
                            @Override
                            public void onResponse(@NonNull Call<R> call1, @NonNull Response<R> response) {
//                                postValue(Resource.Companion.toResource(response));
                            }

                            @Override
                            public void onFailure(@NonNull Call<R> call1, @NonNull Throwable t) {
//                                postValue(Resource.Companion.<R>error(t.getMessage()));
                            }
                        });
                    }
                }
            };
        }
    }
}
