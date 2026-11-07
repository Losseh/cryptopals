import util.BinaryConverter;
import util.HexToBase64;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        BinaryConverter binaryConverter = new BinaryConverter();
        HexToBase64 hexToBase64 = new HexToBase64();
//        String base64value = hexToBase64.convert("49276d206b696c6c696e6720796f757220627261696e206c696b65206120706f69736f6e6f7573206d757368726f6f6d");
//        System.out.println(base64value);


        hexToBase64.convert("0f0f0f", HexToBase64.Strategy.MINE);
    }
}