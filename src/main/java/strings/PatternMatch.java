package strings;


import org.apache.commons.lang3.StringUtils;

public class PatternMatch {

    public static boolean matchFound(String str, String pattern) {
        //check if chars and ptn are not null/empty
        if (StringUtils.isEmpty(str) || StringUtils.isEmpty(pattern))
            throw new IllegalArgumentException("Argument null or empty");

        //check if ptn length is greater than str length
        if (str.length() < pattern.length())
            throw new IllegalArgumentException("Pattern longer than string");

        char[] chars = str.toCharArray();
        char[] ptn = pattern.toCharArray();

        //set found to false
        boolean found = false;

        //loop through chars in str
        for (int i=0; i<chars.length; i++) {
            //if chars[i] == ptn[0] match found
            if (chars[i] == ptn[0]) {
                //if ptn is length 1
                if (ptn.length == 1)
                    //match found return true
                    return true;

                //start of found items
                found = true;
                //loop through ptn starting at index=1 (since we know index 0 matches)
                for (int j=1; j<ptn.length; j++) {
                    //if ptn[j] != char[i+j] break out of the loop
                    if (ptn[j] != chars[i+j]) {
                        //ptn not found
                        found = false;
                        break;
                    }
                }
                //full pattern matched, break out of the loop and return true
                if (found)
                    break;
            } // else go to next char in str
        }

        return found;
    }
}
