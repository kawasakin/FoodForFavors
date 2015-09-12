package imagedeck;

import android.widget.ImageView;

import com.example.ajindal1.foodforfavors.R;

/**
 * Created by ajindal1 on 9/12/15.
 */
public class ImageDeck
{
    private int[] imageIDs = {R.drawable.burrito_menu,
            R.drawable.beer_menu,
            R.drawable.chicken_menu};;
    private int currentImage;

    public ImageDeck() {
        int currentImage = 0;
    }

    public int moveRight() {
        currentImage = (currentImage + 1)%imageIDs.length;
        return imageIDs[currentImage];
    }

    public int moveLeft() {
        currentImage = (currentImage - 1 + imageIDs.length)%imageIDs.length;
        return imageIDs[currentImage];
    }

    public int getCurrent() {
        return imageIDs[currentImage];
    }

}
