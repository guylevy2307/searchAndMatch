import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Aggregator {
      HashMap<String, ArrayList<int[]>> map;
        String[] words;

        public Aggregator(String[] words) {

                this.map = new HashMap<>() ;
                this.map=new HashMap<>() ;
            this.words=words;
                for (String w:this.words) {
                        map.put(w, new ArrayList<>());
                }

        }

        public void addToMap(HashMap<String, ArrayList<int[]>> newMap){
                for (String name:words) {
                        this.map.get(name).addAll(newMap.get(name));
                }

        }

        public void print() {
            for (HashMap.Entry<String, ArrayList<int[]>> entry : this.map.entrySet()) {
                String str="[";
                for (int[] place:entry.getValue()) {
                    str=str.concat("[lineOffset= "+place[0]+", charOffset= "+place[1]+"],");
                }
                str=str.concat("]");
                System.out.println(entry.getKey() + "-->" + str);
            }
        }


}
