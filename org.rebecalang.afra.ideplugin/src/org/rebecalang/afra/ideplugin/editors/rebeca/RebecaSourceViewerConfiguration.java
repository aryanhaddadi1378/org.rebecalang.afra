package org.rebecalang.afra.ideplugin.editors.rebeca;

import org.eclipse.jface.text.contentassist.ContentAssistant;
import org.eclipse.jface.text.contentassist.IContentAssistant;
import org.eclipse.jface.text.rules.RuleBasedScanner;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.source.ISourceViewer;
import org.rebecalang.afra.ideplugin.editors.ColorManager;
import org.rebecalang.afra.ideplugin.editors.GeneralSourceViewerConfiguration;
import org.rebecalang.afra.ideplugin.editors.GeneralTextAttribute;


public class RebecaSourceViewerConfiguration extends GeneralSourceViewerConfiguration {

	public RebecaSourceViewerConfiguration(ColorManager cm) {
		super(cm);
	}

	@Override
	public RuleBasedScanner createScanner() {
		return new RebecaScanner(colorManager);
	}

	@Override
	protected GeneralTextAttribute[] getContentTypeAttributes() {
		return new RebecaPartitionScanner().getContentTypeAttributes();
	}

	@Override
	public String[] getContentTypes() {
		return new RebecaPartitionScanner().getContentTypes();
	}

	@Override
	protected RuleBasedScanner getScanner() {
		if (scanner == null)
		{
			scanner = createScanner();
			scanner.setDefaultReturnToken(new Token(
					RebecaTextAttribute.DEFAULT.getTextAttribute(colorManager)));
		}
		return scanner;
	}
	public IContentAssistant getContentAssistant(ISourceViewer sv) {
      ContentAssistant contentAssist = new ContentAssistant();/*
      IContentAssistProcessor cap = new CompletionProcessor();
      contentAssist.setContentAssistProcessor(cap, IDocument.DEFAULT_CONTENT_TYPE);*/
      contentAssist.enableAutoActivation(true);
      contentAssist.enableColoredLabels(true);
      contentAssist.enableAutoInsert(true);
      contentAssist.setInformationControlCreator(getInformationControlCreator(sv));
      return contentAssist;
   }
	
}
