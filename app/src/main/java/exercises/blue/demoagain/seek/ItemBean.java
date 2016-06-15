package exercises.blue.demoagain.seek;

/**
 * Created by blue on 16-6-15.
 */
public class ItemBean {
    public int ItemImageResid;
    public String ItemTitle;
    public String ItemContent;

    public ItemBean(int itemImageResid, String itemTitle, String itemContent) {
        ItemImageResid = itemImageResid;
        ItemTitle = itemTitle;
        ItemContent = itemContent;
    }
    public ItemBean( String itemTitle, String itemContent) {
        ItemImageResid = 0;
        ItemTitle = itemTitle;
        ItemContent = itemContent;
    }
}
