package exercises.blue.demoagain.userdata;

/**
 *
 * Discarded！！
 * This has been replaced by responseBean
 * Created by getbl on 2016/4/20.
 */
public class friendsDatum {

    private String title;
    private String content;
    private int category;//废弃
    private int goodCount;//点赞数//废弃
    private String author;


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

    public friendsDatum(){
        title="Default Title";
        content="Content";
        goodCount=0;
        category=0;
    }

    public friendsDatum(String title, String content, String author, int goodCount){
        this.title=title;
        this.content=content;
        this.category=0;
        this.author=author;
        this.goodCount= 233;
    }

    public friendsDatum(String title,String content,String author){
        this.title=title;
        this.content=content;
        this.author = author;
        this.category=0;
        this.goodCount=233;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
