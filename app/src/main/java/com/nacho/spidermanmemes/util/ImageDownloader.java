package com.nacho.spidermanmemes.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class ImageDownloader extends AsyncTask<String, Integer, Bitmap> {

    public interface ImageDownloaderObservable {
        public void onImageDownloadingStart();
        public void onImageDownloadingFinish();
    }
    private ImageDownloaderObservable listener;

    private WeakReference<ImageView> mImageView;

    public ImageDownloader(WeakReference<ImageView> imageView) {
        this.mImageView = imageView;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (listener != null) {
            listener.onImageDownloadingStart();
        }
    }

    @Override
    protected Bitmap doInBackground(String... imageNames) {
        String imageName = null;
        if (imageNames != null & imageNames.length > 0){
            imageName = imageNames[0];
        }

        Bitmap bitmap = null;

        try {
            URL imageUrl = new URL(imageName);
            URLConnection connection = imageUrl.openConnection();
            bitmap = BitmapFactory.decodeStream(connection.getInputStream());
        } catch (MalformedURLException e) {
            e.printStackTrace();
            Log.e("", "Image downloading problem ".concat(imageName));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }

        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);

        ImageView im = mImageView.get();
        if (im != null){
            im.setImageBitmap(bitmap);
        }

        if (listener != null) {
            listener.onImageDownloadingFinish();
        }
    }

    public ImageDownloaderObservable getListener() {
        return listener;
    }

    public void setListener(ImageDownloaderObservable listener) {
        this.listener = listener;
    }
}
