package first;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;

public class Model {
	
	private int size = 10;;
	private int[] x = new int[size];
	private int[] y = new int[size];
	private int sumOfX;
	private int sumOfY;
	private int[] sumsX= new int[size];
	private int[] sumsY= new int[size];
	private int[][] array= new int[size][size];
	
	public void setSize(int size){
		this.size = size;
	}
	
	public void run(){
		createArrays();
		makeEqualSumOfArrays();
		fillArray();
		printConsole();
		saveInFile();
	}
	
	private void saveInFile() {
		File file = new File("src/main/resources/result.txt");
		PrintWriter pr;
		try {
			pr = new PrintWriter(file);
			pr.print("/ |");
			for(int i=0; i<size; i++){
				pr.print(String.format("%02d", sumsX[i])+"|");
			}
			pr.println();
			for(int i=0; i<size; i++){
				pr.print(String.format("%02d", sumsY[i])+"|");
				for(int j=0; j<size; j++){
					pr.print(String.format("%02d", array[i][j])+"|");
				}
				pr.println();
			}
			pr.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void createArrays(){
		Random generator = new Random();
		for(int i=0; i<size; i++){
			int randomVert = generator.nextInt(100);
			int randomHor = generator.nextInt(100);
			x[i] = randomVert;
			y[i] = randomHor;
			sumOfX+=randomVert;
			sumOfY+=randomHor;
		}
	}
	
	private void makeEqualSumOfArrays(){
		int different = sumOfX - sumOfY;
		int[] targetArray = null;
		if(different>0){
			targetArray = y;
		}else{
			targetArray = x;
		}
		int differentAbs = Math.abs(different);
		int i = 0;
		while(differentAbs>0){
			if(targetArray[i]!=99){
				targetArray[i]++;
				differentAbs--;
			}
			if(i==size-1){
				i=0;
			}else{
				i++;
			}
		}
	}
	
	private void printConsole(){
		System.out.print("/ |");
		for(int i = 0; i<size; i++){
			System.out.print(String.format("%02d", sumsX[i])+"|");
		}
		System.out.println();
		for(int i=0;i<size;i++){
			System.out.print(String.format("%02d", sumsY[i])+"|");
			for(int k=0;k<size;k++){
				System.out.print(String.format("%02d", this.array[i][k])+"|");
			}
			System.out.print("///"+String.format("%02d",sumsY[i])+"|");
			System.out.println();
		}
		System.out.println("=================================");
		System.out.print("/ |");
		for(int i=0;i<size;i++){
			System.out.print(String.format("%02d", sumsX[i])+"|");
		}
	}
	
	private void fillArray() {
		for (int i = 0; i < size; i++) {
			int j = 0;
			while (y[i] != 0) {
				if (x[j] > 0 & y[i] > 0) {
					int different = Math.min(x[j], y[i]);
					array[i][j] += different;
					sumsX[j] += different;
					sumsY[i] += different;
					x[j] -= different;
					y[i] -= different;
				}
				if (j == size-1) {
					j = 0;
				} else {
					j++;
				}
			}
		}
	}

}
