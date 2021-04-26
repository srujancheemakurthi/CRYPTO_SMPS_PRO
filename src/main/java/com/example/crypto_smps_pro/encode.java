package com.example.crypto_smps_pro;

public class encode {
    public static String enc(String s)
    {
        String initializer="11111111";
        int ct=0;                            //counter ct=0
        int a[] = new int [11111111];      //  Here we are creating an array of size 8 times 1

        for( int i=0; i<s.length();i++)
        {
            a[i] = (int)s.charAt(i);      // storing the string in the given array (a)
            ct++;                          // Here incrimenting the counter value by 1.

        }
        String res ="";       // Resultant string as res. Where it is instilised to null
        int bin [] = new int [111]; // A binary area of size 111
        int idx =0;     // An Index value of ZERO .

        for(int ii=0; ii<ct;ii++)
        {
            int temp = a[ii];
            for (int j=0;j<ct;j++) bin [j] =0;
            idx=0;
            while (temp>0)
            {   bin[idx++]=temp%2;
                temp=temp/2;
            }
            String dig=""; //digits to null
            String temps; // temps is different from temp. There it is int here it is String.
            for(int j=0; j<7;j++)
            {
                temps = Integer.toString(bin[j]);   // Here we are just concatinating the digits after coverting the string into integer form and we reverse the whole thing.
                dig = dig.concat(temps);
            }
            // Main encryption starts from here
            String rev="";   //rev = reverse
            for (int j = dig.length()-1;j>=0;j--)
            {                                         // for encryption we are reversing the whole binary value
                char ca = dig.charAt(j);
                rev = rev.concat(String.valueOf(ca));
            }
            res=res.concat(rev);   // result = concatinated value of reversed one

        }
        res =initializer.concat(res);
        return res;



    }





}


