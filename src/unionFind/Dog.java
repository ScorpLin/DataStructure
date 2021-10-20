package unionFind;

public class Dog {
    public String name;

    public void bark() {
        System.out.println(this.name + "汪汪汪");
    }

    public Dog(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        Dog dog = new Dog("LGX");
        dog.bark();
    }
}
