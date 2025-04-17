package com.AccioJob.VoiceToDoListWithAi.util;

import org.springframework.data.util.Pair;

import java.util.Set;

public class StemmerHelper {
   public static Pair<String,String> getPlaceHolder(String text, Set<String> protectedWords){
       String variable=null;
        for(String term:protectedWords){
            if(text.contains(term)){
                //storing the term before it replaced with Placeholder
                variable=term;
                text=text.replace(term,"_PLACEHOLDER_");
            }
        }
        return Pair.of(text,variable);
    }

}
