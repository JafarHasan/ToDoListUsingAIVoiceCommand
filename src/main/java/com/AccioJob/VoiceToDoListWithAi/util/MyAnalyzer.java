package com.AccioJob.VoiceToDoListWithAi.util;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.CharArraySet;
import org.apache.lucene.analysis.StopFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.core.WhitespaceTokenizer;
import org.apache.lucene.analysis.en.PorterStemFilter;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class MyAnalyzer extends Analyzer {
    private final CharArraySet stopWords;
    private final Set<String> protectedTerms;

    public MyAnalyzer(Set<String> protectedTerms) {
        HashSet<String> strings=new HashSet<>();
        strings.add("Nike");//case should be same like Nike =Nike but not Nike!= nike
        this.stopWords = new CharArraySet(strings,false);
        this.protectedTerms = protectedTerms;
    }

    @Override
    protected TokenStreamComponents createComponents(String s) {
        //on the basis of space just split the text
        WhitespaceTokenizer tokenizer=new WhitespaceTokenizer();
        //do stamming on individual words(tokens) and stop words will take stop words like (for,to,in)
        //we hv to provide /pass stop words by our own
        TokenStream tokenStream=new StopFilter(tokenizer,stopWords);
        tokenStream=new PorterStemFilter(tokenStream);

        return new TokenStreamComponents(tokenizer,tokenStream);
    }

    public String stem(String text){
        if(text==null ||text.isBlank())return null;
        StringBuilder result=new StringBuilder();
        try{
            TokenStream tokenStream=tokenStream(null,text);

            CharTermAttribute charTermAttribute=tokenStream.getAttribute(CharTermAttribute.class);
            tokenStream.reset();
            //on the basis of space it has words
            while(tokenStream.incrementToken()){
                result.append(charTermAttribute.toString()).append(" ");
            }
            tokenStream.end();
        }
        catch (IOException e){
            throw new RuntimeException(e);
        }
        return result.toString();
    }
}
