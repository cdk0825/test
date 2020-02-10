
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author work
 */
public class TestRandomNo01 {
	
	public static void main(String[] args){
		
		int result = (int) (Math.floor(Math.random() * 1000000)+100000);
		if(result>1000000){
		   result = result - 100000;
		}
		
		System.out.println("result=" + result);

	}
}
