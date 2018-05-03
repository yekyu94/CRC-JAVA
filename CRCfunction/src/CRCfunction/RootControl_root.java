package CRCfunction;

import java.net.URL;
import java.util.Arrays;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;

public class RootControl_root implements Initializable{
	@FXML private CheckBox check1, check2, check3, check4, check5, check6, check7;
	@FXML private TextArea Text1, Text2;
	@FXML private TextField DataText;
	@FXML private ComboBox ErrorText;
	@FXML private Button btnCheck;
	@FXML private ImageView map, line6, line5, line4, line3, line2, line1, xor5, xor4, xor3, xor2, xor1, xor0;
	
	static int[] xorLoc = new int[7];
	char[] dataBox;
	int dataIdx=0;
	int sArray[][]=new int[100][0]; //Ascii + CRC 배열 
	int kArray[][]=new int[100][0]; //Ascii + CRC 배열 
	int tArray[][]=new int[100][0];
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		map.setImage(new Image(getClass().getResource("SR.png").toString()));
		ObservableList<String> list = FXCollections.observableArrayList("0","1","2","3","4","5","6","7","8","9","10",
				"11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30",
				"31","32","33","34","35","36","37","38","39","40","41","42","43","44","45","46","47","48","49","50","51",
				"52","53","54","55","56","57","58","59","60","61","62","63","64","65","66","67","68","69","70","71","72","73",
				"74","75","76","77","78","79","80","81","82","83","84","85","86","87","88","89","90","91","92","93","94","95","96"
				,"97", "98", "99","100");
		ErrorText.setItems(list);
	}

	
	public void checkXor(ActionEvent e){
		if(check7.isSelected()){
			xorLoc[0] = 1;		line6.setOpacity(1);
		}else{ xorLoc[0] = 0;	line6.setOpacity(0);}
		
		if(check6.isSelected()){
			xorLoc[1] = 1;
			if(xorLoc[0]==1){
			xor5.setOpacity(1); line5.setOpacity(0);
			}else{line5.setOpacity(1); xor5.setOpacity(0);}
		}else{ xorLoc[1] = 0;	xor5.setOpacity(0);	line5.setOpacity(0);}
		
		if(check5.isSelected()){
			xorLoc[2] = 1;
			if(xorLoc[0]==1 || xorLoc[1]==1){
			xor4.setOpacity(1); line4.setOpacity(0);
			}else{line4.setOpacity(1); xor4.setOpacity(0);}
		}else{ xorLoc[2] = 0;	xor4.setOpacity(0);	line4.setOpacity(0);}
		
		if(check4.isSelected()){
			xorLoc[3] = 1;
			if(xorLoc[0]==1 || xorLoc[1]==1 || xorLoc[2]==1){
			xor3.setOpacity(1); line3.setOpacity(0);
			}else{line3.setOpacity(1); xor3.setOpacity(0);}
		}else{ xorLoc[3] = 0;	xor3.setOpacity(0);	line3.setOpacity(0);}
		
		if(check3.isSelected()){
			xorLoc[4] = 1;
			if(xorLoc[0]==1 || xorLoc[1]==1 || xorLoc[2]==1 || xorLoc[3]==1){
			xor2.setOpacity(1); line2.setOpacity(0);
			}else{line2.setOpacity(1); xor2.setOpacity(0);}
		}else{ xorLoc[4] = 0;	xor2.setOpacity(0);	line2.setOpacity(0);}
		
		if(check2.isSelected()){
			xorLoc[5] = 1;
			if(xorLoc[0]==1 || xorLoc[1]==1 || xorLoc[2]==1 || xorLoc[3]==1 || xorLoc[4]==1){
			xor1.setOpacity(1); line1.setOpacity(0);
			}else{line1.setOpacity(1); xor1.setOpacity(0);}
		}else{ xorLoc[5] = 0;	xor1.setOpacity(0);	line1.setOpacity(0);}
		
		if(check1.isSelected()){
			xorLoc[6] = 1;		xor0.setOpacity(1);
		}else{ xorLoc[6] = 0;	xor0.setOpacity(0);}

		
		//System.out.println(Arrays.toString(xorLoc));
	}
	
	public void Data(KeyEvent e){
		dataBox = DataText.getText().toCharArray();
		//System.out.println(Arrays.toString(dataBox));
		
		
		for(int i=0; i<dataBox.length; i++){
			sArray[i] = genarator(dataBox[i]);
			if(sArray[i]==null)Text1.setText("Function Error!");
		}
		//System.out.println("------------------------\n"+Arrays.toString(sArray[0]));
		//System.out.println(Arrays.toString(sArray[1]));
		//System.out.println(Arrays.toString(sArray[2]));
		
		
		try{
			//System.arraycopy(sArray, 0, kArray, 0, sArray.length);
			kArray = sArray;
			String all= "Data And CRC\n : ";
			for(int i=0; i<sArray.length; i++){
				for(int j=0; j<sArray[i].length; j++){
					all += sArray[i][j];
				}
			}
			Text1.setText(all);
			//System.out.println("초기화 전" + Arrays.toString(sArray[0]));
			sArray=new int[100][0];
		}catch(Exception e2){
			System.out.println("Function Error!\n");
		}
	}
	
	public void checker(ActionEvent e){
		try{
			for ( int i = 0; i < kArray.length; i++ ) {
			tArray[i] = Arrays.copyOf(kArray[i], kArray[i].length);		//2차원 배열 깊은 복사 
			}
			tArray = errorGenarator(tArray);	//애러생성 		System.out.println(Arrays.toString(kArray[0]));
			String sum="전송받은 데이터(Error율 적용) : \n";
			for(int j=0; j<tArray.length; j++){
				int register[] = new int[6];	
				int tmp[] = new int[7];
				int maximum;
				if(xorLoc[0]==1)maximum=5;
				else if(xorLoc[1]==1)maximum=4;
				else if(xorLoc[2]==1)maximum=3;
				else if(xorLoc[3]==1)maximum=2;
				else if(xorLoc[4]==1)maximum=1;
				else maximum=0;	//망
				for(int i=0; i<tArray[j].length; i++){
	//				System.out.println(j +", "+i);
					tmp[0] = xorLoc[6]==1? tArray[j][i]^register[maximum]:tArray[j][i];
					tmp[1] = xorLoc[5]==1? register[0]^register[maximum]:register[0];
					tmp[2] = xorLoc[4]==1? register[1]^register[maximum]:register[1];
					tmp[3] = xorLoc[3]==1? register[2]^register[maximum]:register[2];
					tmp[4] = xorLoc[2]==1? register[3]^register[maximum]:register[3];
					tmp[5] = xorLoc[1]==1? register[4]^register[maximum]:register[4];
					for(int k=0;k<6;k++){
						register[k] = tmp[k];
					}
	//				System.out.println("레지스터 상태 : "+register[5]+register[4]+register[3]+register[2]+register[1]+register[0]);
	
					if(i+1==tArray[j].length){
						int dec = 64*tArray[j][0] + 32*tArray[j][1] +16* tArray[j][2] + 8*tArray[j][3] + 4*tArray[j][4] + 2*tArray[j][5] + tArray[j][6];
						sum += "" + tArray[j][0] + tArray[j][1] + tArray[j][2] + tArray[j][3] + tArray[j][4] + tArray[j][5] + tArray[j][6] + " : " + (char)dec;
						if(register[5]+register[4]+register[3]+register[2]+register[1]+register[0]==0){
							sum += "\n";
							Text2.setText(sum);
						}else{
							sum += " <Error> \n";
							Text2.setText(sum);
						}
					}
				}
			}
		}catch(Exception e3){
			Text2.setText("Function Error!");
		}
	}
	
	int[][] errorGenarator(int array[][]){
		Random random = new Random();
		int num = 0;
		try{
		num = Integer.parseInt((ErrorText.getValue().toString()));
		} catch(Exception e){
			System.out.println("애러확률 설정 안함");
			num=0;
		}
		for(int i=0; i<array.length; i++){
			int k = array[i].length;
			if(k>7)k=7;
			for(int j=0; j<k; j++){
				if(random.nextInt(100)<num){
					if(array[i][j]==1)array[i][j]=0;
					else if(array[i][j]==0){array[i][j]=1;
					}else{array[i]=null;}
				}
			}
		}
		return array;
	}
	
	
	static int[] genarator(char X){
		int ascii[] = mkbinary(X);
		int register[] = new int[6];	
		int tmp[] = new int[7];
		int maximum;
		if(xorLoc[0]==1)maximum=5;
		else if(xorLoc[1]==1)maximum=4;
		else if(xorLoc[2]==1)maximum=3;
		else if(xorLoc[3]==1)maximum=2;
		else if(xorLoc[4]==1)maximum=1;
		else if(xorLoc[5]==1)maximum=0;	//망
		else maximum=-1;
		if(maximum==-1){
			return null;
		}
		//System.out.println("maximum="+maximum);
		for(int i=0; i<ascii.length; i++){
			tmp[0] = xorLoc[6]==1? ascii[i]^register[maximum]:ascii[i];
			tmp[1] = xorLoc[5]==1? register[0]^register[maximum]:register[0];
			tmp[2] = xorLoc[4]==1? register[1]^register[maximum]:register[1];
			tmp[3] = xorLoc[3]==1? register[2]^register[maximum]:register[2];
			tmp[4] = xorLoc[2]==1? register[3]^register[maximum]:register[3];
			tmp[5] = xorLoc[1]==1? register[4]^register[maximum]:register[4];


			//System.out.println("Clock발생");
			for(int j=0;j<6;j++){
				register[j] = tmp[j];
			}
			
			//System.out.println("레지스터 상태 : "+register[5]+register[4]+register[3]+register[2]+register[1]+register[0]);
		}
		//System.out.println(Arrays.toString(ascii));
		for(int i=0;;i++){
			ascii[7+i]=register[maximum-i];
			if(i==maximum)break;
		}
		//System.out.println(Arrays.toString(ascii));
		return ascii;
	}
	static int[] mkbinary(int ascii){ //10진수인 아스키코드를 2진수로 변경하여 리턴
		int a;
		if(xorLoc[0]==1) a=6;
		else if(xorLoc[1]==1) a=5;
		else if(xorLoc[2]==1) a=4;
		else if(xorLoc[3]==1) a=3;
		else if(xorLoc[4]==1) a=2;
		else if(xorLoc[5]==1) a=1;
		else a=0;
		int binary, sum[] = new int[7+a], i = 0;
		while(ascii>0){
			binary=ascii%2;
			sum[6-i]=binary;
			ascii=ascii/2;
			i++;
		}
		//System.out.println(Arrays.toString(sum)); //지울것 
		return sum;
	}
}
