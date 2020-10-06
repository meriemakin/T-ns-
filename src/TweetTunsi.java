import javax.swing.*;
import java.net.URLDecoder;
import java.util.Hashtable;

public class TweetTunsi{

    private char[] tweetOldTunsi;
    private char[] tweetModernTunsi;
    private Hashtable<Character,Character> modernTunsiLetters;

    public TweetTunsi()
    {
        this.setmodernTunsiLetters();
    }

    public void setmodernTunsiLetters()
    {
        Hashtable<Character, Character> table =  new Hashtable<Character, Character>();
        /*without or deduced from writing that it has to has to be only this way*/
        table.put('\u0621', ' '); /*ء*/
        table.put('\u0623', 'e'); /*أ*/
        table.put('\u0627', 'ê'); /*ا*/
        table.put('\u0622', 'ê'); /*آ*/
        table.put('\u0649', 'ê'); /*ى*/
        table.put('\u0624', 'u'); /*ؤ*/
        table.put('\u0625', 'i'); /*إ*/
        table.put('\u0626', 'i'); /*ئ*/
        table.put('\u0628', 'b'); /*ب*/
        table.put('\u062B', '\u03D1'); /*θ-ث*/
        table.put('\u062C', 'j'); /*ج*/
        table.put('\u062E', '\u03C7'); /*χ-خ*/
        table.put('\u062F', 'd'); /*د*/
        table.put('\u0630', '\u03B6'); /*ζ-ذ!!!!!!!!!!!!!!!!1*/
        table.put('\u0636', '\u03B6'); /*ζ-!!!!!!!!!!!!!!!!!!ض*/
        table.put('\u0638', '\u03B6'); /*ζ-ظ!!!!!!!!!!!!!!!!!*/
        table.put('\u0631', 'r'); /*ر*/
        table.put('\u0632', 'z'); /*ز*/
        table.put('\u0633', 's'); /*س*/
        table.put('\u0635', 's'); /*ص*/
        table.put('\u0634', '\u015F'); /*ş-ش*/
        table.put('\u0637', 't'); /*ط*/
        table.put('\u062A', 't'); /*ت*/
        table.put('\u0639', '\u03B1'); /*α-ع*/
        table.put('\u063A', '\u03B3'); /*γ-غ*/
        table.put('\u0641', 'f'); /*ف*/
        table.put('\u0642', 'k'); /*ق*/
        table.put('\u0643', 'k'); /*ك*/
        table.put('\u0644', 'l'); /*ل*/
        table.put('\u0645', 'm'); /*م*/
        table.put('\u0646', 'n'); /*ن*/
        table.put('\u0629', 'h'); /*ة*/
        table.put('\u0647', 'h'); /*ه*/
        table.put('\u062D', 'h'); /*ح*/
        table.put('\u0648', 'w'); /*و*/
        table.put('\u064A', 'y'); /*ي*/
        table.put('\u068A', 'g'); /*ڨ*/

        this.modernTunsiLetters = table;
    }

    public Hashtable<Character, Character> getModernTunsiLetters()
    {
        return modernTunsiLetters;
    }

    public void setTweetOldTunsi(String tweet)
    {
        char [] characters = tweet.toCharArray();
        this.tweetOldTunsi = characters;
    }

    public char[] getTweetOldTunsi()
    {
        return tweetOldTunsi;
    }


