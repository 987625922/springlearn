package designpatterns.builder;

/**
 * 简单的建筑者模式
 *
 * @author LL
 */
public class BuilderModel {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String name;
        private int age;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder age(int age) {
            this.age = age;
            return this;
        }

        public BuilderModel build() {
            BuilderModel student = new BuilderModel();
            student.setAge(age);
            student.setName(name);
            return student;
        }
    }
}
