package com.codeandme.searchprovider.ui;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.search.ui.ISearchResult;
import org.eclipse.search.ui.ISearchResultListener;
import org.eclipse.search.ui.ISearchResultPage;
import org.eclipse.search.ui.ISearchResultViewPart;
import org.eclipse.search.ui.SearchResultEvent;
import org.eclipse.search.ui.text.AbstractTextSearchViewPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.IPageSite;

import com.codeandme.searchprovider.FileSearchResultEvent;

public class SearchResultPage extends AbstractTextSearchViewPage implements ISearchResultPage, ISearchResultListener {

	private String fId;
	private Composite fRootControl;
	private IPageSite fSite;
	private Text ftext;
	
	private ISearchResultListener resultListener; 
	
	public SearchResultPage()
	{
		super(AbstractTextSearchViewPage.FLAG_LAYOUT_FLAT); 
		setID(SearchResultPage.class.getName());
	}

	@Override
	public Object getUIState() 
	{
		return null;
	}

	@Override
	public void setInput(ISearchResult search, Object uiState) 
	{
		search.addListener(this);
	}

	@Override
	public void setViewPart(ISearchResultViewPart part) 
	{
	}

	@Override
	public void setID(String id) 
	{
		fId = id;
	}

	@Override
	public String getID()
	{
		return fId;
	}

	@Override
	public String getLabel() 
	{
		return "Filesystem Search Results";
	}

	@Override
	public IPageSite getSite()
	{
		return fSite;
	}

	@Override
	public void init(IPageSite site)
	{
		fSite = site;
	}

	@Override
	public void createControl(Composite parent) 
	{
		fRootControl = new Composite(parent, SWT.NULL);
		fRootControl.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		ftext = new Text(fRootControl, SWT.BORDER | SWT.READ_ONLY | SWT.H_SCROLL | SWT.V_SCROLL | SWT.CANCEL | SWT.MULTI);
	}

	@Override
	public void dispose() 
	{
		// nothing to do
	}

	@Override
	public Control getControl() 
	{
		return fRootControl;
	}

	@Override
	public void setActionBars(IActionBars actionBars) 
	{
	}

	@Override
	public void setFocus()
	{
		fRootControl.setFocus();
	}

	@Override
	public void restoreState(IMemento memento)
	{
		// nothing to do
	}

	@Override
	public void saveState(IMemento memento)
	{
		// nothing to do
	}

	@Override
	public void searchResultChanged(SearchResultEvent event) 
	{
		if (event instanceof FileSearchResultEvent) 
		{
			Display.getDefault().asyncExec(new Runnable()
			{
				@Override
				public void run() 
				{
					String newText = ftext.getText() + "\n" + ((FileSearchResultEvent) event).getAddedFile();
					ftext.setText(newText);
				}
			});
		}
	}

	@Override
	protected void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void configureTableViewer(TableViewer viewer) {
		viewer.setLabelProvider(viewer.getLabelProvider());
		viewer.setContentProvider( new FileSearchResultContentProvider() );
	}

	@Override
	protected void configureTreeViewer(TreeViewer arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void elementsChanged(Object[] arg0) {
		// TODO Auto-generated method stub
		
	}
}
