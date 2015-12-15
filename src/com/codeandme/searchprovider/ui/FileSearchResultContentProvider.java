package com.codeandme.searchprovider.ui;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import com.codeandme.searchprovider.FileSearchResult;

public class FileSearchResultContentProvider implements ITreeContentProvider, IStructuredContentProvider
{
	private FileSearchResult result;
	private Viewer viewer; 
	 
	@Override
	public void dispose() 
	{
		
		
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) 
	{
		this.viewer = viewer;
		result = ( FileSearchResult )newInput;
	}

	@Override
	public Object[] getChildren(Object arg0) 
	{

		return null;
	}

	@Override
	public Object[] getElements(Object inputElement)
	{
		return result.getElements();
	}

	@Override
	public Object getParent(Object arg0) 
	{

		return null;
	}

	@Override
	public boolean hasChildren(Object arg0) 
	{

		return false;
	}

}
