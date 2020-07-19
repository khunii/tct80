import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Executor {
	
	List<Complex> complexList = new ArrayList<>();
	
	public void summarizeComplex(int[][] town){
		int maxComplexNo = 1;
		Complex maxComplex = complexList.stream().max(Comparator.comparingInt(Complex::getComplexNo)).orElse(null);
		if (maxComplex != null) {
			maxComplexNo = maxComplex.getComplexNo();
		}

		for(int i=0; i<town.length; i++) {
			for(int j=0; j<town[i].length; j++) {
				if (town[i][j] ==1 && !isVisited(i, j)) {
					visitLand(maxComplexNo++, i, j, town);
				}
			}
		}
	}
	
	public boolean isVisited(int x, int y) {
		for(int i=0; i<complexList.size(); i++) {
			int compX = complexList.get(i).getX();
			int compY = complexList.get(i).getY();
			if (x == compX && y == compY) {
				return true;
			}
		}
		return false;
	}
	
	public void visitLand(int idx, int x, int y, int[][] town) {
		if (town[x][y] == 1) {
			int upper = 0;
			int bottom = 0;
			int left = 0;
			int right = 0;
			
			if ( x-1 >= 0 ) {
				upper = town[x-1][y];
			}
			if ( x+1 <= town.length-1) {
				bottom = town[x+1][y];
			}
			if ( y-1 >= 0 ) {
				left = town[x][y-1];
			}
			if ( y+1 <= town[0].length-1) {
				right = town[x][y+1];
			}

			
			if (!isVisited(x,y)) {
				complexList.add(new Complex(idx, x, y));
				if (upper == 1) {
					visitLand(idx, x-1, y, town);
				}
				if (bottom == 1) {
					visitLand(idx, x+1, y, town);
				}
				if (left == 1) {
					visitLand(idx, x, y-1, town);
				}
				if (right == 1) {
					visitLand(idx, x, y+1, town);
				}
			}else {
				return;
			}
		}
		return;
	}

	public static void main(String[] args) {
		
		int[][] town = {
				{0,1,1,0,1,0,0},
				{0,1,1,0,1,0,1},
				{1,1,1,0,1,0,1},
				{0,0,0,0,1,1,1},
				{0,1,0,0,0,0,0},
				{0,1,1,1,1,1,0},
				{0,1,1,1,0,0,0}
		};
		
		Executor e = new Executor();		
		e.summarizeComplex(town);
		System.out.println("단지수 :" + e.complexList.size());
		System.out.println("단지번호     집수");
		System.out.println("----------------");
		for(int i = 0; i< e.complexList.size(); i++) {
			Complex complex = e.complexList.get(i);
			System.out.println(complex);
		}
	}

	class Complex {
		int complexNo;
		int x;
		int y;
		
		public Complex(int complexNo, int x, int y) {
			this.complexNo = complexNo;
			this.x = x;
			this.y = y;
		}
		public int getComplexNo() {
			return complexNo;
		}
		public void setComplexNo(int complexNo) {
			this.complexNo = complexNo;
		}
		public int getX() {
			return x;
		}
		public void setX(int x) {
			this.x = x;
		}
		public int getY() {
			return y;
		}
		public void setY(int y) {
			this.y = y;
		}
		
		public String toString() {
			return "단지번호: "+this.complexNo+" x좌표: "+this.x+" y좌표: "+this.y;
		}
		
		
	}
}
