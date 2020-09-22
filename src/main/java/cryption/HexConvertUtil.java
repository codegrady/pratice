package cryption;

import java.io.UnsupportedEncodingException;
import java.util.Formatter;

public class HexConvertUtil
{
    private HexConvertUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static String bin2hex(String binString) throws UnsupportedEncodingException {
        byte[] bytes;
        Formatter f = new Formatter();
        try {
            bytes = binString.getBytes("UTF-8");
            for (byte c : bytes)
                f.format("%02X",c);

            return (f.toString().toLowerCase());
        } finally {
            f.close();
        }
    }

    /**
     * 将byte转为16进制
     *
     * @author CaoLu.
     */
    private static String byte2Hex(byte[] bytes) {
        StringBuffer stringBuffer = new StringBuffer();
        String temp = null;
        for (int i = 0; i < bytes.length; i++) {
            temp = Integer.toHexString(bytes[i] & 0xFF);
            if (temp.length() == 1) {
                // 1得到一位的进行补0操作
                stringBuffer.append("0");
            }
            stringBuffer.append(temp);
        }
        return stringBuffer.toString();
    }

    public static String hex2bin(String hexString)
    {
        if (!hexString.matches("^[0-9a-fA-F]+$")) {
            return null;
        }
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < hexString.length(); i+=2) {
            String str = hexString.substring(i, i+2);
            output.append((char)Integer.parseInt(str, 16));
        }
        return output.toString();
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        String password = "PegVX2Jf+kf5SlvNgiE+Wj2WiZQjILkUS67PexNVx/KO4A3FEKesi7HhjtVP5+xT2CI1SG4FL/7B\n" +
                "gw4Q+x4i0zUizbu24iU+sEDvbPkTfMQsvvWgVR7+3SLYTiIwmx68pO9bj1673wA3qy50F4JIStXr\n" +
                "LutjRgaPUJK4x+ehtfii3VJ/Sr6htU6pIOUQX+kHm4nbX6rYSt81EhwVuIWzK+niZrTV2dzF+CXy\n" +
                "d1hkNxSCuSP3vbmFhZiobWlanp2klh0aVrfKc5z4BJs/OPsQGJc1O+mN5xGFo3Qhih5I4YaYsnZA\n" +
                "nzPWXaW2lNg73oH2AQ6VpH5I6Nwzzjc/F5jA5g==";
        String s1 = byte2Hex(password.getBytes("UTF-8"));
        String s2 = bin2hex(password);
        System.out.println(s1.equals(s2));
    }
}