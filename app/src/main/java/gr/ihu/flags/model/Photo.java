package gr.ihu.flags.model;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Photo implements Serializable {
    private String name;
    private String type = "mammal";

    private String filename;

    private byte[] data;

    private String description = "";

    private String url = "";
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String calculateVideoCode() {
        String pattern = "(?:https?://)?(?:www\\.)?(?:youtube\\.com/(?:watch\\?(?:.*&)?v=|embed/|v/|shorts/)|youtu\\.be/)([a-zA-Z0-9_-]{11})";
        Pattern compiledPattern = Pattern.compile(pattern);
        Matcher matcher = compiledPattern.matcher(url);

        if (matcher.find()) {
            return matcher.group(1); // The video ID
        } else {
            return ""; // No valid video ID found
        }
    }
}