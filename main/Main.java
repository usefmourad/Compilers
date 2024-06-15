package csen1002.main;
import java.util.*;

public class Main {

    public static int maxOccured(int n, int l[], int r[], int maxx) {

        int maxidx = -1;
        int maxdiff = -1;
        for(int i = 0; i<l.length; i++){
            if(r[i] - l[i] > maxdiff){
                maxidx = i;
                maxdiff = r[i] - l[i];
            }
        }

        ArrayList<Integer> list = new ArrayList<Integer>();

        for(int i = l[maxidx]; i<= r[maxidx]; i++){
            list.add(i);
        }


        for(int j = 0; j<l.length; j++){

            
        }




    }
}
