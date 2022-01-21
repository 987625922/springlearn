package basis.clone;

import java.io.*;

public class Student implements Serializable,Cloneable {
    private String name;  // 姓名
    private int age;      // 年龄
    private Major major;  // 所学专业

    public Student(String name, int age, Major major) {
        this.name = name;
        this.age = age;
        this.major = major;
    }

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

    public Major getMajor() {
        return major;
    }

    public void setMajor(Major major) {
        this.major = major;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {

        // 浅拷贝的实现
//        return super.clone();

        // 深拷贝实现
//        Student student = (Student) super.clone();
//        student.major = (Major) major.clone(); // 重要！！！
//        return student;

        // 序列化/反序列化实现深拷贝
        try {
            // 将对象本身序列化到字节流
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream =
                    new ObjectOutputStream( byteArrayOutputStream );
            objectOutputStream.writeObject( this );

            // 再将字节流通过反序列化方式得到对象副本
            ObjectInputStream objectInputStream =
                    new ObjectInputStream( new ByteArrayInputStream( byteArrayOutputStream.toByteArray() ) );
            return objectInputStream.readObject();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }
}
