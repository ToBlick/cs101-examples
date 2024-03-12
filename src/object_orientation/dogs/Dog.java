package dogs;

/**
 * A virtual representation of the concept, Dog.
 * Objects will be instantiated from this class in the TestDog.java file.
 */
public class Dog {

    // properties (a.k.a data field)
    private String breed = "Corgi";
    private String name;
    private int age = 0;    
    private static int numDogs = 0;

    /**
     * No-args constructor with default settings.
     */
    public Dog() {
        this.name = "Spot";
        Dog.numDogs++; // if this constructor method has been run, we've made a dog object
    }

    /**
     * An overloaded constructor that allows us to set a custom name when instantiating a Dog object.
     * @param name The name to give the dog.
     */
    public Dog(String name) {
        this.setName(name); // assign the object's property value to be the argument value
        Dog.numDogs++; // if this constructor method has been run, we've made a dog object
    }

    // actions (a.k.a methods)

    /**
     * Make the dog bark.
     */
    public void bark() {
        if (this.getName().equals("Bob")) {
            System.out.printf("%s says, 'No... I'm not going to bark... do it yourself!'\n", this.getName());
        }
        else {
            System.out.println(this.name + " says, 'Woof!'");
        }
    }


    /**
     * Classic 'getter' function for the name property.
     * @return The name of the dog.
     */
    public String getName() {
        return this.name;
    }

    /**
     * A 'setter' for the name property.
     * Includes validation that only allows some values into the property and not others.
     * @param name The name to try to give the dog.
     */
    public void setName(String name) {
        // validation!
        if (!name.toLowerCase().equals("amos")) {
            this.name = name;
        }
        else {
            System.out.printf("Sorry, can't assign the name %s to this dog... naming it 'Spot' instead!\n", name);
            this.name = "Spot";
        }
    }

    /**
     * A 'setter' for the breed property.
     * @param breed The breed to try to give the dog.
     */
    public void setBreed(String breed) {
        this.breed = breed;
    }

    /**
     * A 'setter' for the age property.
     * @param age The age to try to give the dog.
     */
    public void setAge(int age) {
        this.age = age;
    }
    
    /**
     * Classic 'getter' function for the breed property.
     * @return The breed of the dog.
     */
    public String getBreed() {
        return this.breed;
    }


    /**
     * Classic 'getter' function for the age property.
     * @return The age of the dog.
     */
    public int getAge() {
        return this.age;
    }

    /**
     * Classic 'getter' function for the static numDogs property.
     * @return The number of dogs that have been instantiated so far.
     */
    public static int getNumDogs() {
        // the numDogs property is static, meaning it belongs to the class, so reference it correctly prefixed with the class name.
        return Dog.numDogs;
    }

    /**
     * Compare this dog to any other dog.
     * @param otherDog The other dog to compare this dog to.
     * @return Whether this dog and the other dog have all the same property values.
     */
    public boolean equals(Dog otherDog) {
        boolean nameIsSame = (this.getName().equals(otherDog.getName()));
        boolean breedIsSame = (this.getBreed().equals(otherDog.getBreed()));
        boolean ageIsSame = (this.getAge() == otherDog.getAge());
        return (nameIsSame && breedIsSame && ageIsSame);
    }

    /**
     * Get a string representation of this dog.
     * @return The string representation of this object.
     */
    public String toString() {
        String me = String.format("name: %s; age: %d; breed: %s", this.getName(), this.getAge(), this.getBreed() );
        return me;
    }

}
