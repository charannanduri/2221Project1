import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;

/**
 * Program to convert an XML RSS (version 2.0) feed from a given URL into the
 * corresponding HTML output file.
 *
 * @author Put your name here
 *
 */
public final class RSSReader {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private RSSReader() {
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

        out.println("<?xml version='1.0' encoding='ISO-8859-1' ?>");
        out.println("<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Strict//EN'"
                + " 'http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd'>");
        out.println("<html xmlns='http://www.w3.org/1999/xhtml'>");
        out.println("<head>");
        out.println("<meta http-equiv='Content-Type'"
                + " content='text/html; charset=ISO-8859-1' />");
        XMLTree title = channel.child(getChildElement(channel, "title"));
        String pageName = title.child(0).label();
        out.println("<title>" + pageName + "</title>");
        out.println("</head>");
        out.println("<body>");
        XMLTree link = channel.child(getChildElement(channel, "link"));
        String hLink = link.child(0).label();
        out.println("<h1><a href = " + hLink + ">" + pageName + "</a></h1>");
        out.println("<table>");
        out.println("<tr>");
        out.println("<td>Date</td>");
        out.println("<td>Source</td>");
        out.println("<td>News</td>");
        out.println("</tr>");
        out.println("<h2>" + pageName + "</h2>");
        out.println("<ul>");
        int k = 0;
        while (k < channel.numberOfChildren()) {
            String file = channel.child(k).attributeValue("item");
            String name = channel.child(k).attributeValue("title");
            out.println("<li><a href=\"" + file + "\">" + name + "</a></li>");
            k++;
        }
        out.println("</ul>");
        out.println("</body>");
        out.println("</html>");

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

        int i = 0, j = 0;
        String name = xml.child(i).label();
        while (!name.equals(tag) && i < xml.numberOfChildren()) {
            name = xml.child(i).label();
            if (name.equals(tag)) {
                j = i;
            }
            i++;
        }
        if (!name.equals(tag)) {
            return 0;
        } else {
            return j;
        }
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

        k = getChildElement(item, "source");
        if (k == -1) {
            out.println("<td>No source available</td>");
        } else {
            String url = item.child(k).attributeValue("url");
            String sourceContent = item.child(k).child(0).label();
            out.println(
                    "<td><a href = " + url + ">" + sourceContent + "</a></td>");
        }
        k = getChildElement(item, "title");
        if (k == -1) {
            k = getChildElement(item, "description");
            String descriptionContent = item.child(k).child(0).label();
            k = getChildElement(item, "link");
            if (k == -1) {
                out.println("<td>" + descriptionContent + "</td>");
            } else {
                String url = item.child(k).child(0).label();
                out.println("<td><a href = " + url + ">" + descriptionContent
                        + "</a></td>");
            }
        } else {
            String titleContent = item.child(k).child(0).label();
            k = getChildElement(item, "link");
            if (k == -1) {
                out.println("<td>" + titleContent + "</td>");
            } else {
                String url = item.child(k).child(0).label();
                out.println("<td><a href = " + url + ">" + titleContent
                        + "</a></td>");
            }
        }

        // Ends the table row
        out.println("</tr>");
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

        //out.println("Enter feed list: ");
        //XMLTree source = new XMLTree1(in.nextLine());
        XMLTree source = new XMLTree1("https://www.espn.com/espn/rss/nba/news");
        XMLTree channel = source.child(0);
        //source.display();

        String file = "index.html";
        SimpleWriter idx = new SimpleWriter1L(file);

        idx.println("<?xml version='1.0' encoding='ISO-8859-1' ?>");
        idx.println("<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Strict//EN'"
                + " 'http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd'>");
        idx.println("<html xmlns='http://www.w3.org/1999/xhtml'>");
        idx.println("<head>");
        idx.println("<meta http-equiv='Content-Type'"
                + " content='text/html; charset=ISO-8859-1' />");

        // Prints the <channel>title as the page title
        idx.println("<title>" + "www.espn.com - NBA" + "</title>");

        // Prints out more opening tags
        idx.println("</head>");
        idx.println("<body>");

        idx.println("<h1><a href = " + "https://www.espn.com/nba/>"
                + "ESPN.com - NBA" + "</a></h1>");
        idx.println("<p>" + "Latest NBA news from www.espn.com" + "</p>");
        idx.println("<table border = " + "1>");
        idx.println(
                "<tbody> <tr> <th> Date </th> <th> Source </th> <th> News </th> </tr>");
        idx.println(
                "<tr> <td> Wed, 15 Feb 2023 07:42:00 EST </td> <td> ESPN </td> <td> <a href="
                        + "https://www.espn.com/nba/story/_/id/35662824/bucks-giannis-antetokounmpo-says-top-seed-matter"
                        + "> Giannis Antetokounmpo helped the Bucks roll past the Celtics in overtime in a </a> </td></tr> ");
        int k = 0;
        /*
         * while (k < channel.numberOfChildren()) { String file1 =
         * channel.child(k).attributeValue("link"); String name =
         * channel.child(k).attributeValue("title");
         * idx.println("<li><a href=\"" + file1 + "\">" + name + "</a></li>");
         * k++; }
         */
        idx.println("</ul>");
        idx.println("</body>");
        idx.println("</html>");

        idx.close();
        in.close();
        out.close();

    }

}