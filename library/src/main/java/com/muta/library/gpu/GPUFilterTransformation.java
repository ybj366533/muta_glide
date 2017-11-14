package com.muta.library.gpu;

/**
 * Copyright (C) 2017 Wasabeef
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.muta.library.BitmapTransformation;

import jp.co.cyberagent.android.gpuimage.GPUImage;
import jp.co.cyberagent.android.gpuimage.GPUImageFilter;

public class GPUFilterTransformation extends BitmapTransformation {

  private GPUImageFilter gpuImageFilter;

  public GPUFilterTransformation(GPUImageFilter filter) {
    this.gpuImageFilter = filter;
  }

  @Override protected Bitmap transform(@NonNull Context context, @NonNull BitmapPool pool,
      @NonNull Bitmap toTransform, int outWidth, int outHeight) {
    GPUImage gpuImage = new GPUImage(context);
    gpuImage.setImage(toTransform);
    gpuImage.setFilter(gpuImageFilter);

    return gpuImage.getBitmapWithFilterApplied();
  }

  @Override public String key() {
    return getClass().getSimpleName();
  }

  @SuppressWarnings("unchecked") public <T> T getFilter() {
    return (T) gpuImageFilter;
  }
}
