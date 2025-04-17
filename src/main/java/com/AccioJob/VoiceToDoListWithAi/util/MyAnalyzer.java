package com.AccioJob.VoiceToDoListWithAi.util;

import org.apache.lucene.analysis.*;
import org.apache.lucene.analysis.core.WhitespaceTokenizer;
import org.apache.lucene.analysis.en.PorterStemFilter;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.springframework.data.util.Pair;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class MyAnalyzer extends Analyzer {
    private final CharArraySet stopWords;
    private final Set<String> protectedTerms;

    public MyAnalyzer(Set<String> stopwordsList,Set<String> protectedTerms) {
//        HashSet<String> strings=new HashSet<>();
//        strings.add("Nike");//case should be same like Nike =Nike but not Nike!= nike
        //Do you want to match NIKE with nike yes ->true

        this.stopWords = new CharArraySet(stopwordsList,true);
        this.protectedTerms = new HashSet<>(protectedTerms);
    }

    @Override
    protected TokenStreamComponents createComponents(String s) {
        //on the basis of space just split the text
        WhitespaceTokenizer tokenizer=new WhitespaceTokenizer();
        //do stamming on individual words(tokens) and stop words will take stop words like (for,to,in)
        //we hv to provide /pass stop words by our own
        TokenStream tokenStream=new StopFilter(tokenizer,stopWords);
        //adding one more filter
        tokenStream=new TokenFilter(tokenStream) {
            @Override
            public boolean incrementToken() throws IOException {
                if(!input.incrementToken()){
                    return false;
                }
                CharTermAttribute termAttribute=getAttribute(CharTermAttribute.class);

                String term=termAttribute.toString();
                //if it's a protected word don't stem it
                if(protectedTerms.contains(term)){
                    return true;
                }
                return true;
            }
        };
        tokenStream=new PorterStemFilter(tokenStream);

        return new TokenStreamComponents(tokenizer,tokenStream);
    }

//Nike shoes for mens ->
    //tokens
    //nike
    //shoes->shoe
    //for
    //man

    //get the user spoken sentence

    public String stem(String text){
        if(text==null ||text.isEmpty())
            return text;
        StringBuilder result=new StringBuilder();
        Pair<String,String> placeHolder=StemmerHelper.getPlaceHolder(text,protectedTerms);
        System.out.println("Modified text="+text);
        try{
            ///token stream take the text(spoken by user)
            TokenStream tokenStream=tokenStream(null, String.valueOf(new StringBuilder(text)));


            //this charAttribute(LUCENE)
            //sentence will be broken on the bases of spaces(bcz we hv used whiteSpace tokenizer
            CharTermAttribute charTermAttribute=tokenStream.getAttribute(CharTermAttribute.class);

            //when new thread come i want to reset the token for them(optional)
            tokenStream.reset();
            //on the basis of space it has words
            //firstly it will remove for (stoke word)
            //NIKE shoes for man ->nike shoe man
            //now increment token stream will iterate each words
            while(tokenStream.incrementToken()){
                result.append(charTermAttribute.toString()).append(" ");
            }
            tokenStream.end();
            String stemmedText=result.toString();
            if(placeHolder.getSecond()!=null){
                stemmedText=stemmedText.replace("_PLACEHOLDER_", placeHolder.getSecond());
            }
            return stemmedText.trim();
        }
        catch (IOException e){
            throw new RuntimeException("Error occurred..."+e);
        }
       // return result.toString();
    }
    public static void main(String...args){
        Set<String> protectedTerm=new HashSet<>();
        protectedTerm.add("Nikes shoes");
        MyAnalyzer myAnalyzer=new MyAnalyzer(new HashSet<>(),protectedTerm);
        String tempVar=myAnalyzer.stem("Nikes shoes for Mens");
        System.out.println("temp is="+tempVar);
    }
}
