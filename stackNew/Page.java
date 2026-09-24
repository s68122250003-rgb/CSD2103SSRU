public class Page {
    private String pageId;
    private String pageTitle;
    private String url;
    private String visitedTime;

    public Page(String pageId, String pageTitle, String url, String visitedTime) {
        this.pageId = pageId;
        this.pageTitle = pageTitle;
        this.url = url;
        this.visitedTime = visitedTime;
    }

    @Override
    public String toString() {
        return pageTitle + " (" + url + ")";
    }
}