    public void setTweetModernTunsi()
    {
        this.tweetModernTunsi = new char[this.tweetOldTunsi.length];
        int countModernTweet = 0;
        for(int count=0; count<this.tweetOldTunsi.length; count++)
        {
            char currentCharacter = this.tweetOldTunsi[count];
            char previousConsonant;
            int posPreviousConsonant = searchConsonant(count);
            boolean harderLetters = false;
            if(count > 0 && posPreviousConsonant !=-1)
            {
               previousConsonant = this.tweetOldTunsi[posPreviousConsonant];
               harderLetters = (previousConsonant == '\u062D') || (previousConsonant == '\u062E') ||
                        (previousConsonant == '\u0631') || (previousConsonant == '\u0635') ||
                        (previousConsonant == '\u0636') || (previousConsonant == '\u0637') ||
                        (previousConsonant == '\u0638') || (previousConsonant == '\u0639') ||
                        (previousConsonant == '\u063A') || (previousConsonant == '\u0642') ;
            }

            if(currentCharacter=='\u064E' && count>0)
            {
                if(harderLetters)
                {
                   this.tweetModernTunsi[countModernTweet]='a';
                }
                else
                {
                   this.tweetModernTunsi[countModernTweet]='e';
                }

            }
            else if(currentCharacter=='\u064F' && count>0)
            {
                if(harderLetters)
                {
                   this.tweetModernTunsi[countModernTweet]='o';
                }
                else
                {
                   this.tweetModernTunsi[countModernTweet]='u';
                }
            }
            else if(currentCharacter=='\u0650' && count>0)
            {
                if(harderLetters)
                {
                   this.tweetModernTunsi[countModernTweet]='\u0131';
                }
                else
                {
                   this.tweetModernTunsi[countModernTweet]='i';
                }
            }
            else if(currentCharacter=='\u0652' && count>0)
            {
               //skip
            }
            else if(currentCharacter=='\u0651' && count>0)
            {
                int consonant = searchConsonant(count);
                if(consonant != -1)
                {
                    if(consonant != (count-1))
                    {
                        this.tweetModernTunsi[countModernTweet]=this.tweetModernTunsi[countModernTweet-1];
                        this.tweetModernTunsi[countModernTweet-1]=this.tweetModernTunsi[consonant];
                    }
                    else
                    {
                        this.tweetModernTunsi[countModernTweet]=this.tweetModernTunsi[consonant];
                    }

                }
            }
            else if(this.modernTunsiLetters.containsKey(currentCharacter))
            {

               if(currentCharacter == '\u064A' && (count<this.tweetOldTunsi.length-1) && count>0)
               {
                   char charBefore = this.tweetModernTunsi[countModernTweet-1];
                   boolean voyelBefore = (charBefore == 'a') || (charBefore == 'â') || (charBefore == 'e') ||
                           (charBefore == 'ê') || (charBefore == 'u') || (charBefore == 'û') || (charBefore == 'o') ||
                           (charBefore == 'ô');
                  if((this.tweetOldTunsi[count+1] != '\u064F' && this.tweetOldTunsi[count+1] != '\u064E' &&
                        this.tweetOldTunsi[count+1] != '\u0652' && this.tweetOldTunsi[count+1] != '\u0650' &&
                          this.tweetOldTunsi[count+1] != '\u0651') || (voyelBefore == false))
                  {
                      if(harderLetters)
                      {
                          this.tweetModernTunsi[countModernTweet] = '\u0131';
                          this.tweetModernTunsi = concatenate(this.tweetModernTunsi, 'î', count+1);
                          countModernTweet++;
                      }
                      else
                      {
                          this.tweetModernTunsi[countModernTweet] = 'î';
                      }
                  }
                  else
                  {
                      this.tweetModernTunsi[countModernTweet]=this.modernTunsiLetters.get(currentCharacter);
                  }
               }
               else if(currentCharacter == '\u064A' && (count==this.tweetOldTunsi.length-1))
               {
                   char charBefore = this.tweetModernTunsi[countModernTweet-1];
                   boolean voyelBefore = (charBefore == 'a') || (charBefore == 'â') || (charBefore == 'e') ||
                           (charBefore == 'ê') || (charBefore == 'u') || (charBefore == 'û') || (charBefore == 'o') ||
                           (charBefore == 'ô');
                   if(voyelBefore == false)
                   {
                       if(harderLetters)
                       {
                           this.tweetModernTunsi[countModernTweet] = '\u0131';
                           this.tweetModernTunsi = concatenate(this.tweetModernTunsi, 'î', count+1);
                           countModernTweet++;
                       }
                       else
                       {
                           this.tweetModernTunsi[countModernTweet] = 'î';
                       }
                   }
                   else
                   {
                       this.tweetModernTunsi[countModernTweet]=this.modernTunsiLetters.get(currentCharacter);
                   }

               }
               else if(currentCharacter == '\u064F' && (count<this.tweetOldTunsi.length-1) && count>0)
               {
                   if((this.tweetOldTunsi[count+1] != '\u064F' && this.tweetOldTunsi[count+1] != '\u064E' &&
                           this.tweetOldTunsi[count+1] != '\u0652' && this.tweetOldTunsi[count+1] != '\u0650') &&
                           this.tweetOldTunsi[count+1] != '\u0651')
                   {
                       if(harderLetters)
                       {
                           this.tweetModernTunsi[countModernTweet] = 'o';
                           this.tweetModernTunsi = concatenate(this.tweetModernTunsi, 'u', count+1);
                           countModernTweet++;
                       }
                       else
                       {
                           this.tweetModernTunsi[countModernTweet] = 'û';
                       }

                   }
               }
               else if(currentCharacter == '\u064F' && (count==this.tweetOldTunsi.length-1))
               {
                   if(harderLetters)
                   {
                       this.tweetModernTunsi[countModernTweet] = 'o';
                       this.tweetModernTunsi = concatenate(this.tweetModernTunsi, 'u', count+1);
                       countModernTweet++;
                   }
                   else
                   {
                       this.tweetModernTunsi[countModernTweet] = 'û';
                   }
               }
               else if((currentCharacter == '\u0627' || currentCharacter == '\u0649') && harderLetters)
               {
                   this.tweetModernTunsi[countModernTweet] = 'â';
               }
               else
               {
                  this.tweetModernTunsi[countModernTweet]=this.modernTunsiLetters.get(currentCharacter);
               }
            }
            else
            {
              if(currentCharacter == '\u061F')
              {
                  this.tweetModernTunsi[countModernTweet]='?';
              }
              else if(currentCharacter == '\u060C')
              {
                 this.tweetModernTunsi[countModernTweet]=',';
              }
              else if(currentCharacter == '\u061B')
              {
                  this.tweetModernTunsi[countModernTweet]=';';
              }
              else
              {
                  this.tweetModernTunsi[countModernTweet]=currentCharacter;
              }
            }
            countModernTweet++;
        }
        String tweet = new String(this.tweetModernTunsi);
        if(tweet.contains("  "))
        {
            tweet = tweet.replace("  ", " ");
        }
        if(tweet.contains("eu"))
        {
            tweet = tweet.replace("eu", "u");
        }
        /*delete redundancy*/
        if(tweet.contains("ee") || tweet.contains("eê") || tweet.contains("êe"))
        {
            tweet = tweet.replace("ee","ê");
            tweet = tweet.replace("eê","ê");
            tweet = tweet.replace("êe","ê");
        }
        if(tweet.contains("aa") || tweet.contains("aâ") || tweet.contains("âa"))
        {
            tweet = tweet.replace("aa","â");
            tweet = tweet.replace("aâ","â");
            tweet = tweet.replace("âa","â");
        }
        if(tweet.contains("ii") || tweet.contains("îi") || tweet.contains("iî") || tweet.contains("\u0131\u0131"))
        {
            tweet = tweet.replace("ii","î");
            tweet = tweet.replace("iî","î");
            tweet = tweet.replace("îi","î");
            tweet = tweet.replace("\u0131\u0131","\u0131");
        }
        if(tweet.contains("uu") || tweet.contains("uû") || tweet.contains("ûu"))
        {
            tweet = tweet.replace("uu","û");
            tweet = tweet.replace("uû","û");
            tweet = tweet.replace("ûu","û");
        }
        if(tweet.contains("oo") || tweet.contains("oô") || tweet.contains("ôo"))
        {
            tweet = tweet.replace("oo","ô");
            tweet = tweet.replace("oô","ô");
            tweet = tweet.replace("ôo","ô");
        }
        this.tweetModernTunsi = tweet.toCharArray();
    }

