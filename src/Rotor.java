import java.util.*;

/* A rotor can encrypt a character forwards and backwards. For each character that is forwards encrypted
 * using the rotor, the rotor itself rotates forwards. Note that a reflected encryption will not rotate the
 * rotor as it is part of a single character encryption operation. After a rotation, the one-to-one encryption
 * array will be offset by 1. This is a method to increase the difficulty in decrypting a message, as two
 * consecutive identical plaintext characters will result in two different ciphertext characters. This also
 * means that to correctly decrypt a string, the orientation of the rotors that was used to encrypt the string
 * must be known first.*/

public class Rotor {

    private char[] encoding;
    private int state = 0;

    public Rotor() {
        System.out.println("Rotor initialized");
        encoding = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray(); //SWAYKZHRECLDVMQFXGTIJNBPUO
        state = 0;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding.toCharArray();
        System.out.println("encoding set!" + encoding);
        for(char c : encoding.toCharArray()) {
            System.out.print(c);
        }
        System.out.println();
    }

    public void offset(String offset) {
        encoding = offset.toCharArray();
    }

    public char forwardsFeed(char input) { //cipher of forwards feed
        return encoding[((int)input - (int)'A')];
    }

    public char reflectedFeed(char input) { //cipher of reflected feed
        for (int i = 0; i <= 25; i++) {
            if (encoding[i] == input) {
                return (char) (i + (int) 'A');
            }
        }

        return '?';
    }

    public void rotate() { //shift encoding array
        System.out.println("rotate!");
        for(char c : encoding) {
            System.out.print(c);
        }
        System.out.println();

        state++;
        state %= 26;
        char temp = 'a';
        char temp2 = encoding[0];
        char temp3 = encoding[25];
        for (int i = 0; i <= 25; i++) {
            temp = encoding[i];
            encoding[i] = temp2;
            temp2 = temp;
        }
        encoding[0] = temp3;

        for(char c : encoding) {
            System.out.print(c);
        }
        System.out.println();
    }

    public int getState() {
        return state;
    }

    public void setState(int n) {
        state = 0;
        for(int i = 0; i<n; i++) {
            rotate();
        }
    }
}
