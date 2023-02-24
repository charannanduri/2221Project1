import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;

/**
 * This GitHub code is for my (Charan's) personal use. do not copy this code. I am not responsible
 for any resulting action by any governing body based upon the use of the code in my (Charan's) personal repository.
 * Program to convert an XML RSS (version 2.0) feed from a given URL into the
 * corresponding HTML output file.
 *
 * @author Charan Nanduri
 *
 */
public final class RSSAggregator {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private RSSAggregator() {
    }

    /**
     * Outputs the "opening" tags in the generated HTML file. These are the
     * expected elements generated by this method:
     *
     * <html> <head> <title>the channel tag title as the page title</title>
     * </head> <body>
     * <h1>the page title inside a link to the <channel> link</h1>
     * <p>
     * the channel description
     * </p>
     * <table border="1">
     * <tr>
     * <th>Date</th>
     * <th>Source</th>
     * <th>News</th>
     * </tr>
     *
     * @param channel
     *            the channel element XMLTree
     * @param out
     *            the output stream
     * @updates out.content
     * @requires [the root of channel is a <channel> tag] and out.is_open
     * @ensures out.content = #out.content * [the HTML "opening" tags]
     */
    private static void outputHeader(XMLTree channel, SimpleWriter out) {
        assert channel != null : "Violation of: channel is not null";
        assert out != null : "Violation of: out is not null";
        assert channel.isTag() && channel.label().equals("channel") : ""
                + "Violation of: the label root of channel is a <channel> tag";
        assert out.isOpen() : "Violation of: out.is_open";
        out.println("<?xml version='2.0' encoding='ISO-8859-1' ?>");
        out.println("<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Strict//EN'"
                + " 'http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd'>");
        out.println("<html xmlns='http://www.w3.org/1999/xhtml'>");
        out.println("<head>");
        out.println("<meta http-equiv='Content-Type'"
                + " content='text/html; charset=ISO-8859-1' />");

        String title = " N/A ";
        String description = "none";
        int iTitle = getChildElement(channel, "title");
        int iDesc = getChildElement(channel, "description");
        // Prints the <channel>title as the page title
        if (channel.child(iTitle).numberOfChildren() > 0) {
            title = channel.child(getChildElement(channel, "title")).child(0)
                    .label();
        } else {
            title = "None";
        }
        if (channel.child(iDesc).numberOfChildren() > 0) {
            description = channel.child(getChildElement(channel, "description"))
                    .child(0).toString();
        } else {
            description = "None";
        }
        String link = channel.child(getChildElement(channel, "link")).child(0)
                .toString();

        // Prints out more opening tags
        out.println("</head>");
        out.println("<body>");

        out.println("<h1><a href = " + "\"" + link + "\"" + ">" + title
                + "</a></h1>");
        out.println("<p>" + description + "</p>");

        out.println("<table border = " + "1>");
        out.println(
                "<tbody> <tr> <th> Date </th> <th> Source </th> <th> News </th> </tr>");

    }

    /**
     * Outputs the "closing" tags in the generated HTML file. These are the
     * expected elements generated by this method:
     *
     * </table>
     * </body> </html>
     *
     * @param out
     *            the output stream
     * @updates out.contents
     * @requires out.is_open
     * @ensures out.content = #out.content * [the HTML "closing" tags]
     */
    private static void outputFooter(SimpleWriter out) {
        assert out != null : "Violation of: out is not null";
        assert out.isOpen() : "Violation of: out.is_open";

        out.println("</table>");
        out.println("</body>");
        out.println("</html>");
    }

    /**
     * Finds the first occurrence of the given tag among the children of the
     * given {@code XMLTree} and return its index; returns -1 if not found.
     *
     * @param xml
     *            the {@code XMLTree} to search
     * @param tag
     *            the tag to look for
     * @return the index of the first child of type tag of the {@code XMLTree}
     *         or -1 if not found
     * @requires [the label of the root of xml is a tag]
     * @ensures <pre>
     * getChildElement =
     *  [the index of the first child of type tag of the {@code XMLTree} or
     *   -1 if not found]
     * </pre>
     */
    private static int getChildElement(XMLTree xml, String tag) {
        assert xml != null : "Violation of: xml is not null";
        assert tag != null : "Violation of: tag is not null";
        assert xml.isTag() : "Violation of: the label root of xml is a tag";

        int i = -1, j = 0;
        //String name = xml.child(i).label();
        while (j < xml.numberOfChildren() && i == -1) {
            if (xml.child(j).label().equals(tag)) {
                i = j;
            }
            j++;
        }
        return i;
    }

