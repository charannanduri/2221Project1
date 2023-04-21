import java.util.Comparator;

import components.map.Map;
import components.map.Map1L;
import components.queue.Queue;
import components.queue.Queue1L;
import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Creates an HTML glossary from a inputed text file.
 *
 * @author Charan
 *
 */
public final class Glossary {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private Glossary() {
    }

    /**
     * Takes file and makes a map that uses.
     *
     * @code key as the key and @code val as the value.
     *
     *       @param in is the input file reader.
     * @param Words
     *            is the ouput Map that will contain the words and their paired
     *            definition
     *
     * @update Words
     *
     */
    private static void input(SimpleReader in, Map<String, String> Words) {
        /*
         *
         */
        String key = "";
        String val = "";

        while (!in.atEOS()) {
            String t = in.nextLine();
            if (isOneWord(t))//check if single word - build method isOneWord
            {
                key = t;
            }

            else if (isSentence(t))//check sentence - build method isASentence
            {
                val = val + t;
            }

            else //...
            {
                Words.add(key, val);
                key = "";
                val = "";

            }
        }
        if (val.length() > 0 && key.length() > 0) {
            Words.add(key, val);//add together if both have any len
        }

    }

    /**
     * Checks to see if the string is a sentence.
     *
     * @param s
     *            input string default status is false since we assume it is a
     *            word until we check.
     * @return
     */
    public static boolean isSentence(String s) {
        boolean status = false;
        if (s.length() < 1) {
            status = false;
        } else {
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == ' ') {
                    status = true;
                }
            }
        }
        return status;
    }

    /**
     * finds all the terms within a definition sentence.
     *
     * @param s
     *            // definition
     * @param Words
     *            //Map that stores all words.
     * @return //Set string that keeps all words within the given definition.
     */
    public static Set<String> termsinSentence(String s,
            Map<String, String> Words) {
        Set<String> words = new Set1L<String>();
        for (Map.Pair<String, String> word : Words) {
            if (s.contains(word.key()) && !words.contains(word.key())) {
                words.add(word.key());
            }
        }

        return words;
    }

    /**
     * Checks to see if the string is just one word.
     *
     * @param s
     *            input string
     * @return
     */
    public static boolean isOneWord(String s) {
        boolean status = true;
        if (s.length() < 1) {
            status = false;

        } else {
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == ' ') {
                    status = false;

                }

            }
        }
        return status;
    }

    /**
     * We need a method to compare string length.
     *
     * @param s1
     *            // string 1
     * @param s2
     *            // string 2
     * @return 2 if s1 is larger than s2, 1 if s2 is larger than s1, 0 if they
     *         are equivalent
     */
    private static class compLength implements Comparator<String> {
        @Override
        public int compare(String s1, String s2) {
            if (s1.compareTo(s2) < 0) {
                return -1;
            } else if (s1.compareTo(s2) > 0) {
                return 1;
            } else {
                return 0;
            }

        }

    }

    /**
     * alphabetical sort of the map
     *
     * @param Words
     */
    /**
     * Isolates terms within the map and puts them into a Queue
     */
    public static Queue<String> makeSortedQ(Map<String, String> Words) {
        Queue<String> allWords = new Queue1L<String>();
        for (Map.Pair<String, String> terms : Words) {
            allWords.enqueue(terms.key());
        }
        Comparator<String> compStr = new compLength();
        allWords.sort(compStr);

        return allWords;
    }

    /**
     * makes each term its own HTML page.
     *
     * @param Words
     * @param outputPath
     */
    public static void makeTermPage(Map<String, String> Words,
            String outputPath) {
        Set<String> words = new Set1L<String>();
        for (Map.Pair<String, String> word : Words) {
            String def = word.value();
            SimpleWriter page = new SimpleWriter1L(
                    outputPath + "/" + word.key() + ".html");
            page.print("<html>\n<head>\n<title>" + word.key()
                    + "</title>\n</head>\n");
            page.print("<body>\n<h2><b><i><font color=\"red\">" + word.key()
                    + "</font></i></b></h2>\n");
            words = termsinSentence(word.value(), Words);
            for (String terms : words) {
                def = def.substring(0, def.indexOf(terms)) + makeLink(terms)
                        + terms + "</a>"
                        + def.substring(def.indexOf(terms) + terms.length());
            }
            page.print("<blockquote>");
            page.print(def + "</blockquote>");
            page.println("<hr/>");
            page.println("<p> Return to <a href=\"index.html\">index</a>.</p>");
            page.print("</body></html>");
            page.close();
        }
    }

    /**
     * makes a html link with given string.
     *
     * @param s
     * @return link //the link
     */
    public static String makeLink(String s) {
        //String link = "< a href=\"" + s + ".html\">";
        return "<a href=\"" + s + ".html\">";
    }

    /**
     * makes index.html.
     *
     * @param words
     * @param outputPath
     */
    public static void makeIndexPage(Queue<String> words, String outputPath) {
        SimpleWriter index = new SimpleWriter1L(outputPath + "/index.html");
        index.print("<html><head><title>Glossary Index</title></head><body>"
                + "<h2>Glossary Index</h2><hr /><h3>Index</h3><ul>");
        for (String word : words) {
            index.println(
                    "<li><a href=\"" + word + ".html\">" + word + "</a></li>");
        }
        index.print("</ul></body></html>");
        index.close();
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        out.println("enter name of text file with glossary items:"); // used terms.txt provided.
        String fileName = in.nextLine();
        out.print("enter output folder name:"); //I used /doc as the html file dump
        String outputPath = in.nextLine();
        out.println("Building glossary...");

        SimpleReader reader = new SimpleReader1L(fileName);
        Map<String, String> words = new Map1L<String, String>();

        input(reader, words);
        //out.println(words.toString());

        Queue<String> terms = new Queue1L<String>();
        terms = makeSortedQ(words);
        makeIndexPage(terms, outputPath);
        out.println("Built Index page.");
        makeTermPage(words, outputPath);
        out.println("Built each term page.");
        out.println("Complete, files are located in " + outputPath);

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
