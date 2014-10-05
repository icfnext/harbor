package com.citytechinc.aem.harbor.api.content.rss;

public interface RSSItem {

	public String getTitle();

	public String getLink();

	public String getDescription();

	public String getAuthor();

	// TODO: Categories

	public String getComments();

	// TODO: enclosure
	// TODO: guid

	public String getPubDate();

	public String getSource();

}
