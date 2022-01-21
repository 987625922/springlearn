package basis.clone;

import java.io.Serializable;

public class Major implements Serializable,Cloneable {
    private String majorName; // 专业名称
    private long majorId;     // 专业代号

    public Major(String majorName, long majorId) {
        this.majorName = majorName;
        this.majorId = majorId;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public long getMajorId() {
        return majorId;
    }

    public void setMajorId(long majorId) {
        this.majorId = majorId;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        // 浅拷贝的实现
        return super.clone();
    }
}
