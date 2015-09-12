package imagedeck;

import android.widget.ImageView;

import com.example.ajindal1.foodforfavors.R;

/**
 * Created by ajindal1 on 9/12/15.
 */
public class ImageDeck
{
    private FoodItem[] menu = { new FoodItem(R.drawable.burrito_menu,"burrito",1),
                                new FoodItem(R.drawable.beer_menu,"a few Beers",3),
                                new FoodItem(R.drawable.chicken_menu,"some chicken wings",10)
                                new FoodItem(R.drawable.pizza_menu, "pizza", 3)};

    private int currentImage;

    public ImageDeck() {
        int currentImage = 0;
    }

    public FoodItem moveRight() {
        currentImage = (currentImage + 1)%menu.length;
        return menu[currentImage];
    }

    public FoodItem moveLeft() {
        currentImage = (currentImage - 1 + menu.length)%menu.length;
        return menu[currentImage];
    }

    public FoodItem getCurrent() {
        return menu[currentImage];
    }

}
