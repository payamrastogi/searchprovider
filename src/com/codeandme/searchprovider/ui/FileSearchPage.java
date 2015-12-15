package com.codeandme.searchprovider.ui;

import org.eclipse.jface.dialogs.DialogPage;
import org.eclipse.search.ui.ISearchPage;
import org.eclipse.search.ui.ISearchPageContainer;
import org.eclipse.search.ui.NewSearchUI;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.codeandme.searchprovider.FileSearchQuery;

public class FileSearchPage extends DialogPage implements ISearchPage {

	private ISearchPageContainer fContainer;

	private Text txtQuery;

	public FileSearchPage() {
	}

	@Override
	public boolean performAction() 
	{
		FileSearchQuery searchQuery = new FileSearchQuery(txtQuery.getText());
		NewSearchUI.runQueryInForeground(fContainer.getRunnableContext(), searchQuery);

		return true;
	}

	@Override
	public void setContainer(ISearchPageContainer container) 
	{
		fContainer = container;
	}

	@Override
	public void createControl(Composite parent) 
	{
		Composite root = new Composite(parent, SWT.NULL);
		root.setLayout(new GridLayout(3, false));

		Label lblQuery = new Label(root, SWT.NONE);
		lblQuery.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblQuery.setText("Search Query");

		txtQuery = new Text(root, SWT.BORDER);
		txtQuery.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		// need to set the root element
		setControl(root);
	}
}
