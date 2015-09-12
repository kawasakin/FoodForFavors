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

    public int getQuantity() {
        return mQuantity;
    }

    public void setPrice(int quantity) {
        this.mQuantity = quantity;
    }

    int mImageID;
    String mName;
    int mQuantity;

    public FoodItem(int imageID, String name, int quantity) {
        mImageID = imageID;
        mName = name;
        mQuantity = quantity;
    }
}
