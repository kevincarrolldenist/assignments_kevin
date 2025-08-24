package org.example;

import java.util.Scanner;

public class day3a {
    public static void main(String[] args) {
        double temperature[]=new double[10];
        Scanner sc1 = new Scanner(System.in);
        for(int i=0;i<10;i++)
        {
            System.out.println("Enter the temperature for day " + (i+1) + ": ");
            temperature[i] = sc1.nextDouble();
        }
        int count=0;
        for(double temp:temperature)
        {
            if(temp>40)
            {
                count++;
            }
        }
        System.out.println("Number of days with temperature above 40 degrees: " + count);

    }
}
