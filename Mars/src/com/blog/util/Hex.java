package com.blog.util;

/*
 * Copyright 2008-2011 Joel Hockey (joel.hockey@gmail.com).  All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */



import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * Hex encoder.
 * @author Joel Hockey
 */
public class Hex {
    /** Hex digits.  0123456789abcdef */
    public static final char[] HEX_DIGITS = "0123456789abcdef".toCharArray();

    /** Upper case Hex digits.  0123456789ABCDEF */
    public static final char[] HEX_DIGITS_UPPER = "0123456789ABCDEF".toCharArray();

    /** Hex string to byte lookup. */
    public static final int[] HEX_S2B = new int[256];

    /** Hex byte to string lookup. */
    public static final char[][] HEX_B2S = new char[256][];

    /** Upper case Hex byte to string lookup. */
    public static final char[][] HEX_B2S_UPPER = new char[256][];

    static {
        // init lookup tables
        // Hex S2B
        for (int i = 0; i < HEX_S2B.length; i++) {
            HEX_S2B[i] = -1;
        }
        for (int i = '0'; i <= '9'; i++) {
            HEX_S2B[i] = i - '0';
        }
        for (int i = 'A'; i <= 'F'; i++) {
            HEX_S2B[i] = i - 'A' + 10;
        }
        for (int i = 'a'; i <= 'f'; i++) {
            HEX_S2B[i] = i - 'a' + 10;
        }

        // Hex B2S
        for (int i = 0; i < HEX_DIGITS.length; i++) {
            for (int j = 0; j < HEX_DIGITS.length; j++) {
                HEX_B2S[i * 16 + j] = new char[] { HEX_DIGITS[i], HEX_DIGITS[j] };
                HEX_B2S_UPPER[(i * 16 + j)] = new char[] { HEX_DIGITS_UPPER[i], HEX_DIGITS_UPPER[j] };
            }
        }
    }

    /**
     * Return true if c in '0123456789abcdefABCDEF'
     * @param c c to check
     * @return true if c in '0123456789abcdefABCDEF'
     */
    public static boolean isHex(char c) { return nibble(c) != -1; }