    /**
     * Processes one news item and outputs one table row. The row contains three
     * elements: the publication date, the source, and the title (or
     * description) of the item.
     *
     * @param item
     *            the news item
     * @param out
     *            the output stream
     * @updates out.content
     * @requires [the label of the root of item is an <item> tag] and
     *           out.is_open
     * @ensures <pre>
     * out.content = #out.content *
     *   [an HTML table row with publication date, source, and title of news item]
     * </pre>
     */
    private static void processItem(XMLTree item, SimpleWriter out) {

        assert item != null : "Violation of: item is not null";

        assert out != null : "Violation of: out is not null";

        assert item.isTag() && item.label().equals("item") : ""
                + "Violation of: the label root of item is an <item> tag";

        assert out.isOpen() : "Violation of: out.is_open";
        int i = 0;
        out.println("<tr>");
        i = getChildElement(item, "pubDate");
        if (i != -1) {
            if (item.child(i).numberOfChildren() != 0) {
                String pDate = item.child(i).child(0).label();
                out.println("<td>" + pDate + "</td>");
            }
        } else {
            out.println("<td> N/A </td>");
        }

        i = getChildElement(item, "item");
        if (i != -1) {
            if (item.child(i).numberOfChildren() != 0) {
                String source = item.child(i).child(0).label();
                out.println("<td>" + source + "</td>");
            }
        } else {
            i = getChildElement(item, "source");
            if (i != -1) {
                String source = item.child(i).child(0).label();
                out.println("<td>" + source + "</td>");
            } else {
                out.println("<td> N/A </td>");
            }
        }

        i = getChildElement(item, "title");
        if (item.child(i).numberOfChildren() != 0) {
            String title = item.child(i).child(0).label();
        }

        if (i == -1) {
            i = getChildElement(item, "description");
            String description = item.child(i).child(0).label();
            i = getChildElement(item, "link");
            if (i == -1) {
                out.println("<td>" + description + "</td>");
            } else {
                String url = item.child(i).child(0).label();
                out.println("<td><a href = " + url + ">" + description
                        + "</a></td>");
            }
        } else {
            String title = item.child(i).child(0).label();
            i = getChildElement(item, "link");
            if (i == -1) {
                out.println("<td>" + title + "</td>");
            } else {
                String url = item.child(i).child(0).label();
                out.println("<td><a href = " + url + ">" + title + "</a></td>");
            }
        }

        out.println("</tr>");

    }

    /**
     * Processes one XML RSS (version 2.0) feed from a given URL converting it
     * into the corresponding HTML output file.
     *
     * @param url
     *            the URL of the RSS feed
     * @param file
     *            the name of the HTML output file
     * @param out
     *            the output stream to report progress or errors
     * @updates out.content
     * @requires out.is_open
     * @ensures <pre>
     * [reads RSS feed from url, saves HTML document with table of news items
     *   to file, appends to out.content any needed messages]
     * </pre>
     */
    private static void processFeed(String url, String file, SimpleWriter out) {
        XMLTree source = new XMLTree1(url);
        SimpleWriter outputFile = new SimpleWriter1L(file);
        // check that the file is an rss 2.0 feed.
        if (source.label().equals("rss") && (source.hasAttribute("version"))) {
            XMLTree channel = source.child(0);
            outputHeader(channel, outputFile);
            for (int k = 0; k < channel.numberOfChildren(); k++) {
                if (channel.child(k).label().equals("item")) {
                    processItem(channel.child(k), outputFile);
                }
            }
            outputFooter(outputFile);
        } else {
            int i = 0;
        }
        out.println("created " + file);
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();
        out.println(
                "Enter an index XML file name containing a list of RSS 2.0 feeds: ");
        String XML_File = in.nextLine();

        //open new file to store the HTML content for the index page

        SimpleWriter outputFile = new SimpleWriter1L("index.html");
        XMLTree index = new XMLTree1(XML_File);

        //loop to iterate for j number of pages in the XML document.

        for (int k = 0; k < index.numberOfChildren(); k++) {
            //out.print(index.attri());
            String name = index.child(k).attributeValue("file");
            String url = index.child(k).attributeValue("url");
            out.println("creating " + name + " from " + url);
            processFeed(url, name, out);

        }

        String title = index.attributeValue("title");
        outputFile.println("<head> <title>" + title
                + "</title> </head> <body> <head> <h2> " + title
                + "</h2> </head>");
        for (int k = 0; k < index.numberOfChildren(); k++) {
            String name = index.child(k).attributeValue("name");
            String fileName = index.child(k).attributeValue("file");
            outputFile.print("<li> <p> <a href = " + "\"" + fileName + "\""
                    + ">" + name + "</a> </p> </li> ");
        }
        outputFile.print("</ul> </body> </html>");
        out.println("complete.");

        in.close();
        out.close();
        outputFile.close();

    }

}
