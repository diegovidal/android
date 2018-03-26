package basics;

import java.io.IOException;

/**
 * Created by diegovidal on 26/02/2018.
 */
class Main {

    public static void main(String[] args) {

        canThrowAnException();
    }

    private static void canThrowAnException() {

        throw new IllegalStateException();
    }
}

