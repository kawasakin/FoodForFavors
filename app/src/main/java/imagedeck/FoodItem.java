package imagedeck;

/**
 * Created by ajindal1 on 9/12/15.
 */
public class FoodItem
{
    public int getImageID() {
        return mImageID;
    }

    public void setImageID(int mImageID) {
        this.mImageID = mImageID;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public double getPrice() {
        return mPrice;
    }

    public void setPrice(double mPrice) {
        this.mPrice = mPrice;
    }

    int mImageID;
    String mName;
    double mPrice;

    public FoodItem(int imageID, String name, double price) {
        mImageID = imageID;
        mName = name;
        mPrice = price;
    }
}
