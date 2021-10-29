class Singleton {

    private static Singleton instance = new Singleton();

    private Singleton() {}

    static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
    void foo() {
        System.out.println("foo");
    }
}