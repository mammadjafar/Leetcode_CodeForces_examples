import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/*
Pig Latin Task
Write some Java code that translates a string (word, sentence, or paragraph) into “pig-latin” using the
following rules.
o Words that start with a consonant have their first letter moved to the end of the word and the
letters “ay” added to the end.
 Hello becomes Ellohay
o Words that start with a vowel have the letters “way” added to the end.
 apple becomes appleway
o Words that end in “way” are not modified.
 stairway stays as stairway
o Punctuation must remain in the same relative place from the end of the word.
 can’t becomes antca’y
 end. becomes endway.
o Hyphens are treated as two words
 this-thing becomes histay-hingtay
o Capitalization must remain in the same place.
 Beach becomes Eachbay
 McCloud becomes CcLoudmay*/

public class Solution {
	
	private static Set<Character> sc = new HashSet<Character>();
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String str = in.nextLine();
		sc.add('\'');
		sc.add('.');
		sc.add('?');
		sc.add('’');
		String [] strs = str.split("-");
		for(int i=0; i<strs.length; i++)
			strs[i] = pigLatin(strs[i]);
		str = String.join("-", strs);
		System.out.println(str);
		
	}
	
	private static String pigLatin(String str) {
		int len = str.length();
		
		if(str.length() < 1)
			return "";

		//dont touch way
		if(str.length() >= 3 && str.substring(str.length()-3).equals("way"))
			return str;
		
		// find capitals start to end
		List<Integer> caps = new ArrayList();
		for(int i=0; i<str.length(); i++)
			if(isUpperCase(str.charAt(i)))
				caps.add(i);
		
		str = str.toLowerCase();
		String res;
		if(isVowel(str.charAt(0))){
			res = str + "way";
		} else {
			res = str.substring(1) + str.charAt(0) + "ay";
		}
		
		res = removePunc(res);
		for(int i=len-1; i>=0; i--) { // can't
			if(isPunc(str.charAt(i))){
				int k = res.length() - len +i;
				res = res.substring(0, k+1) + str.charAt(i) + 
						res.substring(k+1);
			}
		}
		
		char [] a = res.toCharArray();
		for(int u:caps)
			a[u] = (char) (((int) a[u]) - 32);
		res = new String(a);
		
		return res;
	}
	
	private static String removePunc(String str) {
		for(char c:sc)
			str = str.replace(c+"", "");
		return str;
	}
	
	private static boolean isPunc(char c) {
		// add others.. or regex 
		if(sc.contains(c))
			return true;
		return false;
	}
	
	private static boolean isUpperCase(char c) {
		int ci = (int) c;
		if( ci >= 65 && ci <=90)
			return true;
		return false;
	}
	
	private static boolean isVowel(char c) 
	{ 
	    return (c == 'A' || c == 'E' || c == 'I' ||  
	            c == 'O' || c == 'U' || c == 'a' ||  
	            c == 'e' || c == 'i' || c == 'o' || 
	            c == 'u'); 
	}

}
