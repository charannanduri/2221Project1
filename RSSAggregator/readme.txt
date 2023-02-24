Project: RSS News Aggregator

Objectives

Familiarity with using XMLTrees to process XML
Familiarity with using while loops and static methods
Exposure to RSS technology
The Problem

For this project your task is to update the RSS Reader program you wrote for the previous project so that it reads multiple RSS feeds and generates the same nicely formatted HTML page of links for each feed, plus an HTML index page with links to the individual feed pages.

Your new program should ask the user for the name of an XML file containing a list of URLs for RSS v2.0 feeds (see below for the format of this file) and for the name of an output file in which to generate an HTML page with links to the pages for the individual RSS feeds (see below for an example). It should then read the input XML file into an XMLTree object and then process the list of RSS feeds from the XMLTree. For each RSS feed, the program should generate an HTML page with a table of links to all the news items in the feed (just like in the previous project). The program should also generate an HTML page with an index of links to the individual feed pages.

Format of the Input XML Document

The input XML document is pretty simple. This is the general structure:

<feeds title="Title for index page">
  <feed url="the feed source URL" name="name of feed for index page"
    file="name of HTML file for feed" />
  <feed url="..." name="..." file="..." />
  ...
</feeds>

The top-level tag, <feeds>, has a required attribute, title, whose value is the title to be used in the index page; nested inside the top-level tag are 1 or more <feed> tags with the following required attributes: url, the URL of the RSS feed, name, the name to use for the link to the feed in the index page, and file, the name of the HTML file in which to generate the feed's table of links to news items (with the same format as the output in the previous project).

Here is an example of a valid XML input file.

Format of the HTML Output Index Page

The HTML index page should include the following information:

the <feeds> title as the page title
a heading with the page title inside
an unordered list where each item is the name of a feed linked to the feed URL
You can see an example of the index output here.

Method

Create a new Eclipse project by copying your RSSReader project and name the new project RSSAggregator.
Open the src folder of this project and then open (default package). Rename the RSSReader.java file to RSSAggregator.java. Open the RSSAggregator.java file in the editor.
Edit RSSAggregator.java to satisfy the problem requirements stated above. You should factor out the code in your previous project that processed the RSS feed into the following separate static method:

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
private static void processFeed(String url, String file, SimpleWriter out) {...}
Select your Eclipse project RSSAggregator (not just some of the files, but the whole project), create a zip archive of it, and submit the zip archive to the Carmen dropbox for this project, as described in Submitting a Project.
Additional Activities

Some feeds contain extraneous links like advertisements or sponsor links. Add code to recognize some of these kinds of links and filter them out of your output.
Some feeds contain duplicate links. Add code to recognize duplicate links and filter out the extra copies.
