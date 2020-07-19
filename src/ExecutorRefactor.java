import java.util.Arrays;

public class ExecutorRefactor {
	private static final int MAX = 7;
	
	boolean visited[][] = new boolean[MAX][MAX];
	int visitedComplex[][] = new int[MAX][MAX];
	int complex[] = new int[MAX*2];
	int complexIdx = 0; //1단지 부터 시작을 위해
	
	
	public void summarizeComplex(int[][] town){
		for(int i=0; i<town.length; i++) {
			for(int j=0; j<town[i].length; j++) {
				if (town[i][j] ==1 && !visited[i][j]) {
					complexIdx++;
					visitLand(i, j, town);
				}
			}
		}
	}
	
	public void visitLand(int x, int y, int[][] town) {
		visited[x][y] = true;
		visitedComplex[x][y] = complexIdx;
		complex[complexIdx]++;
		
		int offset[][] = new int[4][2];
		offset[0][0] = x-1; offset[0][1] = y; //상
		offset[1][0] = x+1; offset[1][1] = y; //하
		offset[2][0] = x; offset[2][1] = y-1; //좌
		offset[3][0] = x; offset[3][1] = y+1; //우
		
		for(int i = 0; i<4; i++) {
			if(offset[i][0] >= 0 && offset[i][0] < town.length && offset[i][1] >=0 && offset[i][1] < town[0].length) {
				if (town[offset[i][0]][offset[i][1]] == 1 && !visited[offset[i][0]][offset[i][1]]) {
					visitLand(offset[i][0],offset[i][1], town);
				}
			}
		}
	}

	public static void main(String[] args) {
		
		int[][] town = {
//				{0,1,1,0,1,0,0},
//				{0,1,1,0,1,0,1},
//				{1,1,1,0,1,0,1},
//				{0,0,0,0,1,1,1},
//				{0,1,0,0,0,0,0},
//				{0,1,1,1,1,1,0},
//				{0,1,1,1,0,0,0}
				{1,1,1,1,1,1,1},
				{1,0,0,0,0,0,1},
				{1,0,1,1,0,0,1},
				{1,0,1,0,1,0,1},
				{1,0,0,0,1,0,1},
				{1,0,0,0,0,0,1},
				{1,1,1,1,1,1,1}
		};
		
		ExecutorRefactor e = new ExecutorRefactor();		
		e.summarizeComplex(town);
		System.out.println("단지 수");
		System.out.println(Arrays.toString(e.complex));
		System.out.println("단지 분류");
		for(int i = 0; i<town.length;i++) {
			for(int j=0; j<town[i].length;j++) {
				System.out.print(e.visitedComplex[i][j]+"  ");
			}
			System.out.println();
		}
		
	}
}
