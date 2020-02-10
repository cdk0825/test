
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author work
 */
public class TestReadLogFile {


	public static void main(String[] args){
		
		Path p = Paths.get("D:/cdk/xml/log.txt");
		
		Charset cs = StandardCharsets.UTF_8;
		
		List<String> list = new ArrayList<String>();
		
		try{
			list = Files.readAllLines(p, cs);
		}catch(IOException e){
			e.printStackTrace();
		}
		
		for(String readLine : list){
			System.out.println(readLine);
		}
	}
}
