package com.nacho.spidermanmemes.fragments;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.nacho.spidermanmemes.R;
import com.nacho.spidermanmemes.util.ImageDownloader;
import com.nacho.spidermanmemes.util.Random;

import java.lang.ref.WeakReference;

public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }

    ImageView mImageMeme;
    Button mBtnRandomMeme;
    
    Button mBtnDownloadImage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        mImageMeme = (ImageView) view.findViewById(R.id.random_image);
        mBtnRandomMeme = (Button) view.findViewById(R.id.random_button);
        mBtnDownloadImage = (Button) view.findViewById(R.id.save_button);

        assert mImageMeme != null;

        mBtnRandomMeme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectNextRandomImage();
            }
        });
        mBtnDownloadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                downloadImage();
            }
        });
        
        return view;
    }

    private void downloadImage() {

        WeakReference<ImageView> imageView = new WeakReference<ImageView>(mImageMeme);
        ImageDownloader imageDownloader = new ImageDownloader(imageView);

        imageDownloader.setListener(new ImageDownloader.ImageDownloaderObservable() {
            @Override
            public void onImageDownloadingStart() {

            }

            @Override
            public void onImageDownloadingFinish() {

            }
        });

        imageDownloader.execute("http://static.comicvine.com/uploads/original/11114/111142124/3807028-0520411863-da54c.jpg");
    }

    public static final int NO_INDEX = -1;
    int lastSelectedIndex = NO_INDEX;   // Don't use magic numbers

    private void selectNextRandomImage() {

        final int[] images = {
                R.drawable.butter,
                R.drawable.cancer,
                R.drawable.candy,
                R.drawable.christmas,
                R.drawable.diary,
                R.drawable.gryffindor,
                R.drawable.high,
                R.drawable.hitler,
                R.drawable.jackass,
                R.drawable.lava,
                R.drawable.love,
                R.drawable.masturbating,
                R.drawable.mj,
                R.drawable.narnia,
                R.drawable.options,
                R.drawable.orphans,
                R.drawable.osama,
                R.drawable.shut,
                R.drawable.teach,
                R.drawable.weed
        };

        int number = Random.generate(images.length);

        if (number >= 0 && number < images.length) {
            lastSelectedIndex = number;

            while (lastSelectedIndex != NO_INDEX && number == lastSelectedIndex){
                number = Random.generate(images.length);
            }
            lastSelectedIndex = number;

            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), images[number]);

            mImageMeme.setImageBitmap(bitmap);
        }
    }
}
