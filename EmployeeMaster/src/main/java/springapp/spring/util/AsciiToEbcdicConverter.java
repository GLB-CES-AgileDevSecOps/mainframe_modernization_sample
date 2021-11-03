package springapp.spring.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

public class AsciiToEbcdicConverter {

	private static int[] asciiToEbcdic = new int[128];

	static {

		asciiToEbcdic[0] = 0;

		asciiToEbcdic[1] = 1;

		asciiToEbcdic[2] = 2;

		asciiToEbcdic[3] = 3;

		asciiToEbcdic[4] = 55;

		asciiToEbcdic[5] = 45;

		asciiToEbcdic[6] = 46;

		asciiToEbcdic[7] = 47;

		asciiToEbcdic[8] = 22;

		asciiToEbcdic[9] = 5;

		asciiToEbcdic[10] = 37;

		asciiToEbcdic[11] = 11;

		asciiToEbcdic[12] = 12;

		asciiToEbcdic[13] = 13;

		asciiToEbcdic[14] = 14;

		asciiToEbcdic[15] = 15;

		asciiToEbcdic[16] = 16;

		asciiToEbcdic[17] = 17;

		asciiToEbcdic[18] = 18;

		asciiToEbcdic[19] = 19;

		asciiToEbcdic[20] = 60;

		asciiToEbcdic[21] = 61;

		asciiToEbcdic[22] = 50;

		asciiToEbcdic[23] = 38;

		asciiToEbcdic[24] = 24;

		asciiToEbcdic[25] = 25;

		asciiToEbcdic[26] = 63;

		asciiToEbcdic[27] = 39;

		asciiToEbcdic[28] = 34;

		// asciiToEbcdic[29] = ;

		// asciiToEbcdic[30] = ;

		// asciiToEbcdic[31] = ;

		asciiToEbcdic[32] = 64;

		asciiToEbcdic[33] = 90;

		asciiToEbcdic[34] = 127;

		asciiToEbcdic[35] = 123;

		asciiToEbcdic[36] = 91;

		asciiToEbcdic[37] = 108;

		asciiToEbcdic[38] = 80;

		asciiToEbcdic[39] = 125;

		asciiToEbcdic[40] = 77;

		asciiToEbcdic[41] = 93;

		asciiToEbcdic[42] = 92;

		asciiToEbcdic[43] = 78;

		asciiToEbcdic[44] = 107;

		asciiToEbcdic[45] = 96;

		asciiToEbcdic[46] = 75;

		asciiToEbcdic[47] = 97;

		// Numbers start here 0 - 9

		asciiToEbcdic[48] = 240; // 0

		asciiToEbcdic[49] = 241;

		asciiToEbcdic[50] = 242;

		asciiToEbcdic[51] = 243;

		asciiToEbcdic[52] = 244;

		asciiToEbcdic[53] = 245;

		asciiToEbcdic[54] = 246;

		asciiToEbcdic[55] = 247;

		asciiToEbcdic[56] = 248;

		asciiToEbcdic[57] = 249; // 9

		// Numbers end here 0 - 9

		asciiToEbcdic[58] = 122;

		asciiToEbcdic[59] = 94;

		asciiToEbcdic[60] = 76;

		asciiToEbcdic[61] = 126;

		asciiToEbcdic[62] = 110;

		asciiToEbcdic[63] = 111;

		asciiToEbcdic[64] = 124;

		// Capital letters start here A - Z

		asciiToEbcdic[65] = 193; // A

		asciiToEbcdic[66] = 194;

		asciiToEbcdic[67] = 195;

		asciiToEbcdic[68] = 196;

		asciiToEbcdic[69] = 197;

		asciiToEbcdic[70] = 198;

		asciiToEbcdic[71] = 199;

		asciiToEbcdic[72] = 200;

		asciiToEbcdic[73] = 201;

		asciiToEbcdic[74] = 209;

		asciiToEbcdic[75] = 210;

		asciiToEbcdic[76] = 211;

		asciiToEbcdic[77] = 212;

		asciiToEbcdic[78] = 213;

		asciiToEbcdic[79] = 214;

		asciiToEbcdic[80] = 215;

		asciiToEbcdic[81] = 216;

		asciiToEbcdic[82] = 217;

		asciiToEbcdic[83] = 226;

		asciiToEbcdic[84] = 227;

		asciiToEbcdic[85] = 228;

		asciiToEbcdic[86] = 229;

		asciiToEbcdic[87] = 230;

		asciiToEbcdic[88] = 231;

		asciiToEbcdic[89] = 232;

		asciiToEbcdic[90] = 233; // Z

		// Capital letters end here A - Z

		// asciiToEbcdic[91] = ;

		asciiToEbcdic[92] = 224;

		// asciiToEbcdic[93] = ;

		// asciiToEbcdic[94] = ;

		asciiToEbcdic[95] = 109;

		asciiToEbcdic[96] = 121;

		// Small letters start here a - z

		asciiToEbcdic[97] = 129; // a

		asciiToEbcdic[98] = 130;

		asciiToEbcdic[99] = 131;

		asciiToEbcdic[100] = 132;

		asciiToEbcdic[101] = 133;

		asciiToEbcdic[102] = 134;

		asciiToEbcdic[103] = 135;

		asciiToEbcdic[104] = 136;

		asciiToEbcdic[105] = 137;

		asciiToEbcdic[106] = 145;

		asciiToEbcdic[107] = 146;

		asciiToEbcdic[108] = 147;

		asciiToEbcdic[109] = 148;

		asciiToEbcdic[110] = 149;

		asciiToEbcdic[111] = 150;

		asciiToEbcdic[112] = 151;

		asciiToEbcdic[113] = 152;

		asciiToEbcdic[114] = 153;

		asciiToEbcdic[115] = 162;

		asciiToEbcdic[116] = 163;

		asciiToEbcdic[117] = 164;

		asciiToEbcdic[118] = 165;

		asciiToEbcdic[119] = 166;

		asciiToEbcdic[120] = 167;

		asciiToEbcdic[121] = 168;

		asciiToEbcdic[122] = 169; // z

		// Small letters end here a - z

		asciiToEbcdic[123] = 192;

		asciiToEbcdic[124] = 79;

		asciiToEbcdic[125] = 208;

		asciiToEbcdic[126] = 161;

		asciiToEbcdic[127] = 7;

		// asciiToEbcdic[128] = ;

	}

