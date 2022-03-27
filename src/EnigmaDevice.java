import java.util.HashMap;
import java.util.Map;

public class EnigmaDevice {

    private Rotor r1, r2, r3, reflector;
    private Map<Integer, Integer> plugboard = new HashMap<>();

    public EnigmaDevice() {
        r1 = new Rotor();
        r2 = new Rotor();
        r3 = new Rotor();
        reflector = new Rotor();
        this.reset();
        //reset plugboard?
    }

    public void reset() {
        r1.setEncoding("EKMFLGDQVZNTOWYHXUSPAIBRCJ");
        r2.setEncoding("AJDKSIRUXBLHWTMCQGZNPYFVOE");
        r3.setEncoding("BDFHJLCPRTXVZNYEIWGAKMUSQO");
        reflector.setEncoding("EJMZALYXVBWFCRQUONTSPIKHGD");
    }

    public char encrypt(char in)  {

        System.out.println(in);
        char out;
        in = (char)((int)'A' + (plugboard.get(((int)in - (int)'A'))));
        out = r1.forwardsFeed(in);
        System.out.println(out);
        out = r2.forwardsFeed(out);
        System.out.println(out);
        out = r3.forwardsFeed(out);
        System.out.println(out);
        out = reflector.forwardsFeed(out);
        System.out.println(out);
        out = r3.reflectedFeed(out);
        System.out.println(out);
        out = r2.reflectedFeed(out);
        System.out.println(out);
        out = r1.reflectedFeed(out);
        System.out.println(out);

        r1.rotate();
        if(r1.getState() == 0) {
            r2.rotate();
            if(r2.getState() == 0) {
                r3.rotate();
            }
        }

        //return out;
        //System.out.print("out = " + out);
        System.out.println("eyo" + plugboard.get((int)(out - 'A')));
        System.out.println((char) ('A' + plugboard.get((int)(out - 'A'))));
        return (char) ('A' + plugboard.get((int)(out - 'A')));

    }

    public int getRotorState1() {
        return r1.getState();
    }

    public int getRotorState2() {
        return r2.getState();
    }

    public int getRotorState3() {
        return r3.getState();
    }

    public void setRotorState1(int state) {
        r1.setState(state);
    }

    public void setRotorState2(int state) {
        r2.setState(state);
    }

    public void setRotorState3(int state) {
        r3.setState(state);
    }

    public void plugboardConfig(int input, int output) {
        input = input - (int)('A');
        output = output - (int)('A');
        plugboard.replace(input, output);
        plugboard.replace(output, input);
        System.out.print(input);
        System.out.println(output);
    }

    public void plugboardInit() {
        for(int i = 0; i<26; i++) {
            plugboard.put(i, i);
        }
    }

    public void plugboardReset() {
        plugboard = new HashMap<>();
        plugboardInit();
    }

}
