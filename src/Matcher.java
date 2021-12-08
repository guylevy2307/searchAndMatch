import java.io.*;
import java.net.URL;
import java.util.*;

public class Matcher implements Runnable  {
    Scanner part;
    String partText;
    String[] match;
    int lineOffset;
    int charOffset;
    Map<String, List<int[]>> map;

    public Matcher(String part, String[] words, int stratLine, int startChar) {
        this.part = new Scanner(part);
        this.partText=part;
        this.match=words;
        this.map=new HashMap<>() ;
        for (String w:this.match) {
            map.put(w, new ArrayList<>());
        }
        this.lineOffset=stratLine;
        this.charOffset=startChar;
    }

    public void Test(){

        int charNumber=0;
        String[] lines=this.partText.split("\n");
        for(int i=0;i<lines.length;i++){
            lines[i]=lines[i].replace("[^a-zA-Z0-9]"," ");
            String[] line=lines[i].split(" ");
            int j=0;

            for(;j<line.length;j++){
                if(map.containsKey(line[j])){
                    List t=map.get(line[j]);
                    int[] place=new int[]{lineOffset+i,charOffset+charNumber};
                    t.add(place);
                    map.put(line[j],t);
                }
                charNumber+=line[j].length();
            }

        }
    }

    @Override
    public void run() {
        int lineNumber=0,charNumber=0;
        String[] lines=this.partText.split("\n");
        for(int i=0;i<lines.length;i++){
            lines[i]=lines[i].replace("[^a-zA-Z0-9]"," ");
            String[] line=lines[i].split(" ");
            int j=0;
            int charOff=0;
            for(;j<line.length;j++){
                if(map.containsKey(line[j])){
                       List t=map.get(line[j]);
                       int[] place=new int[]{lineOffset+i,charOffset+charOff};
                       t.add(place);
                       map.put(line[j],t);
                }
                charOff+=line[j].length();
            }

        }
    }




}
