package exercises.blue.userdata;

import android.support.annotation.Nullable;

/**
 * Created by getbl on 2016/4/20.
 */
public class userDatum {

    private String title;
    private String content;
    private int category;
    private int goodCount;//点赞数

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getGoodCount() {
        return goodCount;
    }

    public void setGoodCount(int goodCount) {
        this.goodCount = goodCount;
    }

    public userDatum(){
        title="Default Title";
        content="Content";
        goodCount=0;
        category=0;
    }

    public userDatum(String title, String content, int category, int goodCount){
        this.title=title;
        this.content=content;
        this.category=category;
        this.goodCount=goodCount;
    }
}