    public char[] concatenate(char[] tweet, char character, int position)
    {
        char[] newTweet = new char[tweet.length+1];
        for(int count=0; count<position; count++)
        {
            newTweet[count] = tweet[count];
        }
        newTweet[position] = character;
        return newTweet;
    }

    public char[] getTweetModernTunsi()
    {
        return this.tweetModernTunsi;
    }

    public int searchConsonant(int current)
    {
        int consonant = -1;
        if(current != 0)
        {
            for(int count1=current; (count1 >=current-2) && ((current-2)>=0); count1--)
            {
                if(this.modernTunsiLetters.containsKey(tweetOldTunsi[count1]) && tweetOldTunsi[count1]!='\u0627' &&
                tweetOldTunsi[count1]!='\u0649')
                {
                    consonant=count1;
                    break;
                }

            }
        }
        return consonant;
    }

    public int[] searchVoyels(int current)
    {
        int length = this.tweetOldTunsi.length;
        int[] voyels = new int[2];
        if(current == 0)
        {
            voyels[0] = length;
        }
        else if(current==this.tweetOldTunsi[length-1])
        {
            voyels[1] = length;
        }
        else
        {
            for(int count1=current; (count1 >current-2) && ((current-2)>=0); count1--)
            {
                 if((this.tweetOldTunsi[count1] == '\u064F' || this.tweetOldTunsi[count1] == '\u064E' ||
                         this.tweetOldTunsi[count1] == '\u0652' || this.tweetOldTunsi[count1] == '\u0650'))
                 {
                     voyels[0]=count1;
                 }
            }
            for(int count2=current; (count2 <current+2) && ((current+2)<length); count2++)
            {
                if((this.tweetOldTunsi[count2] == '\u064F' || this.tweetOldTunsi[count2] == '\u064E' ||
                        this.tweetOldTunsi[count2] == '\u0652' || this.tweetOldTunsi[count2] == '\u0650'))
                {
                    voyels[1]=count2;
                }
            }
        }
        return voyels;
    }



}