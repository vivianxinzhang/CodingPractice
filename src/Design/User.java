package Design;

public class User {
    private final String firstName; // required, and immutable
    private final String lastName;  // required
    private final int age;    // optional, but immutable
    private String phone;   // optional, mutable
    private String address; // optional

    private User(UserBuilder builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.age = builder.age;
        this.phone = builder.phone;
        this.address = builder.address;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public static class UserBuilder {
        private String firstName;   // these two are required
        private String lastName;
        private int age = 0;    // default value is 0
        private String phone = "";   // default value is an empty string
        private String address; // default value is null

        public UserBuilder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public UserBuilder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public UserBuilder age(int age) {
            this.age = age;
            return this;
        }

        public UserBuilder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public UserBuilder address(String address) {
            this.address = address;
            return this;
        }

        public User build() {
            if (firstName == null || lastName == null) {
                throw new IllegalArgumentException("required fields are not set!");
            }
            return new User(this);
        }
    }

    public static void main(String[] args) {
        User user = new UserBuilder()
                .lastName("Zhang")
                .firstName("San")
                .phone("1234567890")
                .age(25)
                .address("Fake address")
                .build();
        // ...
    }
}
