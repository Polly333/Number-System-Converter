//package com.company; // Package name which will contain this class named Main
/*
Character.getNumericValue('a'); //10
For capital a also value is 10
Character.getNumericValue('12'); //12
and vice versa 10=a, 11=b ...
Character.forDigit(Value, radix)
-------------------------------------
We can have number like: 3e.4fc/3E.4FC
*/

import java.util.Scanner;
import java.lang.Math;
public class Main
{
    public static void main (String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        //Initializations
        int source_radix = 0;
        String source_number = "";
        int target_radix = 0;
        System.out.println("---------------------------------------------");
        System.out.println("Enter three inputs which contains - \nSource Radix\nSource Number\nTarget Radix");
        System.out.println("---------------------------------------------");
    //----------ERROR HANDLING---------------

        if (!scanner.hasNextInt()) {
            System.out.print("Enter a number for source radix!");
            return;
        } else {
            source_radix = scanner.nextInt();
        }

            source_number = scanner.next();


        if (!scanner.hasNextInt()) {
            System.out.print("Enter a number for target radix!");
            return;
        } else {
            target_radix = scanner.nextInt();
        }

        if (source_radix < 1 || source_radix > 36 || target_radix < 1 || target_radix > 36)
        {
            System.out.print("The source radix and target radix should be in range 1-36(included)!");
            return;
        }



        String int_part ="";
        String frac_part ="";


//-----------------------------SPLITTING-----------------------------
        if(source_number.contains("."))
        {
            int indexOfDecimal = source_number.indexOf(".");
            int_part = source_number.substring(0, indexOfDecimal);
            frac_part = source_number.substring(indexOfDecimal+1);//Dropping the decimal point
        }


//----------------CONVERTING TO DECIMAL NUMBER SYS---------------------

        int deci_no = 0;//Resultant decimal Number for number with no decimal part
        double decimal_no =0.0d; //Resultant decimal Number for other than that


        if (source_radix == 1) //No decimal part if source_radix is 1
        {
            deci_no = String.valueOf(source_number).length();
        }
        else
        {
            int int_dec=0;
            if(!int_part.equals(""))
            {
                int_dec = Integer.parseInt(int_part,source_radix);
                //frac part to deci
                double frac_dec=0;
                char[] ch = frac_part.toCharArray();
                int n = ch.length;
                for(int i =0;i<n;i++)
                {   int v = Character.getNumericValue(ch[i]);//to get number value for digits a,b,c...

                    frac_dec = frac_dec + v/(Math.pow(source_radix,i+1));

                }
                decimal_no=int_dec+frac_dec;

            }//If the input number is in Fraction
            else{
                try
                {
                    deci_no = Integer.parseInt(source_number,source_radix);
                }
                catch(NumberFormatException e)
                {
                    System.out.print("The digits of the source number should be according to available digits in the source radix");
                    return;
                }


            }

        }


//---------------------CONVERTING DECIMAL NO TO TARGET RADIX NUMBER SYSTEM-----------------------
String target_number =""; // Initialization needed
//If target radix is 1, the consider only integer part of source number.


        if (target_radix == 1) {
            if (deci_no != 0) {
                //System.out.print(target_num);
                for (int i = 0; i < deci_no; i++) {

                    target_number = target_number.concat("1");
                }
                //System.out.print(target_number);
            }

            else
            {

                String no = decimal_no+"" ;//Converting double to string

                //Extracting integer part
                int indexOfDecimal = no.indexOf(".");
                String tar_int_part = no.substring(0, indexOfDecimal);

                int tar_decimal_no = Integer.parseInt(tar_int_part);//Converting string to integer
                for(int i = 1; i<=tar_decimal_no;i++ )
                {
                    target_number = target_number.concat("1");
                }
                //System.out.print(target_number);
            }

        }
  else
    {
       if(deci_no!=0) //Input number not fractional
       {
           target_number = Integer.toString(deci_no,target_radix); //Works for input in decimal number system
       }
       else{
           String no = decimal_no+"" ;//Converting double to string

           //Extracting  and Converting integer part
           int indexOfDecimal = no.indexOf(".");
           String tar_int_part = no.substring(0, indexOfDecimal);

           int tar_decimal_no = Integer.parseInt(tar_int_part);//Converting string to integer
           String tar_int_number = Integer.toString(tar_decimal_no,target_radix);

           //Extracting  and Converting fractional part
           String tar_frac_part = no.substring(indexOfDecimal); //Making it 0.234 like this format
           String tar_frac_number="";
           double iterate = Double.parseDouble(tar_frac_part);//Converting string to double
           for(int i=1;i<=5;i++) //To get 5 decimal places
           {
              iterate = iterate * target_radix;
              String s = iterate +"" ; //double to string
               int index =  s.indexOf(".");
               iterate = Double.parseDouble(s.substring(index)); //Making it 0.234 like this format

               //Getting the numeric value of the character like 10 = a, etc
              String p = s.substring(0, index);
              int q = Integer.parseInt(p);
              char c = Character.forDigit(q,target_radix);
              String d = c+"";

            tar_frac_number = tar_frac_number.concat(d);//Concat

           }


     target_number = tar_int_number +"."+ tar_frac_number;

       }
    }

    System.out.print(target_number);

    }
}

