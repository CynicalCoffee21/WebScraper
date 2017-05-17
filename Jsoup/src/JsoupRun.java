import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class JsoupRun {

	public static void main(String[] args) throws IOException {
		String url = args[0];
		
	    Document doc = Jsoup.connect(url).get();
	    org.jsoup.select.Elements media = doc.select("img[alt]");

        System.out.println("Media: " + media.size());
        for (Element image : media) {
            if (image.tagName().equals("img")){
            	String src = image.absUrl("src");
            	System.out.println("img - " + src);
            	getImages(src);
            }
        }
	}

private static void getImages(String src) throws IOException {
        int indexname = src.lastIndexOf("/");

        if (indexname == src.length()) {
            src = src.substring(1, indexname);
        }

        indexname = src.lastIndexOf("/");
        String name = src.substring(indexname, src.length());

        System.out.println(name);      

        URL url = new URL(src);
        java.io.InputStream in = url.openStream();

        OutputStream out = new BufferedOutputStream(new FileOutputStream("folderpath"+ name)); //change to desired folder

        for (int b; (b = in.read()) != -1;) {
            out.write(b);
        }

        out.close();
        in.close();
    }


}
