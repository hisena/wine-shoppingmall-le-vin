package kr.or.kosta.levin.common.domain;

/**
 * 인증 코드를 랜덤으로 생성하기 위한 클래스
 * @author 박소연
 *
 */
public class RandomNumberGenerator {

	// 인증 코드에 들어갈 숫자와 문자 배열
	private static String [] charSet = {"0","1","2","3","4","5","6","7","8","9", 
			"a", "b", "c", "d", "e", "f", "g", "H", "I", "J", "K", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W",
			"x", "y", "z"};
    
	// 랜덤 인증코드 만드는 메소드
    public static String generateRandomNum() {
        
        String randomNum = "";

        for (int i = 0; i < 8; i++) {
            int tmp = (int)(Math.random()*charSet.length);
            randomNum += charSet[tmp];
        }
        
        return randomNum;
    }
}