    /**
     * Return true if c in '0123456789abcdefABCDEF' for all c in s
     * @param s string to check
     * @return true if c in '0123456789abcdefABCDEF' for all c in s
     */
    public static boolean isHex(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (nibble(s.charAt(i)) == -1) {
                return false;
            }
        }
        return true;
    }

    /**
     * Return nibble value or -1 if not hex char
     * @param c hex char
     * @return nibble value or -1 if not hex char
     */
    public static int nibble(char c) {
        return (c & 0xff00) == 0 ? HEX_S2B[c] : -1;
    }

    /**
     * Returns lower case hex string representation of byte[].
     * @param buf byte array
     * @return lower case hex encoded string
     */
    public static String b2s(byte[] buf) {
        if (buf == null) return null;
        return b2s(buf, 0, buf.length, false);
    }

    /**
     * Returns upper case hex string representation of byte[].
     * @param buf byte array
     * @return hex encoded string
     */
    public static String B2S(byte[] buf) {
        if (buf == null) return null;
        return b2s(buf, 0, buf.length, true);
    }
    
    
    public static String RightStr(String str,int len){
    	
    	int l = str.length();
    	
    	if (l < len){
    		String tempstr = "0000000000";
    		return (tempstr.substring(0,len)+str);
    	}else if (l == len){
    		return str;
    	}else{
    		
    		return str.substring(l-len);
    	}
    		
    }
    
    public static String F2S(String floats) {
    	
       int len = floats.length();	
       int pointpos = floats.indexOf(".");
       if (pointpos < 0){
    	   floats = floats+"00";
    	   int floats_int=Integer.parseInt(floats);
    	   return I2S(floats_int);
       }
       else if(len-pointpos ==2){
    	   
    	  floats = floats.substring(0, pointpos) + floats.substring(len-1)+"0";
    	  int floats_int=Integer.parseInt(floats);
   	      return I2S(floats_int);
       }else if(len-pointpos == 3){
    	   
    	   floats = floats.substring(0, pointpos) + floats.substring(len-2);
     	   int floats_int=Integer.parseInt(floats);
    	   return I2S(floats_int);
       }else{
    	   return "FF";
       }
        
    }
    
    public static String S2F(String HexStr) {
    	
    	 char[] HexChar = HexStr.toCharArray();
    	 int a0 =Hex.nibble(HexChar[0]);
    	 int a1 =Hex.nibble(HexChar[1]);
    	 int a2 =Hex.nibble(HexChar[2]);
    	 int a3 =Hex.nibble(HexChar[3]);
    	 int a4 =Hex.nibble(HexChar[4]);
    	 int a5 =Hex.nibble(HexChar[5]);
    	 int a6 =Hex.nibble(HexChar[6]);
    	 int a7 =Hex.nibble(HexChar[7]);
    	 double sum = a0*Math.pow(16, 7)+a1*Math.pow(16, 6)+a2*Math.pow(16, 5)+a3*Math.pow(16, 4)+a4*Math.pow(16, 3)+a5*Math.pow(16,2)+a6*Math.pow(16, 1)+a7*Math.pow(16, 0);
    	 
         String IntStr = Integer.toString((int)sum);
         int len = IntStr.length();
         if (len == 1){
        	 IntStr = "0.0"+IntStr;
         }else if(len == 2){
        	 IntStr = "0."+IntStr;
         }else{
         IntStr = IntStr.substring(0,len-2)+"."+IntStr.substring(len-2);
         }
         return IntStr;
}

    /**
     * Returns lower case hex string representation of byte[].
     * @param buf byte array
     * @param start pos in buf to start at
     * @param len number of bytes to encode
     * @return hex encoded string
     */
    public static String b2s(byte[] buf, int start, int len) {
        return b2s(buf, start, len, false);
    }

    /**
     * Returns upper case hex string representation of byte[].
     * @param buf byte array
     * @param start pos in buf to start at
     * @param len number of bytes to encode
     * @return upper case hex encoded string
     */
    public static String B2S(byte[] buf, int start, int len) {
        return b2s(buf, start, len, true);
    }

    /**
     * Returns hex string representation of byte[].
     * @param buf byte array
     * @param start pos in buf to start at
     * @param len number of bytes to encode
     * @param upper if true then use upper
     * @return hex encoded string
     */
    public static String b2s(byte[] buf, int start, int len, boolean upper) {
        if (buf == null) return null;
        if (start < 0 || start > buf.length) {
            try {
				throw new Exception("start index must be between 0 and buf.length [" + buf.length + "].  Got value" + start);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }

        if (len < 0 || start + len > buf.length) {
            try {
				throw new Exception("len must be between 0 and (buf.length - start) ["
				        + buf.length + " - " + start + " = " + (buf.length - start)+ "].  Got value " + len);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }

        char[][] lkp = (upper) ? HEX_B2S_UPPER : HEX_B2S;

        char[] cbuf = new char[len * 2];
        for (int i = 0; i < len; i++) {
            System.arraycopy(lkp[buf[start + i] & 0xff], 0, cbuf, i * 2, 2);
        }
        return new String(cbuf);
    }

    /**
     * Return 8 char (lower case) hex encoded 32-bit big-endian value
     * @param num number to encode
     * @return 8 char (lower case) hex encoded 32-bit big-endian value
     */
    public static String i2s(int num) {
        char[] cbuf = new char[8];
        // start at rhs
        for (int i = 3; i >= 0; i--) {
            System.arraycopy(Hex.HEX_B2S[num & 0xff] , 0, cbuf, i*2, 2);
            num >>>= 8;
        }
        return new String(cbuf);
    }

    /**
     * Return 8 char (upper case) hex encoded 32-bit big-endian value
     * @param num number to encode
     * @return 8 char (upper case) hex encoded 32-bit big-endian value
     */
    public static String I2S(int num) {
        char[] cbuf = new char[8];
        // start at rhs
        for (int i = 3; i >= 0; i--) {
            System.arraycopy(Hex.HEX_B2S_UPPER[num & 0xff] , 0, cbuf, i*2, 2);
            num >>>= 8;
        }
        return new String(cbuf);
    }

    /**
     * Return 16 char (lower case) hex encoded 64-bit big-endian value
     * @param num number to encode
     * @return 16 char (lower case) hex encoded 64-bit big-endian value
     */
    public static String l2s(long num) {
        char[] cbuf = new char[16];
        // start at rhs
        for (int i = 7; i >= 0; i--) {
            System.arraycopy(Hex.HEX_B2S[(int) (num & 0xff)] , 0, cbuf, i*2, 2);
            num >>>= 8;
        }
        return new String(cbuf);
    }

    /**
     * Return 16 char (lower case) hex encoded 64-bit big-endian value
     * @param num number to encode
     * @return 16 char (lower case) hex encoded 64-bit big-endian value
     */
    public static String L2S(long num) {
        char[] cbuf = new char[16];
        // start at rhs
        for (int i = 7; i >= 0; i--) {
            System.arraycopy(Hex.HEX_B2S_UPPER[(int) (num & 0xff)] , 0, cbuf, i*2, 2);
            num >>>= 8;
        }
        return new String(cbuf);
    }

    /**
     * Returns byte[] from hex string.
     * Ignores any non-hex chars.
     * Pads extra 0 on end if odd number of hex chars.
     * @param hex hex string e.g. "01ff"
     * @return byte array. e.g. byte[] {1, 255}
     */
    public static byte[] s2b(String hex) {
        if (hex == null) return null;

        byte[] buf = new byte[(hex.length() + 1) / 2];
        int tmpbuf = 0;       // stores nibble
        int bits = 0;         // num of bits in tmpbuf
        int i = 0;            // index into hex
        int j = 0;            // index into result buf
        while (i < hex.length()) {
            int c = Hex.HEX_S2B[hex.charAt(i++) & 0xff];
            // skip non-hex chars
            if (c < 0) {
                continue;
            }
            tmpbuf = tmpbuf | c;
            bits += 4;
            if (bits == 8) {
                buf[j++] = (byte) tmpbuf;
                bits = 0;
            }
            tmpbuf <<= 4;
        }
        // add extra char if exists
        if (bits > 0) {
            buf[j++] = (byte) tmpbuf;
        }

        // return correctly sized byte[]
        if (j == buf.length) {
            return buf;
        } else {
            byte[] smallbuf = new byte[j];
            System.arraycopy(buf, 0, smallbuf, 0, j);
            return smallbuf;
        }
    }

    /**
     * Returns InputStream from hex string.
     * @see #s2b(String)
     * @param hex hex input strem
     * @return ByteArrayInputStream
     */
    public static InputStream s2is(String hex) {
        return new ByteArrayInputStream(s2b(hex));
    }

    /**
     * Hex dump.
     * @param buf byte array to dump
     * @return formatted dump
     */
    public static String dump(byte[] buf)     {
        StringBuilder sb = new StringBuilder();
        dump(sb, buf, 0, buf.length, "", 16, true);
        return sb.toString();
    }

    /**
     * Hex dump.
     * @param sb stringbuilder for result
     * @param buf buf to dump
     * @param start start index
     * @param len length
     * @param indent string for indent
     * @param lineLen number of bytes per line (16 or 32 are good choices)
     * @param lineNum if true, line numbers are shown in left col
     */
    public static void dump(StringBuilder sb, byte[] buf, int start, int len,
            String indent, int lineLen, boolean lineNum) {
        if (buf == null) {
            if (lineNum) {
                sb.append(i2s(0)).append(" - ");
            }
            sb.append(indent).append("null");
            return;
        }

        char[] ascii = new char[lineLen];
        int lineOffset = 0; // resets to zero for every line

        int i = start; // index into buf
        int end = start + len;

        while (i < end) {
            // put '\n' and indent to start each line
            if (lineOffset == 0) {
                if (i > start) {
                    sb.append('\n');
                }
                if (lineNum) {
                    sb.append(i2s(i - start)).append(" - ");
                }
                sb.append(indent);

            // put a '-' every 8 chars
            } else if ((lineOffset & 0x7) == 0) {
                sb.append("- ");
            }

            // put ascii into ascii buf
            ascii[lineOffset++] = (buf[i] >= 32 && buf[i] <= 126) ? (char) buf[i] : '.';
            // put hex into sb
            sb.append(HEX_B2S[(buf[(i++)] & 0xff)]).append(' ');

            // put ascii at end of each line
            if (lineOffset == ascii.length) {
                sb.append("  ").append(ascii);
                lineOffset = 0;
            }
        }

        if (lineOffset == 0) {
            return;
        }

        // put fill to line up ascii print
        int missingHex = ascii.length - lineOffset;
        // 3 for each hex, 2 for each '- ', 2 at end
        int fillLen = missingHex * 3 + missingHex / 8 * 2 + 2;

        while (fillLen-- > 0) {
            sb.append(' ');
        }
        sb.append(ascii, 0, lineOffset);
    }

}
