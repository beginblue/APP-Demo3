package exercises.blue.demoagain.userdata;

import java.util.List;

/**
 * Created by getbl on 2016/6/15.
 */
public class responseBean {
    /**
     * error : false
     * results : [{"_id":"5760b303421aa940e70aa911","createdAt":"2016-06-15T09:44:35.925Z","desc":"直接看图，，不用看描述。","publishedAt":"2016-06-15T11:55:46.992Z","source":"web","type":"福利","url":"http://ww1.sinaimg.cn/mw690/692a6bbcgw1f4fz6g6wppj20ms0xp13n.jpg","used":true,"who":"龙龙童鞋"},{"_id":"575e0824421aa9296bddf5a4","createdAt":"2016-06-13T09:11:00.530Z","desc":"直接看图，，不用看描述。","publishedAt":"2016-06-14T11:52:47.320Z","source":"web","type":"福利","url":"http://ww3.sinaimg.cn/mw690/81309c56jw1f4sx4ybttdj20ku0vd0ym.jpg","used":true,"who":"龙龙童鞋"},{"_id":"575cbba1421aa96b24382520","createdAt":"2016-06-12T09:32:17.746Z","desc":"跟上一个是一个系列的。","publishedAt":"2016-06-13T11:38:17.247Z","source":"web","type":"福利","url":"http://ww4.sinaimg.cn/mw690/9844520fjw1f4fqrpw1fvj21911wlb2b.jpg","used":true,"who":"龙龙童鞋"}]
     */

    private boolean error;
    /**
     * _id : 5760b303421aa940e70aa911
     * createdAt : 2016-06-15T09:44:35.925Z
     * desc : 直接看图，，不用看描述。
     * publishedAt : 2016-06-15T11:55:46.992Z
     * source : web
     * type : 福利
     * url : http://ww1.sinaimg.cn/mw690/692a6bbcgw1f4fz6g6wppj20ms0xp13n.jpg
     * used : true
     * who : 龙龙童鞋
     */

    private List<ResultsBean> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }


    public static class ResultsBean {
        private String _id;
        private String createdAt;
        private String desc;
        private String publishedAt;
        private String source;
        private String type;
        private String url;
        private boolean used;
        private String who;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public boolean isUsed() {
            return used;
        }

        public void setUsed(boolean used) {
            this.used = used;
        }

        public String getWho() {
            return who;
        }

        public void setWho(String who) {
            this.who = who;
        }
    }
}