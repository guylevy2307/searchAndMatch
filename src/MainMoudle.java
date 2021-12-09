import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

public class MainMoudle {
    BufferedReader brText;
    String[] words;
    ExecutorService pl;
    Aggregator agg;
    public MainMoudle(BufferedReader brText,String[] words,int numOfLines) {
        int stratLine=0,startChar=0;
        this.brText = brText;
        this.words=words;
        ArrayList<Future<HashMap<String, ArrayList<int[]>>>> futures = new ArrayList<Future<HashMap<String, ArrayList<int[]>>>>();
        agg=new Aggregator(words);
        int numberOfThread=Math.round(numOfLines/1000)+1;
        pl = Executors.newFixedThreadPool(numberOfThread);
        try {
            String pointer= brText.readLine();
            String st="";
            while( pointer!=null) {
                for (int i = 1; i <= 1000&&pointer!=null; i++) {
                    try {
                        st=st.concat(pointer+"\n");
                        pointer=brText.readLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }//for

                //adding slow and fast way
                Matcher match=new Matcher(st,this.words,stratLine,startChar);
                Callable<HashMap<String, ArrayList<int[]>>> task = match;
                futures.add(pl.submit(task));

                stratLine+=1000;
                startChar+=st.length();
                st="";
            }//while

        } catch (IOException e) {
            e.printStackTrace();
        }
            for(Future<HashMap<String, ArrayList<int[]>>> future : futures){
            try {
                agg.addToMap(future.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        agg.print();
    }

}
