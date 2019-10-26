package utils;

/**
 * @description: tree视图工具类
 * @company: yaorange
 * @author: 土匪
 * @version: 1.0
 * @create: 16-10-23 16:27:50
 */
public class ZtreeVO {
    private String id;
    private String pId;
    private String name;
    private Boolean checked;
    private Boolean open;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }
}
