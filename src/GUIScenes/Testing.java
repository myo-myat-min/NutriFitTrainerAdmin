/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIScenes;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


/**
 *2021-02-06
 * @author ACER
 */
public class Testing {
    
    public static void main(String[]args) {
        Scanner scanner = new Scanner( System.in );  
  System.out.print("Enter ur age ");  
    
  int value = scanner.nextInt();  
  assert value>=18:" Not valid";  
  
  System.out.println("value is "+value);
    }
    
}
