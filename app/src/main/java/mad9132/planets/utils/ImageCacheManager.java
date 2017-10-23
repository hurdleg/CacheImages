package mad9132.planets.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import mad9132.planets.model.PlanetPOJO;

/**
 * Cache images in persistent storage
 *
 * @author David Gasner (original)
 */
public class ImageCacheManager {

    public static Bitmap getBitmap(Context context, PlanetPOJO planet) {
        String fileName = context.getCacheDir() + "/" + planet.getImage().replaceAll("\\s+", "");
        fileName = fileName.replace("/images", "");
        File file = new File(fileName);
        if (file.exists()) {
            try {
                return BitmapFactory.decodeStream(new FileInputStream(file));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static void putBitmap(Context context, PlanetPOJO planet, Bitmap bitmap) {
        String fileName = context.getCacheDir() + "/" + planet.getImage().replaceAll("\\s+", "");
        fileName = fileName.replace("/images", "");
        File file = new File(fileName);
        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 85, outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
