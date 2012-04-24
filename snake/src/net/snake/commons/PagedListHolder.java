package net.snake.commons;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
public class PagedListHolder<E> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final int DEFAULT_PAGE_SIZE = 10;

	public static final int DEFAULT_MAX_LINKED_PAGES = 10;


	private List<E> source;

	private Date refreshDate;


	private int pageSize = DEFAULT_PAGE_SIZE;

	private int page = 0;

	private boolean newPageSet;

	private int maxLinkedPages = DEFAULT_MAX_LINKED_PAGES;


	/**
	 * Create a new holder instance.
	 * You'll need to set a source list to be able to use the holder.
	 * @see #setSource
	 */
	public PagedListHolder() {
		this(new ArrayList<E>(0));
	}

	/**
	 * Create a new holder instance with the given source list, starting with
	 * a default sort definition (with "toggleAscendingOnProperty" activated).
	 * @param source the source List
	 * @see MutableSortDefinition#setToggleAscendingOnProperty
	 */
	public PagedListHolder(List<E> source) {
		setSource(source);
	}

	/**
	 * Set the source list for this holder.
	 */
	public void setSource(List<E> source) {
		this.source = source;
		this.refreshDate = new Date();
	}

	/**
	 * Return the source list for this holder.
	 */
	public List<E> getSource() {
		return this.source;
	}

	/**
	 * Return the last time the list has been fetched from the source provider.
	 */
	public Date getRefreshDate() {
		return this.refreshDate;
	}

	/**
	 * Set the current page size.
	 * Resets the current page number if changed.
	 * <p>Default value is 10.
	 */
	public void setPageSize(int pageSize) {
		if (pageSize != this.pageSize) {
			this.pageSize = pageSize;
			if (!this.newPageSet) {
				this.page = 0;
			}
		}
	}

	/**
	 * Return the current page size.
	 */
	public int getPageSize() {
		return this.pageSize;
	}

	/**
	 * Set the current page number.
	 * Page numbering starts with 0.
	 */
	public void setPage(int page) {
		this.page = page;
		this.newPageSet = true;
	}

	/**
	 * Return the current page number.
	 * Page numbering starts with 0.
	 */
	public int getPage() {
		this.newPageSet = false;
		if (this.page >= getPageCount()) {
			this.page = getPageCount() - 1;
		}
		return this.page;
	}

	/**
	 * Set the maximum number of page links to a few pages around the current one.
	 */
	public void setMaxLinkedPages(int maxLinkedPages) {
		this.maxLinkedPages = maxLinkedPages;
	}

	/**
	 * Return the maximum number of page links to a few pages around the current one.
	 */
	public int getMaxLinkedPages() {
		return this.maxLinkedPages;
	}


	/**
	 * Return the number of pages for the current source list.
	 */
	public int getPageCount() {
		float nrOfPages = (float) getNrOfElements() / getPageSize();
		return (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages);
	}

	/**
	 * Return if the current page is the first one.
	 */
	public boolean isFirstPage() {
		return getPage() == 0;
	}

	/**
	 * Return if the current page is the last one.
	 */
	public boolean isLastPage() {
		return getPage() == getPageCount() -1;
	}

	/**
	 * Switch to previous page.
	 * Will stay on first page if already on first page.
	 */
	public void previousPage() {
		if (!isFirstPage()) {
			this.page--;
		}
	}

	/**
	 * Switch to next page.
	 * Will stay on last page if already on last page.
	 */
	public void nextPage() {
		if (!isLastPage()) {
			this.page++;
		}
	}

	/**
	 * Return the total number of elements in the source list.
	 */
	public int getNrOfElements() {
		return getSource().size();
	}

	/**
	 * Return the element index of the first element on the current page.
	 * Element numbering starts with 0.
	 */
	public int getFirstElementOnPage() {
		return (getPageSize() * getPage());
	}

	/**
	 * Return the element index of the last element on the current page.
	 * Element numbering starts with 0.
	 */
	public int getLastElementOnPage() {
		int endIndex = getPageSize() * (getPage() + 1);
		int size = getNrOfElements();
		return (endIndex > size ? size : endIndex) - 1;
	}

	/**
	 * Return a sub-list representing the current page.
	 */
	public List<E> getPageList() {
		return getSource().subList(getFirstElementOnPage(), getLastElementOnPage() + 1);
	}

	/**
	 * Return the first page to which create a link around the current page.
	 */
	public int getFirstLinkedPage() {
		return Math.max(0, getPage() - (getMaxLinkedPages() / 2));
	}

	/**
	 * Return the last page to which create a link around the current page.
	 */
	public int getLastLinkedPage() {
		return Math.min(getFirstLinkedPage() + getMaxLinkedPages() - 1, getPageCount() - 1);
	}

}
