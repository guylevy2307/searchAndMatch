import java.io.*;
import java.net.URL;

public class main {

    public static void main(String args[])
    {
        URL url = main.class.getResource("big.txt");
        File file = new File(url.getPath());
        URL url2 = main.class.getResource("words.txt");
        File file2 = new File(url2.getPath());
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            BufferedReader br2 = new BufferedReader(new FileReader(file2));
            String[] words=br2.readLine().split(",");
            MainMoudle md=new MainMoudle(br,words);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}

