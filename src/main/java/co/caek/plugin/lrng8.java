package co.caek.plugin;

public class lrng8 {
    // Not going to try to figure out the details of Java unsigned ints
    private static long seed = System.nanoTime();
    public static long buffer = 0;

    private static void lrng8_32() {
        buffer = seed >> 4;
        seed *= 69069;
        seed++;
    }

    // return a value from 0 to 15
    public static int rand() {
        // enforce the 32 bit by binary and - IGNORE YOUR IDE SUGGESTION
        if ((buffer & 0xffffffff) == 0) {
            lrng8_32();
            return (int) seed & 0x0f;
        } else {
            int result = (int) buffer & 0x0f;
            buffer = buffer >> 4;
            return result;
        }
    }
}