	/*
	 * public Matcher getMatcher(String line, String pattern1) {
	 * 
	 * Pattern p1 = Pattern.compile("[0-9]+"); Pattern p2 =
	 * Pattern.compile("[a-zA-Z]+"); Matcher m1 = p1.matcher(line); return m1;
	 * 
	 * }
	 */

	public static int compareStringInEbcdic(String str1, String str2) {
		boolean alphanumeric = false;

		Pattern p1 = Pattern.compile("[a-zA-Z]+");
		Pattern p2 = Pattern.compile("\\d+");
		Pattern p3 = Pattern.compile("[a-zA-Z\\d]+");

		Matcher m1 = p1.matcher(str1);
		Matcher m2 = p2.matcher(str1);
		Matcher m3 = p3.matcher(str1);

		if (m1.matches() || m2.matches()) {
			m1 = p1.matcher(str2);
			m2 = p2.matcher(str2);
			m3 = p3.matcher(str2);

			if (m1.matches() || m2.matches()) {

			} else {
				if (m3.matches()) {
					alphanumeric = true;
				}
			}
		} else {
			if (m3.matches()) {
				alphanumeric = true;
			} else {
				m1 = p1.matcher(str2);
				m2 = p2.matcher(str2);
				m3 = p3.matcher(str2);

				if (m1.matches() || m2.matches()) {

				} else {
					if (m3.matches()) {
						alphanumeric = true;
					}
				}

			}
		}
		if (alphanumeric) {

			int[] str1EbcdicCodes = new int[str1.length()];

			int[] str2EbcdicCodes = new int[str2.length()];

			int resultNum = 0;

			// Changes for Numeric comparison. This will come into
			// picture only if both strings are numeric. we will do alphanumeric
			// comparison

			if (StringUtils.isNumeric(StringUtils.stripStart(str1.trim(), "0"))
					&& StringUtils.isNumeric(StringUtils.stripStart(str2.trim(), "0")))

			{

				int num1 = Integer.parseInt(str1.trim());

				int num2 = Integer.parseInt(str2.trim());

				if (num1 > num2)

				{

					return 1;

				}

				else if (num1 < num2)

				{

					return -1;

				}

				else

				{

					return 0;

				}

			}

			int i = 0;

			for (char c : str1.toCharArray()) {

				str1EbcdicCodes[i++] = asciiToEbcdic[(int) c];

			}

			i = 0;

			for (char c : str2.toCharArray()) {

				str2EbcdicCodes[i++] = asciiToEbcdic[(int) c];

			}

			for (i = 0; i < str1.length(); i++) {

				if (i < str2.length()) {

					if (!(str1EbcdicCodes[i] == str2EbcdicCodes[i])) {

						resultNum = str1EbcdicCodes[i] - str2EbcdicCodes[i];

						break;

					}

				} else {

					resultNum++;

				}

			}

			if (resultNum == 0 && i < str2.length()) {

				for (int j = i; j < str2.length(); j++) {

					resultNum--;

				}

			}

			return resultNum;
		} else {
			return str1.compareTo(str2);
		}
	}

	public static void main(String args[]) {
		/*String s1 = "123A";
		String s2 = "1234";
		AsciiToEbcdicConverter.compareStringInEbcdic(s1, s2);*/
		
		AsciiToEbcdicConverter ebcdicConverter = new AsciiToEbcdicConverter();
		String Str1 ="123A"; //049050051065 //241242243193
		String Str2 ="1234"; //049050051052 //241242243244 
		//int result =Str1.compareTo(Str2);
		int result = ebcdicConverter.compareStringInEbcdic(Str1, Str2); //if str1>str2
		//System.out.println("Result :: "+result);

	}
}