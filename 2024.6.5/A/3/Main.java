public class Main {

    public static void main(String[] args) {
        Patient april = new Patient();
        april.setName("name1");
        april.setSex('f');
        april.setAge(30);
        april.setWeight(60.72f);
        april.setAllergies(true);
        System.out.println("name: " + april.getName());
        System.out.println("sex: " + april.getSex());
        System.out.println("age: " + april.getAge());
        System.out.println("weight: " + april.getWeight());
        System.out.println("allergies: " + april.isAllergies());
        System.out.println("Patient info: " + april.toString());

        Patient patient_ = new Patient();
        patient_.setName("name2");
        patient_.setSex('m');
        patient_.setAge(25);
        patient_.setWeight(70.30f);
        patient_.setAllergies(false);
        System.out.println("name: " + patient_.getName());
        System.out.println("sex: " + patient_.getSex());
        System.out.println("age: " + patient_.getAge());
        System.out.println("weight: " + patient_.getWeight());
        System.out.println("allergies: " + patient_.isAllergies());
        System.out.println("Patient info: " + patient_.toString());
    }
}
class Patient {
    private String name;
    private char sex;
    private int age;
    private float weight;
    private boolean allergies;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public boolean isAllergies() {
        return allergies;
    }

    public void setAllergies(boolean allergies) {
        this.allergies = allergies;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "name='" + name + '\'' +
                ", sex=" + sex +
                ", age=" + age +
                ", allergies=" + allergies +
                '}';
    }
}
