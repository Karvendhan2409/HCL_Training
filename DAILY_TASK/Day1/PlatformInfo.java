package Day1;
public class PlatformInfo {
    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();

        System.out.println("Java version: " + System.getProperty("java.version"));
        System.out.println("Operating system: " + System.getProperty("os.name"));
        System.out.println("Available processors: " + runtime.availableProcessors());
        System.out.println("Maximum heap (bytes): " + runtime.maxMemory());
        System.out.println("Free heap (bytes): " + runtime.freeMemory());
    }
}
