import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.concurrent.Callable;

public class Matcher implements Callable<HashMap<String, ArrayList<int[]>>> {
    Scanner part;
    String partText;
    String[] match;
    int lineOffset;
    int charOffset;
    HashMap<String, ArrayList<int[]>> map;

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

    private String changeChar(String str, boolean flag){
            if(flag){
                return str.replace("[^a-zA-Z0-9]"," ");
            }else{
                return str;
            }
    }
    @Override
    public HashMap<String, ArrayList<int[]>> call() {
        int charNumber=0;
        String[] lines=this.partText.split("\n");
        for(int i=0;i<lines.length;i++){
            lines[i]=changeChar( lines[i],true);
            String[] line=lines[i].split(" ");
            int j=0;

            for(;j<line.length;j++){
                if(map.containsKey(line[j])){
                    ArrayList t=map.get(line[j]);
                    int[] place=new int[]{lineOffset+i,charOffset+charNumber};
                    t.add(place);
                    map.put(line[j],t);
                }
                charNumber+=line[j].length();
            }

        }
        return this.map;
    }
}
