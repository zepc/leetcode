package zep.leetcode.java;

/**
 * Initially, there is a Robot at position (0, 0). Given a sequence of its moves, judge if this
 * robot makes a circle, which means it moves back to the original place. The move sequence is
 * represented by a string. And each move is represent by a character. The valid robot moves are R
 * (Right), L (Left), U (Up) and D (down). The output should be true or false representing whether
 * the robot makes a circle.
 * <p>
 * Example 1:
 * <p>
 * Input: "UD" Output: true
 * <p>
 * <p>
 * Example 2:
 * <p>
 * Input: "LL" Output: false
 */

public class q_1041_JudgeCircle {

	public static void main(String[] args) {
		boolean ud = judgeCircle("UDUUDDLLRRDDUU");
		System.out.println("ud = " + ud);
	}

	public static boolean judgeCircle(String moves) {

		int x = 0, y = 0;

		for (char c :
				moves.toCharArray()) {
			if (c == 'U') {
				x++;
			}
			else if (c == 'D') {
				x--;
			}
			else if (c == 'L') {
				y++;
			}
			else {
				y--;
			}
		}
//        for (int i = 0; i < moves.length(); i++) {
//
//            switch (moves.charAt(i)) {
//                case 'U' :
//                    x++;
//                    break;
//                case 'L':
//                    y++;
//                    break;
//                case 'R' :
//                    y--;
//                    break;
//                case 'D' :
//                    x--;
//            }
//        }

		return x == 0 && y == 0;
//        switch (moves) {
//           case "UD" :
//               return true;
//           case "LR" :
//               return true;
//           case "RL" :
//               return true;
//           case "DU" :
//               return true;
//        }
//        return false;
	}

}
