package CRCfunction;
import java.util.Arrays;

public class CRCFunction {

	static int[] mkbinary(int ascii){
		int binary, sum[] = new int[7+4], i = 0;
		while(ascii>0){
			binary=ascii%2;
			sum[6-i]=binary;
			ascii=ascii/2;
			i++;
		}
		System.out.println(""+sum[0]+sum[1]+sum[2]+sum[3]+sum[4]+sum[5]+sum[6]); //지울것 
		return sum;
	}
	
	static int[] genaratorEX(int ascii[]){ 
		int register[][] = new int[2][5];	
		int tmp[] = new int[3];
		
		for(int i=0; i<15; i++){
			tmp[0] = ascii[i]^register[1][4];
			register[0][0] = tmp[0];
			register[0][1] = register[1][0];
			tmp[1] = register[1][1]^register[1][4];
			register[0][2] = tmp[1];
			register[0][3] = register[1][2];
			tmp[2] = register[1][3]^register[1][4];
			register[0][4] = tmp[2];
			System.out.println("Clock발생");
			for(int j=0;j<5;j++){
				register[1][j] = register[0][j];
			}
			
			System.out.println(": "+register[1][4]+register[1][3]+register[1][2]+register[1][1]+register[1][0]);
		}
		return null;
	}
	
	static int[] genarator(int ascii[]){ //1011 -> x3 + x + 1
		int register[][] = new int[2][5];	// [ [실값][임시값] ]*7
		int tmp[] = new int[3];
		
		for(int i=0; i<7+4; i++){
			tmp[0] = ascii[i]^register[1][3];
			register[0][0] = tmp[0];
			tmp[1] = register[1][0]^register[1][3];
			register[0][1] = tmp[1];
			register[0][2] = register[1][1];
			register[0][3] = register[1][2];
			System.out.println("Clock발생");
			for(int j=0;j<4;j++){
				register[1][j] = register[0][j];
			}
			
			System.out.println(": "+register[1][3]+register[1][2]+register[1][1]+register[1][0]);
		}

		ascii[7]=register[1][3];
		ascii[8]=register[1][2];
		ascii[9]=register[1][1];
		ascii[10]=register[1][0];
		
		return ascii;
	}
	
	public static void main(String[] args) {
		int ascii[] = {1,0,1,0,0,0,1,1,0,1,0,0,0,0,0};
		genaratorEX(ascii);
		
		int ascii2[] = {1,1,0,0,0,0,1,0,0,0,0};
		System.out.println("------------------------------");
		genarator(ascii2);
		System.out.println("------------------------------");
		
		
		System.out.println(Arrays.toString(genarator(mkbinary('a'))));
	}

}
