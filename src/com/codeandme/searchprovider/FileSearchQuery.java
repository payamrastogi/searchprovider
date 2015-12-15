
package com.codeandme.searchprovider;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;
import org.eclipse.jdt.core.search.IJavaSearchConstants;
import org.eclipse.jdt.core.search.IJavaSearchScope;
import org.eclipse.jdt.core.search.SearchEngine;
import org.eclipse.jdt.core.search.SearchMatch;
import org.eclipse.jdt.core.search.SearchParticipant;
import org.eclipse.jdt.core.search.SearchPattern;
import org.eclipse.jdt.core.search.SearchRequestor;
import org.eclipse.search.ui.ISearchQuery;
import org.eclipse.search.ui.ISearchResult;

public class FileSearchQuery implements ISearchQuery 
{
	private final String query;

	private final FileSearchResult fSearchResult;

	public FileSearchQuery(String query) 
	{
		this.query = query;
		fSearchResult = new FileSearchResult(this);
	}

	@Override
	public IStatus run(IProgressMonitor monitor) throws OperationCanceledException
	{
		SearchPattern pattern = SearchPattern.createPattern("String",
									IJavaSearchConstants.TYPE, 
									IJavaSearchConstants.PARAMETER_DECLARATION_TYPE_REFERENCE,
									SearchPattern.R_EXACT_MATCH);
	 
		// step 2: Create search scope
		// IJavaSearchScope scope = SearchEngine.createJavaSearchScope(packages);
		IJavaSearchScope scope = SearchEngine.createWorkspaceScope();
		
		// step3: define a result collector
		SearchRequestor requestor = new SearchRequestor() 
		{
			public void acceptSearchMatch(SearchMatch match) 
			{
				fSearchResult.addFile(match.getElement().toString());
			}
		};
	 
		// step4: start searching
		SearchEngine searchEngine = new SearchEngine();
		try
		{
			searchEngine.search(pattern, 
								new SearchParticipant[] { SearchEngine.getDefaultSearchParticipant() }, 
								scope, 
								requestor,
								null);
		}
		catch (CoreException e) 
		{
			e.printStackTrace();
		}
		return Status.OK_STATUS;
	}

	@Override
	public String getLabel()
	{
		return "NLP Query Search";
	}

	@Override
	public boolean canRerun() 
	{
		return true;
	}

	@Override
	public boolean canRunInBackground() 
	{
		return true;
	}

	@Override
	public ISearchResult getSearchResult()
	{
		return fSearchResult;
	}
}
