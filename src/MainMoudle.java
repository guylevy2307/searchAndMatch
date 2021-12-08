import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainMoudle {
    BufferedReader brText;
    String[] words;
    ExecutorService pl = Executors.newFixedThreadPool(1000);
    Aggregator agg;
    public MainMoudle(BufferedReader brText,String[] words) {
        int stratLine=0,startChar=0;
        this.brText = brText;
        this.words=words;
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
                Matcher match=new Matcher(st,this.words,stratLine,startChar);
                pl.execute(match);
                stratLine+=1000;
                startChar+=st.length();
                st="";
            }//while
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
