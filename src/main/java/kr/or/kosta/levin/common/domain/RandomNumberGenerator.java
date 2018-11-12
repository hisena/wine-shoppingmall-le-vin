package kr.or.kosta.levin.common.domain;

public class RandomNumberGenerator {

	private static String [] charSet = {"0","1","2","3","4","5","6","7","8","9", 
			"a", "b", "c", "d", "e", "f", "g", "H", "I", "J", "K", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W",
			"x", "y", "z"};
    
    public static String generateRandomNum() {
        
        String randomNum = "";

        for (int i = 0; i < 8; i++) {
            int tmp = (int)(Math.random()*charSet.length);
            randomNum += charSet[tmp];
        }
        
        return randomNum;
    }
}
