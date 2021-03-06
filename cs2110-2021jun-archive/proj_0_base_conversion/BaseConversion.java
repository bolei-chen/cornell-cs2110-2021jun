package proj_0_base_conversion;

public class BaseConversion {

	public static String toBinary(String decimal) {
		int decimalNum = Integer.parseInt(decimal);
		String reversedBinaryNum = "";
		String binaryNum = "";
		int remainder = 0;
		while (decimalNum != 0) {
			remainder = decimalNum % 2;
			if (remainder == 1) {
				reversedBinaryNum += "1";
				decimalNum /= 2;
			} else {
				reversedBinaryNum += "0";
				decimalNum /= 2;
			}
		}

		binaryNum = new StringBuilder(reversedBinaryNum).reverse().toString();
		return binaryNum;
	}

	public static String toDecimal(String binary) {
		int binaryNum = Integer.parseInt(binary);
		String decimalNum = "";
		int sum = 0;
		int remainder = 0;
		int degree = 0;
		int digitValue = 0;
		while (binaryNum != 0) {
			remainder = binaryNum % 10;
			digitValue = (int) (remainder * Math.pow(2, degree));
			sum += digitValue;
			degree += 1;
			binaryNum /= 10;

		}
		decimalNum = String.valueOf(sum);
		return decimalNum;
	}

	public static void main(String[] args) {
		boolean invalidInput = (args.length != 2);
		String result = "";
		if (!invalidInput) {

			String arg0 = args[0];
			String arg1 = args[1];
			if (arg0.equals("to2")) {
				result = toBinary(arg1);
				System.out.println(result);
			} else if (arg0.equals("to10")) {
				result = toDecimal(arg1);
				System.out.println(result);
			}
		}
	}

}
